<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="withdrawForm" :model="withdrawForm" :rules="withdrawFormRules" label-width="80px">

      <el-row>
        <el-col :span="12">
          <span style="margin-left: 10px">目前总金额:{{withdrawForm.totalAmount}}元</span>
        </el-col>
        <el-col :span="12">
          <span style="margin-left: 10px">提现后金额:{{withdrawForm.totalAmountAfte}}元</span>
        </el-col>
      </el-row>
      <el-row style="margin-top: 2%">
        <el-col :span="23">
          <el-form-item label="提现金额" prop="rachargeAmount">
            <el-input-number min="0" @change="amountChange" controls-position="right" style="width: 100%;" v-model="withdrawForm.rachargeAmount" placeholder="请输入提现金额" />
          </el-form-item>
        </el-col>
        <el-col :span="1" style="margin-top:6px; ">
          <span >元</span>
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
  import {withdraw,toAliPay} from "@/api/account/account";
    export default {
        name: "withdrawModal",
        props: {
          withdraw: null,
          currentData:null,
        },
      watch:{
        withdraw:function (e) {
          this.dialogVisible=e;
        },
        currentData:function (e) {
          this.withdrawForm=e;
          this.withdrawForm.totalAmount=e.totalAmount;
          this.withdrawForm.totalAmountAfte=e.totalAmount;

        },
      },
      data() {
        return {
          title:"提现",
          dialogVisible:false,
          // 表单参数
          withdrawForm:{
            rachargeAmount:null
          },
          // 表单校验
          withdrawFormRules:{},
        }
      },
      methods: {
        amountChange(e){
          this.withdrawForm.totalAmountAfte= this.withdrawForm.totalAmount-e;
        },
        handleClose() {
          this.$emit('closeWithdraw', false);
        },
        /** 提交按钮 */
        submitForm: function() {
          this.$refs["withdrawForm"].validate(valid => {
            if (valid) {
              withdraw(this.withdrawForm).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("提现成功");
                  this.$emit('closeWithdraw', false);
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
