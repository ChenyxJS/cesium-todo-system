import Layout from '@/layout'

export const createStageRoutes = [
  {
    path: '/permission-manage',
    component: Layout,
    redirect: 'noRedirect',
    meta: { title: '权限管理', icon: 'dashboard' },
    alwaysShow: true,
    children: [
      {
        path: '/permissionRole',
        component: () => import('@/views/permission-manage/role/index'),
        name: 'permissionRole',
        meta: { title: '角色管理', noCache: true }
      },
    ]
  },
  {
    path: '/platform-manage',
    component: Layout,
    redirect: 'noRedirect',
    meta: { title: '平台设置', icon: 'dashboard' },
    alwaysShow: true,
    children: [
      {
        path: '/platformMenu',
        component: () => import('@/views/platform-manage/menu/index'),
        name: 'platformMenu',
        meta: { title: '菜单管理', noCache: true }
      }
    ]
  }
]
