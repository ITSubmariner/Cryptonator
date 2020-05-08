const path = require('path');

module.exports = {
    entry: {
        main: path.join(__dirname, 'src', 'main', 'resources', 'js', 'main.js'),
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules|bower_components)/,
                loader: "babel-loader",
                options: { presets: ["@babel/env"] }
            },
            {
                test: /\.css$/,
                use: ["style-loader", "css-loader"]
            }
        ]
    },
    /*plugins: [
        new webpack.HotModuleReplacementPlugin()
    ],*/
    resolve: {
        alias: {
            Css: path.resolve(__dirname, 'src', 'main', 'resources', 'css'),
            Js: path.resolve(__dirname, 'src', 'main', 'resources', 'js')
        },
        modules: [
            path.join(__dirname, 'src', 'main', 'resources', 'js'),
            path.join(__dirname, 'node_modules'),
        ],
    }
}