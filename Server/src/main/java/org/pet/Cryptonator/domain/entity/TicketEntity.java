package org.pet.Cryptonator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pet.Cryptonator.domain.Period;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {

    private long market;
    private Period period;
    private LocalDateTime startsAt;
    private double open;
    private double close;
    private double volume;

}
