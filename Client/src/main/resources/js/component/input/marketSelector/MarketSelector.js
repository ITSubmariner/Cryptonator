import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import { setMarket } from "Js/store/input/action"
import { marketSelector } from "Js/store/input/selector"
import { Box, Select, InputLabel, FormControl, MenuItem } from "@material-ui/core"
import { MonetizationOn } from "@material-ui/icons"
import PropTypes from "prop-types"

class MarketSelector extends React.Component {
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

    getMarkets() {
        axios.get("/market").then(
            response => {this.setState({markets: response.data})},
            error => {}
        )
    }

    renderMarket(market) {
        return (
            <MenuItem value={market.id} key={market.id}>
                {market.name}
            </MenuItem>
        )
    }

    handleMarketChange(event) {
        let marketId = Number(event.target.value)
        this.props.setMarket(marketId)
    }

    render() {
        return (
            <FormControl fullWidth>
                <InputLabel id="market-selector-label">Валютная пара</InputLabel>
                <Select value={this.props.market} onChange={this.handleMarketChange} labelId="market-selector-label">
                    { this.state.markets.map(market => this.renderMarket(market)) }
                </Select>
            </FormControl>
        )
    }

}

MarketSelector.propTypes = {
    market: PropTypes.number,
    markets: PropTypes.array,
    setMarket: PropTypes.func
}

const mapStateToProps = state => {
    return {
        market: marketSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    setMarket: market => dispatch(setMarket(market))
})

export default connect(mapStateToProps, mapDispatchToProps)(MarketSelector)