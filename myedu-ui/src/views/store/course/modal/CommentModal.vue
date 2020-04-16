<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="10px">
      <el-row>
          <el-col :span="24">
              <!-- <el-form-item label="评价类型">
              <el-select v-model="form.type" placeholder="请选择评价类型">
                <el-option
                  v-for="dict in typeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item> -->
          </el-col>
      </el-row>
      <el-row>
         <el-col :span="24">
              <el-form-item   prop="content">
                <el-input  type="textarea"  :rows="5" v-model="form.content"></el-input>
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
  import {comment} from "@/api/store/comment";
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
         this.form.id = e.id;
        },
      },
        data() {
          return {
            title:"家长课程评价",
            dialogVisible:false,
            form:{content:null},
            // 表单校验
            rules: {
              content: [
                { required: true, message: "评价内容不能为空", trigger: "blur" }
              ],
            }
          }
        },
      methods: {
        handleClose() {
          this.resetForm("form");
          this.$emit('closeCommentModal', false);
        },
        /** 提交按钮 */
        submitForm: function() {
          this.$refs["form"].validate(valid => {
            if (valid) {
              comment(this.form).then(response => {
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
