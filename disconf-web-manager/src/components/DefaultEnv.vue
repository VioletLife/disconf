<template>
  <div id="defaultEnv" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>基础设置</el-breadcrumb-item>
          <el-breadcrumb-item>基础环境</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2" style="text-align: right;">
        <span>环境名称：</span>
      </el-col>
      <el-col :span="6">
        <el-input v-model="envName" placeholder="请输入环境名称" size="small"></el-input>
      </el-col>
      <el-col :span="4">&nbsp;</el-col>
      <el-col :span="12" style="display: flex;justify-content: flex-end;">
        <el-button type="primary" @click="searchDefaultEnv">查询</el-button>
        <el-button type="primary" @click="addNewDefaultEnv">添加</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px;">
      <el-col :span="24">
        <el-table
          :data="defaultEnvData"
          border
          style="width:100%"
        >
          <el-table-column
            prop="envName"
            label="环境名称"
            width="220">
          </el-table-column>
          <el-table-column
            prop="envComments"
            label="环境说明">
          </el-table-column>
          <el-table-column
            label="操作"
            width="120"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editDefaultEnv(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deleteDefaultEnv(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <page-sequence :data="page" @pageChange="pageChange" style="margin-top: 16px;"></page-sequence>
      </el-col>
    </el-row>
    <el-dialog :title="title" :visible.sync="dialogAppEnvDefaultVisible">
      <el-form :model="envDefaultForm" :rules="envDefaultFormRules" ref="envDefaultFormRef">
        <el-form-item label="环境名称" label-width="100px" prop="envName">
          <el-input v-model="envDefaultForm.envName" placeholder="请输入环境名称"></el-input>
        </el-form-item>
        <el-form-item label="环境说明" label-width="100px">
          <el-input v-model="envDefaultForm.envComments" placeholder="请输入环境说明"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAppEnvDefaultVisible=false">取 消</el-button>
        <el-button type="primary" @click="confirmAppEnvDefaultDialog('envDefaultFormRef')">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'DefaultEnv',
    data () {
      return {
        envName: '',
        isEditMode: false,
        title: '添加应用环境信息',
        currentSelectedEnv: {},
        defaultEnvData: [],
        page: {
          allCount: 0,
          pageNumber: 1,
          pageSize: 50
        },
        addVersionFormDialogVisible: false,
        dialogAppEnvDefaultVisible: false,
        envDefaultForm: {
          envName: '',
          envComments: ''
        },
        envDefaultFormRules: {
          envName: [
            {required: true, message: '请输入应用环境名称', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    mounted () {
      this.searchDefaultEnv()
    },
    methods: {
      searchDefaultEnv () {
        let vmSelf = this
        Utils.ajax({
          url: 'api/env/page/list',
          data: {
            envName: this.envName,
            pageNumber: this.page.pageNumber,
            pageSize: this.page.pageSize
          },
          success: function (response) {
            vmSelf.defaultEnvData = response.result.records
            vmSelf.page.allCount = response.result.total
          }
        })
      },
      addNewDefaultEnv () {
        this.dialogAppEnvDefaultVisible = true
        this.title = '添加应用环境信息'
        this.isEditMode = false
        this.resetEnvForm()
      },
      editDefaultEnv (row) {
        this.isEditMode = true
        this.currentSelectedEnv = row
        this.dialogAppEnvDefaultVisible = true
        this.title = '更新应用环境信息'
        this.envDefaultForm.envName = row.envName
        this.envDefaultForm.envComments = row.envComments
      },
      deleteDefaultEnv (row) {
        let vmSelf = this
        const h = this.$createElement
        this.$msgbox({
          title: '删除确认',
          message: h('div', null, ['确定删除默认环境', h('span', {style: {color: 'red'}}, row.envName)]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              Utils.ajax({
                url: 'api/env/default/delete',
                data: {
                  envId: row.id
                },
                success: function (response) {
                  vmSelf.$message({
                    type: 'success',
                    message: '已删除'
                  })
                  vmSelf.searchDefaultEnv()
                }
              })
            }
          }
        })
      },
      pageChange (pageNumber) {
        this.page.pageNumber = pageNumber
        this.searchDefaultEnv()
      },
      resetEnvForm () {
        this.envDefaultForm.envName = ''
        this.envDefaultForm.envComments = ''
      },
      confirmAppEnvDefaultDialog (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let {envName, envComments} = this.envDefaultForm
            if (this.isEditMode) {
              Utils.ajax({
                url: 'api/env/default/update',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify({envName, envComments, id: this.currentSelectedEnv.id}),
                success: function (response) {
                  if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                    vmSelf.$message({
                      type: 'error',
                      message: response.message.status.message
                    })
                  } else {
                    vmSelf.dialogAppEnvDefaultVisible = false
                    vmSelf.isEditMode = false
                    vmSelf.$message({
                      type: 'success',
                      message: '默认环境更新成功'
                    })
                    vmSelf.searchDefaultEnv()
                    vmSelf.resetEnvForm()
                    vmSelf.title = '添加应用环境信息'
                  }
                }
              })
            } else {
              Utils.ajax({
                url: 'api/env/default/create',
                type: 'post',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify({envName, envComments}),
                success: function (response) {
                  if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                    vmSelf.$message({
                      type: 'error',
                      message: response.message.status.message
                    })
                  } else {
                    vmSelf.dialogAppEnvDefaultVisible = false
                    vmSelf.$message({
                      type: 'success',
                      message: '默认环境添加成功'
                    })
                    vmSelf.searchDefaultEnv()
                    vmSelf.resetEnvForm()
                  }
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
  #defaultEnv
    width 100%
</style>
