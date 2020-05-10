import React, {Component} from "react";
import Input from "../component/input/Input"
import Result from "../component/result/Result"
import rootReducer from "../store/reducer"
import { createStore } from "redux"
import { Provider } from "react-redux"
import { Container, CssBaseline, Grid } from "@material-ui/core"

const store = createStore(rootReducer)

export default class App extends Component{
    render(){
        return(
            <Provider store={store}>
                <Container>
                    <Grid container>
                        <Input />
                        <Result />
                    </Grid>
                </Container>
            </Provider>
        );
    }
}