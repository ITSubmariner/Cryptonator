package org.pet.Cryptonator.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.pet.Cryptonator.domain.Period;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TicketDto {

    private long id;
    private long market;
    private Period period;
    private LocalDateTime startsAt;
    private double open;
    private double close;
    private double volume;

}
