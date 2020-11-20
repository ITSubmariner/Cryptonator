package org.pet.cryptonator.repository;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteCache;
import org.pet.cryptonator.domain.Period;
import org.pet.cryptonator.domain.converter.TicketConverter;
import org.pet.cryptonator.domain.dto.TicketDto;
import org.pet.cryptonator.domain.entity.TicketEntity;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Repository
public class TicketRepository {
    private static final String CACHE_NAME = "tickets";
    private static final String SEQUENCE_NAME = "tickets_sequence";

    private final IgniteAtomicSequence ticketsSequence;
    private final IgniteCache<Long, TicketEntity> ticketsCache;
    private final TicketConverter ticketConverter;

    public TicketRepository(Ignite ignite, TicketConverter ticketConverter, MarketRepository marketRepository) {
        this.ticketsCache = ignite.getOrCreateCache(CACHE_NAME);
        this.ticketsSequence = ignite.atomicSequence(SEQUENCE_NAME, 0, true);
        this.ticketConverter = ticketConverter;
    }

    public boolean ticketExistence(long marketId, Period period) {
        Iterator<Cache.Entry<Long, TicketEntity>> iterator = ticketsCache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<Long, TicketEntity> entry = iterator.next();
            TicketEntity entity = entry.getValue();
            if (entity.getMarket() == marketId && entity.getPeriod().compareTo(period) == 0) {
                return true;
            }
        }
        return false;
    }

    public List<TicketDto> get(long marketId, Period period) {
        List<TicketDto> result = new ArrayList<>();
        Iterator<Cache.Entry<Long, TicketEntity>> iterator = ticketsCache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<Long, TicketEntity> entry = iterator.next();
            TicketEntity entity = entry.getValue();
            if (entity.getMarket() == marketId && entity.getPeriod() == period) {
                TicketDto ticketDto = ticketConverter.entityToDto(entry.getKey(), entry.getValue());
                result.add(ticketDto);
            }
        }
        result.sort(Comparator.comparing(TicketDto::getStartsAt));
        return result;
    }

    public TicketDto save(TicketEntity entity) {
        long id = ticketsSequence.getAndIncrement();
        ticketsCache.getAndPut(id, entity);
        return ticketConverter.entityToDto(id, entity);
    }

    public List<TicketDto> saveAll(List<TicketEntity> entities) {
        List<TicketDto> result = new ArrayList<>();
        entities.forEach(entity -> result.add(save(entity)));
        result.sort(Comparator.comparing(TicketDto::getStartsAt));
        return result;
    }
}
