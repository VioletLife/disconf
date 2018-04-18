import Vue from 'vue'

/**
 * 用于权限控制
 * 使用方法:
 * <a v-validate-permission='moduleName.permissionName'>操作按钮</a>
 *  在登陆成功后，获取当前用户的操作权限,之后的功能页面都可以使用该指令进行全
 *  权限处理。如果不存在响应的权限,则操作按钮隐藏
 */
Vue.directive('validate-permission', {
  bind: function (el, binding, vnode) {
    console.info(vnode)
    if (binding.value) {
      let permissions = vnode.context.$store.getters.permissions
      let shouldDisplay = false
      for (let permission of Object.keys(permissions)) {
        if (permission === binding.value) {
          shouldDisplay = true
          break
        }
      }
      if (!shouldDisplay) {
        el.parentNode.removeChild(el)
      }
    }
  }
})
