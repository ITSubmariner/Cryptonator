import {CLOSE_SNACKBAR, SNACKBAR_ERROR, SNACKBAR_SUCCESS, SNACKBAR_WARNING} from "./action";

const initialState = {
    active: false,
    status: "info",
    text: ""
}

export const snackbarReducer = (state = initialState, action) => {
    switch (action.type) {
        case SNACKBAR_SUCCESS:
            return {
                active: true,
                status: "success",
                text: action.payload
            }
        case SNACKBAR_ERROR:
            return {
                active: true,
                status: "error",
                text: action.payload
            }
        case SNACKBAR_WARNING:
            return {
                active: true,
                status: "warning",
                text: action.payload
            }
        case CLOSE_SNACKBAR:
            return {
                ...state,
                active: false
            }
        default:
            return state
    }
}