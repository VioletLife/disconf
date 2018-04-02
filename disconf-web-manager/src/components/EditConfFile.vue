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
              <span class="app-header-item app-header-link">1个</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">配置文件：</span>
              <span class="app-header-item app-header-link">1个</span>
            </el-col>
            <el-col :span="3">
              <span class="app-header-label">配置项：</span>
              <span class="app-header-item app-header-link">1个</span>
            </el-col>
          </el-row>
        </div>
      </el-col>
    </el-row>
    <el-row style="margin-top: 10px">
      <el-col :span="24">
        <el-col :span="15"><span class="app-message-warnings">正处于编辑状态</span></el-col>
        <el-col :span="9" style="text-align: right;padding-right: 10px;box-sizing: border-box;">
          <el-button type="primary" style="margin-left: 10px;" @click="setEditorMode">{{fileEditorName}}</el-button>
          <el-button type="primary" style="margin-left: 10px;">保存配置</el-button>
          <el-button type="danger" style="margin-left: 10px;">删除配置</el-button>
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
                size="mini"
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
  </div>
</template>
<script>
  import {resolve} from '../js/disconf/resolver'
  import ClipboardJS from 'clipboard'
  import ACELoader from '../js/ace'
  import Utils from '../js/utils'

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
          configId: -1
        },
        fileContent: '',
        fileEditorMode: false,
        fileEditorName: '切换至文件模式',
        fileItems: [],
        editorInstance: null
      }
    },
    mounted: function () {
      let originContent = '#权重设置\n' +
        'hint.key=123\n' +
        '#网页URL参数\n' +
        '#Baidu首页地址\n' +
        'hint.addres=http://www.baidu.com\n'
      let fileAllItems = resolve(originContent)
      this.fileItems = fileAllItems
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
    },
    methods: {
      handleEdit (row) {

      },
      handleDelete (row) {

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
              Utils.ajax({
                url: 'api/web/config/' + vmSelf.$route.query.configId,
                type: 'get',
                success: function (response) {
                  setTimeout(function () {
                    editor.session.setValue(response.result.value)
                  }, 100)
                }
              })
            }
          })
        }, 100)
      },
      setEditorMode () {
        this.fileEditorMode = !this.fileEditorMode
        this.fileEditorName = this.fileEditorMode ? '切换至列表模式' : '切换至文件模式'
        this.$nextTick(() => {
          if (this.fileEditorMode) {
            this.loaderAceEditor()
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
