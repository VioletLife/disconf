import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import AddConfFile from '@/components/AddConfFile'
import EditConfFile from '@/components/EditConfFile'
import AddConfItem from '@/components/AddConfItem'
import EditConfItem from '@/components/EditConfItem'
import AddApp from '@/components/AddApp'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
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
    }
  ]
})
