/* eslint-disable no-lone-blocks */
import _ from 'lodash'

let createFileItem = function ({key, value, comments}) {
  return {
    key,
    value,
    comments
  }
}

let Exception = function (message) {
  let self = this
  self.message = message
}

/**
 *AUTO:如果不存在，则新增，如果存在则更新
 * ADD:新增  如果已存在，则自动覆盖，如果用户的explicit为Function，则根据用户返回的执行进行处理(参考MergeAddExplicitOptions)
 * UPDATE:更新
 * REMOVE:删除
 * @returns {{ADD: string, UPDATE: string, REMOVE: string}}
 * @constructor
 */
let MergeOptions = () => {
  return {
    ADD: {
      name: 'ADD',
      explicit: MergeAddExplicitOptions().AUTO_OVERRIDE
    },
    UPDATE: 'UPDATE',
    REMOVE: 'REMOVE',
    AUTO: 'AUTO'
  }
}

/**
 * 新增过程中的策略:
 * AUTO_OVERRIDE:自动覆盖
 * APPEND:忽略已存在值，执行追加操作
 * SKIP:自动跳过不处理
 * ERROR:打印错误信息，跳过不处理
 * ABORT:终止操作过程
 *
 * @returns {{AUTO_OVERRIDE: string, APPEND: string, SKIP: string, ERROR: string}}
 * @constructor
 */
let MergeAddExplicitOptions = () => {
  return {
    AUTO_OVERRIDE: 'AUTO_OVERRIDE',
    APPEND: 'APPEND',
    SKIP: 'SKIP',
    ERROR: 'ERROR',
    ABORT: 'ABORT'
  }
}

let convertArrayToMap = (arrays) => {
  let map = new Map()
  if (arrays) {
    arrays.forEach(function (item) {
      if (item.key) {
        map.set(item.key, item)
      }
    })
  }
  return map
}

let createFileItemForProcess = function ({key, value, comments, mergeOptions = MergeOptions().AUTO}) {
  return {key, value, comments, mergeOptions}
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
    fileContent = fileContent.split('\n').filter(function (value) {
      return !!(value && value.replace(/\s/gi, '').length > 0)
    })
    fileContent.forEach(function (value) {
      if (value.indexOf('#') === 0) {
        fileItemComments += value.slice(1) + '\n'
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

let merge = function (fileContent, targetItems) {
  let mergeOptions = MergeOptions()
  let fileItems = resolve(fileContent)
  let fileItemsMap = convertArrayToMap(fileItems)
  targetItems = validate(targetItems)
  let appendPlaceHolders = []
  let removeKeys = []
  if (fileItems && fileItems.length > 0 && targetItems && targetItems.length > 0) {
    targetItems.forEach(function (target) {
      let targetItem = fileItemsMap.get(target.key)
      let mergeObject = target
      switch (mergeObject.mergeOptions) {
        case mergeOptions.ADD.name: {
          let explicit = ''
          if (typeof mergeObject.ADD.explicit === 'string') {
            explicit = mergeObject.ADD.explicit
          }
          if (typeof mergeObject.ADD.explicit === 'function') {
            explicit = mergeObject.ADD.explicit()
          }
          explicit = (explicit || '').toUpperCase()
          let mergeAddExplicitOptions = MergeAddExplicitOptions()
          switch (explicit) {
            case mergeAddExplicitOptions.AUTO_OVERRIDE: {
              if (targetItem) {
                targetItem = _.merge(targetItem, target)
              } else {
                let cleanerObject = JSON.parse(JSON.stringify(target))
                delete cleanerObject.mergeOptions
                appendPlaceHolders.push(cleanerObject)
              }
            }
              break
            case mergeAddExplicitOptions.APPEND: {
              let cleanerObject = JSON.parse(JSON.stringify(target))
              delete cleanerObject.mergeOptions
              appendPlaceHolders.push(cleanerObject)
            }
              break
            case mergeAddExplicitOptions.SKIP: {
              /**
               * SKIP
               */
              console.info('SKIP')
            }
              break
            case mergeAddExplicitOptions.ERROR: {
              /**
               * ERROR
               */
            }
              break
            case mergeAddExplicitOptions.ABORT: {
              /**
               * ABORT
               */
              throw new Exception('MergeAddExplicitOptions.ABORT')
            }
          }
        }
          break
        case mergeOptions.UPDATE: {
          if (targetItem) {
            targetItem = _.merge(targetItem, target)
          }
        }
          break
        case mergeOptions.REMOVE: {
          removeKeys.push(target.key)
        }
          break
        case mergeOptions.AUTO: {
          if (targetItem) {
            targetItem = _.merge(targetItem, target)
          } else {
            let cleanerObject = JSON.parse(JSON.stringify(target))
            delete cleanerObject.mergeOptions
            appendPlaceHolders.push(cleanerObject)
          }
        }
          break
      }
    })
  }
  /**
   * 删除指定Key
   */
  if (removeKeys && removeKeys.length > 0) {
    removeKeys.forEach(function (removeItem) {
      fileItemsMap.delete(removeItem)
    })
  }

  /**
   * 新增配置
   */
  if (appendPlaceHolders && appendPlaceHolders.length > 0) {
    appendPlaceHolders.forEach(function (holder) {
      fileItemsMap.set(holder.key, holder)
    })
  }

  /**
   * 重建fileItems
   */
  if (fileItemsMap && fileItemsMap.size > 0) {
    let fileItemsCache = []
    fileItemsMap.forEach(function (item) {
      fileItemsCache.push(item)
    })
    fileItems = fileItemsCache
  }
  return {
    fileItems,
    toString () {
      let fileContentCache = ''
      this.fileItems.forEach(function (fileItem) {
        if (fileItem.comments && fileItem.comments.length > 0) {
          fileContentCache += `#${fileItem.comments}\n${fileItem.key}=${fileItem.value}\n\n`
        } else {
          fileContentCache += `${fileItem.key}=${fileItem.value}\n\n`
        }
      })
      return fileContentCache
    }
  }
}

let validate = (targetItems) => {
  if (targetItems) {
    return targetItems.filter(function (fileItems) {
      let {key, mergeOptions} = fileItems
      if (key) {
        if (!mergeOptions) {
          fileItems.mergeOptions = MergeOptions().AUTO
        }
        return true
      }
      return false
    })
  }
}

export {
  resolve,
  merge,
  MergeOptions,
  MergeAddExplicitOptions,
  createFileItemForProcess
}
