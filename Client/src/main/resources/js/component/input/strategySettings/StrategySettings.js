import React from "react"
import { connect } from "react-redux"
import { setPercent, setFirstPeriod, setSecondPeriod } from "Js/store/input/action"
import { firstPeriodSelector, percentSelector, secondPeriodSelector } from "Js/store/input/selector"
import PropTypes from "prop-types"

class StrategySettings extends React.Component{
    constructor(props) {
        super(props)

        this.onFirstPeriodChange = this.onFirstPeriodChange.bind(this);
        this.onSecondPeriodChange = this.onSecondPeriodChange.bind(this);
        this.onPercentChange = this.onPercentChange.bind(this);
    }

    onFirstPeriodChange(event) {
        let firstPeriod = Number(event.target.value)
        this.props.setFirstPeriod(firstPeriod)
    }

    onSecondPeriodChange(event) {
        let secondPeriod = Number(event.target.value)
        this.props.setSecondPeriod(secondPeriod)
    }

    onPercentChange(event) {
        let percent = Number(event.target.value)
        this.props.setPercent(percent)
    }

    render() {
        return (
            <div>
                <span>Период первой скользящей</span><br/>
                <input type="number" value={this.props.firstPeriod} onChange={this.onFirstPeriodChange} />
                <br/>
                <span>Период второй скользящей</span><br/>
                <input type="number" value={this.props.secondPeriod} onChange={this.onSecondPeriodChange} />
                <br/>
                <span>Выигрыш со сделки, %</span><br/>
                <input type="number" placeholder="Выигрыш со сделки" value={this.props.percent} onChange={this.onPercentChange} />
            </div>
        )
    }
}

StrategySettings.propTypes = {
    firstPeriod: PropTypes.number,
    secondPeriod: PropTypes.number,
    percent: PropTypes.number,
    setFirstPeriod: PropTypes.func,
    setSecondPeriod: PropTypes.func,
    setPercent: PropTypes.func
}

const mapStateToProps = state => {
    return {
        firstPeriod: firstPeriodSelector(state),
        secondPeriod: secondPeriodSelector(state),
        percent: percentSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    setFirstPeriod: firstPeriod => dispatch(setFirstPeriod(firstPeriod)),
    setSecondPeriod: secondPeriod => dispatch(setSecondPeriod(secondPeriod)),
    setPercent: percent => dispatch(setPercent(percent))
})

export default connect(mapStateToProps, mapDispatchToProps)(StrategySettings)