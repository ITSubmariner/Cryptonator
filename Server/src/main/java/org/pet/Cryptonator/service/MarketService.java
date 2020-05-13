package org.pet.Cryptonator.service;

import org.pet.Cryptonator.domain.dto.MarketDto;

import java.util.List;

public interface MarketService {
    List<MarketDto> getAll();
}
