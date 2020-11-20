package org.pet.cryptonator.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Deal {
    private LocalDateTime start;
    private LocalDateTime end;
    private double buyPrice;
    private boolean completed;
}
