<template>
  <div id="editConfFile">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>应用中心</el-breadcrumb-item>
          <el-breadcrumb-item>我的应用</el-breadcrumb-item>
          <el-breadcrumb-item>{{appInfo.appName}}</el-breadcrumb-item>
          <el-breadcrumb-item>{{appInfo.fileKey}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24">
        <div class="app-statistics-info">
          <el-row style="height: 100%;line-height: 70px;padding-left: 18px;box-sizing: border-box;">
            <el-col :span="4">
              <span class="app-header-label">应用：</span>
              <span class="app-header-item">{{appInfo.appName}}</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">环境配置：</span>
              <span class="app-header-item">{{appInfo.envName}}</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">配置版本：</span>
              <span class="app-header-item">{{appInfo.version}}</span>
            </el-col>
            <el-col :span="5">
              <span class="app-header-label">配置名称：</span>
              <span class="app-header-item">{{appInfo.fileKey}}</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">历史版本：</span>
              <span class="app-header-item app-header-link">{{appInfo.historyNumber}}个</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">配置文件：</span>
              <span class="app-header-item app-header-link">{{appInfo.fileNumber}}个</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">配置项：</span>
              <span class="app-header-item app-header-link">{{appInfo.itemNumber}}个</span>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <el-col :span="15"><span class="app-message-warnings">正处于编辑状态</span></el-col>
        <el-col :span="9" style="text-align: right;padding-right: 10px;box-sizing: border-box;">
          <el-button type="primary" style="margin-left: 10px;" @click="addFileItem">新增配置</el-button>
          <el-button type="primary" style="margin-left: 10px;" @click="setEditorMode">{{fileEditorName}}</el-button>
          <el-button type="primary" style="margin-left: 10px;" @click="saveAllFileItems">保存配置</el-button>
          <el-button type="danger" style="margin-left: 10px;" @click="deleteConfFile">删除配置</el-button>
        </el-col>
      </el-col>
    </el-row>
    <el-row v-if="!fileEditorMode" style="margin-top: 10px;">
      <el-col :span="24">
        <el-table
          :data="fileItems"
          border
          style="width: 100%">
          <el-table-column
            label="Key"
            width="180">
            <template slot-scope="scope">
              <span>{{scope.row.key}}</span>
              <a class="disconf-icon-copy" :data-clipboard-text="scope.row.key"></a>
            </template>
          </el-table-column>
          <el-table-column
            label="Value"
            width="380">
            <template slot-scope="scope">
              <span>{{scope.row.value}}</span><a class="disconf-icon-copy" :data-clipboard-text="scope.row.value"></a>
            </template>
          </el-table-column>
          <el-table-column
            label="说明">
            <template slot-scope="scope">
              <span>{{scope.row.comments}}</span><a class="disconf-icon-copy"
                                                    :data-clipboard-text="scope.row.comments"></a>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            width="160"
          >
            <template slot-scope="scope">
              <el-button
                size="mini"
                icon="el-icon-edit"
                type="primary"
                @click="handleEdit(scope.$index, scope.row)">
              </el-button>
              <el-button
                size="mini" handleEdit
                icon="el-icon-delete"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
    </el-row>
    <el-row v-if="fileEditorMode" style="margin-top: 10px;">
      <el-col :span="24">
        <div class="disconf-ace-editor">
          <div id="mainEditor">

          </div>
        </div>
      </el-col>
    </el-row>


    <el-dialog :title="dialogFileItemTitle" :visible.sync="updateFileItemVisible">
      <el-form :model="fileItemForm" :rules="fileItemRules" ref="fileItemFormRef">
        <el-form-item label="Key" :label-width="formLabelWidth" prop="itemKey">
          <el-input v-model="fileItemForm.itemKey" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Value" :label-width="formLabelWidth" prop="itemValue">
          <el-input v-model="fileItemForm.itemValue" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="Value" :label-width="formLabelWidth" prop="itemComment">
          <el-input
            type="textarea"
            :rows="3"
            placeholder="配置说明"
            v-model="fileItemForm.itemComment">
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeUpdateFileItemDialog">取 消</el-button>
        <el-button type="primary" @click="confirmUpdateFileItemDialog('fileItemFormRef')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {
    resolve, merge, MergeOptions, createFileItemForProcess
  } from '../js/disconf/resolver'
  import ClipboardJS from 'clipboard'
  import ACELoader from '../js/ace'
  import Utils from '../js/utils'
  import GlobalEnv from '../js/global_env'

  export default {
    name: 'EditConfFile',
    data () {
      return {
        appInfo: {
          appId: -1,
          appName: '',
          envId: -1,
          envName: '',
          version: '',
          fileKey: '',
          configId: -1,
          fileNumber: 0,
          itemNumber: 0,
          historyNumber: 0
        },
        fileItemContent: '',
        dialogFileItemTitle: '修改配置内容',
        updateFileItemVisible: false,
        dialogFIleItemMode: 'Update',
        formLabelWidth: '120px',
        fileItemForm: {
          itemKey: '',
          itemValue: '',
          itemComment: ''
        },
        fileItemRules: {
          itemKey: {
            required: true,
            type: 'string',
            message: '请输入有效的键值'
          },
          itemValue: {},
          itemComment: {}
        },
        fileContent: '',
        fileEditorMode: false,
        fileEditorName: '切换至文件模式',
        fileItems: [],
        editorInstance: null
      }
    },
    mounted: function () {
      let vmSelf = this
      vmSelf.updatePageContent()
      this.$nextTick(() => {
        let vmSelf = this
        let registerClipboardEvent = new ClipboardJS('.disconf-icon-copy')
        registerClipboardEvent.on('success', function (e) {
          if (e.action === 'copy') {
            vmSelf.$message({
              message: '已复制',
              type: 'success'
            })
          }
        })
      })
      /// #/conf/file/edit?
      // appId=2&
      // appName=disconf_demo&
      // envId=1&
      // envName=rd&
      // version=1_0_0_0&
      // fileKey=autoconfig.properties

      this.appInfo = this.$route.query
      this.loadConfigHistory()
    },
    methods: {
      handleEdit (index, row) {
        console.info(row)
        this.fileItemForm.itemKey = row.key
        this.fileItemForm.itemValue = row.value
        this.fileItemForm.itemComment = row.comments
        /**
         * 显示Dialog
         * @type {boolean}
         */
        this.updateFileItemVisible = true
      },
      handleDelete (index, row) {
        let vmSelf = this
        this.$confirm(`确定删除配置Key:${row.key}`, '删除确认', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
        }).then(() => {
          let targetItem = createFileItemForProcess({
            key: row.key,
            mergeOptions: MergeOptions().REMOVE
          })
          let targetItems = [targetItem]
          vmSelf.updateFileContent(targetItems).then(() => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
        })
      },
      loaderAceEditor () {
        let vmSelf = this
        if (this.editorInstance) {
          this.editorInstance.destroy()
        }
        setTimeout(function () {
          ACELoader.create({
            el: 'mainEditor',
            ready: function (editor) {
              vmSelf.editorInstance = editor
              vmSelf.updatePageContent(editor)
            }
          })
        }, 100)
      },
      updatePageContent (editor) {
        let vmSelf = this
        Utils.ajax({
          url: 'api/web/config/' + vmSelf.$route.query.configId,
          type: 'get',
          success: function (response) {
            setTimeout(function () {
              vmSelf.fileItemContent = response.result.value
              if (editor) {
                editor.session.setValue(response.result.value)
              }
              vmSelf.fileItems = resolve(response.result.value)
            }, 100)
          }
        })
      },
      setEditorMode () {
        this.fileEditorMode = !this.fileEditorMode
        this.fileEditorName = this.fileEditorMode ? '切换至列表模式' : '切换至文件模式'
        this.$nextTick(() => {
          if (this.fileEditorMode) {
            this.loaderAceEditor()
          }
        })
      },
      loadConfigHistory () {
        let vmSelf = this
        let configId = this.$route.query.configId
        let {appId, envId, version} = this.$route.query
        Utils.ajax({
          url: 'api/web/config/statistics/file',
          data: {
            configId,
            appId,
            envId,
            version
          },
          success: function (response) {
            if (response && response.result) {
              if (response.result.history) {
                vmSelf.appInfo.historyNumber = response.result.history.length
              }
              if (response.result.configFiles) {
                vmSelf.appInfo.fileNumber = response.result.configFiles.length
              }
              if (response.result.configItems) {
                vmSelf.appInfo.itemNumber = response.result.configItems.length
              }
            }
          }
        })
      },
      closeUpdateFileItemDialog () {
        this.updateFileItemVisible = false
      },
      confirmUpdateFileItemDialog (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            /**
             * 创建处理对象
             * @type {{key, value, comments, mergeOptions}}
             */
            let targetItem = createFileItemForProcess({
              key: vmSelf.fileItemForm.itemKey,
              value: vmSelf.fileItemForm.itemValue,
              comments: vmSelf.fileItemForm.itemComment,
              mergeOptions: MergeOptions().AUTO
            })
            let targetItems = [targetItem]
            vmSelf.updateFileContent(targetItems)
          } else {
            return false
          }
        })
        this.updateFileItemVisible = false
      },
      updateFileContent (targetItems) {
        let vmSelf = this
        return new Promise((resolve, reject) => {
          /**
           * 合并配置Key
           */
          let newFileContent = merge(vmSelf.fileItemContent, targetItems).toString()
          vmSelf.updateOriginFileContent(newFileContent).then(() => {
            resolve()
          })
        })
      },
      updateOriginFileContent (originFileContent) {
        let vmSelf = this
        return new Promise((resolve, reject) => {
          /**
           * 更新数据库信息
           */
          Utils.ajax({
            url: 'api/web/config/filetext/' + vmSelf.$route.query.configId,
            type: 'PUT',
            data: {
              fileContent: originFileContent
            },
            success: function (response) {
              vmSelf.$message({
                type: 'success',
                message: '已保存!'
              })
              vmSelf.$nextTick(() => {
                /**
                 * 更新当前页面数据
                 */
                vmSelf.updatePageContent()
                resolve()
              })
            }
          })
        })
      },
      addFileItem () {
        this.dialogFileItemTitle = '新增配置内容'
        this.dialogFIleItemMode = 'Add'
        this.updateFileItemVisible = true
      },
      saveAllFileItems () {
        if (this.editorInstance) {
          this.fileItemContent = this.editorInstance.session.getValue()
        }
        this.updateOriginFileContent(this.fileItemContent).then(() => {
          this.$message({
            type: 'success',
            message: '已保存!'
          })
        })
      },
      deleteConfFile () {
        let tipsMessage = '确认删除该配置文件：'
        let vmSelf = this

        const h = this.$createElement

        this.$msgbox({
          title: '删除确认',
          message: h('div', null, [tipsMessage, h('span', {style: {color: 'red'}}, [vmSelf.appInfo.fileKey])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action, instance) {
            if (action === 'confirm') {
              Utils.ajax({
                type: 'DELETE',
                url: 'api/web/config/' + vmSelf.$route.query.configId,
                success: function () {
                  vmSelf.$message({
                    type: 'success',
                    message: '已删除!'
                  })
                  setTimeout(function () {
                    /**
                     * 导航至列表页面
                     * @type {string}
                     */
                    window.location.href = GlobalEnv.env().serverUrl + 'static/html/main.html'
                  }, 500)
                }
              })
            }
          }
        })
      }
    }
  }
</script>
<style lang="stylus">
  #editConfFile
    width 100%
    padding-left 10px
    padding-right 10px
    box-sizing border-box
    .disconf-ace-editor
      position relative
      width 100%
      height 400px
      #mainEditor
        position absolute
        width 100%
        height 100%
        min-height 400px
        border 1px solid #DCDFE6
    .app-message-warnings
      color #E6A23C
    .app-statistics-info
      height 70px
      width 100%
      background-color #F2F2F2
      span
        display inline-block
      .app-header-label
        font-size 16px
        display inline-block
        font-weight bold
      .app-header-item
        font-size 14px
        display inline-block
      .app-header-link
        color #409EFF
        opacity .8
        cursor pointer


</style>
