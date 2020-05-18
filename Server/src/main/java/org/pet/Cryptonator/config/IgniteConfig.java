package org.pet.Cryptonator.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.events.EventType;
import org.apache.ignite.spi.discovery.DiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.pet.Cryptonator.domain.entity.MarketEntity;
import org.pet.Cryptonator.domain.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class IgniteConfig {
    @Value("${ignite.discovery}")
    private String igniteDiscoveryIpList;

    @Bean
    public Ignite igniteInstance(IgniteConfiguration configuration) {
        return Ignition.start(configuration);
    }

    @Bean
    public IgniteConfiguration igniteConfiguration(DiscoverySpi discoverySpi, List<CacheConfiguration> cacheConfigurations) {
        IgniteConfiguration configuration = new IgniteConfiguration();
        configuration.setClientMode(true);
        configuration.setPeerClassLoadingEnabled(true);
        configuration.setIncludeEventTypes(
                EventType.EVT_TASK_STARTED,
                EventType.EVT_TASK_FINISHED,
                EventType.EVT_TASK_FAILED,
                EventType.EVT_TASK_TIMEDOUT,
                EventType.EVT_TASK_SESSION_ATTR_SET,
                EventType.EVT_TASK_REDUCED,
                EventType.EVT_CACHE_OBJECT_PUT,
                EventType.EVT_CACHE_OBJECT_READ,
                EventType.EVT_CACHE_OBJECT_REMOVED);
        configuration.setDiscoverySpi(discoverySpi);
        configuration.setCacheConfiguration(cacheConfigurations.get(0), cacheConfigurations.get(1));
        return configuration;
    }

    @Bean
    public DiscoverySpi igniteDiscovery() {
        TcpDiscoveryMulticastIpFinder finder = new TcpDiscoveryMulticastIpFinder();
        finder.setAddresses(Collections.singletonList(igniteDiscoveryIpList));
        return new TcpDiscoverySpi().setIpFinder(finder);
    }

    @Bean
    public List<CacheConfiguration> igniteCacheConfig() {
        List<CacheConfiguration> cacheConfigurations = new ArrayList<>();
//        create market cache config
        CacheConfiguration<Long, MarketEntity> marketCacheConfiguration = new CacheConfiguration<>();
        marketCacheConfiguration.setName("markets");
        marketCacheConfiguration.setIndexedTypes(Long.class, MarketEntity.class);
//        create ticket cache config
        CacheConfiguration<Long, TicketEntity> ticketCacheConfiguration = new CacheConfiguration<>();
        ticketCacheConfiguration.setName("tickets");
        ticketCacheConfiguration.setIndexedTypes(Long.class, TicketEntity.class);

        cacheConfigurations.add(marketCacheConfiguration);
        cacheConfigurations.add(ticketCacheConfiguration);
        return cacheConfigurations;
    }

}
