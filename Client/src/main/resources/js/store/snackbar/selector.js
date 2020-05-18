import {snackbarSelector} from "../selector"

export const activeSelector = state => {
    let snackbar = snackbarSelector(state)
    return snackbar.active;
}

export const statusSelector = state => {
    let snackbar = snackbarSelector(state)
    return snackbar.status;
}

export const textSelector = state => {
    let snackbar = snackbarSelector(state)
    return snackbar.text;
}