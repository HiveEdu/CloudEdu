<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="publishForm" :model="publishForm" :rules="publishFormRules" label-width="120px">
      <el-form-item label="优惠券名称">
        <el-input v-model="publishForm.cname":disabled="true" />
      </el-form-item>
      <el-form-item label="领取开启时间"  prop="leadStartTime">
        <template>
          <el-date-picker
            v-model="publishForm.leadStartTime"
            type="datetime"
            placeholder="选择日期时间"
            style="width: 100%"
          />
        </template>
      </el-form-item>
      <el-form-item label="券领结束时间"  prop="leadEndTime">
        <template>
          <el-date-picker
            v-model="publishForm.leadEndTime"
            type="datetime"
            placeholder="选择日期时间"
            style="width: 100%"
          />
        </template>
      </el-form-item>
      <el-form-item label="是否不限量" prop="isPermanent">
        <el-radio v-model="publishForm.isPermanent" :label="1">不限量</el-radio>
        <el-radio v-model="publishForm.isPermanent" :label="0">限量</el-radio>
      </el-form-item>
      <el-form-item label="发布数量" v-if="publishForm.isPermanent==0">
        <el-input-number :min="0"  style="width: 100%;" controls-position="right"  v-model="publishForm.totalCount" />
      </el-form-item>
      <el-form-item label="状态"  prop="status">
        <el-radio v-model="publishForm.status" :label="1">开启</el-radio>
        <el-radio v-model="publishForm.status" :label="0">关闭</el-radio>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { publishCou } from "@/api/store/coupon";
    export default {
        name: "publishModal",
        props: {
          publish: null,
          courseData:null,
        },
        watch:{
          publish:function (e) {
            this.dialogVisible=e;
          },
          courseData:function (e) {
            this.publishForm.cid=e.id;
            this.publishForm.cname=e.title;
          },
        },
        data() {
          return {
            title:"优惠卷发布",
            dialogVisible:false,
            // 表单参数
            publishForm:{},
            // 表单校验
            publishFormRules: {
              leadStartTime: [
                { required: true, message: "领卷开启时间不能为空", trigger: "blur" }
              ],
              leadEndTime: [
                { required: true, message: "领卷结束时间不能为空", trigger: "blur" }
              ],
              isPermanent: [
                { required: true, message: "是否限量不能为空", trigger: "blur" }
              ],
            }
          }
        },
        methods: {
          handleClose() {
            this.$emit('closePublish', false);
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["publishForm"].validate(valid => {
              if (valid) {
                publishCou(this.publishForm).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("发布成功");
                    this.$emit('closePublish', false);
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
