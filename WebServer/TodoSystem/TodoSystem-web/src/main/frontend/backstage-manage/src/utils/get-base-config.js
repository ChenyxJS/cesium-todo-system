import { baseConfig } from '@/settings'
import store from '@/store'

export function getBaseUrl() {
  if (process.env.NODE_ENV === 'development') {
    return process.env.VUE_APP_BASE_API
  } else {
    if (baseConfig.mode === 'history') {
      const replacePath = store.getters.replacePath
      // 这个写法有一个限制：就是部署路径存在项目名时，项目名不能和任何一个path有包含关系
      const arr = location.href.split(`${replacePath}`)
      if (baseConfig.projectName !== '') {
        return `${arr[0]}/${baseConfig.projectName}`
      } else {
        return arr[0]
      }
    } else {
      const arr = location.href.split('#')
      // return arr[0].replace(baseConfig.replaceUrl, '').substring(0, arr[0].length - 1)
      return 'https://swapi.jtexplorer.com'
    }
  }
}

export function getFileBaseUrl() {
  if (process.env.NODE_ENV === 'development') {
    return process.env.VUE_APP_BASE_API
  } else {
    return 'https://swfile.jtexplorer.com'
  }
}

export function setReplace(replace) {
  if (baseConfig.mode === 'history') {
    store.dispatch('path/changeReplacePath', replace.fullPath)
  }
}
