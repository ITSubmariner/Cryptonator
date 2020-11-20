package org.pet.cryptonator.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MarketEntity {

    private String name;
    private boolean status;

}
