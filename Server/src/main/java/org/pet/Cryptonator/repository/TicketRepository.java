package org.pet.Cryptonator.repository;

import org.pet.Cryptonator.domain.Period;
import org.pet.Cryptonator.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    boolean existsTicketsByMarket_NameAndPeriod(String market_name, Period period);

    List<Ticket> findAllByMarket_NameAndPeriodOrderByStartsAtAsc(String market_name, Period period);
}
