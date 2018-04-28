<template>
  <div id="accountManagement" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>开通账户</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2" style="text-align: right;line-height: 30px;">
        <span>账户名：</span>
      </el-col>
      <el-col :span="4">
        <el-input v-model="userAccount" placeholder="请输入角色名称" size="small"></el-input>
      </el-col>
      <el-col :span="2" style="text-align: right;line-height: 30px;">创建时间：</el-col>
      <el-col :span="7">
        <el-date-picker
          v-model="createTime"
          type="datetimerange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          @change="createTimeChange"
        >

        </el-date-picker>
      </el-col>
      <el-col :span="2" style="text-align: right;line-height: 30px;">所属部门：</el-col>
      <el-col :span="4">
        <el-cascader
          expand-trigger="hover"
          :options="departmentData"
          :props="{
              value:'departmentCode',
              label:'departmentName',
              children:'children'
          }"
          v-model="departmentCode"
          @change="handleDepartmentCodeChange">
        </el-cascader>
      </el-col>
      <el-col :span="3" style="display: flex;justify-content: flex-end;">
        <el-button type="primary" @click="searchUser">查询</el-button>
        <el-button type="primary" @click="addNewUser">添加</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px;">
      <el-col :span="24">
        <el-table
          :data="userData"
          border
          style="width:100%"
        >
          <el-table-column
            prop="userAccount"
            label="用户名"
            width="110">
          </el-table-column>
          <el-table-column
            prop="mobilePhone"
            label="手机号"
            width="130">
          </el-table-column>
          <el-table-column
            label="用户角色"
            width="220">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookUseRoles(scope.row)">查看详情</el-button>
            </template>
          </el-table-column>
          <el-table-column
            label="所属部门"
            width="180"
          >
            <template slot-scope="scope">
              {{scope.row.orgDepartment.departmentName}}
            </template>
          </el-table-column>
          <el-table-column
            prop="comments"
            label="备注"
          >
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
            width="180"
          >
          </el-table-column>
          <el-table-column
            label="操作"
            width="220"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookUser(scope.row)">查看</el-button>
              <el-button type="text" size="small" @click="editUser(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="resetPassword(scope.row)">重置密码</el-button>
              <el-button type="text" size="small" @click="deleteUser(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <page-sequence :data="page" @pageChange="pageChange" style="margin-top: 16px;"></page-sequence>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="lookUserRolesVisible" :title="lookUserRolesData.title">
      <template v-for="(role,index) in lookUserRolesData.currentRole.roles">
        <el-row style="margin-top: -15px;">
          <el-col :span="24" style="text-align: left;font-size: 16px;font-weight: bold;">
            {{'角色'+(index+1)+'：'+role.rolePermission.role.roleName}}
          </el-col>
        </el-row>
        <el-row v-for="permission in role.rolePermission.permissions" style="margin-top: 4px;">
          <el-col :span="24" style="text-align: left;font-size: 12px;padding-left: 50px;">
            {{'权限'+(index+1)+'：'+permission.permissionName}}
          </el-col>
        </el-row>
      </template>
    </el-dialog>

    <el-dialog :visible.sync="resetPasswordVisible" title="重置密码">
      <el-form :model="resetPasswordForm" :rules="resetPasswordFormRules" ref="resetPasswordFormRef">
        <el-form-item label="原密码：" :label-width="formLabelWidth" prop="oldPassword">
          <el-input v-model="resetPasswordForm.oldPassword" placeholder="请输入原密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码：" :label-width="formLabelWidth" prop="newPassword1">
          <el-input v-model="resetPasswordForm.newPassword1" placeholder="请输入新密码"></el-input>
        </el-form-item>
        <el-form-item label="确认密码：" :label-width="formLabelWidth" prop="newPassword2">
          <el-input v-model="resetPasswordForm.newPassword2" placeholder="请输入再次输入新密码"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeResetPasswordDialog">取 消</el-button>
        <el-button type="primary" @click="confirmResetPasswordDialog('resetPasswordFormRef')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'User',
    data () {
      return {
        lookUserRolesVisible: false,
        formLabelWidth: '100px',
        lookUserRolesData: {
          title: '',
          currentRole: {}
        },
        resetPasswordVisible: false,
        resetPasswordForm: {
          oldPassword: '',
          newPassword1: '',
          newPassword2: ''
        },
        resetPasswordFormRules: {
          oldPassword: [
            {required: true, message: '请填写原密码', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 6 到 12字符', trigger: 'blur'}
          ],
          newPassword1: [
            {required: true, message: '请填写新密码', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 6 到 12字符', trigger: 'blur'}
          ],
          newPassword2: [
            {required: true, message: '请再次输入新密码', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 6 到 12字符', trigger: 'blur'}
          ]
        },
        page: {
          allCount: 0,
          pageNumber: 1,
          pageSize: 50
        },
        userAccount: '',
        userData: [],
        createTime: null,
        departmentCode: [],
        departmentData: [],
        currentSelectedUser: null
      }
    },
    mounted () {
      this.loadDepartmentTree()
      this.searchUser()
    },
    methods: {
      closeResetPasswordDialog () {
        this.resetPasswordVisible = false
        this.resetResetPasswordForm()
      },
      confirmResetPasswordDialog (formName) {
        let vmSelf = this
        if (this.resetPasswordForm.newPassword1 === this.resetPasswordForm.newPassword2) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              let params = {
                userId: vmSelf.currentSelectedUser.userId,
                oldPassword: vmSelf.resetPasswordForm.oldPassword,
                newPassword: vmSelf.resetPasswordForm.newPassword1
              }
              Utils.ajax({
                type: 'POST',
                url: 'api/account/password/reset',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                data: JSON.stringify(params),
                success: function (response) {
                  if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                    vmSelf.$message({
                      type: 'error',
                      message: response.message.status.message
                    })
                  } else {
                    vmSelf.$message({
                      type: 'success',
                      message: '密码重置成功'
                    })
                    vmSelf.resetPasswordVisible = false
                    Utils.sleep(500).then(() => {
                      vmSelf.searchUser()
                    })
                  }
                }
              })
            }
          })
        } else {
          this.$message({
            type: 'error',
            message: '两次密码必须一致'
          })
        }
      },
      resetResetPasswordForm () {
        this.resetPasswordForm.oldPassword = ''
        this.resetPasswordForm.newPassword1 = ''
        this.resetPasswordForm.newPassword2 = ''
      },
      loadDepartmentTree () {
        let vmSelf = this
        Utils.ajax({
          url: 'api/org/department/list',
          success: function (response) {
            response.result.forEach((childrenNode) => vmSelf.removeInvalidChildrens(childrenNode))
            response.result = [{
              'id': -1,
              'departmentCode': '999',
              'departmentName': '所有部门',
              'departmentComments': '所有部门',
              'parentCode': '',
              'isOrgRoot': false,
              'isHiddenNode': true,
              'createTime': '2018-04-25 16:30:05',
              'creator': 6,
              'updateTime': '2018-04-25 16:30:06',
              'updator': 6
            }].concat(response.result)
            vmSelf.departmentData = response.result
          }
        })
      },
      removeInvalidChildrens (node) {
        if (node && node.children) {
          if (node.children && node.children.length === 0) {
            delete node.children
          } else {
            node.children.forEach((childrenNode) => this.removeInvalidChildrens(childrenNode))
          }
        }
      },
      pageChange (pageNumber) {
        this.page.pageNumber = pageNumber
        this.searchUser()
      },
      lookUser (row) {
        this.$router.push({
          path: '/account/user/look',
          query: {userId: row.userId}
        })
      },
      editUser (row) {
        this.$router.push({
          path: '/account/user/edit',
          query: {userId: row.userId}
        })
      },
      resetPassword (row) {
        this.currentSelectedUser = row
        this.resetPasswordVisible = true
      },
      deleteUser (row) {
        let vmSelf = this
        const h = this.$createElement
        this.$msgbox({
          title: '删除确认',
          message: h('div', null, ['确定删除账户：', h('span', {style: {color: 'red'}}, [row.userAccount])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              Utils.ajax({
                type: 'GET',
                url: 'api/account/delete',
                data: {
                  userId: row.userId
                },
                dataType: 'json',
                success: function (response) {
                  vmSelf.$message({
                    type: 'success',
                    message: '删除成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.searchUser()
                  })
                }
              })
            }
          }
        })
      },
      searchUser () {
        let vmSelf = this
        let userAccount = this.userAccount
        let startTime = ''
        let endTime = ''
        let departmentCode = ''
        let {pageNumber, pageSize} = this.page
        if (this.createTime && this.createTime.length > 0) {
          startTime = new Date(this.createTime[0]).getTime()
          endTime = new Date(this.createTime[1]).getTime()
        }
        if (this.departmentCode && this.departmentCode.length > 0) {
          if (this.departmentCode.length === 1 && this.departmentCode[0] === '999') {
            departmentCode = ''
          } else {
            departmentCode = this.departmentCode[this.departmentCode.length - 1]
          }
        }
        Utils.ajax({
          type: 'get',
          url: 'api/account/list',
          data: {
            userAccount,
            startTime,
            endTime,
            departmentCode,
            pageNumber,
            pageSize
          },
          dataType: 'json',
          success: function (response) {
            vmSelf.page.allCount = response.result.total
            vmSelf.userData = response.result.records
          }
        })
      },
      addNewUser () {
        this.$router.push('/account/user/create')
      },
      handleDepartmentCodeChange (value) {
        console.info(value)
      },
      createTimeChange (value) {
        console.info(value)
      },
      lookUseRoles (row) {
        this.lookUserRolesVisible = true
        this.lookUserRolesData.title = row.userAccount
        this.lookUserRolesData.currentRole = row
      }
    },
    filters: {}
  }
</script>
<style lang="stylus">
  #accountManagement
    width 100%
    .el-date-editor--datetimerange.el-input, .el-date-editor--datetimerange.el-input__inner
      width 313px
</style>
