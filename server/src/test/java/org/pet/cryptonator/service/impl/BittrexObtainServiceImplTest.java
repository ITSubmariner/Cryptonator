package org.pet.cryptonator.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.pet.cryptonator.entity.enums.Period;
import org.pet.cryptonator.dto.MarketDto;
import org.pet.cryptonator.dto.TicketDto;
import org.pet.cryptonator.repository.MarketRepository;
import org.pet.cryptonator.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
class BittrexObtainServiceImplTest {

    private final BittrexObtainService bittrexObtainService;
    private final MarketRepository marketRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    BittrexObtainServiceImplTest(BittrexObtainService bittrexObtainService, MarketRepository marketRepository, TicketRepository ticketRepository) {
        this.bittrexObtainService = bittrexObtainService;
        this.marketRepository = marketRepository;
        this.ticketRepository = ticketRepository;
    }

    @Test
    void getMarkets() {
        List<MarketDto> markets = marketRepository.findAll();
        assertFalse(markets.isEmpty());
    }

    @Test
    void getTickets() {
        List<TicketDto> tickets = bittrexObtainService.getTickets(1L, Period.MINUTE_1);
        assertFalse(tickets.isEmpty());
    }

    @Test
    void writeTickets2Db() {
        List<TicketDto> ticketsBefore = ticketRepository.get(2L, Period.MINUTE_1);
        boolean before = ticketsBefore.isEmpty();
        List<TicketDto> tickets = bittrexObtainService.getTickets(2L, Period.MINUTE_1);
        boolean after = !tickets.isEmpty();
        assertTrue(before && after);
    }
}