package org.pet.Cryptonator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarketDto {

    private long id;
    private String name;
    private boolean status;

    public MarketDto(String name, boolean status) {
        this.name = name;
        this.status = status;
    }
}
