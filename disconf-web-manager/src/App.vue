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
              <router-link to="/app/add">创建应用</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">账户中心</template>
            <el-menu-item index="2-1">
              <router-link to="/account/user">开通账户</router-link>
            </el-menu-item>
            <el-submenu index="2-2">
              <template slot="title">权限设置</template>
              <el-menu-item index="2-2-1">
                <router-link to="/auth/role">角色管理</router-link>
              </el-menu-item>
              <el-menu-item index="2-2-2">
                <router-link to="/auth/permission">权限管理</router-link>
              </el-menu-item>
            </el-submenu>
            <el-menu-item index="2-3">
              <router-link to="/org/department">部门管理</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">基础设置</template>
            <el-menu-item index="3-1">基础环境</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">个人中心</template>
            <el-menu-item index="4-1">
              <router-link to="/modify/password">修改密码</router-link>
            </el-menu-item>
            <el-menu-item index="4-2"><a href="#" id="signout">退出</a></el-menu-item>
          </el-submenu>
        </el-menu>
      </el-header>
      <el-container style="min-height: 600px;">
        <router-view/>
      </el-container>
      <el-footer style="padding: 0;margin: 0;width:100%;">
        <disconf-footer :theme="footerDarkTheme"></disconf-footer>
      </el-footer>
    </el-container>
  </div>
</template>
<script>
  import DisconfFooter from './components/DisconfFooter.vue'

  export default {
    name: 'app',
    components: {DisconfFooter},
    data: function () {
      return {
        activeName: '',
        activeCollapseNames: [],
        isCollapse: false,
        activeIndex: '0',
        htmlPath: {},
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
        },
        footerDarkTheme: {
          'background-color': 'rgb(84, 92, 100)'
        }
      }
    },
    mounted: function () {
      this.htmlPath = this.$store.state.htmlPath
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
