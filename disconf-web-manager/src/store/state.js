import GlobalEnv from '../js/global_env'

export default {
  htmlPath: {
    password: GlobalEnv.env().serverUrl + 'static/html/modifypassword.html',
    app: GlobalEnv.env().serverUrl + 'static/html/newapp.html',
    appConfigItem: GlobalEnv.env().serverUrl + 'static/html/newconfig_item.html',
    appConfigFile: GlobalEnv.env().serverUrl + 'static/html/newconfig_file.html',
    appLogin: GlobalEnv.env().serverUrl + 'static/html/login.html',
    appPageList: GlobalEnv.env().serverUrl + 'static/html/main.html',
    fileDownload: GlobalEnv.env().serverUrl + 'api/web/config/download/'
  }
}
