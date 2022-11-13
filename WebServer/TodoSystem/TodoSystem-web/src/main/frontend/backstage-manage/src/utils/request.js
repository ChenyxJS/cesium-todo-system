import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import Qs from 'qs'
import LOG from '@/utils/self-log'
import { getToken } from '@/utils/cookie-token'
import { getBaseUrl } from '@/utils/get-base-config'

const service = axios.create({
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 600000, // request timeout
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
  },
  transformRequest: [function(data) {
    // 对 data 进行任意转换处理
    return Qs.stringify(data)
  }]
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    if (store.getters.token) {
      config.headers['X-Token'] = getToken()
    }
    config.baseURL = getBaseUrl()
    return config
  },
  error => {
    // do something with request error
    LOG.error([error])
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    LOG.info([response.request.responseURL + ' --- ', res])
    if (res.tip === '10001') {
      LOG.info([response.request.responseURL + 'zaizheli  --- ', res])
      store.dispatch('user/resetToken').then(() => {
        location.reload()
      })
    } else {
      return res
    }
  },
  error => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
