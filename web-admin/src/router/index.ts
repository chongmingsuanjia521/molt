import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/dashboard',
    name: 'Dashboard',
    component: () => import('../views/Dashboard.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/test-dropdown',
    name: 'TestDropdown',
    component: () => import('../views/TestDropdown.vue')
  },
  {
    path: '/simple-test',
    name: 'SimpleTest',
    component: () => import('../views/SimpleTest.vue')
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, _from, next) => {
  const token = localStorage.getItem('token')
  
  // 如果访问需要认证的页面但没有token，跳转到登录页
  if (to.meta.requiresAuth && !token) {
    next('/login')
    return
  }
  

  
  // 其他情况正常访问
  next()
})

export default router
