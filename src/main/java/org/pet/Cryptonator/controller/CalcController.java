package org.pet.Cryptonator.controller;

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
    public String calculate(
            @RequestParam("firstPeriod") int firstPeriod,
            @RequestParam("secondPeriod") int secondPeriod,
            @RequestParam("percent") int percent) {
        calcService.calculate(firstPeriod, secondPeriod, percent);
        return null;
    }
}
