/* global VISITOR:true */
(function ($) {
  var globalEnv = '_GLOBAL_ENV_'
  var serverUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverUrl + 'static/html/'
  var originServerUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverUrl
  var serverAPIUrl = JSON.parse(window.localStorage.getItem(globalEnv)).serverAPIUrl
    // 登出
  $('#signout').on('click', function () {
    $.ajax({
      type: 'GET',
      url: serverAPIUrl + 'api/account/signout'
    }).done(function (data) {
      if (data.success === 'true') {
        VISITOR = {}
        getSession()
      }
    })
  })
})(jQuery)
