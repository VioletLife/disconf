import Types from './types'

export default {
  setUserPermissions ({commit}, permissions) {
    commit(Types.SET_USER_PERMISSIONS, permissions)
  },
  setApplicationLayout ({commit}, applicationLayout) {
    commit(Types.SET_APPLICATION_LAYOUT, applicationLayout)
  }
}
