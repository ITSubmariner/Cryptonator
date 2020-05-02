import React, { Component } from "react"

class PeriodSelector extends Component{
    constructor(props) {
        super(props)
        this.state = {
            period: 0
        }
    }

    render() {
        return (
            <select>
                <option value="0">1 минута</option>
                <option value="1">5 минут</option>
                <option value="2">1 час</option>
                <option value="3">1 сутки</option>
            </select>
        )
    }
}

export default PeriodSelector