module.exports = {
    // baseUrl: 'https://xxx.cn',
    publicPath:'./',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/finance':{
                target:'http://financial.dev.bigdatacd.com:9000',
                changeOrigin:true,
                pathRewrite:{
                    '/finance':'/finance'
                }
            }
        }
    },
    configureWebpack:{
        externals:{
            'BMap':'BMap'
        }
    }
}