import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import MarketSelector from "./marketSelector/MarketSelector"
import PeriodSelector from "./periodSelector/PeriodSelector"
import StrategySettings from "./strategySettings/StrategySettings"
import { setResult } from "Js/store/result/action"
import {inputSelector} from "../../store/selector"
import { Box, Paper, Grid, Button } from "@material-ui/core"
import { PlayCircleFilled } from "@material-ui/icons";

class Input extends React.Component{
    constructor(props) {
        super(props)

        this.calculate = this.calculate.bind(this);
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

    calculate() {
        axios.get("/calculate", {params: this.props.input}).then(
            response => this.props.setResult(response.data),
            error => {}
        )
    }
}

const mapStateToProps = state => {
    return {
        input: inputSelector(state)
    }
}

const mapDispatchToProps = {
    setResult
}

export default connect(mapStateToProps, mapDispatchToProps)(Input)