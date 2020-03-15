<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="70%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>门店基本信息</span>
      </div>
      <el-row>
        <el-col :span="8" style="height: 250px;">
          <img   :src="imageView+'/'+form.logo" :onerror="defaultImg" alt="" height="100%" width="100%" style="border-radius:10px">
        </el-col>
        <el-col :span="8" >
          <el-row>
             <div class="text item" style="margin-left: 5%">门店名称:{{ form.name }}</div>
          </el-row>
          <br>
          <el-row>
            <el-col :span="10">
              <div class="text item" style="margin-left: 10%">负责人: {{ form.manager }}</div>
            </el-col>
            <el-col :span="14">
              <div class="text item" style="margin-left: 5%">负责人电话: {{ form.managerPhone }}</div>
            </el-col>
          </el-row>
          <br>
          <e-row>
            <div class="text item" style="margin-left: 5%">类型:
              <span v-for="item in storeTypes">
               <span v-for="item1 in form.storeLabelIds" v-if="item1==item.id">
                 {{ item.name }}
               </span>
            </span>
            </div>
          </e-row>
          <br>
          <e-row>
            <div class="text item" style="margin-left: 5%">标贴:
              <span v-for="item in storeLabels">
               <span v-for="item1 in form.storeTypeIds" v-if="item1==item.id">
                 {{ item.name }}
               </span>
            </span>
            </div>
          </e-row>
          <br>
          <e-row>
            <div class="text item" style="margin-left: 5%">地址: {{ form.province }}</div>
          </e-row>
          <br>
          <e-row>
            <div class="text item" style="margin-left: 5%">街道: {{ form.address }}</div>
          </e-row>
          <br>
          <e-row>
            <el-col :span="12">
              <div class="text item" style="margin-left: 10%">经度:{{ form.mapX }}</div>
            </el-col>
            <el-col :span="12">
              <div class="text item" style="margin-left: 10%">纬度: {{ form.mapY }}</div>
            </el-col>
          </e-row>
          <br><br>
          <e-row>
            <el-col :span="24">
              <div class="text item" style="margin-left: 5%" v-if="form.endExperienceTime!=null">体验结束日期: {{ parseTime(form.endExperienceTime) }} </div>
            </el-col>
          </e-row>
        </el-col>
        <el-col :span="8" style="height: 250px;">
          <video v-if="form.video!=null" :src="imageView+'/'+form.video" class="avatar" controls="controls" style="width: 100%;height: 100%;margin-top: -8%;"></video>
          <img  v-else src="error" :onerror="defaultImg" alt="" height="100%" width="100%">
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <el-row>
        <el-col :span="8" >
          <el-card>
            <div slot="header">
              <span>营业执照</span>
            </div>
            <el-carousel v-if="licenseList!=null&&licenseList.length>0">
              <el-carousel-item v-for="item in licenseList" :key="item">
                <img  :src="imageView+'/'+item.url" :onerror="defaultImg"  alt="" height="100%" width="100%">
              </el-carousel-item>
            </el-carousel>
            <img  v-else src="error" :onerror="defaultImg" alt="" height="297px" width="100%">
          </el-card>
        </el-col>
        <el-col :span="8" >
          <el-card>
            <div slot="header">
              <span>健康证</span>
            </div>
            <el-carousel v-if="healthsList!=null&&healthsList.length>0">
              <el-carousel-item v-for="item in healthsList" :key="item">
                <img  :src="imageView+'/'+item.url" :onerror="defaultImg" alt="" height="100%" width="100%">
              </el-carousel-item>
            </el-carousel>
            <img  v-else src="33" :onerror="defaultImg" alt="" height="297px" width="100%">
          </el-card>
        </el-col>
        <el-col :span="8" >
          <el-card>
            <div slot="header">
              <span>照片墙</span>
            </div>
            <el-carousel v-if="photosList!=null&&photosList.length>0">
              <el-carousel-item v-for="item in photosList" :key="item">
                <img  :src="imageView+'/'+item.url" :onerror="defaultImg" alt="" height="100%" width="100%">
              </el-carousel-item>
            </el-carousel>
            <img  v-else src="33" :onerror="defaultImg" alt="" height="297px" width="100%">
          </el-card>
        </el-col>
      </el-row>
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getStore } from "@/api/store/store";
    export default {
        name: "storeDetailModal",
        props: {
          storeDetail: null,
          currentData:null,
        },
        watch:{
          storeDetail:function (e) {
            this.dialogVisible=e;
          },
          currentData:function (e) {
            getStore(e.id).then(response => {
              this.form = response.data;
              this.storeTypes = response.storeTypes;
              this.storeLabels = response.storeLabels;
              this.form.storeTypeIds = response.storeTypeIds;
              this.form.storeLabelIds = response.storeLabelIds;
              this.licenseList=JSON.parse(this.form.license);
              this.photosList=JSON.parse(this.form.photos);
              this.healthsList=JSON.parse(this.form.healths);
            });
          },
        },
        data() {
          return {
            title:"门店详情",
            dialogVisible:false,
            defaultImg: 'this.src="' + require("@/assets/image/deaufalt.jpg") + '"',
            //门店类型列表
            storeTypes:[],
            //标签类型列表
            storeLabels:[],
            imageView: process.env.VUE_APP_BASE_API,
            //营业执照列表
            licenseList:[],
            //照片墙
            photosList:[],
            //健康证
            healthsList:[],
            //表单参数
            form:{}
          }
        },
      methods: {
        handleClose() {
          this.$emit('closeStoreDetail', false);
        },
      }
    }
</script>

<style scoped>

</style>
