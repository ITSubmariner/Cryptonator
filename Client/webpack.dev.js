const path = require('path');
const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const webpack = require("webpack");

module.exports = merge(common, {
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ],
    mode: 'development',
    devtool: 'inline-source-map',
    watch: true,
    devServer: {
        contentBase: path.join(__dirname, 'src', 'templates'),
        compress: true,
        port: 8000,
        allowedHosts: [
            'localhost:8080'
        ],
        disableHostCheck: true,
        proxy: {
            '/market': 'http://localhost:8080',
            '/calculate': 'http://localhost:8080'
        },
        hot: true,
        inline: true,
        // stats: 'errors-only',
        // clientLogLevel: 'error'
    }
});