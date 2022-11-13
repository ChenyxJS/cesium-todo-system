import request from '@/utils/request'

// 获取角色列表
export function getRoleList(data) {
  return request({
    url: 'admin/role/selectByExample',
    method: 'post',
    data
  })
}

// 插入一个角色，会返回一个RolesId
export function insertRole(data) {
  return request({
    url: '/admin/role/add',
    method: 'post',
    data
  })
}

// 更新一个角色
export function updateRole(data) {
  return request({
    url: '/admin/role/update',
    method: 'post',
    data
  })
}

// 删除一个角色
export function deleteRole(data) {
  return request({
    url: '/admin/role/delete',
    method: 'post',
    data
  })
}

// 给角色配置权限
export function setRolePermission(data) {
  return request({
    url: '/admin/permission/settingPermissionToRole',
    method: 'post',
    data
  })
}

// 获取给运营商的所有权限树
// 传 roleId 就是可以查出来全部的，根据roleHave来识别
export function getOperatePermission(data) {
  return request({
    url: '/admin/permission/selectByExample',
    method: 'post',
    data
  })
}
//配置角色小程序功能权限
export function setAppletPermission(data) {
  return request({
    url: '/admin/miniRolePermission/setting',
    method: 'post',
    data
  })
}

//查询角色小程序功能权限
export function getAppletPermission(data) {
  return request({
    url: '/admin/miniRolePermission/selectByExample',
    method: 'post',
    data
  })
}
