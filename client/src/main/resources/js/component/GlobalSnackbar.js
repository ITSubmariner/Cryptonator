import React from "react"
import {connect} from "react-redux";
import {activeSelector, statusSelector, textSelector} from "Js/store/snackbar/selector"
import Snackbar from "@material-ui/core/Snackbar"
import Alert from "@material-ui/lab/Alert"
import {closeSnackbar} from "Js/store/snackbar/action"

class GlobalSnackbar extends React.Component{
    constructor(props) {
        super(props)

        this.close = this.close.bind(this);
    }

    close(event, reason) {
        if (reason === "timeout") {
            this.props.closeSnackbar()
        }
    }

    render() {
        return (
            <Snackbar anchorOrigin={{vertical: 'bottom', horizontal: 'right'}} open={this.props.active} autoHideDuration={5000} onClose={this.close} >
                <Alert severity={this.props.status}>
                    {this.props.text}
                </Alert>
            </Snackbar>
        )
    }
}

const mapStateToProps = state => {
    return {
        active: activeSelector(state),
        status: statusSelector(state),
        text: textSelector(state)
    }
}

const mapDispatchToProps = dispatch => ({
    closeSnackbar: () => dispatch(closeSnackbar())
})

export default connect(mapStateToProps, mapDispatchToProps)(GlobalSnackbar)