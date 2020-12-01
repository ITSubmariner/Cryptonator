import {inputSelector} from "../selector";

export const marketSelector = state => {
    let input = inputSelector(state)
    return input.market
}

export const periodSelector = state => {
    let input = inputSelector(state)
    return input.period
}

export const firstPeriodSelector = state => {
    let input = inputSelector(state)
    return input.firstPeriod
}

export const secondPeriodSelector = state => {
    let input = inputSelector(state)
    return input.secondPeriod
}

export const percentSelector = state => {
    let input = inputSelector(state)
    return input.percent
}