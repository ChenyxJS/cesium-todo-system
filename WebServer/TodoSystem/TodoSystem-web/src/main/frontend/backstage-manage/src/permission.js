import router from './router'
import store from './store'
import {Message} from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import {getToken} from '@/utils/cookie-token'

import getPageTitle from '@/utils/get-page-title'
import {setReplace} from '@/utils/get-base-config'
import LOG from '@/utils/self-log'

NProgress.configure({showSpinner: false}) // NProgress Configuration

const whiteList = ['/login', '/auth-redirect']// no redirect whitelist

router.beforeEach(async (to, from, next) => {
  LOG.info('在这里 -- ')
  // start progress bar
  NProgress.start()
  document.title = getPageTitle(to.meta.title)
  const isCreateStage = window.STAGE === 'create'
  if (isCreateStage) {
    LOG.info(['isCreateStage -- ', isCreateStage])

    if (to.path === '/login') {
      next({path: '/'})
      NProgress.done()
    } else {
      const hasRoutes = store.getters.permission_routes && store.getters.permission_routes.length > 0
      if (!hasRoutes) {
        const createStageRoutes = await store.dispatch('permission/setCreateStageRoutes')
        LOG.info(['create 阶段路由 -- ', createStageRoutes])
        router.addRoutes(createStageRoutes)
        next({...to, replace: true})
      } else {
        next()
      }
    }
  } else {
    const hasToken = getToken()
    if (hasToken) {
      if (to.path === '/login') {
        next({path: '/'})
        NProgress.done()
      } else {
        const hasRoutes = store.getters.permission_routes && store.getters.permission_routes.length > 0
        LOG.info('在这里99')
        if (hasRoutes) {
          next()
        } else {
          // 如果不符合条件，说明页面刷新了
          try {
            LOG.info('在这里100')
            // 页面刷新之后：
            // 重新获取登录人员信息
            // 重新根据登录的roleId获取该角色的菜单权限
            const userInfo = await store.dispatch('user/getInfo')
            LOG.info(['登录的人员信息 -- ', userInfo])

            const roleList = await store.dispatch('user/getRoles')
            LOG.info(['登录人员的角色列表 -- ', roleList])

            const roleInfo = await store.dispatch('user/getLoginRoleInfo')
            LOG.info(['登录人员角色信息 -- ', roleInfo])

            if (!hasRoutes) {
              const accessRoutes = await store.dispatch('permission/generateRoutes', roleInfo.roleId)
              router.addRoutes(accessRoutes)
            }
            next({...to, replace: true})
          } catch (error) {
            await store.dispatch('user/resetToken')
            Message.error('您没有权限登入该系统')
            next(`/login?redirect=${to.path}`)
            NProgress.done()
          }
        }
      }
    } else {
      if (whiteList.indexOf(to.path) !== -1) {
        next()
      } else {
        // other pages that do not have permission to access are redirected to the login page.
        next(`/login?redirect=${to.path}`)
        NProgress.done()
      }
    }
  }
})

router.afterEach((to, from) => {
  setReplace(to)
  NProgress.done()
})
