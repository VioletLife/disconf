<template>
  <div id="appLogin">
    <el-container style="width:100%;height: 100%;position: absolute;">
      <el-header class="login-header">
        <span class="login-header-title">Disconf</span>
      </el-header>
      <el-container class="login-container">
        <el-row style="width: 100%;">
          <el-col :span="24">
            <div class="login-form-container" v-bind:class="{'login-form-container-active':activeForm}"
                 @mouseenter="activeForm=true" @mouseleave="activeForm=false">
              <el-form :model="loginForm" :rules="loginFormRules" label-width="100px" ref="loginFormRefs">
                <el-form-item label="用户名" prop="userAccount">
                  <el-input v-model="loginForm.userAccount" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="userPassword">
                  <el-input v-model="loginForm.userPassword" placeholder="请输入密码" type="password"></el-input>
                </el-form-item>
                <el-form-item label="记住我" prop="isRemember">
                  <el-checkbox v-model="loginForm.isRemember"></el-checkbox>
                </el-form-item>
                <el-form-item style="text-align: center;">
                  <el-button type="primary" @click="login('loginFormRefs')" style="margin-left: -100px;">登录</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-col>
        </el-row>
      </el-container>
      <el-footer style="padding: 0;margin: 0;width:100%;bottom: 0;position: relative;height: auto;">
        <disconf-footer :theme="footerDarkTheme"></disconf-footer>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
  import Utils from '../js/utils'
  import DisconfFooter from './DisconfFooter.vue'

  export default {
    name: 'Login',
    components: {DisconfFooter},
    beforeCreate () {
      this.$store.dispatch('setApplicationLayout', true)
    },
    data () {
      return {
        activeForm: false,
        userName: '',
        userPassword: '',
        userRemember: false,
        footerDarkTheme: {
          'background-color': 'rgb(84, 92, 100)'
        },
        loginForm: {
          userAccount: '',
          userPassword: '',
          isRemember: false
        },
        loginFormRules: {
          userAccount: [ {required: true, message: '请填写用户名', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10字符', trigger: 'blur'}],
          userPassword: [ {required: true, message: '请填写密码', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10字符', trigger: 'blur'}]
        }
      }
    },
    methods: {
      login (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            Utils.ajax({
              type: 'POST',
              url: 'api/account/signin',
              data: {
                name: this.loginForm.userAccount,
                password: this.loginForm.userPassword,
                remember: this.loginForm.isRemember ? 1 : 0
              },
              success: function (response) {
                if (response && response.success === 'true') {
                  if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                    this.$message({
                      type: 'error',
                      message: response.message.status.message
                    })
                  } else {
                    if (window) {
                      /**
                       * 表示浏览器端
                       */
                      let clientInfoKey = '__CLIENT__INFO__'
                      let dispatchMethod = 'setUserPermissions'
                      let clientInfoUserId = '__CLIENT__INFO__ID__'
                      vmSelf.$store.dispatch(dispatchMethod, response.result)
                      window.localStorage.setItem(clientInfoUserId, response.result.userId)
                      window.localStorage.setItem(clientInfoKey, JSON.stringify(response.result))
                      window.location.href = vmSelf.$store.state.htmlPath.appPageList
                    }
                  }
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
  @keyframes borderAnimation
    0%
      border 1px rgba(204, 204, 204, .5) solid
      box-shadow 0px 0px 2px rgba(204, 204, 204, .5)
    25%
      border 1px rgba(204, 204, 204, .6) solid
      box-shadow 0px 0px 2px rgba(204, 204, 204, .6)
    75%
      border 1px rgba(204, 204, 204, .7) solid
      box-shadow 0px 0px 2px rgba(204, 204, 204, .7)
    100%
      border 1px rgba(204, 204, 204, .8) solid
      box-shadow 0px 0px 2px rgba(204, 204, 204, .8)
  #appLogin
    width 100%
    height 100%
    min-height 100%
    position absolute
    .login-header
      width 100%
      height 61px
      background-color rgb(84, 92, 100)
      position relative
      top 0
    .login-header-title
      font-size 48px
      color white
      margin-left 16px
    .login-container
      position relative
      height calc(100% - 61px - 160px)
      display flex
      flex-direction column
      justify-content center
    .login-form-container
      width 480px
      margin 0 auto
      padding 16px
      border 1px rgba(204, 204, 204, .5) solid
      .el-input
        width 80%
    .login-form-container-active
      animation borderAnimation 2s
      animation-fill-mode forwards
</style>
