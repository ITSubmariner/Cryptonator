package org.pet.cryptonator.domain.converter;

import org.pet.cryptonator.domain.dto.MarketDto;
import org.pet.cryptonator.domain.entity.MarketEntity;
import org.springframework.stereotype.Component;

@Component
public class MarketConverter {

    public MarketDto entityToDto(long id, MarketEntity entity) {
        return new MarketDto()
                .setId(id)
                .setName(entity.getName())
                .setStatus(entity.isStatus());
    }

    public MarketEntity dtoToEntity(MarketDto dto) {
        return new MarketEntity()
                .setName(dto.getName())
                .setStatus(dto.isStatus());
    }

}
