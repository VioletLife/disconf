import GlobalEnv from './global_env'
import $ from 'jquery'

export default class Utils {
  static ajax (options) {
    options.url = GlobalEnv.env().serverAPIUrl + options.url
    return $.ajax(options)
  }
}
