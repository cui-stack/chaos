module.exports = {
    devServer: {
        proxy: {
            '^/api': {
                target: 'http://localhost:38088',
                changeOrigin: true,
                ws: false,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    assetsDir: "static",
    publicPath: './',
    productionSourceMap: false

}
