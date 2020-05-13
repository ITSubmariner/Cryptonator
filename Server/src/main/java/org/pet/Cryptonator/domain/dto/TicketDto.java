package org.pet.Cryptonator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pet.Cryptonator.domain.Period;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private long id;
    private long market;
    private Period period;
    private LocalDateTime startsAt;
    private double open;
    private double close;
    private double volume;

    public TicketDto(long market, Period period, LocalDateTime startsAt, double open, double close, double volume) {
        this.market = market;
        this.period = period;
        this.startsAt = startsAt;
        this.open = open;
        this.close = close;
        this.volume = volume;
    }

}
