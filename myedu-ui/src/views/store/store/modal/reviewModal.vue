<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="reviewForm" :model="reviewForm" :rules="reviewFormRules" label-width="80px">
         <el-row style="margin-left: 8%;font-size: larger;">
          <el-col :span="23">
             <span>门店名称：{{reviewForm.name}}</span>
          </el-col>
        </el-row>
        <el-form-item label="审核" prop="status" style="margin-top:10px">
                <el-radio-group v-model="reviewForm.status" > 
                  <el-radio key="1" label="1">审核通过</el-radio>
                  <el-radio key="2" label="2">审核驳回</el-radio>
                </el-radio-group>
        </el-form-item>
        <el-form-item label="驳回原因" prop="rejectResion" v-if="reviewForm.status==2">
               <el-input v-model="reviewForm.rejectResion" placeholder="请输入驳回原因" type="text"></el-input>
        </el-form-item>
        
    </el-form>
 
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {updateStore } from "@/api/store/store";
    export default {
        name: "reviewModal",
       
        props: {
          review: null,
          currentData:null,
        },
      watch:{
        review:function (e) {
          this.dialogVisible=e;
        },
        currentData:function (e) {
          this.reviewForm=e;
        },
      },
        data() {
          return {
             // 审核状态字典
            checkStatusOptions: [],
            title:'审核',
            dialogVisible:false,
            // 表单参数
            reviewForm:{
              name:null
            },
            // 表单校验
            reviewFormRules:{},
        
          }
        },
        methods: {
          handleClose() {
            this.$emit('closeReview', false);
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["reviewForm"].validate(valid => {
            if (valid) {
                updateStore(this.reviewForm).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("审核成功");
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
