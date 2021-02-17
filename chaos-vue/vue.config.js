module.exports = {
    devServer: {
        proxy: {
            '/': {
                target: 'http://localhost:38899',
                changeOrigin: true,
                ws: false,
            }
        }
    },
    assetsDir: "static",
    publicPath: './',
    productionSourceMap: false
}
