<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-row>
      <el-col :span="12">
        <el-row>
          <el-col :span="4" style="margin-top: 10px">
            <span>充值金额:</span>
          </el-col>
          <el-col :span="8">
            <el-input-number min="0" controls-position="right" v-model="rechargeAmount" placeholder="请输入充值金额" >
            </el-input-number>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-row>
    <br>
    </el-row>
    <el-row>
      <el-col :span="12">
        <el-radio-group label="支付方式" v-model="form.payWay" size="mini" @change="changeWay">
          <el-radio label="1" value="1" border>支付宝</el-radio>
          <el-radio label="2" value="2" border>微信</el-radio>
        </el-radio-group>
      </el-col>
    </el-row>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import {orderPay} from "@/api/order/order";
    export default {
      name: "PayModal",
      props: {
        pay: null,
        currentData:null,
      },
      watch:{
        pay:function (e) {
          this.dialogVisible=e;
          this.form.payWay=0;
        },
        currentData:function (e) {
          this.form = e;
        },
        url(newVal, oldVal) {
          if (newVal && this.newWin) {
            this.newWin.sessionStorage.clear()
            this.newWin.location.href = newVal
            // 重定向后把url和newWin重置
            this.url = ''
            this.newWin = null
          }
        }
      },
      data() {
        return {
          title:"支付页面",
          dialogVisible:false,
          rechargeAmount:null,
          form:{},
          url: '',
          newWin:null,
        }
      },
      methods: {
        handleClose() {
          this.$emit('closePayModal', false);
        },
        changeWay(e){
          this.handleClose();
          this.form.rechargeAmount=this.rechargeAmount;
          // 先打开一个空的新窗口，再请求
          this.newWin = window.open()
          let url = ''
          if (/(Android)/i.test(navigator.userAgent)) { // 判断是否为Android手机
            url = '/order/order/toPayAsWeb'
          } else if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) { // 判断是否为苹果手机
            url = '/order/order/toPayAsWeb'
          } else {
            url = '/order/order/toPayAsPC'
          }
          orderPay(url, this.form).then(res => {
            this.loading = false
            this.url = res.url
          }).catch(err => {
            this.loading = false
          })
        }
      },

    }
</script>

<style scoped>

</style>
