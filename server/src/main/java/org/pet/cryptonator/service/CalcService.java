package org.pet.cryptonator.service;

import org.pet.cryptonator.entity.enums.Period;
import org.pet.cryptonator.dto.Result;

public interface CalcService {
    Result calculate(long marketId, Period period, int smallPeriod, int bigPeriod, double percent);
}
