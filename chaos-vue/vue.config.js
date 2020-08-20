module.exports = {
    devServer: {
        proxy: {
            '^/api': {
                target: 'http://localhost:38089',
                changeOrigin: true,
                ws: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
            '^/chaos': {
                target: 'http://localhost:38088',
                changeOrigin: true,
                ws: false,
                pathRewrite: {
                    '^/chaos': ''
                }
            }
        }
    },

    assetsDir: 'static',
    publicPath: './',
    productionSourceMap: false
}
