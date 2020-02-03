<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="isAdd ? '新增' : '订单详情'" width="700px">
    <el-card>
      <div slot="header">
        <span>学生信息</span>
      </div>
      <div class="text item">用户昵称:{{ form.nickname }}</div>
      <div class="text item">学生姓名: {{ form.studentAssName }}</div>
      <div class="text item">证件类型: {{ form.certificateType }}</div>
      <div class="text item">证件号码: {{ form.certificateNum }}</div>
      <div class="text item">年级: {{ form.gradeName }}</div>
    </el-card>
    <el-card>
      <div slot="header">
        <span>订单信息</span>
      </div>
      <el-row :gutter="24">
        <el-col :span="12">
          <div class="text item">订单编号: {{ form.num }}</div>
          <div class="text item">是否含餐: {{ form.isMeal }}</div>
          <div class="text item">餐费: {{ form.totalNum }}</div>
          <div class="text item">实际支付: {{ form.payPrice }}</div>
          <div class="text item">支付方式: {{ form.payTypeName }}</div>
        </el-col>
        <el-col :span="12">
          <div class="text item">订单状态: {{ form.status }}</div>
          <div class="text item">总金额: {{ form.totalMoney }}元</div>
          <div class="text item">创建时间: {{ parseTime(form.createTime) }}</div>
          <div class="text item">支付时间: {{ formatTime(form.payTime) }}</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>门店信息</span>
      </div>
      <div class="text item">门店名称: {{ form.storeName }}</div>
      <div class="text item">课程名称: {{ form.courseName }}</div>
      <div class="text item">入学时间: {{ parseTime(form.enrolTime)}}</div>
      <div class="text item">托管时长: {{ form.managTime }}</div>
    </el-card>
    <el-card>
      <div slot="header">
        <span>备注信息</span>
      </div>
      <div class="text item">{{ form.remark }}</div>
    </el-card>
  </el-dialog>
</template>

<script>
import { formatTime } from '@/utils/index'
export default {
  props: {
    isAdd: {
      type: Boolean,
      required: true
    }
  },
  data() {
    return {
      loading: false, dialog: false,
      form: {
        id: '',
        num: '',
        studentId: '',
        storeId: '',
        courseId: '',
        greadId: '',
        managTime: '',
        isMeal: '',
        enrolTime: '',
        certificateType: '',
        studentAssName: '',
        certificateNum: '',
        totalMoney: '',
        status: '',
        createBy: '',
        createTime: '',
        updateBy: '',
        updateTime: '',
        delFlag: '',
        createById: '',
        storeName: '',
        courseName: '',
        isSystemDel: ''
      },
      rules: {
        unique: [
          { required: true, message: 'please enter', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    formatTime,
    cancel() {
      this.resetForm()
    },
    doSubmit() {
      this.loading = true
      if (this.isAdd) {
        this.doAdd()
      } else this.doEdit()
    },
    doAdd() {
      add(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '添加成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.init()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    doEdit() {
      edit(this.form).then(res => {
        this.resetForm()
        this.$notify({
          title: '修改成功',
          type: 'success',
          duration: 2500
        })
        this.loading = false
        this.$parent.init()
      }).catch(err => {
        this.loading = false
        console.log(err.response.data.message)
      })
    },
    resetForm() {
      this.dialog = false
      this.$refs['form'].resetFields()
      this.form = {
        id: '',
        num: '',
        uid: '',
        realName: '',
        userPhone: '',
        isSystemDel: ''
      }
    }
  }
}
</script>

<style scoped>
  .text {
    font-size: 12px;
  }

  .item {
    padding: 6px 0;
  }

</style>
