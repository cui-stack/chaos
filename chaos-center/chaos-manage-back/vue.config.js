module.exports = {
    devServer: {
        proxy: {
            '^/api': {
//                target: 'https://firepongo.tech:38088',
                target: 'http://localhost:38088',
                changeOrigin: true,
                ws: true,
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
