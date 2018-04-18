import Types from './types'

export default {
  setUserPermissions ({commit}, permissions) {
    commit(Types.SET_USER_PERMISSIONS, permissions)
  }
}
