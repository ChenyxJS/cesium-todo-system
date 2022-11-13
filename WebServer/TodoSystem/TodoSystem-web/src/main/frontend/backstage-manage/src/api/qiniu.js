import request from '@/utils/request'

export function getToken() {
  return request({
    url: '/system/getQiNiuUploadToken',
    method: 'post',
  })
}
export function getDomain() {
  return request({
    url: '/system/systemConfig/getSystemConfigValue',
    method: 'post',
    data: {
      sycoCode: 'qny_domain'
    }
  })
}

export function getHttp() {
  let actions = ['http://up.qiniup.com','https://up.qiniup.com']
  let obj = {
    url:actions[0]
  };
  let protocolStr = document.location.protocol;
  if(protocolStr === "https:"){
    obj.url = actions[1]
  }else {
    obj.url = actions[0]
  }
  return obj
}
