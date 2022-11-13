/*
 * @Descripttion: request工具类
 * @version: 
 * @Author: Chenyx
 * @Date: 2022-10-16 21:23:00
 * @LastEditors: Chenyx
 * @LastEditTime: 2022-11-13 18:24:29
 */
import axios from "axios"
import Qs from "qs"
const service = axios.create({
    withCredentials: true,
    timeout: 600000,
    headers: { 'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8' },
    transformRequest: [function (data) {
        return Qs.stringify(data)
    }]
})

service.interceptors.request.use(
    config => {
        config.baseURL = "/api"
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

service.interceptors.response.use(
    response => {
        const res = response.data
        if (res.tip === '10001') {
            // 登录失效后
            // store.dispatch('user/resetToken').then(() => {
            //     location.reload()
            // })
        } else {
            return res
        }
        return res
    },
    error => {
        return Promise.reject(error)

    }
)

export default service