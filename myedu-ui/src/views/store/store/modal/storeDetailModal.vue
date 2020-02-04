<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>门店基本信息</span>
      </div>
      <el-row>
        <el-col :span="8">
          <div class="text item">门店名称:{{ form.name }}</div>
        </el-col>
        <el-col :span="8">
          <div class="text item">负责人: {{ form.manager }}</div>
        </el-col>
        <el-col :span="8">
          <div class="text item">负责人电话: {{ form.managerPhone }}</div>
        </el-col>
      </el-row>
      <el-row>
        <br>
      </el-row>
      <el-row>
        <el-col :span="8">
          <div class="text item">类型:
            <span v-for="item in storeTypes">
               <span v-for="item1 in form.storeTypeIds" v-if="item1==item.id">
                 {{ item.name }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="text item">标贴:
            <span v-for="item in storeLabels">
               <span v-for="item1 in form.storeLabelIds" v-if="item1==item.id">
                 {{ item.name }}
               </span>
            </span>
          </div>
        </el-col>
      </el-row>
      <el-row>
        <br>
      </el-row>
      <el-row>
        <el-col :span="24">
          <div class="text item">地址: {{ form.province }}</div>
        </el-col>
      </el-row>

      <el-row>
        <br>
      </el-row>
      <el-row>
        <el-col :span="24">
          <div class="text item">街道: {{ form.address }}</div>
        </el-col>
      </el-row>
      <el-row>
        <br>
      </el-row>
      <el-row>
        <el-col :span="8">
          <div class="text item">经度:{{ form.mapX }}</div>
        </el-col>
        <el-col :span="8">
          <div class="text item">纬度: {{ form.mapY }}</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>营业执照</span>
      </div>
        <el-carousel >
          <el-carousel-item v-for="item in licenseList" :key="item">
            <img  :src="item.url" alt="" height="100%" width="100%">
          </el-carousel-item>
        </el-carousel>
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
            });
          },
        },
        data() {
          return {
            title:"门店详情",
            dialogVisible:false,
            //门店类型列表
            storeTypes:[],
            //标签类型列表
            storeLabels:[],
            //营业执照列表
            licenseList:[],
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
