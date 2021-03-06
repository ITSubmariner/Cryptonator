import { RESULT_CHANGE_STATE } from "./action";

const initialState = {
    startDate: Date.now(),
    endDate: Date.now(),
    deals: [],
    gain: 0
}

export const resultReducer = (state = initialState, action) => {
    if (action.type === RESULT_CHANGE_STATE) {
        return action.payload
    }

    return state
}