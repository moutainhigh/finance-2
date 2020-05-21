module.exports = {
    // baseUrl: 'https://xxx.cn',
    publicPath:'./',
    productionSourceMap: false,
    devServer: {
        proxy: {
            '/finance':{
                target:'http://192.168.0.75:9001',//http://192.168.0.75:9001 //http://financial.dev.bigdatacd.com:9000
                changeOrigin:true,
                pathRewrite:{
                    '/finance':'/finance'
                }
            },
        }
    },
    configureWebpack:{
        externals:{
            'BMap':'BMap',
            'jQuery':'$'
        }
    }
}