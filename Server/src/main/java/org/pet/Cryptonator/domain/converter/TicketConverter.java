package org.pet.Cryptonator.domain.converter;

import org.pet.Cryptonator.domain.dto.TicketDto;
import org.pet.Cryptonator.domain.entity.TicketEntity;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    public TicketDto entityToDto(long id, TicketEntity entity) {
        return new TicketDto()
                .setId(id)
                .setMarket(entity.getMarket())
                .setPeriod(entity.getPeriod())
                .setStartsAt(entity.getStartsAt())
                .setOpen(entity.getOpen())
                .setClose(entity.getClose())
                .setVolume(entity.getVolume());
    }

    public TicketEntity dtoToEntity(TicketDto dto) {
        return new TicketEntity()
                .setMarket(dto.getMarket())
                .setPeriod(dto.getPeriod())
                .setStartsAt(dto.getStartsAt())
                .setOpen(dto.getOpen())
                .setClose(dto.getClose())
                .setVolume(dto.getVolume());
    }

}
