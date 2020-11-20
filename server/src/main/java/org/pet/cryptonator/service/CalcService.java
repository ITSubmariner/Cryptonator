package org.pet.cryptonator.service;

import org.pet.cryptonator.domain.Period;
import org.pet.cryptonator.domain.Result;

public interface CalcService {
    Result calculate(long marketId, Period period, int smallPeriod, int bigPeriod, double percent);
}
