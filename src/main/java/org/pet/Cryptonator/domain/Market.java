package org.pet.Cryptonator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "markets")
@Data
@NoArgsConstructor
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private boolean status;

    public Market(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
}
