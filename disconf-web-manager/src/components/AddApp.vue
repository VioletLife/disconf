<template>
  <div id="addApp">
    <el-steps :active="currentActiveStep" finish-status="success">
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
          <el-button type="primary" @click="goNextStep(1)">下一步</el-button>
        </el-form-item>
      </el-form>
    </template>
    <template v-if="currentActiveStep===1">
      <el-button type="primary" @click="goNextStep(2)">下一步</el-button>
    </template>
    <template v-if="currentActiveStep===2"></template>
  </div>
</template>
<script>
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
        }
      }
    },
    methods: {
      goNextStep (step) {
        let vmSelf = this
        if (step === 1) {
          this.finishStep(step)
        }
        if (step === 2) {
          this.finishStep(step)
          this.finishStep(3)
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
      }
    }
  }
</script>
<style lang="stylus">
  #addApp
    width 100%
</style>
