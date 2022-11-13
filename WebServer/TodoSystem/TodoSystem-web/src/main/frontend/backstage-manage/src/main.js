import Vue from 'vue'

import Cookies from 'js-cookie'

import 'normalize.css/normalize.css' // a modern alternative to CSS resets

import Element from 'element-ui'
import './styles/element-variables.scss'

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import './icons' // icon
import './permission' // permission control

import * as filters from './filters' // global filters
//引入省市区街道四级选择器
import { pcaa } from 'area-data-vue';
import 'area-linkage-vue/dist/index.css';
import AreaLinkageVue from 'area-linkage-vue';
//引入echarts样式
import '@/utils/echarts/macarons.js'


Vue.prototype.$pcaa = pcaa;
Vue.use(AreaLinkageVue)



Vue.use(Element, {
  size: Cookies.get('size') || 'medium', // set element-ui default size
})

// register global utility filters
Object.keys(filters).forEach(key => {
  Vue.filter(key, filters[key])
})

Vue.config.productionTip = true;
import {getBaseUrl,getFileBaseUrl} from '@/utils/get-base-config'
import LOG from './utils/self-log'
import * as echarts from 'echarts';

Vue.prototype.LOG = LOG;
Vue.prototype.BASEURL = getBaseUrl();
Vue.prototype.BASEFILEURL = getFileBaseUrl();

Vue.prototype.$echarts = echarts;

//window.STAGE = create , docked
//create: 项目创建初期，没有接口可以对接，只能编写界面
//docked: 项目到开发阶段，有接口可以对接，对接时和打包时必须改为 docked
new Vue({
  el: '#app',
  router,
  store,
  created(){
    window.ISLOG = false;
    window.STAGE = 'docked';
  },
  render: h => h(App)
})
