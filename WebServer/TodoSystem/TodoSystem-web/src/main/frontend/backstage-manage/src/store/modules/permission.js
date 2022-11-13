import { asyncRoutes, constantRoutes } from '@/router'
import { getUserRolePermission } from '@/api/user'
import { createStageRoutes } from '@/router/stage-create'
/**
 * Use meta.role to determine if the current user has permission
 * @param roles
 * @param route
 */
function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.includes(role))
  } else {
    return true
  }
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, roles) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(roles, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, roles)
      }
      res.push(tmp)
    }
  })

  return res
}

/**
 * 根据后台传过来的数据生成可渲染的列表数据
 * @param routes
 * @returns {Array}
 */
export function filterAsyncRoutesByPermission(routes) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if(tmp.permType === 'directory'){
      //目录
      let d = {}
      d.path = '/' + tmp.permCode
      d.name = tmp.permCode
      d.redirect = 'noRedirect'
      if(tmp.permStatus === 'Y'){
        d.alwaysShow = true
      }else{
        d.hidden = true
      }
      d.meta = {}
      d.meta.title = tmp.permName
      if(tmp.children  && tmp.children.length > 0){
        if(tmp.permPid !== 0){
          // d.component = (resolve) => require([`@/views${tmp.children[0].permUrl}`],resolve)
          d.component = (resolve) => require([`@/views${tmp.permUrl}`],resolve)

        }else{
          d.meta.icon = tmp.permIcon
          d.component = (resolve) => require([`@/layout`],resolve)
        }
        d.children = filterAsyncRoutesByPermission(tmp.children)
        res.push(d)
      }
    }else if(tmp.permType === 'menu'){
      //菜单
      let m = {}
      m.path = '/' + tmp.permCode
      m.component = (resolve) => require([`@/views${tmp.permUrl}`],resolve)
      m.name = tmp.permCode
      m.meta = {}
      m.meta.title = tmp.permName
      //m.meta.icon = tmp.permIcon
      m.meta.noCache = true
      if(tmp.permShow !== 'Y'){
        m.hidden = true
      }
      if(tmp.children  && tmp.children.length > 0 && tmp.permStatus === 'Y'){
        m.children = filterAsyncRoutesByPermission(tmp.children)
      }
      res.push(m)
      permissionRoutes.push(tmp)
    }else{
      //按钮等
      if(tmp.permStatus === 'Y' && tmp.permShow === 'Y'){
        permissionButtons.push(tmp)
      }
    }
  })
  return res
}

const permissionButtons = []
const permissionRoutes = []

const state = {
  views: [],
  routes: [],
  addRoutes: [],
  buttons:[]
}

const mutations = {
  SET_VIEWS: (state, views) => {
    state.addRoutes = views
    state.views = constantRoutes.concat(views)
  },
  SET_ROUTES: (state, routes) => {
    state.routes = routes
  },
  SET_BUTTONS: (state, buttons) => {
    state.buttons = buttons
  }
}

const actions = {
  generateRoutes({ commit }, roleId) {
    return new Promise((resolve, reject)  => {
      getUserRolePermission({
        roleId: roleId
      }).then(response => {
        let accessedRoutes
        let data = response
        if(data.success){
          accessedRoutes = filterAsyncRoutesByPermission(data.root||[])
          accessedRoutes.push({ path: '*', redirect: '/404', hidden: true })
          commit('SET_VIEWS', accessedRoutes)
          commit('SET_BUTTONS', permissionButtons)
          commit('SET_ROUTES', permissionRoutes)
          resolve(accessedRoutes)
        }else{
          reject(data)
        }
      }).catch(error => {
        reject(error)
      })
    })
  },

  setCreateStageRoutes({ commit }) {
    return new Promise((resolve, reject)  => {
      commit('SET_VIEWS', createStageRoutes)
      commit('SET_ROUTES', createStageRoutes)
      resolve(createStageRoutes)
    })
  },

  removeRoutes({commit}) {
    return new Promise(resolve => {
      commit('SET_ROUTES', []);
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
