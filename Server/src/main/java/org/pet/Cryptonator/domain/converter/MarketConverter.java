package org.pet.Cryptonator.domain.converter;

import org.pet.Cryptonator.domain.dto.MarketDto;
import org.pet.Cryptonator.domain.entity.MarketEntity;
import org.springframework.stereotype.Component;

@Component
public class MarketConverter {

    public MarketDto entityToDto(long id, MarketEntity entity) {
        return new MarketDto(id, entity.getName(), entity.isStatus());
    }

    public MarketEntity dtoToEntity(MarketDto dto) {
        return new MarketEntity(dto.getName(), dto.isStatus());
    }

}
