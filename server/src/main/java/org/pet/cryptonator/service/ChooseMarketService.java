package org.pet.cryptonator.service;

import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.general.SymbolInfo;
import com.binance.api.client.domain.general.SymbolStatus;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerStatistics;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.pet.cryptonator.dto.TradingAnalysis;
import org.pet.cryptonator.service.strategy.TraidingStrategy;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.analysis.criteria.AverageProfitableTradesCriterion;
import org.ta4j.core.analysis.criteria.TotalProfitCriterion;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.num.Num;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChooseMarketService {

    private final static BigDecimal MIN_VOLUME = BigDecimal.valueOf(30);
    private final BinanceApiRestClient binanceApiRestClient;
    private final TraidingStrategy traidingStrategy;


    public String chooseMarket() {
        TradingAnalysis bestMarket = null;
        List<String> marketList = getMarkets();
        log.info("Good market count: {}", marketList.size());

        for (String market : marketList) {
            BaseBarSeries series = new BaseBarSeries(market);
            List<Candlestick> candleList = binanceApiRestClient.getCandlestickBars(market, CandlestickInterval.ONE_MINUTE);
            for (Candlestick candle : candleList) {
                series.addBar(ZonedDateTime.ofInstant(Instant.ofEpochMilli(candle.getCloseTime()), ZoneId.systemDefault()),
                        candle.getOpen(), candle.getHigh(), candle.getLow(), candle.getClose(), candle.getVolume());
            }
            Strategy strategy = traidingStrategy.getStrategy(series);

            BarSeriesManager barSeriesManager = new BarSeriesManager(series);
            TradingRecord tradingRecord = barSeriesManager.run(strategy);

            AnalysisCriterion profitTradesRatio = new AverageProfitableTradesCriterion();
            Num profitableTrades = profitTradesRatio.calculate(series, tradingRecord);

            TotalProfitCriterion totalProfitCriterion = new TotalProfitCriterion();
            Num profit = totalProfitCriterion.calculate(series, tradingRecord);

            if (bestMarket == null || bestMarket.getProfit().isLessThan(profit)) {
                log.info("Changing best market to {}", market);
                bestMarket = new TradingAnalysis()
                        .setMarket(market)
                        .setTrades(tradingRecord.getTrades())
                        .setTradeCount(tradingRecord.getTradeCount())
                        .setProfitTrades(profitableTrades)
                        .setProfit(profit);
            }
        }

        if (bestMarket == null) {
            throw new NullPointerException();
        }

        log.info("Best market analysis: {}", bestMarket);
        BaseBarSeries series = new BaseBarSeries(bestMarket.getMarket());
        List<Candlestick> candleList = binanceApiRestClient.getCandlestickBars(bestMarket.getMarket(), CandlestickInterval.ONE_MINUTE);
        for (Candlestick candle : candleList) {
            series.addBar(ZonedDateTime.ofInstant(Instant.ofEpochMilli(candle.getCloseTime()), ZoneId.systemDefault()),
                    candle.getOpen(), candle.getHigh(), candle.getLow(), candle.getClose(), candle.getVolume());
        }

        bestMarket.getTrades().forEach(t -> {
            if (t.getEntry().getPricePerAsset().isGreaterThan(t.getExit().getPricePerAsset())) {
                log.info("{}", t);
                log.info("Entry: {}, Exit: {}", candleList.get(t.getEntry().getIndex()), candleList.get(t.getExit().getIndex()));
                log.info("--------------------");
            }
        });
        return bestMarket.getMarket();

    }

    private List<String> getMarkets() {
        List<String> btcSymbols = binanceApiRestClient.getExchangeInfo().getSymbols().stream()
                .filter(s -> s.getQuoteAsset().equals("BTC"))
                .filter(s -> s.getStatus() == SymbolStatus.TRADING)
                .map(SymbolInfo::getSymbol)
                .collect(Collectors.toList());

        return binanceApiRestClient.getAll24HrPriceStatistics().stream()
                .filter(s -> btcSymbols.contains(s.getSymbol()))
                .filter(s -> isGoodVolume(s.getVolume(), s.getWeightedAvgPrice()))
                .map(TickerStatistics::getSymbol)
                .collect(Collectors.toList());
    }

    private boolean isGoodVolume(String volume, String avgPrice) {
        BigDecimal vol = new BigDecimal(volume);
        BigDecimal price = new BigDecimal(avgPrice);
        return vol.multiply(price).compareTo(MIN_VOLUME) > 0;
    }

}
