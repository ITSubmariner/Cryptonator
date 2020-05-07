package org.pet.Cryptonator.service;

import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Ticket;

import java.util.List;

public interface BittrexObtainService {
    List<Ticket> getTickets(String marketName, Period period);
}
