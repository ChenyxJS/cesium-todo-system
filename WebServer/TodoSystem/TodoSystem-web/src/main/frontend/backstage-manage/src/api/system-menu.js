import request from '@/utils/request'

/**
 * 获取登陆人的所有权限，树形结构
 */
export function getSystemAllPermission(data) {
  return request({
    url: '/admin/permission/selectByExample',
    method: 'post',
    data
  })
}

export function insertPermission(data) {
  return request({
    url: '/admin/permission/add',
    method: 'post',
    data
  })
}

export function updatePermission(data) {
  return request({
    url: '/admin/permission/update',
    method: 'post',
    data
  })
}

export function deletePermission(data) {
  return request({
    url: '/admin/permission/delete',
    method: 'post',
    data
  })
}
