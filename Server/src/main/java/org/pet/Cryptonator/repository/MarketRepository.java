package org.pet.Cryptonator.repository;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicSequence;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.CachePeekMode;
import org.pet.Cryptonator.domain.converter.MarketConverter;
import org.pet.Cryptonator.domain.dto.MarketDto;
import org.pet.Cryptonator.domain.entity.MarketEntity;
import org.springframework.stereotype.Repository;

import javax.cache.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Repository
public class MarketRepository {
    private static final String CACHE_NAME = "markets";
    private static final String SEQUENCE_NAME = "markets_sequence";

    private final IgniteAtomicSequence marketsSequence;
    private final IgniteCache<Long, MarketEntity> marketsCache;
    private final MarketConverter marketConverter;

    public MarketRepository(Ignite ignite, MarketConverter marketConverter) {
        this.marketsCache = ignite.getOrCreateCache(CACHE_NAME);
        this.marketsSequence = ignite.atomicSequence(SEQUENCE_NAME, 0, true);
        this.marketConverter = marketConverter;
    }

    public MarketDto get(long id) {
        MarketEntity entity = marketsCache.get(id);
        return marketConverter.entityToDto(id, entity);
    }

    public List<MarketDto> findAll() {
        List<MarketDto> result = new ArrayList<>();
        Iterator<Cache.Entry<Long, MarketEntity>> iterator = marketsCache.iterator();
        while (iterator.hasNext()) {
            Cache.Entry<Long, MarketEntity> entry = iterator.next();
            MarketDto marketDto = marketConverter.entityToDto(entry.getKey(), entry.getValue());
            result.add(marketDto);
        }
        result.sort(Comparator.comparing(MarketDto::getId));
        return result;
    }

    public MarketDto save(MarketEntity entity) {
        long id = marketsSequence.getAndIncrement();
        marketsCache.put(id, entity);
        return marketConverter.entityToDto(id, entity);
    }

    public List<MarketDto> saveAll(List<MarketEntity> markets) {
        List<MarketDto> result = new ArrayList<>();
        for (MarketEntity market : markets) {
            result.add(save(market));
        }
        return result;
    }

    public boolean notEmpty() {
        return marketsCache.size(CachePeekMode.ALL) != 0;
    }

}
