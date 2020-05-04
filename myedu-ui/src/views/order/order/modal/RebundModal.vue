<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="10px">
      <el-row>
        <el-col :span="24">
          <el-form-item   prop="refundReason">
            <el-input  type="textarea"  :rows="5" v-model="form.refundReason"></el-input>
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
  import { updateOrder } from "@/api/order/order";
    export default {
        name: "RebundModal",
        props: {
          rebund: null,
          currentData:null,
        },
        watch:{
          rebund:function (e) {
            this.dialogVisible=e;
          },
          currentData:function (e) {
            this.form.id=e.id;
          },
        },
      data() {
        return {
          title: "退款申请",
          dialogVisible: false,
          form: {
            content: null,
          },
          // 表单校验
          rules: {
            refundReason: [
              {required: true, message: "退款原因不能为空", trigger: "blur"}
            ],
          }
        }
      },
      methods: {
        handleClose() {
          this.resetForm("form");
          this.$emit('closeRebundModal', false);
        },

          /** 提交按钮 */
          submitForm: function() {
            this.form.status=6;//退款申请中
            this.$refs["form"].validate(valid => {
              if (valid) {
                if (this.form.id != undefined) {
                  updateOrder(this.form).then(response => {
                    if (response.code === 200) {
                      this.msgSuccess("退款申请提交成功");
                      this.resetForm("form");
                      this.$emit('closeRebundModal', false);
                    } else {
                      this.msgError(response.msg);
                    }
                  });
                }
              }
            });
        },
      }
    }
</script>

<style scoped>

</style>
