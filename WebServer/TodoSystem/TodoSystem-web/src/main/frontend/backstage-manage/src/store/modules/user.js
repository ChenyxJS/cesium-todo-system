import {login,loginBySmsCode, logout, getUserInfo, getUserRoleList, getOperatorInfo ,getUserOrganizationList,getLoginRoleInfo} from '@/api/user'
import {getToken, setToken, removeToken} from '@/utils/cookie-token'
import {getRole, setRole, removeRole} from '@/utils/cookie-role'

const state = {
  token: getToken(),
  userInfo: null,
  //所属运营商信息
  operatorInfo: null,
  //登陆角色信息
  roleInfo:null,
  roles: [],
  organizations:[]

}

const mutations = {
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USER_INFO: (state, userInfo) => {
    state.userInfo = userInfo
  },
  SET_OPERATOR_INFO: (state, operatorInfo) => {
    state.operatorInfo = operatorInfo
  },
  SET_ROLES: (state, roles) => {
    state.roles = roles
  },
  SET_ORGANIZATIONS: (state, organizations) => {
    state.organizations = organizations
  },
  SET_ROLE_INFO: (state, roleInfo) => {
    state.roleInfo = roleInfo
  },
}

const actions = {
  // user login
  login({commit}, userInfo) {
    const {username, password} = userInfo;
    return new Promise((resolve, reject) => {
      login({account: username.trim(), password: password}).then(response => {
        let data = response;
        if (data.success) {
          let token = new Date().getTime() + 'admin_token';
          commit('SET_TOKEN', token);
          const {object} = data;
          commit('SET_USER_INFO', object);
          setToken(token);
          resolve()
        } else {
          reject(data)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  loginBySmsCode({commit}, userInfo) {
    const {phone, smsCode} = userInfo;
    return new Promise((resolve, reject) => {
      loginBySmsCode({phone:phone, smsCode: smsCode}).then(response => {
        let data = response;
        if (data.success) {
          let token = new Date().getTime() + 'admin_token';
          commit('SET_TOKEN', token);
          const {object} = data;
          commit('SET_USER_INFO', object);
          setToken(token);
          resolve()
        } else {
          reject(data)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取登录的人员信息
  getInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      getUserInfo().then(response => {
        let data = response;
        if (data.success) {
          const {object} = data;
          commit('SET_USER_INFO', object);
          resolve(object)
        } else {
          reject(data.failReasonShow)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  //获取所属运营商信息
  getOperatorInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      getOperatorInfo().then(response => {
        let data = response;
        if (data.success) {
          const {object} = data;
          commit('SET_OPERATOR_INFO', object);
          resolve(object)
        } else {
          reject(data.failReasonShow)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  //获取登陆角色信息
  getLoginRoleInfo({commit, state}) {
    return new Promise((resolve, reject) => {
      getLoginRoleInfo().then(response => {
        let data = response;
        if (data.success) {
          const {object} = data;
          commit('SET_ROLE_INFO', object);
          resolve(object)
        } else {
          reject(data.failReasonShow)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },


  // 获取登录人员权限列表
  getRoles({commit, state},orgId) {
    return new Promise((resolve, reject) => {
      getUserRoleList({orgId:orgId}).then(response => {
        let data = response;
        console.log("getRoles",data)
        if (data.success) {
          const root = data.root || [];
          //如果只有一个角色自动登录
          if(root.length === 1){
            setRole(root[0].roleId);
          }
          commit('SET_ROLES', root);
          resolve(root)
        } else {
          reject(data.failReasonShow)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取登录人员机构列表
  getOrganizations({commit, state}) {
    return new Promise((resolve, reject) => {
      getUserOrganizationList().then(response => {
        let data = response;
        if (data.success) {
          const root = data.root || [];
          // //如果只有一个角色自动登录
          if(root.length === 1){
            setRole(root[0].roleId);
          }
          commit('SET_ORGANIZATIONS', root);
          resolve(root)
        } else {
          reject(data.failReasonShow)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({commit, state}) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        commit('SET_TOKEN', null);
        commit('SET_USER_INFO', null);
        removeToken();
        removeRole();
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({commit}) {
    return new Promise(resolve => {
      commit('SET_TOKEN', null);
      commit('SET_USER_INFO', null);
      removeToken();
      removeRole();
      resolve()
    })
  },
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
