import React from "react"
import { connect } from "react-redux"
import { setPeriod } from "../../../store/input/action"

class PeriodSelector extends React.Component{
    constructor(props) {
        super(props)

        this.onPeriodChange = this.onPeriodChange.bind(this);
    }

    render() {
        return (
            <select value={this.props.period} onChange={this.onPeriodChange}>
                <option value="0">1 минута</option>
                <option value="1">5 минут</option>
                <option value="2">1 час</option>
                <option value="3">1 сутки</option>
            </select>
        )
    }

    onPeriodChange(event) {
        this.props.setPeriod(event.target.value)
    }
}

const mapStateToProps = state => {
    return {
        period: state.input.period
    }
}

const mapDispatchToProps = {
    setPeriod
}

export default connect(mapStateToProps, mapDispatchToProps)(PeriodSelector)