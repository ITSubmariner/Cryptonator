package org.pet.Cryptonator.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.pet.Cryptonator.domain.Market;
import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Ticket;
import org.pet.Cryptonator.exception.GetMarketsException;
import org.pet.Cryptonator.exception.GetTicketsException;
import org.pet.Cryptonator.repository.MarketRepository;
import org.pet.Cryptonator.repository.TicketRepository;
import org.pet.Cryptonator.service.BittrexObtainService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BittrexObtainServiceImpl implements BittrexObtainService {
    private final String marketUrl = "https://api.bittrex.com/v3/markets";
    private final MarketRepository marketRepository;
    private final TicketRepository ticketRepository;

    public BittrexObtainServiceImpl(MarketRepository marketRepository, TicketRepository ticketRepository) {
        this.marketRepository = marketRepository;
        this.ticketRepository = ticketRepository;
    }

    @PostConstruct
    private void getMarkets() {
        //get all markets as JsonNodes
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode[]> response = restTemplate.getForEntity(marketUrl, JsonNode[].class);
        //check response code
        if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
            //deserialize bittrex json to Market list
            List<Market> markets = new ArrayList<>();
            for (JsonNode node : response.getBody()) {
                Market market = new Market(
                        node.get("symbol").asText(),
                        node.get("status").asText().equals("ONLINE")
                );
                markets.add(market);
            }
            //write market list to db
            marketRepository.saveAll(markets);
        } else {
            throw new GetMarketsException();
        }
    }

    @Override
    public List<Ticket> getTickets(String marketName, Period period) {
        //check if this {pair, period} tickets exists in db
        if (ticketRepository.existsTicketsByMarket_NameAndPeriod(marketName, period)) {
            //return tickets from db
            return ticketRepository.findAllByMarket_NameAndPeriodOrderByStartsAtAsc(marketName, period);
        } else {
            //get and return tickets from bittrex api
            return getTicketsFromApi(marketName, period);
        }
    }

    private List<Ticket> getTicketsFromApi(String marketName, Period period) {
        String ticketUrl = "https://api.bittrex.com/v3/markets/" + marketName + "/candles/" + period.name() + "/recent";
        //get tickets from bittrex api as JsonNodes
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode[]> response = restTemplate.getForEntity(ticketUrl, JsonNode[].class);
        //check response code
        if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
            //get market from db
            Market market = marketRepository.findByName(marketName);
            //deserialize bittrex json to Ticket list
            List<Ticket> tickets = new ArrayList<>();
            for (JsonNode node : response.getBody()) {
                Ticket ticket = new Ticket(
                        market,
                        period,
                        LocalDateTime.parse(node.get("startsAt").asText(), DateTimeFormatter.ISO_DATE_TIME),
                        node.get("open").asDouble(),
                        node.get("close").asDouble(),
                        node.get("volume").asDouble()
                );
                tickets.add(ticket);
            }
            this.ticketRepository.saveAll(tickets);
            return tickets;
        } else {
            throw new GetTicketsException();
        }
    }
}
