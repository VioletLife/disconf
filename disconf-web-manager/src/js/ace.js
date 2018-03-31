import GlobalEnv from './global_env'

export default class ACELoader {
  static defaultOptions () {
    return {
      el: 'editor',
      ready: function () {

      }
    }
  }

  /**
   *
   * @param options
   */
  static create (options) {
    let mergeOptions = _.merge(ACELoader.defaultOptions(), options)
    ACELoader.loaderScripts(function () {
      let editor = ace.edit(mergeOptions.el)
      let PropertiesMode = ace.require('ace/mode/properties').Mode
      editor.session.setMode(new PropertiesMode())
      mergeOptions.ready(editor)
    })
  }

  static loaderScripts (callback) {
    let serverUrl = GlobalEnv.env().serverUrl
    $.getScript(serverUrl + 'static/ace/ace.js', function () {
      $.getScript(serverUrl + 'static/ace/mode-properties.js', function () {
        callback()
      })
    })
  }
}
