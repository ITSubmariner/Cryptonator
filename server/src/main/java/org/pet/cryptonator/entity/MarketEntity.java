package org.pet.cryptonator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "T_MARKETS")
@Accessors(chain = true)
public class MarketEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

}
