<template>
  <el-dialog :append-to-body="true" :close-on-click-modal="false" :before-close="cancel" :visible.sync="dialog" :title="detail" width="700px">
    <el-card>
      <div slot="header">
        <span>课程信息</span>
      </div>
      <div class="text item">课程名称:{{ form.name }}</div>
      <div class="text item">课程类型: {{ form.classify }}</div>
      <div class="text item">开始时间: {{ parseTime(form.cStarttime) }}</div>
      <div class="text item">结束时间: {{ parseTime(form.cEndtime) }}</div>
      <div class="text item">课程状态: {{ form.status}}</div>
      <div class="text item">课程内容: {{ form.content }}</div>
    </el-card>
    <el-card>
      <div slot="header">
        <span>招生信息</span>
      </div>
      <el-row :gutter="24">
        <el-col :span="12">
          <div class="text item">是否一对一: {{ isOneToOneForm(form.isOneToOne) }}</div>
          <div class="text item">招生对象: {{ form.gradeId }}</div>
          <div class="text item">招生人数: {{ form.stuCount }}</div>
          <div class="text item">上课时间: {{ form.classTime }}</div>
          <div class="text item">上课时长: {{ form.classHour }}</div>
        </el-col>
        <el-col :span="12">
          <div class="text item">周次: {{ form.week }}</div>
          <div class="text item">总课时: 共{{ form.classAll }}节</div>
          <div class="text item">课程费用: {{ form.courseCost}}元</div>
          <div class="text item">餐费: {{ form.meals}}元</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>课程介绍</span>
      </div>
      <div class="text item">课程介绍: {{ form.introduce }}</div>
    </el-card>
  </el-dialog>
</template>

<script>
import { formatTime } from '@/utils/index'
export default {
  name: "eDetailModal",
  props: {
    detail: {
      type: Boolean,
      required: true
    }
  },
   watch:{
          detail:function (e) {
            this.getDicts("manage_time").then(response => {
              this.manageTimeOptions = response.data;
            });
            this.getDicts("is_meal").then(response => {
              this.isMealOptions = response.data;
            });
            this.getDicts("certificate_type").then(response => {
              this.certificateTypeOptions = response.data;
            });
            getOrder().then(response => {
              this.sysGrades = response.sysGrades;
              this.students = response.students;
            });
            this.dialogVisible=e;
          },
          courseData:function (e) {
             this.signUpForm.storeId=e.storeId;
             this.signUpForm.courseId=e.id;
          },
        },
  data() {
    return {

      // 是否一对一字典
      isOneToOneOptions: [],
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
    // 是否一对一字典翻译
    isOneToOneFormat(row, column) {
      return this.selectDictLabel(this.isOneToOneOptions, row.isOneToOne);
    },
    isOneToOneForm(e){
     return this.selectDictLabel(this.isOneToOneOptions, row.isOneToOne);
        },
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
