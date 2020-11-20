package org.pet.cryptonator.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MarketDto {

    private long id;
    private String name;
    private boolean status;

}
