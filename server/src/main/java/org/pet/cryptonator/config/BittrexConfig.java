package org.pet.cryptonator.config;

import com.github.ccob.bittrex4j.BittrexExchange;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BittrexConfig {

    @Value("${bittrex.login}")
    private String login;

    @Value("${bittrex.password}")
    private String password;

    @Bean
    @SneakyThrows
    public BittrexExchange bittrexExchange() {
        return new BittrexExchange(login, password);
    }

}
