package org.pet.cryptonator.mapping;

import org.mapstruct.Mapper;
import org.pet.cryptonator.dto.MarketDto;
import org.pet.cryptonator.entity.MarketEntity;

@Mapper(componentModel = "spring")
public interface MarketEntityToDtoMapper extends GenericMapper<MarketEntity, MarketDto> {

    @Override
    MarketDto mapSourceToDestination(MarketEntity source);

    @Override
    MarketEntity mapDestinationToSource(MarketDto source);

}
