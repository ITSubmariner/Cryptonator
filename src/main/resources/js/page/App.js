import React, { Component} from "react";

import Input from "../component/InputForm"
import Result from "../component/ResultForm"

class App extends Component{
    render(){
        return(
            <div className="App">
                <Input />
                <Result />
            </div>
        );
    }
}

export default App