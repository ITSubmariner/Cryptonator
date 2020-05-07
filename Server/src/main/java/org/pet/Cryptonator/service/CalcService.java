package org.pet.Cryptonator.service;

import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Result;

public interface CalcService {
    Result calculate(String market, Period period, int smallPeriod, int bigPeriod, double percent);
}
