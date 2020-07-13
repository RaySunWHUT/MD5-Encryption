const webpack = require('webpack')

module.exports = {

    runtimeCompiler: true,

    devServer: {

        port: 8081 // 此处修改你想要的端口号，

    },

    pluginOptions: {

        electronBuilder: {

            builderOptions: {
                win: {
                    icon: './public/icone.ico'
                },
                mac: {
                    icon: './public/icone.ico'
                },
                productName: 'Encryption'
            }

        }

    }

}