package org.pet.cryptonator.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Result {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Deal> deals = new ArrayList<>();
    private double gain;

    public void startDeal(LocalDateTime start, double buyPrice) {
        Deal deal = new Deal();
        deal.setStart(start);
        deal.setBuyPrice(buyPrice);
        deals.add(deal);
    }

    public void endDeal(LocalDateTime end) {
        deals.get(deals.size()-1).setEnd(end);
        deals.get(deals.size()-1).setCompleted(true);
    }

    public void addGain(double percent) {
        gain += percent;
    }
}
