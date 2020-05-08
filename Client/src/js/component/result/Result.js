import React from "react"
import { connect } from "react-redux"

const table = {
    border: "1px solid black"
}

class Result extends React.Component {
    render() {
        return (
            <table style={table}>
                <thead>
                <tr>
                    <th>Время покупки</th>
                    <th>Время продажи</th>
                    <th>Стоимость при покупке</th>
                    <th>Завершенность</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.result.deals.map((deal, n) => {
                        return (
                            <tr key={n}>
                                <td>{deal.start}</td>
                                <td>{deal.end}</td>
                                <td>{deal.buyPrice}</td>
                                <td>{deal.completed ? '+' : '-'}</td>
                            </tr>
                        )
                    })
                }
                </tbody>
            </table>
        )
    }
}

const mapStateToProps = state => {
    return {
        result: state.result
    }
}

const mapDispatchToProps = {

}

export default connect(mapStateToProps, mapDispatchToProps)(Result)