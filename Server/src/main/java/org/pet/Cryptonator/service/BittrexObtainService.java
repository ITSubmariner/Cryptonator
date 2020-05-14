package org.pet.Cryptonator.service;

import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.dto.TicketDto;

import java.util.List;

public interface BittrexObtainService {
    List<TicketDto> getTickets(long marketId, Period period);
}
