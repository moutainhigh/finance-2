module.exports = {
    baseUrl: 'https://xxx',
    publicPath:'./',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
                target:'https://xxx',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            }
        }
    },
}