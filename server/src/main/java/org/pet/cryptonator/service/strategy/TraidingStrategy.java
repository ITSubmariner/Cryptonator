package org.pet.cryptonator.service.strategy;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Strategy;

public interface TraidingStrategy {
    Strategy getStrategy(BarSeries series);
}
