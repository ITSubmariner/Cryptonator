package org.pet.cryptonator.service;

import org.pet.cryptonator.domain.dto.MarketDto;

import java.util.List;

public interface MarketService {
    List<MarketDto> getAll();
}
