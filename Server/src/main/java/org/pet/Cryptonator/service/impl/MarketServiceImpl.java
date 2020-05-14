package org.pet.Cryptonator.service.impl;

import org.pet.Cryptonator.domain.converter.MarketConverter;
import org.pet.Cryptonator.domain.dto.MarketDto;
import org.pet.Cryptonator.exception.GetFrontMarketsException;
import org.pet.Cryptonator.repository.MarketRepository;
import org.pet.Cryptonator.service.MarketService;
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
