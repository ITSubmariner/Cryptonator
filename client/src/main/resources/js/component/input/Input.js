import React from "react"
import {connect} from "react-redux"
import MarketSelector from "./marketSelector/MarketSelector"
import PeriodSelector from "./periodSelector/PeriodSelector"
import StrategySettings from "./strategySettings/StrategySettings"
import {setResult} from "Js/store/result/action"
import {inputSelector} from "../../store/selector"
import {Box, Button, Grid, Paper} from "@material-ui/core"
import {PlayCircleFilled} from "@material-ui/icons"
import {showErrorSnackbar, showSuccessSnackbar, showWarningSnackbar} from "../../store/snackbar/action"
import axios from "axios"

class Input extends React.Component{
    constructor(props) {
        super(props)

        this.calculate = this.calculate.bind(this);
        this.validate = this.validate.bind(this);
    }

    validate() {
        let lessZero = this.props.input.firstPeriod < 0 && this.props.input.secondPeriod < 0 && this.props.input.percent < 0
        let firstGreatherSecond = this.props.input.firstPeriod >= this.props.input.secondPeriod
        let periodsNotInteger = !Number.isInteger(this.props.input.firstPeriod) && !Number.isInteger(this.props.input.secondPeriod)
        let periodsDifferenceLess3 = this.props.input.secondPeriod / this.props.input.firstPeriod < 3

        if (periodsNotInteger) {
            this.props.showErrorSnackbar("Периоды скользящих должны быть целыми числами!")
            return false
        }
        if (lessZero) {
            this.props.showErrorSnackbar("Периоды скользящих и выигрыш со сделки должны быть больше нуля!")
            return false
        }
        if (firstGreatherSecond) {
            this.props.showErrorSnackbar("Период второй скользящей должен быть больше периода первой скользящей!")
            return false
        }
        if (periodsDifferenceLess3) {
            this.props.showWarningSnackbar("Рекомендуется выбирать скользящие, различные в 3 или более раз.")
            return true
        }

        return true
    }

    calculate() {
        if (this.validate()) {
            axios.get("/calculate", {params: this.props.input}).then(
                response => {
                    this.props.setResult(response.data)
                    this.props.showSuccessSnackbar("Результирующие данные успешно получены!")
                },
                error => {
                    this.props.showErrorSnackbar("Результирующие данные не получены!")
                }
            );
        }
    }

    render() {
        return (
            <Grid>
                <Box m={1} px={3} py={2} border={1} borderColor="grey.500" borderRadius="5%" minWidth="250px" component={Paper}>
                    <MarketSelector />
                    <PeriodSelector />
                    <StrategySettings />
                    <Grid container justify="center">
                        <Box mt={2}>
                            <Button size="small" variant="contained" color="primary" onClick={this.calculate} startIcon={<PlayCircleFilled />}>Запуск</Button>
                        </Box>
                    </Grid>
                </Box>
            </Grid>
        )
    }
}

const mapStateToProps = state => {
    return {
        input: inputSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    setResult: result => dispatch(setResult(result)),
    showSuccessSnackbar: snackbar => dispatch(showSuccessSnackbar(snackbar)),
    showErrorSnackbar: snackbar => dispatch(showErrorSnackbar(snackbar)),
    showWarningSnackbar: snackbar => dispatch(showWarningSnackbar(snackbar)),
})

export default connect(mapStateToProps, mapDispatchToProps)(Input)