import React from "react"
import { connect } from "react-redux"
import { setPeriod } from "Js/store/input/action"
import {periodSelector} from "../../../store/input/selector"
import { Box, Select, InputLabel, MenuItem, FormControl } from "@material-ui/core"


class PeriodSelector extends React.Component{
    constructor(props) {
        super(props)

        this.onPeriodChange = this.onPeriodChange.bind(this);
    }

    render() {
        return (
            <FormControl fullWidth margin="dense">
                <InputLabel id="perid-selector-label">Периодичность тикетов</InputLabel>
                <Select value={this.props.period} onChange={this.onPeriodChange} labelId="period-selector-label">
                    <MenuItem value="MINUTE_1">1 минута</MenuItem>
                    <MenuItem value="MINUTE_5">5 минут</MenuItem>
                    <MenuItem value="HOUR_1">1 час</MenuItem>
                    <MenuItem value="DAY_1">1 сутки</MenuItem>
                </Select>
            </FormControl>
        )
    }

    onPeriodChange(event) {
        this.props.setPeriod(event.target.value)
    }

}

const mapStateToProps = state => {
    return {
        period: periodSelector(state)
    }
}

const mapDispatchToProps = {
    setPeriod
}

export default connect(mapStateToProps, mapDispatchToProps)(PeriodSelector)