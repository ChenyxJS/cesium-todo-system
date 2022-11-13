import { createRouter, createWebHashHistory} from 'vue-router'

const routes = [
    {
        path: '/',
        component:()=>import("../views/CesiumIndex/index.vue")
    },
]
export const router = createRouter({
    history:createWebHashHistory(),
    routes:routes
})
export default router