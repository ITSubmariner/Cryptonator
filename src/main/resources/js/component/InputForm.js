import React, { Component } from "react"
import MarketSelector from "./inputForm/MarketSelector"
import PeriodSelector from "./inputForm/PeriodSelector"

class InputForm extends Component{
    constructor(props) {
        super(props)
    }

    render() {
        return (
            <div>
                <MarketSelector />
                <PeriodSelector />
            </div>
        )
    }
}

export default InputForm