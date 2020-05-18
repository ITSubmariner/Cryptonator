export const SNACKBAR_SUCCESS = "SNACKBAR_SUCCESS"
export const SNACKBAR_ERROR = "SNACKBAR_ERROR"
export const SNACKBAR_WARNING = "SNACKBAR_WARNING"
export const CLOSE_SNACKBAR = "CLOSE_SNACKBAR"

export const showSuccessSnackbar = message => ({
    type: SNACKBAR_SUCCESS,
    payload: message
})

export const showErrorSnackbar = message => ({
    type: SNACKBAR_ERROR,
    payload: message
})

export const showWarningSnackbar = message => ({
    type: SNACKBAR_WARNING,
    payload: message
})

export const closeSnackbar = () => ({
    type: CLOSE_SNACKBAR
})