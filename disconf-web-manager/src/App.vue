<template>
  <div id="app">
    <el-container>
      <el-header>
        <el-menu
          :default-active="activeIndex"
          class="el-menu-demo"
          mode="horizontal"
          @select="handleSelect"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b"
        >
          <el-submenu index="1">
            <template slot="title">应用中心</template>
            <el-menu-item index="1-1"><a :href="htmlPath.appPageList">我的应用</a></el-menu-item>
            <el-menu-item index="1-2">
              <a :href="htmlPath.app">创建应用</a>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">账户中心</template>
            <el-menu-item index="2-1">开通账户</el-menu-item>
            <el-submenu index="2-2">
              <template slot="title">权限设置</template>
              <el-menu-item index="2-2-1">角色管理</el-menu-item>
              <el-menu-item index="2-2-2">权限管理</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">基础设置</template>
            <el-menu-item index="3-1">基础环境</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">个人中心</template>
            <el-menu-item index="4-1"><a :href="htmlPath.password">修改密码</a></el-menu-item>
            <el-menu-item index="4-2"><a href="#" id="signout">退出</a></el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-container>
        <router-view/>
      </el-container>
      <el-footer>
        <div id="footer">
          <div id="BottomMain">
            <div class="inner">
              <strong>
                <a href="#" class="dark">关于</a> &nbsp;
              </strong>
              <strong>
                <a href="#" class="dark">升级</a> &nbsp;
              </strong>
              <div class="sep10"></div>
              <span>分布式配置管理平台</span>
              <div class="sep10"></div>
              <span class="myfade">Theme by <a href="http://sov5.com/" target="_blank">Sov5搜索</a>，
                        Power by 百度程序化广告交易工程平台技术部，
                    Copyright &copy; 2014~2016 </span>
            </div>
          </div>
        </div>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
  import GlobalEnv from './js/global_env'

  export default {
    name: 'app',
    data: function () {
      return {
        activeName: '',
        activeCollapseNames: [],
        isCollapse: false,
        activeIndex: '0',
        htmlPath: {
          password: GlobalEnv.env().serverUrl + 'static/html/modifypassword.html',
          app: GlobalEnv.env().serverUrl + 'static/html/newapp.html',
          appConfigItem: GlobalEnv.env().serverUrl + 'static/html/newconfig_item.html',
          appConfigFile: GlobalEnv.env().serverUrl + 'static/html/newconfig_file.html',
          appLogin: GlobalEnv.env().serverUrl + 'static/html/login.html',
          appPageList: GlobalEnv.env().serverUrl + 'static/html/main.html',
          fileDownload: GlobalEnv.env().serverUrl + 'api/web/config/download/'
        },
        visible: false,
        userAppList: [],
        userEnvList: [],
        currentAppName: '',
        currentAppId: 0,
        currentAppVersionInfo: [],
        currentAppEnvId: 0,
        confContentDialog: {
          confContentVisible: false,
          confContentTitle: '',
          confContent: ''
        }
      }
    },
    mounted: function () {

    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath)
      }
    }
  }
</script>

<style>
  #app {

  }
</style>
