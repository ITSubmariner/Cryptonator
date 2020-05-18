import {combineReducers} from "redux"
import {inputReducer} from "./input/reducer";
import {resultReducer} from "./result/reducer";
import {snackbarReducer} from "./snackbar/reducer";

export default combineReducers({
    input: inputReducer,
    result: resultReducer,
    snackbar: snackbarReducer
})