import request from '@/utils/request'

export function sendVerifyCode(data) {
  return request({
    url: '/sms/sendVerifyCode',
    method: 'post',
    data
  })
}
