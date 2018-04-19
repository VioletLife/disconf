import GlobalEnv from './global_env'
import $ from 'jquery'
import {Message} from 'element-ui'
import State from '../store/state'

export default class Utils {
  static ajax(options) {
    options.url = GlobalEnv.env().serverAPIUrl + options.url
    let rawSuccess = options.success
    options.success = (response) => {
      if (response && response.message && response.message.global && response.message.global === '请登录系统') {
        Message.error(response.message.global)
        setTimeout(function () {
          window.location.href = State.htmlPath.appLogin
        }, 500)
      }
      else {
        rawSuccess(response)
      }
    }
    return $.ajax(options)
  }
}
