package org.pet.Cryptonator.repository;

import org.pet.Cryptonator.domain.Market;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarketRepository extends JpaRepository<Market, Long> {
    Market findByName(String name);
}
