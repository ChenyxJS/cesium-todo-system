import request from '@/utils/request'

// 查询公司列表
export function getOperatorList(data) {
  return request({
    url: '/admin/operator/selectByExample',
    method: 'post',
    data
  })
}

export function insertOperator(data) {
  return request({
    url: '/admin/operator/add',
    method: 'post',
    data
  })
}

// 更新一个运营商
export function updateOperator(data) {
  return request({
    url: '/admin/operator/update',
    method: 'post',
    data
  })
}

// 删除一个运营商
export function deleteOperator(data) {
  return request({
    url: '/admin/operator/delete',
    method: 'post',
    data
  })
}

// 给运营商配置权限
export function setOperatorPermission(data) {
  return request({
    url: '/admin/permission/settingPermissionToOperator',
    method: 'post',
    data
  })
}
