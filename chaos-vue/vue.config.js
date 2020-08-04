module.exports = {
    devServer: {
        proxy: {
            '/api': {
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
    publicPath: './'

}
