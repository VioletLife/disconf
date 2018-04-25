<template>
  <div id="userDepartment" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>账户中心</el-breadcrumb-item>
          <el-breadcrumb-item>部门管理</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="24" style="display: flex;justify-content: flex-end;">
        <el-button type="primary" @click="addOrg">新增组织</el-button>
      </el-col>
    </el-row>
    <el-row style="margin-top: 8px;">
      <el-col :span="24">
        <el-tree
          :data="departmentData"
          node-key="id"
          default-expand-all
          :expand-on-click-node="false">
           <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button
            type="text"
            size="mini"
            @click="() => addDepartment(node, data)">
            添加
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => editDepartment(node, data)">
            编辑
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => deleteDepartment(node, data)">
            删除
          </el-button>
          <el-button
            type="text"
            size="mini"
            @click="() => hideDepartment(node, data)">
            隐藏
          </el-button>
        </span>
      </span>
        </el-tree>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="addOrgDialogVisible" title="新增组织">
      <el-form :model="addOrgForm" :rules="addOrgFormRules" ref="addOrgFormRef">
        <template v-if="isAddDepartmentOperate">
          <el-form-item label="上级名称：" :label-width="formLabelWidth">
            <span>{{addOrgForm.parentCode}}</span>
          </el-form-item>
          <el-form-item label="上级名称：" :label-width="formLabelWidth">
            <<span>{{addOrgForm.parentName}}</span>
          </el-form-item>
        </template>
        <el-form-item label="部门编码：" :label-width="formLabelWidth" prop="departmentCode">
          <el-input v-model="addOrgForm.departmentCode" placeholder="请输入部门编码"></el-input>
          <el-button type="primary" @click="generateCurrentNodeCode">自动生成</el-button>
        </el-form-item>
        <el-form-item label="部门名称：" :label-width="formLabelWidth" prop="departmentName">
          <el-input v-model="addOrgForm.departmentName" placeholder="请输入部门名称"></el-input>
        </el-form-item>
        <el-form-item label="部门说明：" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="2" v-model="addOrgForm.departmentComments" placeholder="请输入部门说明"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="closeAddOrgDialog">取 消</el-button>
        <el-button type="primary" @click="confirmAddOrgDialog('addOrgFormRef')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'Department',
    data() {
      return {
        isAddDepartmentOperate: false,
        addOrgDialogVisible: false,
        formLabelWidth: '100px',
        addOrgForm: {
          parentCode: '',
          parentName: '',
          departmentCode: '',
          departmentName: '',
          departmentComments: ''
        },
        addOrgFormRules: {
          departmentCode: [
            {required: true, message: '请填写部门编码', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 20字符', trigger: 'blur'}
          ],
          departmentName: [
            {required: true, message: '请填写部门名称', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 20字符', trigger: 'blur'}
          ]
        },
        departmentData: [
          {
            id: 1,
            label: '神州',
            children: [
              {
                id: 2,
                label: '神州',
                children: [
                  {
                    id: 3,
                    label: '神州',
                    children: [
                      {
                        id: 4,
                        label: '神州',
                        children: [
                          {
                            id: 5,
                            label: '神州'
                          }
                        ]
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }
    },
    methods: {
      loadDepartmentTree() {
        Utils.ajax({
          url: 'api/org/department/list',
          success: function (response) {
            console.info(response)
          }
        })
      },
      addOrg() {
        this.setIsAddDepartmentOperate(false)
        this.addOrgDialogVisible = true
      },
      addDepartment(node, data) {
        this.setIsAddDepartmentOperate(true)
      },
      editDepartment(node, data) {
        this.setIsAddDepartmentOperate(true)
      },
      deleteDepartment(node, data) {

      },
      hideDepartment(node, data) {

      },
      closeAddOrgDialog() {
        this.addOrgDialogVisible = false
      },
      resetAddDepartmentDialog() {

      },
      confirmAddOrgDialog(formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let departmentForm = {}
            Utils.ajax({
              type: 'POST',
              url: 'api/org/department/create',
              dataType: 'json',
              data: JSON.stringify(departmentForm),
              success: function (response) {
                if (response && response.message && response.message.status && response.message.status.code && response.message.status.code !== 0) {
                  vmSelf.$message({
                    type: 'error',
                    message: response.message.status.message
                  })
                } else {
                  vmSelf.addRoleDialog = false
                  vmSelf.resetAddDepartmentDialog()
                  vmSelf.$message({
                    type: 'success',
                    message: '部门添加成功'
                  })
                  Utils.sleep(500).then(() => {
                    vmSelf.loadDepartmentTree()
                  })
                }
              }
            })
          }
        })
      },
      setIsAddDepartmentOperate(value) {
        this.isAddDepartmentOperate = value
      },
      generateCurrentNodeCode() {

      }
    }
  }
</script>
<style lang="stylus">
  #userDepartment
    width 100%
    .department-tree-node
      width 100%
    .custom-tree-node {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 14px;
      padding-right: 8px;
    }
</style>
