import React from "react"
import { connect } from "react-redux"
import "Css/result.css"
import {resultSelector} from "../../store/selector";

class Result extends React.Component {
    constructor(props) {
        super(props)

        this.getTableFooter = this.getTableFooter.bind(this);
        this.getStatistics = this.getStatistics.bind(this);
    }

    render() {
        return (
            <div className="result">
                { this.getStatistics() }
                <table>
                    <thead>
                    <tr>
                        <th>Время покупки</th>
                        <th>Время продажи</th>
                        <th>Стоимость при покупке</th>
                        <th>Завершённость</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        this.props.result.deals.map((deal, n) => {
                            return (
                                <tr key={n}>
                                    <td>{ this.getFormattedDate(deal.start) }</td>
                                    <td>{ this.getFormattedDate(deal.end) }</td>
                                    <td>{deal.buyPrice}</td>
                                    <td>{deal.completed ? '+' : '-'}</td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                    { this.getTableFooter() }
                </table>
            </div>
        )
    }

    getTableFooter() {
        if (this.props.result.deals.length === 0) {
            return (
                <tfoot>
                <tr>
                    <td colSpan="100%">Нет данных</td>
                </tr>
                </tfoot>
            )
        }
    }

    getFormattedDate(date) {
        let pDate = new Date(date)
        return (
            <span>
                <span className="time">{ pDate.toLocaleTimeString().substring(0, 5) }</span><span className="date">{ pDate.toLocaleDateString() }</span>
            </span>
        )
    }

    getStatistics() {
        if (this.props.result.deals.length) {
            return (
                <div className="statistics">
                    <span>Общее количество сделок: { this.props.result.deals.length }</span>
                    <br/>
                    <span>Теоретическая прибыль: { this.props.result.gain }%</span>
                    <br/>
                    <span>Период проведения расчетов: { this.getFormattedDate(this.props.result.startDate) } - { this.getFormattedDate(this.props.result.endDate) }</span>
                </div>
            )
        }
    }
}

const mapStateToProps = state => {
    return {
        result: resultSelector(state)
    }
}

const mapDispatchToProps = {

}

export default connect(mapStateToProps, mapDispatchToProps)(Result)