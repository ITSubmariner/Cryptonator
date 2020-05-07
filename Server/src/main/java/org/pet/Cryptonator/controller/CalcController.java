package org.pet.Cryptonator.controller;

import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Result;
import org.pet.Cryptonator.service.CalcService;
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
            @RequestParam("market") String market,
            @RequestParam("period") String period,
            @RequestParam("firstPeriod") int firstPeriod,
            @RequestParam("secondPeriod") int secondPeriod,
            @RequestParam("percent") double percent) {
        return calcService.calculate(market, Period.valueOf(period), firstPeriod, secondPeriod, percent);
    }
}
