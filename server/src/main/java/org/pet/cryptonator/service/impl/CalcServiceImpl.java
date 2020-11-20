package org.pet.cryptonator.service.impl;

import com.github.ccob.bittrex4j.BittrexExchange;
import lombok.RequiredArgsConstructor;
import org.pet.cryptonator.entity.enums.Period;
import org.pet.cryptonator.dto.Result;
import org.pet.cryptonator.dto.TicketDto;
import org.pet.cryptonator.service.CalcService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalcServiceImpl implements CalcService {

    private final BittrexExchange bittrexExchange;

    @Override
    public Result calculate(long marketId, Period period, int smallPeriod, int bigPeriod, double percent) {
        Result result = new Result();
        List<TicketDto> tickets = this.bittrexObtainService.getTickets(marketId, period);
//        set start date
        result.setStartDate(tickets.get(0).getStartsAt());
//        set end date
        result.setEndDate(tickets.get(tickets.size()-1).getStartsAt());
//        calculate ema for smallest period
        List<Double> smallEma = getEma(tickets, smallPeriod);
//        calculate ema for biggest period
        List<Double> bigEma = getEma(tickets, bigPeriod);

        boolean dealInProgress = false;
        double priceForSell = 0;
        for (int i = bigPeriod; i < tickets.size(); i++) {
            if (!dealInProgress && smallEma.get(i) != null && bigEma.get(i) != null && smallEma.get(i - 1) != null && bigEma.get(i - 1) != null) {
//                small ema increasing or same as previous
                boolean smallEmaIncreasingOrConstant = smallEma.get(i) >= smallEma.get(i - 1);
//                big ema decreasing or same as previous
//                boolean bigEmaDecreasingOrConstant = bigEma.get(i) <= bigEma.get(i - 1);
//                small ema cross over big ema
                boolean smallEmaCrossBigEma = smallEma.get(i - 1) < bigEma.get(i - 1) && smallEma.get(i) >= bigEma.get(i);
                if (smallEmaCrossBigEma && smallEmaIncreasingOrConstant) {
                    dealInProgress = true;
                    priceForSell = tickets.get(i).getClose() * (1 + percent / 100d);
                    result.startDeal(tickets.get(i).getStartsAt(), tickets.get(i).getClose());
                }
            } else {
                boolean sellResponsibility = priceForSell <= tickets.get(i).getClose();
                if (sellResponsibility) {
                    dealInProgress = false;
                    priceForSell = 0;
                    result.endDeal(tickets.get(i).getStartsAt());
                    result.addGain(percent);
                }
            }
        }
        return result;
    }

    private List<Double> getEma(List<TicketDto> tickets, int period) {
//        calculate alfa
        double alfa = (double) 2 / (period + 1);
        List<Double> ema = new ArrayList<>();
        double sum = 0;
//        calculate sum of first {period} elements
        for (int i = 0; i < period; i++) {
            sum += tickets.get(i).getClose();
//            add null for time sync
            ema.add(null);
        }
//        add ema(0)
        ema.set(period - 1, sum / period);
//        calculate ema(1), ema(2), ...
        for (int i = period; i < tickets.size(); i++) {
            double currentEma = alfa * tickets.get(i).getClose() + (1 - alfa) * ema.get(i-1);
            ema.add(currentEma);
        }
        return ema;
    }

}
