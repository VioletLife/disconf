// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import 'es6-promise/auto'
import ElementUi from 'element-ui'
import './css/base.css'
import 'element-ui/lib/theme-chalk/index.css'

import App from './App'
import router from './router'
import $ from 'jquery'
import _ from 'lodash'
import * as Global from './js/global'
import store from './store/store'
/**
 * 导入全局配置
 */
import './js/global_env'


window.$ = window.jQuery = $
window._ = _
window.__GLOBAL__ = Global

Vue.use(ElementUi)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: {App}
})
