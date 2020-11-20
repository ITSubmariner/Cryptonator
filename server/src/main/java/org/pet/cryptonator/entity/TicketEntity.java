package org.pet.cryptonator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.pet.cryptonator.entity.enums.Period;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "T_TICKETS")
@Accessors(chain = true)
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "market")
    private Long market;

    @Enumerated(EnumType.STRING)
    private Period period;

    @Column(name = "startsAt")
    private LocalDateTime startsAt;

    @Column(name = "open")
    private Double open;

    @Column(name = "close")
    private Double close;

    @Column(name = "volume")
    private Double volume;

}
