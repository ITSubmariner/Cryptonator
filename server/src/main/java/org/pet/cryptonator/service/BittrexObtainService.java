package org.pet.cryptonator.service;

import org.pet.cryptonator.domain.Period;
import org.pet.cryptonator.domain.dto.TicketDto;

import java.util.List;

public interface BittrexObtainService {
    List<TicketDto> getTickets(long marketId, Period period);
}
