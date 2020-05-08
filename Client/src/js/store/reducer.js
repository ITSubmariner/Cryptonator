import {combineReducers} from "redux"
import {inputReducer} from "./input/reducer";
import {resultReducer} from "./result/reducer";

export default combineReducers({
    input: inputReducer,
    result: resultReducer
})