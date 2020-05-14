package org.pet.Cryptonator.controller;

import org.pet.Cryptonator.domain.dto.MarketDto;
import org.pet.Cryptonator.service.MarketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("market")
public class MarketController {
    private final MarketService marketService;

    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping
    public List<MarketDto> getMarkets() {
        return marketService.getAll();
    }

}
