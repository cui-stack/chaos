module.exports = {
    devServer: {
        proxy: {
            '/': {
                target: 'http://localhost:38089',
                changeOrigin: true,
                ws: false,
            }
        }
    },
    assetsDir: "static",
    publicPath: './',
    productionSourceMap: false

}
