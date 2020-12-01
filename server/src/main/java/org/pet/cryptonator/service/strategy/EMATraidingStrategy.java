package org.pet.cryptonator.service.strategy;

import org.springframework.stereotype.Service;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.MACDIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.*;

@Service
public class EMATraidingStrategy implements TraidingStrategy {

    public Strategy getStrategy(BarSeries series) {
        ClosePriceIndicator closePriceIndicator = new ClosePriceIndicator(series);
        EMAIndicator shortEMA = new EMAIndicator(closePriceIndicator, 5);
        EMAIndicator longEMA = new EMAIndicator(closePriceIndicator, 15);
        MACDIndicator macdIndicator = new MACDIndicator(closePriceIndicator, 12, 26);

        Rule buyingRule = new CrossedUpIndicatorRule(shortEMA, longEMA)
                .and(new IsRisingRule(macdIndicator, 2))
                .and(new UnderIndicatorRule(macdIndicator, 0));
        Rule sellingRule = new StopGainRule(closePriceIndicator, 2)
                .or(new StopLossRule(closePriceIndicator, 2));

        return new BaseStrategy(buyingRule, sellingRule);
    }

}
