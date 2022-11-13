const getters = {
  sidebar: state => state.app.sidebar,
  size: state => state.app.size,
  device: state => state.app.device,
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  token: state => state.user.token,
  userInfo: state => state.user.userInfo,
  operatorInfo: state => state.user.operatorInfo,
  roles: state => state.user.roles,
  permission_views: state => state.permission.views,
  permission_routes: state => state.permission.routes,
  permission_buttons: state => state.permission.buttons,
  organizations:state => state.user.organizations,
  roleInfo:state => state.user.roleInfo,

}
export default getters
