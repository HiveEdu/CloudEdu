<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="10px">
      <el-row>
         <el-col :span="24">
              <el-form-item   prop="content">
                <el-input  type="textarea"  :rows="5" v-model="form.content"></el-input>
              </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item v-model="form.scoreContent">
            <div class="block" v-for="item in gradingOptions">
              <span> {{item.dictLabel}}</span>
                <el-row>
                  <el-rate
                    v-model="item.dictValue"
                    show-text
                    score-template="{value}"
                   >
                  </el-rate>
                </el-row>
            </div>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">提 交</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import {addComment} from "@/api/store/comment";
    export default {
        name: "CommentModal",
        props: {
          comment: null,
          currentData:null,
        },
        watch:{
          comment:function (e) {
            this.dialogVisible=e;
          },
          currentData:function (e) {
           this.form.courseId = e.id;
           this.form.storeId = e.storeId;
          },
        },
        data() {
          return {
            title:"家长课程评价",
            dialogVisible:false,
            form:{
              content:null,
              scoreContent:null},
            // 评分标准字典
            gradingOptions: [],
            // 表单校验
            rules: {
              content: [
                { required: true, message: "评价内容不能为空", trigger: "blur" }
              ],
            }
          }
        },
      created() {
        this.getDicts("GradingPolicies").then(response => {
          this.gradingOptions = response.data;
          for(let i=0;i<this.gradingOptions.length;i++) {
            this.gradingOptions[i].dictValue=0;
          }
        });
      },
      methods: {
        handleClose() {
          this.resetForm("form");
          this.$emit('closeCommentModal', false);
        },
        /** 提交按钮 */
        submitForm: function() {
          debugger
          let jsonObj="";
          for(let i=0;i<this.gradingOptions.length;i++) {
            if(i<this.gradingOptions.length-1){
              jsonObj=jsonObj+'"'+this.gradingOptions[i].dictLabel+'"'+':'+this.gradingOptions[i].dictValue+',';
            }else{
              jsonObj=jsonObj+'"'+this.gradingOptions[i].dictLabel+'"'+':'+this.gradingOptions[i].dictValue;
            }
          }
          this.form.scoreContent=jsonObj;
          this.$refs["form"].validate(valid => {
            if (valid) {
              addComment(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("评论成功");
                  this.resetForm("form");
                  this.$emit('closeCommentModal', false);
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
