let createFileItem = function ({key, value, comments}) {
  return {
    key,
    value,
    comments
  }
}

let resolve = function (fileContent) {
  let allFileItems = []
  if (fileContent && fileContent.length > 0) {
    /**
     * 解析规则:
     *  #开头的内容解析成为comments,紧随其后的有效值解析为key和value 如果紧随其后发现多个有效行数据，则认为comments为空。
     */
    let fileItemKey = ''
    let fileItemValue = ''
    let fileItemComments = ''
    fileContent = fileContent.split('\n').filter(function (value) { return !!(value && value.replace(/\s/gi, '').length > 0) })
    fileContent.forEach(function (value) {
      if (value.indexOf('#') === 0) {
        fileItemComments += value + '\n'
      } else {
        let equalsOperatorIndex = value.indexOf('=')
        fileItemKey = value.substring(0, equalsOperatorIndex)
        fileItemValue = value.slice(equalsOperatorIndex + 1)
        allFileItems.push(createFileItem({key: fileItemKey, value: fileItemValue, comments: fileItemComments}))
        fileItemKey = fileItemValue = fileItemComments = ''
      }
    })
  }
  return allFileItems
}

export {
  resolve
}
