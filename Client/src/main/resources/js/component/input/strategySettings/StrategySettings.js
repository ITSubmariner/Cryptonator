import React from "react"
import { connect } from "react-redux"
import { setPercent, setFirstPeriod, setSecondPeriod } from "Js/store/input/action"
import { firstPeriodSelector, percentSelector, secondPeriodSelector } from "Js/store/input/selector"
import { TextField, Grid, Box } from "@material-ui/core"
import PropTypes from "prop-types"


class StrategySettings extends React.Component {
    constructor(props) {
        super(props)

        this.onFirstPeriodChange = this.onFirstPeriodChange.bind(this);
        this.onSecondPeriodChange = this.onSecondPeriodChange.bind(this);
        this.onPercentChange = this.onPercentChange.bind(this);
    }

    handleFirstPeriodChange(event) {
        let firstPeriod = Number(event.target.value)
        this.props.setFirstPeriod(firstPeriod)
    }

    handleSecondPeriodChange(event) {
        let secondPeriod = Number(event.target.value)
        this.props.setSecondPeriod(secondPeriod)
    }

    handlePercentChange(event) {
        let percent = Number(event.target.value)
        this.props.setPercent(percent)
    }

    render() {
        return (
            <Grid container direction="column">
                <Box mt={1}>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.firstPeriod} onChange={this.handleFirstPeriodChange} label="Период первой скользящей" />
                </Box>
                <Box>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.secondPeriod} onChange={this.handleSecondPeriodChange} label="Период второй скользящей" />
                </Box>
                <Box>
                    <TextField required inputProps={{min: 0}} margin="dense" fullWidth variant="outlined" type="number" value={this.props.percent} onChange={this.handlePercentChange} label="Выигрыш со сделки, %" />
                </Box>
            </Grid>
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