package org.pet.Cryptonator.domain.converter;

import org.pet.Cryptonator.domain.dto.TicketDto;
import org.pet.Cryptonator.domain.entity.TicketEntity;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    public TicketDto entityToDto(long id, TicketEntity entity) {
        return new TicketDto(
                id,
                entity.getMarket(),
                entity.getPeriod(),
                entity.getStartsAt(),
                entity.getOpen(),
                entity.getClose(),
                entity.getVolume()
        );
    }

    public TicketEntity dtoToEntity(TicketDto dto) {
        return new TicketEntity(
                dto.getMarket(),
                dto.getPeriod(),
                dto.getStartsAt(),
                dto.getOpen(),
                dto.getClose(),
                dto.getVolume()
        );
    }

}
