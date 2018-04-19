<template>
  <div id="userPermission" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>权限设置</el-breadcrumb-item>
          <el-breadcrumb-item>权限管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2" style="text-align: right;">
        <span>权限名称：</span>
      </el-col>
      <el-col :span="6">
        <el-input v-model="permissionName" placeholder="请输入权限名称" size="small"></el-input>
      </el-col>
      <el-col :span="4">&nbsp;</el-col>
      <el-col :span="12" style="display: flex;justify-content: flex-end;">
        <el-button type="primary" @click="searchPermission">查询</el-button>
        <el-button type="primary" @click="addNewPermission">添加</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px;">
      <el-col :span="24">
        <el-table
          :data="permissionData"
          border
          style="width:100%"
        >
          <el-table-column
            prop="permissionCode"
            label="权限编码"
            width="220">
          </el-table-column>
          <el-table-column
            prop="permissionName"
            label="权限名称"
            width="220">
          </el-table-column>
          <el-table-column
            prop="permissionComments"
            label="权限说明"
          >
          </el-table-column>
          <el-table-column
            label="操作"
            width="120"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editPermission(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deletePermission(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <page-sequence :data="page" @pageChange="pageChange" style="margin-top: 16px;"></page-sequence>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="addPermissionDialog" title="新增权限">
      <el-form :model="permissionForm" :rules="permissionFormRules" ref="permissionFormRef">
        <el-form-item label="权限编码：" :label-width="formLabelWidth" prop="permissionCode">
          <el-input v-model="permissionForm.permissionCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限名称" :label-width="formLabelWidth" prop="permissionName">
          <el-input v-model="permissionForm.permissionName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限说明" :label-width="formLabelWidth" prop="permissionComments">
          <el-input
            type="textarea"
            :rows="3"
            placeholder="配置说明"
            v-model="permissionForm.permissionComments">
          </el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeAddPermissionDialog">取 消</el-button>
        <el-button type="primary" @click="confirmAddPermissionDialog('permissionFormRef')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'UserPermission',
    data() {
      return {
        permissionForm: {
          permissionCode: '',
          permissionName: '',
          permissionComments: ''
        },
        permissionFormRules: {
          permissionCode: [
            {required: true, message: '请填写权限编码', trigger: 'blur'},
            {min: 1, max: 50, message: '长度在 1 到 50字符', trigger: 'blur'}
          ],
          permissionName: [
            {required: true, message: '请填写权限名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20字符', trigger: 'blur'}
          ]
        },
        formLabelWidth: '100px',
        addPermissionDialog: false,
        addPermissionDialogForm: {},
        addPermissionDialogOpenMode: 'ADD',
        permissionName: '',
        permissionData: [],
        page: {
          allCount: 0,
          pageNumber: 1,
          pageSize: 50
        }
      }
    },
    mounted() {
      this.searchPermission()
    },
    methods: {
      searchPermission() {
        let vmSelf = this
        let params = {
          permissionName: this.permissionName,
          pageNumber: this.page.pageNumber,
          pageSize: this.page.pageSize
        }
        Utils.ajax({
          type: 'GET',
          url: 'api/auth/permission/list',
          dataType: 'json',
          data: params,
          success: function (response) {
            if (response.result) {
              vmSelf.page.allCount = response.result.total
              vmSelf.permissionData = response.result.records
            }
          }
        })
      },
      addNewPermission() {
        this.addPermissionDialog = true
      },
      pageChange(pageNumber) {
        this.page.pageNumber = pageNumber
        this.searchPermission()
      },
      editPermission() {

      },
      deletePermission() {

      },
      closeAddPermissionDialog() {
        this.addPermissionDialog = false
        this.resetAddPermissionDialog()
      },
      resetAddPermissionDialog() {
        this.permissionForm.permissionCode = ''
        this.permissionForm.permissionName = ''
        this.permissionForm.permissionComments = ''
      },
      confirmAddPermissionDialog(formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let permissionAdd = {
              permissionCode: this.permissionForm.permissionCode,
              permissionName: this.permissionForm.permissionName,
              permissionComments: this.permissionForm.permissionComments
            }
            Utils.ajax({
              type: 'POST',
              url: 'api/auth/permission/create',
              data: JSON.stringify(permissionAdd),
              dataType: 'json',
              contentType: 'application/json;charset=utf-8',
              success: function (response) {
                if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                  vmSelf.$message({
                    type: 'error',
                    message: response.message.status.message
                  })
                } else {
                  vmSelf.addPermissionDialog = false
                  vmSelf.resetAddPermissionDialog()
                  vmSelf.$message({
                    type: 'success',
                    message: '权限添加成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.searchPermission()
                  })
                }
              }
            })
          }
        })
      }
    },
    computed: {}
  }
</script>
<style lang="stylus">
  #userPermission
    width 100%
</style>
