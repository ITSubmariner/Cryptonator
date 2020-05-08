import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import MarketSelector from "./marketSelector/MarketSelector"
import PeriodSelector from "./periodSelector/PeriodSelector"
import StrategySettings from "./strategySettings/StrategySettings"
import { setResult } from "../../store/result/action"

class Input extends React.Component{
    constructor(props) {
        super(props)

        this.calculate = this.calculate.bind(this);
    }
    
    render() {
        return (
            <div>
                <MarketSelector />
                <PeriodSelector />
                <StrategySettings />
                <button onClick={this.calculate}>Вычислить</button>
            </div>
        )
    }

    calculate() {
        axios.get("/calculate", {params: this.props.input}).then(
            response => this.props.setResult(response.data),
            error => {}
        )
    }
}

const mapStateToProps = state => {
    return {
        input: state.input
    }
}

const mapDispatchToProps = {
    setResult
}

export default connect(mapStateToProps, mapDispatchToProps)(Input)