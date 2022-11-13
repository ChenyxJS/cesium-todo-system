import Cookies from 'js-cookie'

//特殊逻辑需求，多角色切换使用
const RoleKey = 'Login-Role';

export function getRole() {
  return Cookies.get(RoleKey)
}

export function setRole(role) {
  return Cookies.set(RoleKey, role)
}

export function removeRole() {
  return Cookies.remove(RoleKey)
}
