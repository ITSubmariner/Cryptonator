package org.pet.cryptonator.controller;

import org.pet.cryptonator.entity.enums.Period;
import org.pet.cryptonator.dto.Result;
import org.pet.cryptonator.service.CalcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("calculate")
public class CalcController {
    private final CalcService calcService;

    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    @GetMapping
    public Result calculate(
            @RequestParam("market") long marketId,
            @RequestParam("period") String period,
            @RequestParam("firstPeriod") int firstPeriod,
            @RequestParam("secondPeriod") int secondPeriod,
            @RequestParam("percent") double percent) {
        return calcService.calculate(marketId, Period.valueOf(period), firstPeriod, secondPeriod, percent);
    }
}
