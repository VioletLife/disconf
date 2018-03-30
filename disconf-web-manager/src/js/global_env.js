let globalEnv = {
  serverUrl: '/'
}
let globalEnvVar = '_GLOBAL_ENV_'
if (window) {
  window.localStorage.setItem(globalEnvVar, JSON.stringify(globalEnv))
}
export default class GlobalEnv {
  static env () {
    return globalEnv
  }
}
