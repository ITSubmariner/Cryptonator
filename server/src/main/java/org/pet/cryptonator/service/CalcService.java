package org.pet.cryptonator.service;

import com.binance.api.client.BinanceApiCallback;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.CandlestickEvent;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.pet.cryptonator.service.strategy.TraidingStrategy;
import org.springframework.stereotype.Service;
import org.ta4j.core.*;
import org.ta4j.core.num.PrecisionNum;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CalcService {

    private final ChooseMarketService chooseMarketService;
    private final BinanceApiRestClient binanceApiRestClient;
    private final BinanceApiWebSocketClient binanceApiWebSocketClient;
    private final TraidingStrategy traidingStrategy;

    private BarSeries series;
    private Strategy strategy;
    TradingRecord tradingRecord = new BaseTradingRecord();

    @SneakyThrows
    @PostConstruct
    public void calculate() {
        String market = chooseMarketService.chooseMarket();

        series = new BaseBarSeries(market);
        series.setMaximumBarCount(30);

        List<Candlestick> candlestickBars = binanceApiRestClient.getCandlestickBars(market, CandlestickInterval.ONE_MINUTE);
        for (Candlestick candle : candlestickBars) {
            series.addBar(ZonedDateTime.ofInstant(Instant.ofEpochMilli(candle.getCloseTime()), ZoneId.systemDefault()),
                    candle.getOpen(), candle.getHigh(), candle.getLow(), candle.getClose(), candle.getVolume());
        }
        log.info("{}", candlestickBars);

        strategy = traidingStrategy.getStrategy(series);

        binanceApiWebSocketClient.onCandlestickEvent(market.toLowerCase(), CandlestickInterval.ONE_MINUTE, new BinanceCandlestickEvent());
    }

    private class BinanceCandlestickEvent implements BinanceApiCallback<CandlestickEvent> {

        @Override
        public void onResponse(CandlestickEvent response) {
            if (response.getBarFinal()) {
                log.info("Поступила новая свеча: {}", response);
                ZonedDateTime closeTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(response.getCloseTime()), ZoneId.systemDefault());
                if (closeTime.equals(series.getLastBar().getEndTime())) {
                    series.addPrice(response.getClose());
                } else {
                    series.addBar(closeTime, response.getOpen(), response.getHigh(), response.getLow(), response.getClose(), response.getVolume());
                }
                if (strategy.shouldEnter(series.getEndIndex())) {
                    log.info("Начало сделки! {}", series.getLastBar());
                    tradingRecord.enter(series.getEndIndex(), series.getLastBar().getClosePrice(), PrecisionNum.valueOf(1));
                } else if (strategy.shouldExit(series.getEndIndex())) {
                    log.info("Окончание сделки! {}", series.getLastBar());
                    tradingRecord.exit(series.getEndIndex(), series.getLastBar().getClosePrice(), PrecisionNum.valueOf(1));
                    log.info("Все сделки: {}", tradingRecord);
                }
            }
        }

        @Override
        public void onFailure(Throwable cause) {
            log.error("Возникла ошибка при получении информации о свечах.", cause);
        }
    }

}
