<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-form ref="rechargeForm" :model="rechargeForm" :rules="rechargeFormRules" label-width="80px">

      <el-row>
        <el-col :span="12">
          <span style="margin-left: 10px">目前总金额:{{rechargeForm.totalAmount}}元</span>
        </el-col>
        <el-col :span="12">
          <span style="margin-left: 10px">充值后金额:{{rechargeForm.totalAmountAfte}}元</span>
        </el-col>
      </el-row>
      <el-row style="margin-top: 2%">
        <el-col :span="23">
          <el-form-item label="充值金额" prop="rachargeAmount">
            <el-input-number min="0" @change="amountChange" controls-position="right" style="width: 100%;" v-model="rechargeForm.rachargeAmount" placeholder="请输入充值金额" />
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
  import {racharge,toAliPay} from "@/api/account/account";
    export default {
        name: "rechargeModal",
        props: {
          recharge: null,
          currentData:null,
        },
      watch:{
        recharge:function (e) {
          this.dialogVisible=e;
        },
        currentData:function (e) {
          this.rechargeForm=e;
          this.rechargeForm.totalAmount=e.totalAmount;
          this.rechargeForm.totalAmountAfte=e.totalAmount;

        },
      },
        data() {
          return {
            newWin:null,
            url: '',
            title:"充值",
            dialogVisible:false,
            // 表单参数
            rechargeForm:{
              rachargeAmount:null
            },
            // 表单校验
            rechargeFormRules:{},
          }
        },
        methods: {
          amountChange(e){
            this.rechargeForm.totalAmountAfte= this.rechargeForm.totalAmount+e;
          },
          handleClose() {
            this.$emit('closeRecharge', false);
          },
          /** 提交按钮 */
          submitForm: function() {
            this.$refs["rechargeForm"].validate(valid => {
              if (valid) {
                // 先打开一个空的新窗口，再请求
                // this.newWin = window.open()
                // let url = ''
                // if (/(Android)/i.test(navigator.userAgent)) { // 判断是否为Android手机
                //   url = '/account/account/toPayAsWeb'
                // } else if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) { // 判断是否为苹果手机
                //   url = '/account/account/toPayAsWeb'
                // } else {
                //   url = '/account/account/toPayAsPC'
                // }
                // toAliPay(url, this.rechargeForm).then(res => {
                //   this.loading = false
                //   this.url = res
                // }).catch(err => {
                //   this.loading = false
                //   console.log(err.response.data.message)
                // })
                racharge(this.rechargeForm).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("充值成功");
                    this.$emit('closeRecharge', false);
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
