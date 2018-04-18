import Types from './types'

export default {
  [Types.SET_USER_PERMISSIONS] (state, payload) {
    state.currentPermissions = payload
  }
}
