<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <e-row >
        <el-col :span="12" >
           <el-card style="height: 377px;overflow: auto;">
            <div slot="header">
              <span>优惠券信息</span>
            </div>
             <el-row>
              <el-col :span="24">
                <div class="text item">优惠券名称: {{ form.cname }}</div>
              </el-col>
            </el-row>
            <el-row>
                <div class="text item">优惠券领取开始时间: {{ parseTime(form.leadStartTime) }}</div>
            </el-row>
             <el-row>
               <div class="text item">优惠券领取结束时间: {{ parseTime(form.leadEndTime) }}</div>
             </el-row>
             <el-row>
               <div class="text item">创建时间: {{ parseTime(form.createTime)}}</div>
             </el-row>
             <el-row>
              <el-col :span="12">
                <div class="text item">限量: {{ form.isPermanent }}</div>
              </el-col>
               <el-col :span="12">
                 <div class="text item">优惠券领取数量: {{ form.leadCount }}</div>
               </el-col>
            </el-row>
             <el-row v-if="form.isPermanent=='是'">
               <el-col :span="12">
                 <div class="text item">优惠券总数量: {{ form.totalCount }}</div>
               </el-col>
              <el-col :span="12">
                <div class="text item">优惠券剩余数量: {{ form.remainCount }}</div>
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
          </el-card>
        </el-col>
        <el-col :span="12" >
          <el-card style="height: 377px;overflow: auto;">
            <div slot="header">
              <span>领用记录</span>
            </div>
            <div>
              <el-row>
                <el-col :span="24" v-for="d in yunStoreCouponReceives" :key="d.id" >
                  <el-card >
                    <div class="bottom clearfix">
                      <time class="time">
                        {{ parseTimeBefore(d.createTime) }}
                      </time>
                      <el-button type="text" class="button">{{ d.createBy }}</el-button>
                    </div>
                  </el-card>
                </el-col>
              </el-row>
            </div>
          </el-card>
        </el-col>
      </e-row>
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
import {getIssue} from "@/api/store/couponIssue";
import { formatTime } from '@/utils/index'
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
              this.yunStoreCouponReceives=response.yunStoreCouponReceives;
              if(this.form.isPermanent==1){
                this.form.isPermanent="否";
              }else{
                this.form.isPermanent="是";
              }
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
          //领用记录
          yunStoreCouponReceives:[],
        }
      },
  methods: {
        handleClose() {
          this.$emit('closeDetail', false);
        },
        parseTimeBefore(e){
          return formatTime((new Date(e)).getTime() / 1000);
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
  .time {
    font-size: 13px;
    color: #999;
  }

  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }

  .clearfix:after {
    clear: both
  }
  .el-card__body{
    height: 336px;
    overflow: hidden;
  }
</style>
