import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import { setMarket } from "Js/store/input/action"
import {marketSelector} from "../../../store/input/selector"
import { Box, Select, InputLabel, FormControl, MenuItem } from "@material-ui/core"
import { MonetizationOn } from "@material-ui/icons"

class MarketSelector extends React.Component{
    constructor(props) {
        super(props)
        this.state = {
            markets: []
        }

        this.onMarketChange = this.onMarketChange.bind(this);
    }

    componentDidMount() {
        this.getMarkets()
    }

    render() {
        return (
            <FormControl fullWidth>
                <InputLabel id="market-selector-label">Валютная пара</InputLabel>
                <Select value={this.props.market} onChange={this.onMarketChange} labelId="market-selector-label">
                    { this.state.markets.map(market => this.renderMarket(market)) }
                </Select>
            </FormControl>
        )
    }

    getMarkets() {
        axios.get("/market").then(
            response => {this.setState({markets: response.data})},
            error => {}
        )
    }

    renderMarket(market) {
        return (
            <MenuItem value={market.name} key={market.id}>
                {market.name}
            </MenuItem>
        )
    }

    onMarketChange(event) {
        this.props.setMarket(event.target.value)
    }

}

const mapStateToProps = state => {
    return {
        market: marketSelector(state)
    }
}

const mapDispatchToProps = {
    setMarket
}

export default connect(mapStateToProps, mapDispatchToProps)(MarketSelector)