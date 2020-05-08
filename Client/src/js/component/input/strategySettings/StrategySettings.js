import React from "react"
import { connect } from "react-redux"
import { setPercent, setFirstPeriod, setSecondPeriod } from "Js/store/input/action"

class StrategySettings extends React.Component{
    constructor(props) {
        super(props)

        this.onFirstPeriodChange = this.onFirstPeriodChange.bind(this);
        this.onSecondPeriodChange = this.onSecondPeriodChange.bind(this);
        this.onPercentChange = this.onPercentChange.bind(this);
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

    onFirstPeriodChange(event) {
        this.props.setFirstPeriod(event.target.value)
    }

    onSecondPeriodChange(event) {
        this.props.setSecondPeriod(event.target.value)
    }

    onPercentChange(event) {
        this.props.setPercent(event.target.value)
    }
}

const mapStateToProps = state => {
    return {
        firstPeriod: state.input.firstPeriod,
        secondPeriod: state.input.secondPeriod,
        percent: state.input.percent
    }
}

const mapDispatchToProps = {
    setFirstPeriod,
    setSecondPeriod,
    setPercent
}

export default connect(mapStateToProps, mapDispatchToProps)(StrategySettings)