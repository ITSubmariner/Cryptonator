import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import MarketSelector from "./marketSelector/MarketSelector"
import PeriodSelector from "./periodSelector/PeriodSelector"
import StrategySettings from "./strategySettings/StrategySettings"
import { setResult } from "../../store/result/action"

class Input extends React.Component{
    render() {
        return (
            <div>
                <MarketSelector />
                <PeriodSelector />
                <StrategySettings />
                <button>Вычислить</button>
            </div>
        )
    }

    calculate() {
        let params = {
            firstPeriod: this.props.firstPeriod,
            secondPeriod: this.props.secondPeriod,
            percent: this.props.percent
        }
        axios.get("/calculate", {params: params}).then(
            response => {},
            error => {}
        )
    }
}

const mapStateToProps = state => {
    return {

    }
}

const mapDispatchToProps = {
    setResult
}

export default connect(mapStateToProps, mapDispatchToProps)(Input)