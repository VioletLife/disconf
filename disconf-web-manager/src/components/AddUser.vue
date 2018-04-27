<template>
  <div id="addUser" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>开通账户</el-breadcrumb-item>
          <el-breadcrumb-item>新建用户</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px">
      <el-col :span="24">
        <el-form :model="addUserForm" :rules="addUserFormRules" ref="addUserFormRef">
          <el-form-item label="账户名称：" :label-width="formLabelWidth" prop="userAccount">
            <el-input v-model="addUserForm.userAccount" placeholder="请输入账户名称"></el-input>
          </el-form-item>
          <el-form-item label="账户密码：" :label-width="formLabelWidth" prop="password1">
            <el-input v-model="addUserForm.password1" placeholder="请输入账户密码"></el-input>
          </el-form-item>
          <el-form-item label="确认密码：" :label-width="formLabelWidth" prop="password2">
            <el-input v-model="addUserForm.password2" placeholder="请输入确认密码"></el-input>
          </el-form-item>
          <el-form-item label="联系人：" :label-width="formLabelWidth" prop="name">
            <el-input v-model="addUserForm.name" placeholder="请输入联系人"></el-input>
          </el-form-item>
          <el-form-item label="联系电话：" :label-width="formLabelWidth" prop="name">
            <el-input v-model="addUserForm.mobilePhone" placeholder="请输入联系人电话"></el-input>
          </el-form-item>
          <el-form-item label="联系邮箱：" :label-width="formLabelWidth" prop="email">
            <el-input v-model="addUserForm.email" placeholder="请输入联系邮箱"></el-input>
          </el-form-item>
          <el-form-item label="QQ：" :label-width="formLabelWidth" prop="email">
            <el-input v-model="addUserForm.qq" placeholder="请输入QQ"></el-input>
          </el-form-item>
          <el-form-item label="微信号：" :label-width="formLabelWidth" prop="email">
            <el-input v-model="addUserForm.weixin" placeholder="请输入微信号"></el-input>
          </el-form-item>
          <el-form-item label="所属部门：" :label-width="formLabelWidth">
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
          </el-form-item>
          <el-form-item label="账户说明：" :label-width="formLabelWidth">
            <el-input
              type="textarea"
              :rows="2"
              v-model="addUserForm.comments"
              placeholder="请输入账户说明"></el-input>
          </el-form-item>
          <el-form-item label="角色设置：" :label-width="formLabelWidth">
            <el-transfer
              v-model="rolesList"
              :data="rolesData"
              :titles="['权限列表','已分配']"
              :props="{
             key: 'id',
             label: 'roleName'
            }"
            ></el-transfer>
          </el-form-item>
          <el-form-item style="display: flex;justify-content: flex-end;">
            <el-button type="danger" @click="resetAddUserForm">重置</el-button>
            <el-button type="warning" @click="cancelAddUserForm">取消</el-button>
            <el-button type="primary" @click="saveAddUserForm('addUserFormRef')">保存</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'AddUser',
    data () {
      return {
        rolesList: [],
        rolesData: [],
        rolesDataCache: {},
        departmentData: [],
        departmentCache: {},
        departmentCode: [],
        formLabelWidth: '100px',
        addUserForm: {
          userAccount: '',
          password1: '',
          password2: '',
          name: '',
          mobilePhone: '',
          email: '',
          qq: '',
          weixin: '',
          departmentId: 0,
          departmentCode: '',
          comments: '',
          roles: []
        },
        addUserFormRules: {
          userAccount: [
            {required: true, message: '请填写账户名称', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 2 到 50字符', trigger: 'blur'}
          ],
          password1: [
            {required: true, message: '请填写账户密码', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 6 到 12字符', trigger: 'blur'}
          ],
          password2: [
            {required: true, message: '请填写确认密码', trigger: 'blur'},
            {min: 6, max: 12, message: '长度在 6 到 12字符', trigger: 'blur'}
          ]
        },
        page: {
          allCount: 0,
          pageNumber: 1,
          pageSize: 50
        }
      }
    },
    mounted () {
      this.loadDepartmentTree()
      this.searchRole()
    },
    methods: {
      removeInvalidChildrens (node) {
        this.departmentCache[node.departmentCode] = node
        if (node && node.children) {
          if (node.children && node.children.length === 0) {
            delete node.children
          } else {
            node.children.forEach((childrenNode) => this.removeInvalidChildrens(childrenNode))
          }
        }
      },
      handleDepartmentCodeChange (value) {
        console.info(value)
      },
      searchRole () {
        let vmSelf = this
        let params = {
          roleName: '',
          pageNumber: 1,
          pageSize: 10000
        }
        Utils.ajax({
          type: 'GET',
          url: 'api/auth/role/list',
          dataType: 'json',
          data: params,
          success: function (response) {
            if (response.result) {
              vmSelf.page.allCount = response.result.total
              let roleDataFormat = []
              response.result.records.forEach(function (role) {
                vmSelf.rolesDataCache['node_' + role.id] = role
                let {
                  id,
                  roleCode,
                  roleName,
                  roleComments,
                  createTime,
                  creator,
                  updateTime,
                  updator
                } = role.role
                roleDataFormat.push({
                  id,
                  roleCode,
                  roleName,
                  roleComments,
                  createTime,
                  creator,
                  updateTime,
                  updator,
                  permissions: role.permissions
                })
              })
              vmSelf.rolesData = roleDataFormat
            }
          }
        })
      },
      resetAddUserForm () {
      },
      cancelAddUserForm () {
      },
      saveAddUserForm (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.rolesList.length > 0 && this.departmentCode.length > 0) {
              let rolesData = []
              this.rolesList.forEach((value) => {
                rolesData.push({roleId: value})
              })
              let userParams = {
                name: vmSelf.addUserForm.name,
                password: vmSelf.addUserForm.password1,
                userAccount: vmSelf.addUserForm.userAccount,
                mobilePhone: vmSelf.addUserForm.mobilePhone,
                email: vmSelf.addUserForm.email,
                token:'',
                qq: vmSelf.addUserForm.qq,
                weixin: vmSelf.addUserForm.weixin,
                departmentId: vmSelf.departmentCache[vmSelf.departmentCode[vmSelf.departmentCode.length - 1]].id,
                departmentCode: vmSelf.departmentCache[vmSelf.departmentCode[vmSelf.departmentCode.length - 1]].departmentCode,
                comments: vmSelf.addUserForm.comments,
                roles: rolesData
              }
              Utils.ajax({
                type: 'POST',
                url: 'api/account/create',
                dataType: 'json',
                data: JSON.stringify(userParams),
                contentType: 'application/json;charset=utf-8',
                success: function (response) {
                  if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                    vmSelf.$message({
                      type: 'error',
                      message: response.message.status.message
                    })
                  } else {
                    vmSelf.$message({
                      type: 'success',
                      message: '角色添加成功'
                    })
                    Utils.sleep(500).then(() => {
                      vmSelf.$router.push({path: '/account/user'})
                    })
                  }
                }
              })
            } else {
              this.$message({
                type: 'error',
                message: '请选择用户部门，并且设置角色'
              })
            }
          }
        })
      },
      loadDepartmentTree () {
        let vmSelf = this
        Utils.ajax({
          url: 'api/org/department/list',
          success: function (response) {
            response.result.forEach((childrenNode) => vmSelf.removeInvalidChildrens(childrenNode))
            vmSelf.departmentData = response.result
          }
        })
      }
    }
  }
</script>
<style lang="stylus">
  #addUser
    width 100%
</style>
