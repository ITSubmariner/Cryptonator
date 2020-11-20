export const INPUT_CHANGE_MARKET = "INPUT_CHANGE_MARKET"
export const INPUT_CHANGE_PERIOD = "INPUT_CHANGE_PERIOD"
export const INPUT_CHANGE_FIRST_EMA_PERIOD = "INPUT_CHANGE_FIRST_EMA_PERIOD"
export const INPUT_CHANGE_SECOND_EMA_PERIOD = "INPUT_CHANGE_SECOND_EMA_PERIOD"
export const INPUT_CHANGE_PERCENT = "INPUT_CHANGE_PERCENT"

export const setMarket = market => ({
    type: INPUT_CHANGE_MARKET,
    payload: market
})

export const setPeriod = period => ({
    type: INPUT_CHANGE_PERIOD,
    payload: period
})

export const setFirstPeriod = period => ({
    type: INPUT_CHANGE_FIRST_EMA_PERIOD,
    payload: period
})

export const setSecondPeriod = period => ({
    type: INPUT_CHANGE_SECOND_EMA_PERIOD,
    payload: period
})

export const setPercent = percent => ({
    type: INPUT_CHANGE_PERCENT,
    payload: percent
})

