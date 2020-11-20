package org.pet.cryptonator.repository;

import org.pet.cryptonator.entity.MarketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MarketRepository extends JpaRepository<MarketEntity, String> {
}
