import axios from 'axios'

const http = options => {
    return new Promise((resolve, reject) => {

        const defaultOptions = {};
        const newOptions = {
            ...defaultOptions,
            ...options
        };

        // headers默认传递json格式数据，这里也可以设置token，每次调用都会携带
        newOptions.headers = {
            // 'content-Type': 'application/json;charset=UTF-8',
            ...newOptions.headers
        };

        // 这里可以在调用的时候看到你的method、url、data、headers等参数
        // console.log(newOptions);
        axios({

            headers: newOptions.headers,
            method: newOptions.method,
            url: newOptions.url,
            data: newOptions.data,
            responseType: newOptions.responseType

        }).then(res => {

            // 根据返回的状态码判断，注意res返回的并不一定都是status，比如小程序就是statusCode
            if (res.status == 200) {

                // console.log(newOptions);

                // 这里我们只需要获取返回的data中的数据即可

                resolve(res.data);

            } else {

                reject(res);

            }

        }).catch(err => {

            reject(err);

        })

    })

};

export default http