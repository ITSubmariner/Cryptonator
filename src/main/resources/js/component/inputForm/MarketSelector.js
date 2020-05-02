import React, { Component } from "react"
import axios from "axios";

const statusOffline = {
    color: "red"
}

const statusOnline = {
    color: "green"
}

class MarketSelector extends Component{
    constructor(props) {
        super(props)
        this.state = {
            markets: []
        }
    }

    componentWillMount() {
        this.getMarkets()
    }

    render() {
        return (
            <select>
                {
                    this.state.markets.map(market => this.renderMarket(market))
                }
            </select>
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
            <option value={market.id} style={market.status ? statusOnline : statusOffline} key={market.id}>
                {market.name}
            </option>
        )
    }

}

export default MarketSelector