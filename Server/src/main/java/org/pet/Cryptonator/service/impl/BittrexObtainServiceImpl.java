package org.pet.Cryptonator.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.dto.MarketDto;
import org.pet.Cryptonator.domain.dto.TicketDto;
import org.pet.Cryptonator.domain.entity.MarketEntity;
import org.pet.Cryptonator.domain.entity.TicketEntity;
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
//        check if there are markets in ignite
        if (this.marketRepository.notEmpty()) {
            return;
        }
        //get all markets as JsonNodes
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode[]> response = restTemplate.getForEntity(marketUrl, JsonNode[].class);
        //check response code
        if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
            //deserialize bittrex json to Market list
            List<MarketEntity> markets = new ArrayList<>();
            for (JsonNode node : response.getBody()) {
                MarketEntity market = new MarketEntity(
                        node.get("symbol").asText(),
                        node.get("status").asText().equals("ONLINE")
                );
                markets.add(market);
            }
            //write market list to ignite
            marketRepository.saveAll(markets);
        } else {
            throw new GetMarketsException();
        }
    }

    @Override
    public List<TicketDto> getTickets(long marketId, Period period) {
        //check if this {pair, period} tickets exists in ignite
        if (ticketRepository.ticketExistence(marketId, period)) {
            //return tickets from ignite
            return ticketRepository.get(marketId, period);
        } else {
            //get and return tickets from bittrex api
            return getTicketsFromApi(marketId, period);
        }
    }

    private List<TicketDto> getTicketsFromApi(long marketId, Period period) {
        MarketDto marketDto = marketRepository.get(marketId);
        String ticketUrl = "https://api.bittrex.com/v3/markets/" + marketDto.getName() + "/candles/" + period.name() + "/recent";
        //get tickets from bittrex api as JsonNodes
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode[]> response = restTemplate.getForEntity(ticketUrl, JsonNode[].class);
        //check response code
        if (response.getStatusCodeValue() == 200 && response.getBody() != null) {
            //deserialize bittrex json to Ticket list
            List<TicketEntity> tickets = new ArrayList<>();
            for (JsonNode node : response.getBody()) {
                TicketEntity ticket = new TicketEntity(
                        marketId,
                        period,
                        LocalDateTime.parse(node.get("startsAt").asText(), DateTimeFormatter.ISO_DATE_TIME),
                        node.get("open").asDouble(),
                        node.get("close").asDouble(),
                        node.get("volume").asDouble()
                );
                tickets.add(ticket);
            }
            return this.ticketRepository.saveAll(tickets);
        } else {
            throw new GetTicketsException();
        }
    }
}
