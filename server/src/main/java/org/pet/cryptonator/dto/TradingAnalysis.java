package org.pet.cryptonator.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.ta4j.core.Trade;
import org.ta4j.core.num.Num;

import java.util.List;

@Data
@Accessors(chain = true)
public class TradingAnalysis {

    private String market;
    private List<Trade> trades;
    private Integer tradeCount;
    private Num profitTrades;
    private Num profit;

    @Override
    public String toString() {
        return "TradingAnalysis{" +
                "market=" + market +
                ", tradeCount=" + tradeCount +
                ", profitTrades=" + profitTrades +
                ", profit=" + profit +
                '}';
    }
}
