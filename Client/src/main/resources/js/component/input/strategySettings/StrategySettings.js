import React from "react"
import { connect } from "react-redux"
import { setPercent, setFirstPeriod, setSecondPeriod } from "Js/store/input/action"
import {firstPeriodSelector, percentSelector, secondPeriodSelector} from "Js/store/input/selector"
import { TextField, Grid, Box } from "@material-ui/core"


class StrategySettings extends React.Component{
    constructor(props) {
        super(props)

        this.onFirstPeriodChange = this.onFirstPeriodChange.bind(this);
        this.onSecondPeriodChange = this.onSecondPeriodChange.bind(this);
        this.onPercentChange = this.onPercentChange.bind(this);
    }

    render() {
        return (
            <Grid container direction="column">
                <Box mt={1}>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.firstPeriod} onChange={this.onFirstPeriodChange} label="Период первой скользящей" />
                </Box>
                <Box>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.secondPeriod} onChange={this.onSecondPeriodChange} label="Период второй скользящей" />
                </Box>
                <Box>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.percent} onChange={this.onPercentChange} label="Выигрыш со сделки, %" />
                </Box>
            </Grid>
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
        firstPeriod: firstPeriodSelector(state),
        secondPeriod: secondPeriodSelector(state),
        percent: percentSelector(state)
    }
}

const mapDispatchToProps = {
    setFirstPeriod,
    setSecondPeriod,
    setPercent
}

export default connect(mapStateToProps, mapDispatchToProps)(StrategySettings)