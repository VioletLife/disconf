<template>
  <div id="addApp" class="page-container">
    <el-row>
      <el-col :span="24" style="height: 48px;padding-top: 18px;padding-bottom: 18px;">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item>应用中心</el-breadcrumb-item>
          <el-breadcrumb-item>我的应用</el-breadcrumb-item>
          <el-breadcrumb-item>创建应用</el-breadcrumb-item>
        </el-breadcrumb>
      </el-col>
    </el-row>
    <el-steps class="page-steps-container" style="width: 100% !important;" :active="currentActiveStep"
              finish-status="success" align-center>
      <el-step>
        <template slot="title">{{stepStatus(1).title}}</template>
        <template slot="description">{{stepStatus(1).description}}</template>
      </el-step>
      <el-step>
        <template slot="title">{{stepStatus(2).title}}</template>
        <template slot="description">{{stepStatus(2).description}}</template>
      </el-step>
      <el-step>
        <template slot="title">{{stepStatus(3).title}}</template>
        <template slot="description">{{stepStatus(3).description}}</template>
      </el-step>
    </el-steps>
    <el-row class="page-steps-container" style="margin-top: 40px !important;">
      <el-col :span="24">
        <template v-if="currentActiveStep===0">
          <el-form :model="appNameForm" :rules="appNameRules" ref="appNameFormRef" label-width="100px">
            <el-form-item label="应用名：" prop="appName">
              <el-input v-model="appNameForm.appName"></el-input>
            </el-form-item>
            <el-form-item label="说明：" prop="appDescription">
              <el-input
                type="textarea"
                :rows="2"
                placeholder="请输入内容"
                v-model="appNameForm.appDescription">
              </el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="goNextStep(1,'appNameFormRef')" style="float: right;">下一步</el-button>
            </el-form-item>
          </el-form>
        </template>
        <template v-if="currentActiveStep===1">
          <el-row>
            <el-col :span="4">
              <span style="font-weight: bold;font-size: 16px;">应用环境</span>
            </el-col>
            <el-col :span="20">&nbsp;</el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-card v-for="env in envDefaultList" class="box-card" style="margin-top: 8px;">
                <div slot="header" class="clearfix">
                  <span>{{env.envName}}</span>
                  <i class="el-icon-delete" @click="deleteCurrentAppEnv(env)"></i>
                </div>
                <span>{{env.envComments}}</span>
              </el-card>
            </el-col>
          </el-row>

          <el-button type="primary" @click="goNextStep(2)"
                     style="float: right;margin-top: 16px;margin-bottom: 16px;margin-left: 16px;">
            确认创建
          </el-button>
          <el-button type="warning" @click="resetDefaultEnv" style="float: right;margin-top: 16px;margin-bottom: 16px;">
            恢复默认
          </el-button>
          <el-button type="primary" @click="createDefaultEnv"
                     style="float: right;margin-top: 16px;margin-bottom: 16px;">
            创建环境
          </el-button>
        </template>
        <template v-if="currentActiveStep===3">
          <span>应用创建完成，去<a :href="appPageListUrl">我的应用</a>创建配置</span>
        </template>
      </el-col>
    </el-row>
    <el-dialog title="添加应用环境信息" :visible.sync="dialogAppEnvDefaultVisible">
      <el-form :model="envDefaultForm" :rules="envDefaultFormRules" ref="envDefaultFormRef">
        <el-form-item label="环境名称" label-width="100px" prop="envName">
          <el-input v-model="envDefaultForm.envName" placeholder="请输入环境名称"></el-input>
        </el-form-item>
        <el-form-item label="环境说明" label-width="100px">
          <el-input v-model="envDefaultForm.envComments" placeholder="请输入环境说明"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAppEnvDefaultVisible=false">取 消</el-button>
        <el-button type="primary" @click="confirmAppEnvDefaultDialog('envDefaultFormRef')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import Utils from '../js/utils'

  export default {
    name: 'AddApp',
    data () {
      return {
        step: [1, 2, 3],
        activeStepsStatus: [{
          stepNumber: 1,
          isCompleted: false,
          uncompleted: {
            title: '应用名称',
            description: '填写应用名称'
          },
          completed: {
            title: '已完成',
            description: '填写应用名称'
          }
        },
        {
          stepNumber: 2,
          isCompleted: false,
          uncompleted: {
            title: '应用环境',
            description: '默认配置需要启用的应用环境'
          },
          completed: {
            title: '已完成',
            description: '默认配置需要启用的应用环境'
          }
        },
        {
          stepNumber: 3,
          isCompleted: false,
          uncompleted: {
            title: '创建完成',
            description: '创建完成可以添加自定义配置了'
          },
          completed: {
            title: '创建完成',
            description: '创建完成可以添加自定义配置了'
          }
        }],
        currentActiveStep: 0,
        appNameForm: {
          appName: '',
          appDescription: ''
        },
        appNameRules: {
          appName: [
            {required: true, message: '请填写应用名称', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10字符', trigger: 'blur'}
          ]
        },
        envDefaultList: [],
        envCacheDefaultList: [],
        dialogAppEnvDefaultVisible: false,
        envDefaultForm: {
          envName: '',
          envComments: ''
        },
        envDefaultFormRules: {
          envName: [
            {required: true, message: '请输入应用环境名称', trigger: 'blur'},
            {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    mounted () {
      let vmSelf = this
      Utils.ajax({
        url: 'api/env/default/list',
        dataType: 'json',
        success: function (response) {
          if (response && response.success && response.success === 'true') {
            response.page.result.forEach(function (envDefault) {
              envDefault['isEnvDefault'] = true
            })
            vmSelf.envDefaultList = response.page.result
            vmSelf.envCacheDefaultList = JSON.parse(JSON.stringify(response.page.result))
          }
        }
      })
    },
    methods: {
      goNextStep (step, formName) {
        let vmSelf = this
        if (step === 1 && formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              this.finishStep(step)
            }
          })
        }
        if (step === 2) {
          /***
           * 创建应用
           */
          let app = {
            app: {
              name: this.appNameForm.appName,
              description: this.appNameForm.appDescription
            },
            appEnvs: []
          }
          this.envDefaultList.forEach(function (env) {
            let envVariables = {
              env: {
                envName: env.envName,
                envComments: env.envComments,
                isEnvDefault: env.isEnvDefault,
                envId: env.isEnvDefault ? env.id : 0
              }
            }
            app.appEnvs.push(envVariables)
          })
          Utils.ajax({
            type: 'POST',
            url: 'api/app/create',
            contentType: 'application/json;charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(app)
          }).then((response) => {
            console.info(response)
            this.finishStep(step)
            this.finishStep(3)
          })
        }
      },
      finishStep (step) {
        let vmSelf = this
        this.activeStepsStatus.forEach(function (status) {
          if (status.stepNumber === step) {
            status.isCompleted = true
            vmSelf.currentActiveStep = step
          }
        })
      },
      stepStatus (step) {
        let stepStatusMessage = {title: '', description: ''}
        this.activeStepsStatus.forEach(function (status) {
          if (status.stepNumber === step) {
            stepStatusMessage = status.isCompleted ? status['completed'] : status['uncompleted']
          }
        })
        return stepStatusMessage
      },
      deleteCurrentAppEnv (env) {
        let vmSelf = this
        let h = this.$createElement
        this.$msgbox({
          title: '删除确认',
          message: h('div', null, ['确认删除当前配置:', h('span', {style: {color: 'red'}}, [env.envName])]),
          showCancelButton: true,
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          callback: function (action) {
            if (action === 'confirm') {
              let removeIndex = -1
              vmSelf.envDefaultList.forEach(function (envValue, index) {
                if (envValue.id === env.id) {
                  removeIndex = index
                }
              })
              if (removeIndex > -1) {
                vmSelf.envDefaultList.splice(removeIndex, 1)
              }
            }
          }
        })
      },
      resetDefaultEnv () {
        this.envDefaultList = JSON.parse(JSON.stringify(this.envCacheDefaultList))
      },
      createDefaultEnv () {
        this.dialogAppEnvDefaultVisible = true
      },
      confirmAppEnvDefaultDialog (formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let autoId = (new Date()).getTime()
            let newAppEnvLocal = {
              id: autoId,
              envName: this.envDefaultForm.envName,
              envComments: this.envDefaultForm.envComments,
              isEnvDefault: false
            }
            this.envDefaultList.push(newAppEnvLocal)
            this.envDefaultForm.envName = ''
            this.envDefaultForm.envComments = ''
            this.dialogAppEnvDefaultVisible = false
          }
        })
      }
    },
    computed: {
      appPageListUrl () {
        return this.$store.state.htmlPath.appPageList
      }
    }
  }
</script>
<style lang="stylus">
  #addApp
    width 100%
    .page-steps-container
      margin-top: 8px;
      width: 80%;
      margin-left: auto;
      margin-right: auto;
    .el-icon-delete
      color red
      opacity .6
      cursor pointer
      float right
    .el-icon-delete:hover
      color red
      opacity 1
      cursor pointer
</style>
