// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuetify from 'vuetify'
import config from './config'
import MyComponent from './components/MyComponent'
import './http';
import 'vuetify/dist/vuetify.min.css'
// qs工具注入
import qs from 'qs'
import 'element-ui/lib/theme-chalk/index.css';
import './assets/material.css'

Vue.use(Vuetify, { theme: config.theme})
Vue.use(MyComponent)
// 获取一个js对象的原型
Vue.prototype.$qs = qs;

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
