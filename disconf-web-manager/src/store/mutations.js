import Types from './types'

export default {
  [Types.SET_USER_PERMISSIONS] (state, payload) {
    state.currentPermissions = payload
  },
  [Types.SET_APPLICATION_LAYOUT] (state, payload) {
    state.applicationLayout = payload
  }
}
