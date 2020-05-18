import React from "react"
import { connect } from "react-redux"
import { setPeriod } from "Js/store/input/action"
import { Box, Select, InputLabel, MenuItem, FormControl } from "@material-ui/core"
import { periodSelector } from "Js/store/input/selector"
import PropTypes from "prop-types"

class PeriodSelector extends React.Component{
    constructor(props) {
        super(props)

        this.onPeriodChange = this.onPeriodChange.bind(this);
    }

    handlePeriodChange(event) {
        this.props.setPeriod(event.target.value)
    }

    render() {
        return (
            <FormControl fullWidth margin="dense">
                <InputLabel id="perid-selector-label">Периодичность тикетов</InputLabel>
                <Select value={this.props.period} onChange={this.handlePeriodChange} labelId="period-selector-label">
                    <MenuItem value="MINUTE_1">1 минута</MenuItem>
                    <MenuItem value="MINUTE_5">5 минут</MenuItem>
                    <MenuItem value="HOUR_1">1 час</MenuItem>
                    <MenuItem value="DAY_1">1 сутки</MenuItem>
                </Select>
            </FormControl>
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