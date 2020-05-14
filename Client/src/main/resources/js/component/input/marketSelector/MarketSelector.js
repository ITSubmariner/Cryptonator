import React from "react"
import axios from "axios"
import { connect } from "react-redux"
import { setMarket } from "Js/store/input/action"
import { marketSelector } from "Js/store/input/selector"
import PropTypes from "prop-types"
import "Css/input.css"


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

    getMarkets() {
        axios.get("/market").then(
            response => {this.setState({markets: response.data})},
            error => {}
        )
    }

    renderMarket(market) {
        return (
            <option value={market.name} className={market.status ? "statusOnline" : "statusOffline"} key={market.id}>
                {market.name}
            </option>
        )
    }

    onMarketChange(event) {
        this.props.setMarket(event.target.value)
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

}

MarketSelector.propTypes = {
    market: PropTypes.string,
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