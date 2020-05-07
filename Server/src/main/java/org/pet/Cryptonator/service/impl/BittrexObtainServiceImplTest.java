package org.pet.Cryptonator.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.pet.Cryptonator.domain.Market;
import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Ticket;
import org.pet.Cryptonator.repository.MarketRepository;
import org.pet.Cryptonator.repository.TicketRepository;
import org.pet.Cryptonator.service.BittrexObtainService;
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
        List<Market> markets = marketRepository.findAll();
        assertFalse(markets.isEmpty());
    }

    @Test
    void getTickets() {
        Market market = marketRepository.findById(1L).get();
        List<Ticket> tickets = bittrexObtainService.getTickets(market.getName(), Period.MINUTE_1);
        assertFalse(tickets.isEmpty());
    }

    @Test
    void writeTickets2Db() {
        Market market = marketRepository.findById(2L).get();
        List<Ticket> ticketsBefore = ticketRepository.findAllByMarket_NameAndPeriodOrderByStartsAtAsc(market.getName(), Period.MINUTE_1);
        boolean before = ticketsBefore.isEmpty();
        List<Ticket> tickets = bittrexObtainService.getTickets(market.getName(), Period.MINUTE_1);
        boolean after = !tickets.isEmpty();
        assertTrue(before && after);
    }
}