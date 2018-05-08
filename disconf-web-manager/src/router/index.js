import Vue from 'vue'
import Router from 'vue-router'
import AddConfFile from '@/components/AddConfFile'
import EditConfFile from '@/components/EditConfFile'
import AddConfItem from '@/components/AddConfItem'
import EditConfItem from '@/components/EditConfItem'
import AddApp from '@/components/AddApp'
import Index from '@/components/Index'
import ModifyPassword from '@/components/ModifyPassword'
import UserPermission from '@/components/UserPermission'
import UserRole from '@/components/UserRole'
import Department from '@/components/Department'
import User from '@/components/User'
import AddUser from '@/components/AddUser'
import LookUser from '@/components/LookUser'
import DefaultEnv from '@/components/DefaultEnv'
import Login from '@/components/Login'
import Utils from '../js/utils'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: {
        name: 'Login'
      }
    },
    {
      path: '/conf/file/add',
      name: 'AddConfFile',
      component: AddConfFile
    },
    {
      path: '/conf/file/edit',
      name: 'EditConfFile',
      component: EditConfFile
    },
    {
      path: '/conf/item/add',
      name: 'AddConfItem',
      component: AddConfItem
    },
    {
      path: '/conf/item/edit',
      name: 'EditConfItem',
      component: EditConfItem
    },
    {
      path: '/app/add',
      name: 'AddApp',
      component: AddApp
    },
    {
      path: '/modify/password',
      name: 'ModifyPassword',
      component: ModifyPassword
    },
    {
      path: '/auth/permission',
      name: 'UserPermission',
      component: UserPermission
    },
    {
      path: '/auth/role',
      name: 'UserRole',
      component: UserRole
    },
    {
      path: '/org/department',
      name: 'Department',
      component: Department
    },
    {
      path: '/account/user',
      name: 'User',
      component: User
    },
    {
      path: '/account/user/create',
      name: 'AddUser',
      component: AddUser
    },
    {
      path: '/account/user/edit',
      name: 'EditUser',
      component: AddUser
    },
    {
      path: '/account/user/look',
      name: 'LookUser',
      component: LookUser
    },
    {
      path: '/basic/default/env',
      name: 'DefaultEnv',
      component: DefaultEnv
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})

const validateClientState = (to, from, next) => {
  console.info(router)
  if (window) {
    /**
     * 表示浏览器端
     */
    let clientInfoKey = '__CLIENT__INFO__'
    let dispatchMethod = 'setUserPermissions'
    let clientInfoUserId = '__CLIENT__INFO__ID__'
    let userId = window.localStorage.getItem(clientInfoUserId)
    if (userId) {
      userId = window.parseInt(userId)
      let userInformation = window.localStorage.getItem(clientInfoKey)
      if (userInformation) {
        userInformation = JSON.parse(userInformation)
        router.app.$store.dispatch(dispatchMethod, userInformation)
      } else {
        Utils.ajax({
          url: 'api/account/user/detail',
          data: {
            userId: userId
          },
          success: function (response) {
            if (response && response.result) {
              router.app.$store.dispatch(dispatchMethod, response.result)
              window.localStorage.setItem(clientInfoKey, JSON.stringify(response.result))
            }
          }
        })
      }
    }
  }
}
router.beforeEach((to, from, next) => {
  validateClientState(to, from, next)
  next()
})

export default router
