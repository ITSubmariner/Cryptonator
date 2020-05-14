import React from "react"
import axios from "axios"
import {connect} from "react-redux"
import MarketSelector from "./marketSelector/MarketSelector"
import PeriodSelector from "./periodSelector/PeriodSelector"
import StrategySettings from "./strategySettings/StrategySettings"
import { setResult } from "Js/store/result/action"
import { inputSelector } from "Js/store/selector"
import "Css/input.css"

class Input extends React.Component{
    constructor(props) {
        super(props)

        this.calculate = this.calculate.bind(this);
    }

    calculate() {
        axios.get("/calculate", {params: this.props.input}).then(
            response => this.props.setResult(response.data),
            error => {}
        )
    }

    render() {
        return (
            <div className="input-form">
                <MarketSelector />
                <PeriodSelector />
                <StrategySettings />
                <button onClick={this.calculate}>Вычислить</button>
            </div>
        )
    }
}

const mapStateToProps = state => {
    return {
        input: inputSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    setResult: result => dispatch(setResult(result))
})

export default connect(mapStateToProps, mapDispatchToProps)(Input)