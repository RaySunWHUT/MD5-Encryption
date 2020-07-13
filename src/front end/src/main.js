import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import Qs from 'qs'
import './registerServiceWorker'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { Button, Message, Input, Loading } from 'element-ui'

Vue.config.productionTip = false;

Vue.use(VueAxios, axios);

Vue.use(ElementUI);

Vue.use(Loading.directive);


Vue.prototype.$loading = Loading.service;


// 注释掉, 每次进入页面的会自动出现 message 提示
// Vue.use(Message);
Vue.prototype.$message = Message;

new Vue({

    router,
    store,
    render: h => h(App)

}).$mount('#app')