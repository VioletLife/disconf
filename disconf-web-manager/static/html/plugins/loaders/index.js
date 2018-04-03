(function (window) {
  var globalEnv = '_GLOBAL_ENV_'
  var serverUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverUrl + 'static/html/'
  var originServerUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverUrl
  var serverAPIUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverAPIUrl
  var stylesFiles = [
    // 'dep/bootstrap/css/bootstrap.css',
    // 'dep/jquery-ui-1.10.4.custom/css/ui-lightness/jquery-ui-1.10.4.custom.css',
    'assets/css/project.css',
    'plugins/element-ui/lib/theme-chalk/index.css'
  ]

  var scriptFiles = [
    'plugins/vue/dist/vue.min.js',
    'plugins/element-ui/lib/index.js',
    'assets/js/jquery-1.11.0.js',
    'dep/jquery-ui-1.10.4.custom/js/jquery-ui-1.10.4.custom.js',
    'dep/bootstrap/js/bootstrap.js',
    'assets/js/util.js',
    'assets/js/common.js'
  ]

  var loadStyles = function () {
    var allStyleContent = ''
    stylesFiles.forEach(function (value) {
      var styleElement = '<link rel="stylesheet" href="' + serverUrl + value + '" />'
      allStyleContent += styleElement
    })
    document.write(allStyleContent)
    initLoadScripts(scriptFiles)
  }
  /**
   * 加载页面基本CSS文件
   */

  /**
   * 加载页面基本JS文件
   */
  var initLoadScripts = function (scriptPaths) {
    var allScriptContent = ''
    scriptPaths.forEach(function (value) {
      var scriptElement = '<script src="' + serverUrl + value + '"></script>'
      allScriptContent += scriptElement
    })
    document.write(allScriptContent)
  }

  var loadScripts = function (scriptPaths) {
    scriptPaths.forEach(function (value) {
      var scriptElement = document.createElement('script')
      scriptElement.src = serverUrl + value
      document.body.appendChild(scriptElement)
    })
  }

  loadStyles()
  var _disconf = function () {
    var self = this
    var globalConf = {
      scriptPaths: [],
      completed: function () {

      }
    }
    self.ready = function ({scriptPaths = [], completed}) {
      globalConf.scriptPaths = scriptPaths
      globalConf.completed = completed
    }
    self.loadScripts = loadScripts
    self.conf = globalConf
    self.serverUrl = serverUrl
    self.serverAPIUrl = serverAPIUrl
    self.originServerUrl = originServerUrl
    self.ajax = function (options) {
      options.url = self.serverAPIUrl + options.url
      if (window.$ && window.jQuery) {
        window.$.ajax(options)
      }
    }
  }
  var disconfObject = new _disconf()
  window.disconf = window.disconf || disconfObject
  window.addEventListener('DOMContentLoaded', function () {
    window.disconf.loadScripts(window.disconf.conf.scriptPaths)
    if (window.disconf.conf.completed) {
      window.disconf.conf.completed()
    }
  }, false)
})(window)
