<template>
  <div id="modifyPassword" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>个人中心</el-breadcrumb-item>
          <el-breadcrumb-item>修改密码</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-form :model="password" :rules="passwordRules" ref="modifyPasswordForm" style="width: 80%;margin: 0 auto;">
      <el-form-item label="原密码：" prop="oldPassword">
        <el-input v-model="password.oldPassword" placeholder="请输入原密码"></el-input>
      </el-form-item>
      <el-form-item label="新密码：" prop="newPassword1">
        <el-input v-model="password.newPassword1" placeholder="请输入新密码"></el-input>
      </el-form-item>
      <el-form-item label="重新输入新密码：" prop="newPassword2">
        <el-input v-model="password.newPassword2" placeholder="请再次输入新密码"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center;">
        <el-button type="primary" @click="confirmModifyPassword('modifyPasswordForm')">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'ModifyPassword',
    data () {
      return {
        password: {
          oldPassword: '',
          newPassword1: '',
          newPassword2: ''
        },
        passwordRules: {
          oldPassword: [
            {required: true, message: '请输入正确的原密码', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          newPassword1: [
            {required: true, message: '请输入正确的新密码', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ],
          newPassword2: [
            {required: true, message: '请输入正确的新密码', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      confirmModifyPassword (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            Utils.ajax({
              type: 'PUT',
              url: 'api/account/password',
              data: {
                'old_password': this.password.oldPassword,
                'new_password': this.password.newPassword1,
                'new_password_2': this.password.newPassword1
              },
              dataType: 'json',
              success: function (response) {
                if (response.success && response.success === 'true') {
                  vmSelf.$message({
                    type: 'success',
                    message: '密码修改成功，请使用新密码登录'
                  })
                  setTimeout(function () {
                    window.location.href = vmSelf.$store.state.htmlPath.appLogin
                  }, 500)
                } else {
                  vmSelf.$message({
                    type: 'error',
                    message: response.message.field.old_password
                  })
                }
              }
            })
          }
        })
      }
    }
  }
</script>
<style lang="stylus">
  #modifyPassword
    width 100%
</style>
