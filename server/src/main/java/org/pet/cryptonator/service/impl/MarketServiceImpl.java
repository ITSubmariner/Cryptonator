package org.pet.cryptonator.service.impl;

import org.pet.cryptonator.domain.converter.MarketConverter;
import org.pet.cryptonator.domain.dto.MarketDto;
import org.pet.cryptonator.exception.GetFrontMarketsException;
import org.pet.cryptonator.repository.MarketRepository;
import org.pet.cryptonator.service.MarketService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {
    private final MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository, MarketConverter marketConverter) {
        this.marketRepository = marketRepository;
    }

    @Override
    public List<MarketDto> getAll() {
        try {
            return marketRepository.findAll();
        } catch (Exception e) {
            throw new GetFrontMarketsException();
        }
    }
}
