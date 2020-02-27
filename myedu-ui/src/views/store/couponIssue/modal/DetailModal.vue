<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>优惠券信息</span>
      </div>
       <el-row>
        <el-col :span="24">
          <div class="text item">优惠券名称: {{ form.cname }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <div class="text item">优惠券领取开始时间: {{ parseTime(form.leadStartTime) }}</div>
        </el-col>
         <el-col :span="12">
          <div class="text item">优惠券领取结束时间: {{ parseTime(form.leadEndTime) }}</div>
        </el-col>
      </el-row>
       <el-row>
        <el-col :span="12">
          <div class="text item">限量: {{ form.isPermanent }}</div>
        </el-col>
         <el-col :span="12">
          <div class="text item">优惠券总数量: {{ form.totalCount }}</div>
        </el-col>
      </el-row>
       <el-row>
        <el-col :span="12">
          <div class="text item">优惠券剩余领取数量: {{ form.remainCount }}</div>
        </el-col>
         <el-col :span="12">
          <div class="text item">优惠券剩领取数量: {{ form.leadCount }}</div>
        </el-col>
      </el-row>
       <el-row>
        <el-col :span="12">
          <div class="text item">状态:
             <span v-for="item in statusOptions">
               <span v-if="form.status==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
           </div>
        </el-col>
         <el-col :span="12">
          <div class="text item">创建者: {{ form.createBy }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <div class="text item">创建时间: {{ parseTime(form.createTime)}}</div>
        </el-col>
      </el-row>
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {getIssue} from "@/api/store/couponIssue";
export default {
  name: "DetailModal",
  props: {
    issueDetail: null,
    currentData:null,
  },
   watch:{
          issueDetail:function (e) {
            this.dialogVisible=e;
            this.getDicts("coupon_type").then(response => {
            this.statusOptions = response.data;
            });
          },
          currentData:function (e) {

            getIssue(e.id).then(response => {
              this.form = response.data;
            });
          },
        },
      data() {
        return {
          title:"优惠券发布详情",
          dialogVisible:false,
          // 状态字典
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
