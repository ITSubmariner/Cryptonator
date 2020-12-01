package org.pet.cryptonator.config;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BinanceConfig {

    @Value("${binance.key}")
    private String key;
    @Value("${binance.password}")
    private String password;

    @Bean
    public BinanceApiClientFactory binanceApiClientFactory() {
        return BinanceApiClientFactory.newInstance(key, password);
    }

    @Bean
    @Scope("prototype")
    public BinanceApiRestClient binanceApiRestClient(BinanceApiClientFactory factory) {
        return factory.newRestClient();
    }

    @Bean
    @Scope("prototype")
    public BinanceApiWebSocketClient binanceApiWebSocketClient(BinanceApiClientFactory factory) {
        return factory.newWebSocketClient();
    }

}
