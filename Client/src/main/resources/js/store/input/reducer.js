import {
    INPUT_CHANGE_FIRST_EMA_PERIOD,
    INPUT_CHANGE_MARKET, INPUT_CHANGE_PERCENT,
    INPUT_CHANGE_PERIOD,
    INPUT_CHANGE_SECOND_EMA_PERIOD
} from "./action"

const initialState = {
    market: 0,
    period: "MINUTE_1",
    firstPeriod: 0,
    secondPeriod: 0,
    percent: 0
}

export const inputReducer = (state = initialState, action) => {
    switch (action.type) {
        case INPUT_CHANGE_MARKET:
            return {
                ...state,
                market: action.payload
            }

        case INPUT_CHANGE_PERIOD:
            return {
                ...state,
                period: action.payload
            }

        case INPUT_CHANGE_FIRST_EMA_PERIOD:
            return {
                ...state,
                firstPeriod: action.payload
            }

        case INPUT_CHANGE_SECOND_EMA_PERIOD:
            return {
                ...state,
                secondPeriod: action.payload
            }

        case INPUT_CHANGE_PERCENT:
            return {
                ...state,
                percent: action.payload
            }
    }
    return state
}