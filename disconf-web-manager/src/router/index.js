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

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
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
    }
  ]
})
