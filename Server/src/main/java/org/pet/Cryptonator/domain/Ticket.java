package org.pet.Cryptonator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "ticketMarket", joinColumns = @JoinColumn(name = "ticketId"))
    private Market market;
    @Enumerated(EnumType.STRING)
    private Period period;
    private LocalDateTime startsAt;
    private double open;
    private double close;
    private double volume;

    public Ticket(Market market, Period period, LocalDateTime startsAt, double open, double close, double volume) {
        this.market = market;
        this.period = period;
        this.startsAt = startsAt;
        this.open = open;
        this.close = close;
        this.volume = volume;
    }
}
