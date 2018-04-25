<template>
  <div id="userRole" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>角色设置</el-breadcrumb-item>
          <el-breadcrumb-item>角色管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="2" style="text-align: right;">
        <span>角色名称：</span>
      </el-col>
      <el-col :span="6">
        <el-input v-model="roleName" placeholder="请输入角色名称" size="small"></el-input>
      </el-col>
      <el-col :span="4">&nbsp;</el-col>
      <el-col :span="12" style="display: flex;justify-content: flex-end;">
        <el-button type="primary" @click="searchRole">查询</el-button>
        <el-button type="primary" @click="addNewRole">添加</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px;">
      <el-col :span="24">
        <el-table
          :data="roleData"
          border
          style="width:100%"
        >
          <el-table-column
            prop="roleCode"
            label="角色编码"
            width="220">
          </el-table-column>
          <el-table-column
            prop="roleName"
            label="角色名称"
            width="220">
          </el-table-column>
          <el-table-column
            prop="roleComments"
            label="角色说明"
          >
          </el-table-column>
          <el-table-column
            label="角色权限"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="lookRolePermission(scope.row)">查看权限</el-button>
            </template>
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
          >
          </el-table-column>
          <el-table-column
            label="操作"
            width="120"
          >
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="editRole(scope.row)">编辑</el-button>
              <el-button type="text" size="small" @click="deleteRole(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <page-sequence :data="page" @pageChange="pageChange" style="margin-top: 16px;"></page-sequence>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="addRoleDialog" title="新增角色">
      <el-form :model="roleForm" :rules="roleFormRules" ref="roleFormRef">
        <el-form-item label="角色编码：" :label-width="formLabelWidth" prop="roleCode">
          <el-input v-model="roleForm.roleCode" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleName">
          <el-input v-model="roleForm.roleName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色说明" :label-width="formLabelWidth" prop="roleComments">
          <el-input
            type="textarea"
            :rows="3"
            placeholder="配置说明"
            v-model="roleForm.roleComments">
          </el-input>
        </el-form-item>
        <el-form-item label="权限分配" :label-width="formLabelWidth" prop="rolePermissions">
          <el-transfer
            v-model="roleForm.rolePermissions"
            :data="permissionsData"
            :titles="['权限列表','已分配']"
            :props="{
             key: 'id',
             label: 'permissionName'
            }"
          ></el-transfer>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeAddRoleDialog">取 消</el-button>
        <el-button type="primary" @click="confirmAddRoleDialog('roleFormRef')">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="updateRoleDialog" title="更新角色">
      <el-form :model="updateRoleForm" :rules="updateRoleFormRules" ref="updateRoleFormRef">
        <el-form-item label="角色编码：" :label-width="formLabelWidth">
          <span>{{updateRoleForm.roleCode}}</span>
        </el-form-item>
        <el-form-item label="角色名称" :label-width="formLabelWidth" prop="roleName">
          <el-input v-model="updateRoleForm.roleName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色说明" :label-width="formLabelWidth" prop="roleComments">
          <el-input
            type="textarea"
            :rows="3"
            placeholder="配置说明"
            v-model="updateRoleForm.roleComments">
          </el-input>
        </el-form-item>
        <el-form-item label="权限分配" :label-width="formLabelWidth" prop="rolePermissions">
          <el-transfer
            v-model="updateRoleForm.rolePermissions"
            :data="permissionsData"
            :titles="['权限列表','已分配']"
            :props="{
             key: 'id',
             label: 'permissionName'
            }"
          ></el-transfer>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeUpdateRoleDialog">取 消</el-button>
        <el-button type="primary" @click="confirmUpdateRoleDialog('updateRoleFormRef')">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="lookRoleDialog" :title="lookRoleDialogTitle">
      <el-form :model="lookRoleForm" ref="lookRoleFormRef">
        <template v-for="(permissionDetail,index) in lookRoleForm.permissions">
          <el-form-item :label="index | permissionNumber" :label-width="formLabelWidth">
            <span>编码:</span>
            <el-tooltip class="item" effect="dark" :content="permissionDetail.permissionCode" placement="bottom">
              <span class="look-permission">{{permissionDetail.permissionCode}}</span>
            </el-tooltip>
            <span>名称:</span>
            <el-tooltip class="item" effect="dark" :content="permissionDetail.permissionName" placement="bottom">
              <span class="look-permission">{{permissionDetail.permissionName}}</span>
            </el-tooltip>
          </el-form-item>
        </template>
      </el-form>
    </el-dialog>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'UserRole',
    data () {
      return {
        roleForm: {
          roleCode: '',
          roleName: '',
          roleComments: '',
          rolePermissions: []
        },
        permissionsData: [],
        updatePermissionsData: [],
        updateRoleForm: {
          roleCode: '',
          roleName: '',
          roleComments: '',
          rolePermissions: []
        },
        roleFormRules: {
          roleCode: [
            {required: true, message: '请填写角色编码', trigger: 'blur'},
            {min: 1, max: 50, message: '长度在 1 到 50字符', trigger: 'blur'}
          ],
          roleName: [
            {required: true, message: '请填写角色名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20字符', trigger: 'blur'}
          ]
        },
        updateRoleFormRules: {
          roleName: [
            {required: true, message: '请填写角色名称', trigger: 'blur'},
            {min: 1, max: 20, message: '长度在 1 到 20字符', trigger: 'blur'}
          ]
        },
        formLabelWidth: '100px',
        addRoleDialog: false,
        updateRoleDialog: false,
        addRoleDialogForm: {},
        addRoleDialogOpenMode: 'ADD',
        roleName: '',
        roleData: [],
        page: {
          allCount: 0,
          pageNumber: 1,
          pageSize: 1
        },
        currentEditRoleId: 0,
        lookRoleDialog: false,
        lookRoleForm: {
          permissions: []
        },
        lookRoleDialogTitle: ''
      }
    },
    mounted () {
      this.searchRole()
      this.searchPermissions()
    },
    methods: {
      searchRole () {
        let vmSelf = this
        let params = {
          roleName: this.roleName,
          pageNumber: this.page.pageNumber,
          pageSize: this.page.pageSize
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
                console.info(role)
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
              vmSelf.roleData = roleDataFormat
            }
          }
        })
      },
      addNewRole () {
        this.addRoleDialog = true
      },
      pageChange (pageNumber) {
        this.page.pageNumber = pageNumber
        this.searchRole()
      },
      editRole (row) {
        this.updateRoleForm.roleCode = row.roleCode
        this.updateRoleForm.roleName = row.roleName
        this.updateRoleForm.roleComments = row.roleComments
        this.currentEditRoleId = row.id
        let selectedKeys = []
        row.permissions.forEach(function (permission) {
          selectedKeys.push(permission.id)
        })
        this.updateRoleForm.rolePermissions = selectedKeys
        this.updateRoleDialog = true
      },
      deleteRole (row) {
        let vmSelf = this
        const h = this.$createElement
        this.$msgbox({
          title: '删除确认',
          message: h('div', null, ['确定删除：', h('span', {style: {color: 'red'}}, [row.roleName + '（' + row.roleCode + '）'])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              Utils.ajax({
                url: 'api/auth/role/delete',
                data: {
                  roleId: row.id
                },
                dataType: 'json',
                success: function (response) {
                  vmSelf.$message({
                    type: 'success',
                    message: '删除成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.searchRole()
                  })
                }
              })
            }
          }
        })
      },
      closeAddRoleDialog () {
        this.addRoleDialog = false
        this.resetAddRoleDialog()
      },
      closeUpdateRoleDialog () {
        this.updateRoleDialog = false
        this.resetUpdateRoleDialog()
      },
      resetUpdateRoleDialog () {
        this.updateRoleForm.roleCode = ''
        this.updateRoleForm.roleName = ''
        this.updateRoleForm.roleComments = ''
        this.updateRoleForm.rolePermissions = []
      },
      resetAddRoleDialog () {
        this.roleForm.roleCode = ''
        this.roleForm.roleName = ''
        this.roleForm.roleComments = ''
        this.roleForm.rolePermissions = []
      },
      confirmAddRoleDialog (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let roleAdd = {
              role: {
                roleCode: this.roleForm.roleCode,
                roleName: this.roleForm.roleName,
                roleComments: this.roleForm.roleComments
              },
              permissions: []
            }
            vmSelf.roleForm.rolePermissions.forEach(function (permission) {
              roleAdd.permissions.push({id: permission})
            })
            Utils.ajax({
              type: 'POST',
              url: 'api/auth/role/create',
              data: JSON.stringify(roleAdd),
              dataType: 'json',
              contentType: 'application/json;charset=utf-8',
              success: function (response) {
                if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                  vmSelf.$message({
                    type: 'error',
                    message: response.message.status.message
                  })
                } else {
                  vmSelf.addRoleDialog = false
                  vmSelf.resetAddRoleDialog()
                  vmSelf.$message({
                    type: 'success',
                    message: '角色添加成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.searchRole()
                  })
                }
              }
            })
          }
        })
      },
      confirmUpdateRoleDialog (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let updateData = {
              role: {
                id: this.currentEditRoleId,
                roleName: this.updateRoleForm.roleName,
                roleComments: this.updateRoleForm.roleComments
              },
              permissions: []
            }
            vmSelf.updateRoleForm.rolePermissions.forEach(function (permission) {
              updateData.permissions.push({id: permission})
            })

            Utils.ajax({
              type: 'POST',
              url: 'api/auth/role/update',
              dataType: 'json',
              contentType: 'application/json;charset=utf-8',
              data: JSON.stringify(updateData),
              success: function (response) {
                if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                  vmSelf.$message({
                    type: 'error',
                    message: response.message.status.message
                  })
                } else {
                  vmSelf.updateRoleDialog = false
                  vmSelf.resetUpdateRoleDialog()
                  vmSelf.$message({
                    type: 'success',
                    message: '角色更新成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.searchRole()
                  })
                }
              }
            })
          }
        })
      },
      searchPermissions () {
        let vmSelf = this
        let params = {
          permissionName: '',
          pageNumber: this.page.pageNumber,
          pageSize: 1000
        }
        Utils.ajax({
          type: 'GET',
          url: 'api/auth/permission/list',
          dataType: 'json',
          data: params,
          success: function (response) {
            if (response.result) {
              vmSelf.page.allCount = response.result.total
              vmSelf.permissionsData = response.result.records
              vmSelf.updatePermissionsData = response.result.records
            }
          }
        })
      },
      lookRolePermission (row) {
        this.lookRoleDialogTitle = row.roleName + '>已分配权限'
        this.lookRoleForm.permissions = row.permissions
        this.lookRoleDialog = true
      }
    },
    computed: {},
    filters: {
      permissionNumber (value) {
        return `权限${value + 1}：`
      }
    }
  }
</script>
<style lang="stylus">
  #userRole
    width 100%
    .look-permission
      font-size 16px
      font-weight bold
      width 200px
      display inline-block
      text-overflow ellipsis
</style>
