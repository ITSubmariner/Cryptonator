import {resultSelector} from "../selector";

export const startDateSelector = state => {
    let result = resultSelector(state)
    return result.startDate
}

export const endDateSelector = state => {
    let result = resultSelector(state)
    return result.endDate
}

export const dealsSelector = state => {
    let result = resultSelector(state)
    return result.deals
}

export const gainSelector = state => {
    let result = resultSelector(state)
    return result.gain
}