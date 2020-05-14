import React from "react"
import { connect } from "react-redux"
import { setPeriod } from "Js/store/input/action"
import { periodSelector } from "Js/store/input/selector"
import PropTypes from "prop-types"

class PeriodSelector extends React.Component{
    constructor(props) {
        super(props)

        this.onPeriodChange = this.onPeriodChange.bind(this);
    }

    onPeriodChange(event) {
        this.props.setPeriod(event.target.value)
    }

    render() {
        return (
            <div>
                <span>Периодичность тикетов</span>
                <br/>
                <select value={this.props.period} onChange={this.onPeriodChange}>
                    <option value="MINUTE_1">1 минута</option>
                    <option value="MINUTE_5">5 минут</option>
                    <option value="HOUR_1">1 час</option>
                    <option value="DAY_1">1 сутки</option>
                </select>
            </div>
        )
    }
}

PeriodSelector.propTypes = {
    period: PropTypes.string,
    setPeriod: PropTypes.func
}

const mapStateToProps = state => {
    return {
        period: periodSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    setPeriod: period => dispatch(setPeriod(period))
})

export default connect(mapStateToProps, mapDispatchToProps)(PeriodSelector)