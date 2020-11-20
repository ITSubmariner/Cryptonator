package org.pet.cryptonator.mapping;

import org.mapstruct.Mapper;
import org.pet.cryptonator.dto.TicketDto;
import org.pet.cryptonator.entity.TicketEntity;

@Mapper(componentModel = "spring")
public interface TicketEntityToDtoMapper extends GenericMapper<TicketEntity, TicketDto> {

    @Override
    TicketDto mapSourceToDestination(TicketEntity source);

    @Override
    TicketEntity mapDestinationToSource(TicketDto source);

}
