<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>学生信息</span>
      </div>
       <el-row>
        <el-col :span="12">
          <div class="text item">学生姓名: {{ form.studentAssName }}</div>
        </el-col>     
        <el-col :span="12">
          <div class="text item">年级: {{ form.gradeName }}</div>
        </el-col>     
         <el-col :span="12">
          <div class="text item">证件类型:
            <span v-for="item in certificateTypeOptions">
               <span v-if="form.certificateType==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>    
        <el-col :span="12">
          <div class="text item">证件号码: {{ form.certificateNum }}</div>
        </el-col>     
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>订单信息</span>
      </div>
       <el-row>
        <el-col :span="24">
          <div class="text item">订单编号: {{ form.num }}</div>
        </el-col> 
         <el-col :span="12">
          <div class="text item">是否含餐: 
            <span v-for="item in isMealOptions">
               <span v-if="form.isMeal==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="12">
           <div class="text item">餐费: {{ form.totalNum }}</div>
        </el-col> 
        <el-col :span="12">
           <div class="text item">实际支付: {{ form.payPrice }}</div>
        </el-col>
        <el-col :span="12">
           <div class="text item">支付方式: {{ form.payTypeName }}</div>
        </el-col>
         <el-col :span="12">
            <div class="text item">订单状态:
            <span v-for="item in statusOptions">
               <span v-if="form.status==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>
         <el-col :span="12">
           <div class="text item">总金额: {{ form.totalMoney }}元</div>
        </el-col>
         <el-col :span="12">
           <div class="text item">支付时间: {{ parseTime(form.payTime) }}</div>
        </el-col>
        <el-col :span="12">   
          <div class="text item">创建时间: {{ parseTime(form.createTime) }}</div> 
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>门店信息</span>
      </div>
      <el-row>
        <el-col :span="24">
          <div class="text item">门店名称: {{ form.storeName }}</div>
        </el-col>
         <el-col :span="24">
          <div class="text item">课程名称: {{ form.courseName }}</div>
        </el-col>
         <el-col :span="12">
           <div class="text item">入学时间: {{ parseTime(form.enrolTime)}}</div>
        </el-col>
        <el-col :span="12">
           <div class="text item">托管时长: {{ form.managTime }}</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>备注信息</span>
      </div>
      <!-- <div class="text item">{{ form.remark }}</div> -->
    </el-card>
  </el-dialog>
</template>

<script>
import {getOrder} from "@/api/order/order";
export default {
  name: "DetailModal",
  props: {
    orderDetail: null,
    currentData:null,
  },
   watch:{
          orderDetail:function (e) {
            this.dialogVisible=e;
             this.getDicts("is_meal").then(response => {
              this.isMealOptions = response.data;
            });
            this.getDicts("certificate_type").then(response => {
              this.certificateTypeOptions = response.data;
            });
            this.getDicts("order_status").then(response => {
              this.statusOptions = response.data;
            });
            this.getDicts("manage_time").then(response => {
              this.manageTimeOptions = response.data;
            });
          },
          currentData:function (e) {

            getOrder(e.id).then(response => {
              this.form = response.data;
              this.gradeId = response.gradeId;
              this.sysGrades = response.sysGrades;
            });
          },
        },
      data() {
        return {
          title:"订单详情",
          dialogVisible:false,
          // 是否含餐字典
          isMealOptions: [],
          //托管时长字典
          manageTimeOptions:[],
          // 证件类型字典
          certificateTypeOptions: [],
          // 支付状态字典
          statusOptions: [],
          form:{},
        }
      },
  methods: {
        handleClose() {
          this.$emit('closeDetail', false);
        },
      }
    }
</script>

<style scoped>
  .text {
    font-size: 12px;
  }

  .item {
    padding: 6px 0;
  }

</style>
