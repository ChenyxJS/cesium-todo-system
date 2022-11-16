import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/login',
    method: 'post',
    data
  })
}

export function loginBySmsCode(data) {
  return request({
    url: '/admin/loginBySmsCode',
    method: 'post',
    data
  })
}

//获取登陆人员的信息
export function getUserInfo(data) {
  return request({
    url: '/admin/getLoginEmp',
    method: 'post',
    data
  })
}

//获取所属运营商信息
export function getOperatorInfo(data) {
  return request({
    url: '/admin/getLoginOperator',
    method: 'post',
    data
  })
}

// 获取登陆用户角色信息
export function getLoginRoleInfo(data) {
  return request({
    url: '/admin/getLoginRole',
    method: 'post',
    data
  })
}



//获取登陆人员的角色列表
export function getUserRoleList(data) {
  return request({
    url: '/admin/getLoginRoleList',
    method: 'post',
    data
  })
}

//获取登陆人员的机构列表
export function getUserOrganizationList(data) {
  return request({
    url: '/admin/getLoginOperatorList',
    method: 'post',
    data
  })
}


//获取登陆人员角色的菜单权限
export function getUserRolePermission(data) {
  return request({
    url: '/admin/getLoginPer',
    method: 'post',
    data
  })
}

// 退出登录
export function logout() {
  return request({
    url: '/admin/logOut',
    method: 'post'
  })
}

// 修改登录用户密码
export function updatePassWord(data) {
  return request({
    url: '/admin/userInfo/updatePassWord',
    method: 'post',
    data
  })
}

// 修改个人信息
export function updateLoginInfo(data) {
  return request({
    url: '/admin/updateLoginInfo',
    method: 'post',
    data
  })
}

// 选择用户登陆机构
export function setLoginOrganization(data) {
  return request({
    url: '/admin/settingLoginOrganization',
    method: 'post',
    data
  })
}

// 选择用户登陆角色
export function setLoginRole(data) {
  return request({
    url: '/admin/settingLoginRole',
    method: 'post',
    data
  })
}


