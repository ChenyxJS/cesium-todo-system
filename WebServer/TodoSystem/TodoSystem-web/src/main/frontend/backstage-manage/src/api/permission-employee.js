import request from '@/utils/request'

// 获取系统人员列表
export function getEmployeeList(data) {
  return request({
    url: '/admin/employee/selectByExample',
    method: 'post',
    data
  })
}

// 插入一个系统人员
export function insertEmployee(data) {
  return request({
    url: '/admin/employee/add',
    method: 'post',
    data
  })
}

// 更新一个系统人员
export function updateEmployee(data) {
  return request({
    url: '/admin/employee/update',
    method: 'post',
    data
  })
}

// 删除一个系统人员
export function deleteEmployee(data) {
  return request({
    url: '/admin/employee/delete',
    method: 'post',
    data
  })
}

// 给系统人员配置角色
export function setEmployeeRoles(data) {
  return request({
    url: '/admin/employeeRole/batchAdd',
    method: 'post',
    data
  })
}

