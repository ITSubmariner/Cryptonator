import React from "react"
import { connect } from "react-redux"
import { resultSelector } from "Js/store/selector";
import { Grid, Box, TableContainer, Table, TableHead, TableFooter, TableRow, TableCell, TableBody, Paper, Typography } from "@material-ui/core"
import { CheckCircleOutlined, HighlightOffOutlined, Remove } from "@material-ui/icons"
import { green } from "@material-ui/core/colors"

class Result extends React.Component {
    constructor(props) {
        super(props)

        this.getTableFooter = this.getTableFooter.bind(this);
        this.getStatistics = this.getStatistics.bind(this);
    }

    getTableRows() {
        return this.props.result.deals.map((deal, n) => {
            return (
                <TableRow key={n}>
                    <TableCell align="center">{this.getFormattedDate(deal.start)}</TableCell>
                    <TableCell align="center">{this.getFormattedDate(deal.end)}</TableCell>
                    <TableCell align="center">{deal.buyPrice}</TableCell>
                    <TableCell align="center">{deal.completed ? (<CheckCircleOutlined style={{ color: green[500] }} />) : (<HighlightOffOutlined color="error" />)}</TableCell>
                </TableRow>
            )
        })
    }

    getTableFooter() {
        if (this.props.result.deals.length === 0) {
            return (
                <TableFooter>
                    <TableRow>
                        <TableCell align="center" colspan="100%">
                            Нет данных
                        </TableCell>
                    </TableRow>
                </TableFooter>
            )
        }
    }

    getFormattedDate(date) {
        if (date == null) {
            return (<Remove />)
        }
        let pDate = new Date(date);
        return (
            <Box display="inline">
                <Box fontStyle="italic" display="inline">
                    { pDate.toLocaleTimeString().substring(0, 5) }
                </Box>
                <Box ml={0.5} fontSize={12} display="inline" color="text.secondary">
                    { pDate.toLocaleDateString() }
                </Box>
            </Box>
        )
    }

    getStatistics() {
        if (this.props.result.deals.length) {
            return (
                <Box mb={1} ml={1}>
                    <Box>Общее количество сделок: { this.props.result.deals.length }</Box>
                    <Box>Теоретическая прибыль: { this.props.result.gain }%</Box>
                    <Box>Период проведения расчетов: { this.getFormattedDate(this.props.result.startDate) } - { this.getFormattedDate(this.props.result.endDate) }</Box>
                </Box>
            )
        }
    }

    render() {
        return (
            <Grid>
                <Box mt={2} ml={2}>
                    { this.getStatistics() }
                    <TableContainer component={Paper}>
                        <Table size="small">
                            <TableHead>
                                <TableRow>
                                    <TableCell>Время покупки</TableCell>
                                    <TableCell>Время продажи</TableCell>
                                    <TableCell>Стоимость при покупке</TableCell>
                                    <TableCell>Завершённость</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {this.getTableRows()}
                            </TableBody>
                            {this.getTableFooter()}
                        </Table>

                    </TableContainer>
                </Box>
            </Grid>
        )
    }
}

const mapStateToProps = state => {
    return {
        result: resultSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({

})

export default connect(mapStateToProps, mapDispatchToProps)(Result)