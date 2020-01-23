<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="signUpForm" :model="signUpForm" :rules="signUpFormRules" label-width="80px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="学生" prop="studentId">
            <el-select v-model="signUpForm.studentId"  placeholder="请输入关联学生"  style="width: 100%;">
              <el-option
                v-for="item in students"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>

          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="年级" prop="greadId">
            <el-select v-model="signUpForm.greadId"  placeholder="请输入关联年级"  style="width: 100%;">
              <el-option
                v-for="item in sysGrades"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="托管时长" prop="managTime">
            <el-select style="width: 100%" v-model="signUpForm.managTime" placeholder="请选择托管时长">
              <el-option
                v-for="dict in manageTimeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否含餐">
            <el-radio-group v-model="signUpForm.isMeal">
              <el-radio
                v-for="dict in isMealOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="入学时间" prop="enrolTime">
            <el-date-picker clearable size="small" style="width: 100%"
                            v-model="signUpForm.enrolTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择入学时间">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="证件类型">
            <el-select style="width: 100%" v-model="signUpForm.certificateType" placeholder="请选择证类型">
              <el-option
                v-for="dict in certificateTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="学生姓名" prop="studentName">
            <el-input v-model="signUpForm.studentName" placeholder="请输入学生姓名" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="证件号">
            <el-input v-model="signUpForm.certificateNum" placeholder="请选择证件号" />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import {addOrder,getOrder} from "@/api/order/order";
    export default {
        name: "signUpModal",
        props: {
          signUp: null,
          courseData:null,
        },
        watch:{
          signUp:function (e) {
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
            // 年级列表
            sysGrades: [],
            // 学生列表
            students:[],
            dialogVisible:false,
            title:"报名",
            // 是否含餐字典
            isMealOptions: [],
            //托管时长字典
            manageTimeOptions:[],
            // 证件类型字典
            certificateTypeOptions: [],
            // 表单参数
            signUpForm: {},
            // 表单校验
            signUpFormRules: {
              studentId: [
                { required: true, message: "学生不能为空", trigger: "blur" }
              ],
              greadId: [
                { required: true, message: "年级不能为空", trigger: "blur" }
              ],
              enrolTime: [
                { required: true, message: "入学时间不能为空", trigger: "blur" }
              ],

            }
          }
        },
        methods: {
          handleClose() {
            this.$emit('closeSignUp', false);
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["signUpForm"].validate(valid => {
              if (valid) {
                  addOrder(this.signUpForm).then(response => {
                    if (response.code === 200) {
                      this.msgSuccess("报名成功");
                      this.$emit('closeSignUp', false);
                    } else {
                      this.msgError(response.msg);
                    }
                  });
                }
            });
          },
        }
    }
</script>

<style scoped>

</style>
