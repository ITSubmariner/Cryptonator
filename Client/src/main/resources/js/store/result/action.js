export const RESULT_CHANGE_STATE = "RESULT_CHANGE_STATE"

export const setResult = result => ({
    type: RESULT_CHANGE_STATE,
    payload: result
})