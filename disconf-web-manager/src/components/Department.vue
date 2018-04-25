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
            <span>{{ data.departmentName }}</span>
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
            <template v-if="data.isHiddenNode">显示</template>
            <template v-if="!data.isHiddenNode">隐藏</template>
          </el-button>
        </span>
      </span>
        </el-tree>
      </el-col>
    </el-row>
    <el-dialog :visible.sync="addOrgDialogVisible" title="新增组织">
      <el-form :model="addOrgForm" :rules="addOrgFormRules" ref="addOrgFormRef">
        <template v-if="isAddDepartmentOperate">
          <el-form-item label="上级编码：" :label-width="formLabelWidth">
            <span>{{addOrgForm.parentCode}}</span>
          </el-form-item>
          <el-form-item label="上级名称：" :label-width="formLabelWidth">
            <span>{{addOrgForm.parentName}}</span>
          </el-form-item>
        </template>
        <el-form-item label="部门编码：" :label-width="formLabelWidth" prop="departmentCode">
          <template v-if="isEditDepartmentOperate">
            <span>{{addOrgForm.departmentCode}}</span>
          </template>
          <template v-if="!isEditDepartmentOperate">
            <el-input v-model="addOrgForm.departmentCode" placeholder="请输入部门编码" style="width: 80%;"></el-input>
            <el-button type="primary" @click="generateCurrentNodeCode">自动生成</el-button>
          </template>
        </el-form-item>
        <el-form-item label="部门名称：" :label-width="formLabelWidth" prop="departmentName">
          <el-input v-model="addOrgForm.departmentName" placeholder="请输入部门名称" style="width: 80%;"></el-input>
        </el-form-item>
        <el-form-item label="部门说明：" :label-width="formLabelWidth">
          <el-input type="textarea" :rows="2" v-model="addOrgForm.departmentComments" placeholder="请输入部门说明"
                    style="width: 80%;"></el-input>
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
    data () {
      return {
        isAddDepartmentOperate: false,
        isEditDepartmentOperate: false,
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
        departmentData: [],
        parentNode: null,
        currentEditDepartmentId: 0,
        currentEditDepartmentNode: null
      }
    },
    mounted () {
      this.loadDepartmentTree()
    },
    methods: {
      loadDepartmentTree () {
        let vmSelf = this
        Utils.ajax({
          url: 'api/org/department/list',
          success: function (response) {
            vmSelf.departmentData = response.result
          }
        })
      },
      addOrg () {
        this.setIsAddDepartmentOperate(false)
        this.addOrgDialogVisible = true
      },
      addDepartment (node, data) {
        this.setIsAddDepartmentOperate(true)
        this.addOrgForm.parentCode = data.departmentCode
        this.addOrgForm.parentName = data.departmentName
        this.addOrgDialogVisible = true
        this.isEditDepartmentOperate = false
      },
      editDepartment (node, data) {
        this.currentEditDepartmentId = data.id
        this.currentEditDepartmentNode = data
        if (data.departmentCode.length === 3) {
          this.setIsAddDepartmentOperate(false)
        } else {
          this.setIsAddDepartmentOperate(true)
        }
        this.isEditDepartmentOperate = true
        let currentParentCode = data.parentCode
        let vmSelf = this
        this.departmentData.forEach((node) => {
          if (node.departmentCode === currentParentCode) {
            vmSelf.parentNode = node
          } else {
            vmSelf.lookupParentNode(node.children, currentParentCode)
          }
        })
        if (vmSelf.parentNode) {
          vmSelf.addOrgForm.parentCode = vmSelf.parentNode.departmentCode
          vmSelf.addOrgForm.parentName = vmSelf.parentNode.departmentName
        }
        vmSelf.addOrgForm.departmentCode = data.departmentCode
        vmSelf.addOrgForm.departmentName = data.departmentName
        vmSelf.addOrgForm.departmentComments = data.departmentComments
        this.addOrgDialogVisible = true
      },
      lookupParentNode (children, currentParentCode) {
        let vmSelf = this
        children.forEach(function (node) {
          if (node.departmentCode === currentParentCode) {
            vmSelf.parentNode = node
          } else {
            vmSelf.lookupParentNode(node.children, currentParentCode)
          }
        })
      },
      deleteDepartment (node, data) {
        let vmSelf = this
        let h = this.$createElement
        this.$msgbox({
          title: '删除确认',
          message: h('div', null, ['确认删除当前部门(此操作会删除其子节点):', h('span', {style: {color: 'red'}}, [data.departmentName])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              let departmentId = data.id
              Utils.ajax({
                url: 'api/org/department/delete',
                dataType: 'json',
                data: {
                  departmentId
                },
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
                      message: '部门删除成功'
                    })
                    vmSelf.closeAddOrgDialog()
                    Utils.sleep(500).then(() => {
                      vmSelf.loadDepartmentTree()
                    })
                  }
                }
              })
            }
          }
        })
      },
      hideDepartment (node, data) {
        let vmSelf = this
        let commandName = data.isHiddenNode ? '显示' : '隐藏'
        let h = this.$createElement
        this.$msgbox({
          title: commandName + '确认',
          message: h('div', null, ['确认' + commandName + '当前部门:', h('span', {style: {color: 'red'}}, [data.departmentName])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              let departmentForm = {
                id: data.id,
                departmentCode: data.departmentCode,
                departmentName: data.departmentName,
                departmentComments: data.departmentComments,
                parentCode: data.parentCode,
                isOrgRoot: data.isOrgRoot,
                isHiddenNode: !data.isHiddenNode
              }
              Utils.ajax({
                type: 'POST',
                url: 'api/org/department/update',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
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
                      message: '部门更新成功'
                    })
                    vmSelf.closeAddOrgDialog()
                    Utils.sleep(500).then(() => {
                      vmSelf.loadDepartmentTree()
                    })
                  }
                }
              })
            }
          }
        })
      },
      closeAddOrgDialog () {
        this.addOrgDialogVisible = false
      },
      resetAddDepartmentDialog () {
        this.addOrgForm.parentCode = ''
        this.addOrgForm.parentName = ''
        this.addOrgForm.departmentCode = ''
        this.addOrgForm.departmentName = ''
        this.addOrgForm.departmentComments = ''
      },
      confirmAddOrgDialog (formName) {
        let vmSelf = this
        this.$refs[formName].validate((valid) => {
          if (valid) {
            /**
             * 处于编辑模式
             */
            if (vmSelf.isEditDepartmentOperate) {
              let departmentForm = {
                id: vmSelf.currentEditDepartmentId,
                departmentCode: vmSelf.addOrgForm.departmentCode,
                departmentName: vmSelf.addOrgForm.departmentName,
                departmentComments: vmSelf.addOrgForm.departmentComments,
                parentCode: vmSelf.currentEditDepartmentNode.parentCode,
                isOrgRoot: vmSelf.currentEditDepartmentNode.isOrgRoot,
                isHiddenNode: vmSelf.currentEditDepartmentNode.isHiddenNode
              }
              Utils.ajax({
                type: 'POST',
                url: 'api/org/department/update',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
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
                      message: '部门更新成功'
                    })
                    vmSelf.closeAddOrgDialog()
                    Utils.sleep(500).then(() => {
                      vmSelf.loadDepartmentTree()
                    })
                  }
                }
              })
            } else {
              let departmentForm = {
                departmentCode: vmSelf.addOrgForm.departmentCode,
                departmentName: vmSelf.addOrgForm.departmentName,
                departmentComments: vmSelf.addOrgForm.departmentComments,
                parentCode: this.isAddDepartmentOperate ? vmSelf.addOrgForm.parentCode : '',
                isOrgRoot: !this.isAddDepartmentOperate,
                isHiddenNode: false
              }
              Utils.ajax({
                type: 'POST',
                url: 'api/org/department/create',
                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
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
                    vmSelf.closeAddOrgDialog()
                    Utils.sleep(500).then(() => {
                      vmSelf.loadDepartmentTree()
                    })
                  }
                }
              })
            }
          }
        })
      },
      setIsAddDepartmentOperate (value) {
        this.isAddDepartmentOperate = value
      },
      generateCurrentNodeCode () {
        let parentCode = ''
        if (this.isAddDepartmentOperate) {
          parentCode = this.addOrgForm.parentCode
        } else {
          parentCode = ''
        }
        let vmSelf = this
        Utils.ajax({
          url: 'api/org/department/code',
          dataType: 'json',
          data: {
            parentCode
          },
          success: function (response) {
            vmSelf.addOrgForm.departmentCode = response.result
          }
        })
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
