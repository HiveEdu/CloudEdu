<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="replyForm" :model="replyForm" :rules="replyFormRules" label-width="80px">
        <el-form-item label="答复内容" prop="replyContent" style="margin-top:10px">
                <el-input v-model="replyForm.replyContent" placeholder="请输入答复内容" type="textarea"></el-input>
        </el-form-item> 
        <el-form-item label="答复状态" prop="status" style="margin-top:10px">
                <el-radio-group v-model="replyForm.status" > 
                  <el-radio key="0" label="0" border>投诉中</el-radio>
                  <el-radio key="1" label="1" border>已回复</el-radio>
                </el-radio-group>
        </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {updateComplaint } from "@/api/parents/complaint";
    export default {
        name: "replyModal", 
        props: {
          reply: null,
          currentData:null,
        },
      watch:{
        reply:function (e) {
          this.dialogVisible=e;
        },
        currentData:function (e) {
          this.replyForm=e;
        },
      },
        data() {
          return {
            title:'答复',
            dialogVisible:false,
            // 表单参数
            replyForm:{
              name:null
            },
            // 表单校验
            replyFormRules:{},
        
          }
        },
        methods: {
          handleClose() {
            this.$emit('closeReply', false);
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["replyForm"].validate(valid => {
            if (valid) {
                updateComplaint(this.replyForm).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("答复成功");
                    this.handleClose();
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
