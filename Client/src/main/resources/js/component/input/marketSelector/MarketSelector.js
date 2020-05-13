import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import { setMarket } from "Js/store/input/action"
import "Css/input.css"
import {marketSelector} from "../../../store/input/selector";

const statusOffline = {
    color: "red"
}

const statusOnline = {
    color: "green"
}

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
            <div>
                <span>Валютная пара</span>
                <br/>
                <select value={this.props.market} onChange={this.onMarketChange}>
                    { this.state.markets.map(market => this.renderMarket(market)) }
                </select>
            </div>
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
            <option value={market.id} className={market.status ? "statusOnline" : "statusOffline"} key={market.id}>
                {market.name}
            </option>
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