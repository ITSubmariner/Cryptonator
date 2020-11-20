const merge = require('webpack-merge');
const common = require('./webpack.common.js');
const { CleanWebpackPlugin } = require('clean-webpack-plugin/dist/clean-webpack-plugin');
const path = require('path');

module.exports = merge(common, {
    mode: 'production',
    plugins: [
        new CleanWebpackPlugin()
    ],
    output: {
        path: path.join(__dirname, "src", 'main', 'resources', "static", "js"),
        filename: "[name].js"
    }
});