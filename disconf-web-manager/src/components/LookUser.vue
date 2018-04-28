<template>
  <div id="lookUser" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>开通账户</el-breadcrumb-item>
          <el-breadcrumb-item>用户详情</el-breadcrumb-item>
          <el-breadcrumb-item>{{user.userAccount}}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <div style="width: 80%;margin: 8px auto;">
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">账户名称：</el-col>
        <el-col :span="18">{{user.userAccount}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">联系人：</el-col>
        <el-col :span="18">{{user.name}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">联系电话：</el-col>
        <el-col :span="18">{{user.mobilePhone}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">联系邮箱：</el-col>
        <el-col :span="18">{{user.email}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">QQ：</el-col>
        <el-col :span="18">{{user.qq}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">微信号：</el-col>
        <el-col :span="18">{{user.weixin}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">所属部门：</el-col>
        <el-col :span="18">{{user.orgDepartment?user.orgDepartment.departmentName:''}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">账户说明：</el-col>
        <el-col :span="18">{{user.comments}}</el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="6" style="text-align: right;">角色设置：</el-col>
        <el-col :span="18" style="padding-top: -8px;" class="role-container">
          <template v-for="(role,index) in user.roles">
            <el-row style="margin-top: 8px;">
              <el-col :span="24" style="text-align: left;font-size: 16px;font-weight: bold;">
                {{'角色'+(index+1)+'：'+role.rolePermission.role.roleName}}
              </el-col>
            </el-row>
            <el-row v-for="(permission,roleIndex) in role.rolePermission.permissions" style="margin-top: 4px;">
              <el-col :span="24" style="text-align: left;font-size: 12px;padding-left: 50px;">
                {{'权限'+(roleIndex+1)+'：'+permission.permissionName}}
              </el-col>
            </el-row>
          </template>
        </el-col>
      </el-row>
      <el-row style="margin-top: 8px;margin-left: auto;margin-right: auto;">
        <el-col :span="24" style="text-align: center;">
          <el-button type="primary" @click="backToUserList">返回</el-button>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'LookUser',
    data () {
      return {
        user: {}
      }
    },
    mounted () {
      this.searchUserInformation()
    },
    methods: {
      searchUserInformation () {
        let vmSelf = this
        Utils.ajax({
          url: 'api/account/user/detail',
          data: {
            userId: this.$route.query.userId
          },
          dataType: 'json',
          success: function (response) {
            vmSelf.user = response.result
          }
        })
      },
      backToUserList () {
        this.$router.push('/account/user')
      }
    }
  }
</script>
<style lang="stylus">
  #lookUser
    width 100%
    .role-container
      .el-row:first-child
        margin-top 0 !important
</style>
