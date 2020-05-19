module.exports = {
    // baseUrl: 'https://xxx.cn',
    publicPath:'./',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/api':{
                target:'https://xxx.cn',
                changeOrigin:true,
                pathRewrite:{
                    '/api':''
                }
            }
        }
    },
}