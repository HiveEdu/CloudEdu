<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>动态内容</span>
      </div>
       <div class="text item" style="margin-left: 5%">{{ form.content }}</div>
    </el-card>
    <el-card>
      <div slot="header">
        <span>动态照片</span>
      </div>
        <el-carousel >
          <el-carousel-item v-for="item in fileList" :key="item">
            <img  :src="imageView+'/'+item.url" alt="" height="100%" width="100%">
          </el-carousel-item>
        </el-carousel>
    </el-card>
    <el-card>
       <el-row>
         <el-col :span="12">
          <div class="text item">动态类型: 
            <span v-for="item in typeOptions">
               <span v-if="form.type==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="text item" style="margin-left: 5%">发布时间:{{ parseTimeBefore(form.createTime)}}</div>
        </el-col>
      </el-row> 
      <el-row>
      <br/>
      </el-row>
      <el-row>
        <el-col :span="12">
          <div class="text item" style="margin-left: 5%">点赞数:{{ form.likes}}</div>
        </el-col>
        <el-col :span="12">
          <div class="text item" style="margin-left: 5%">评论数:{{ form.comments}}</div>
        </el-col>
      </el-row>  
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getDynamic } from "@/api/dynamic/dynamic";
  import { formatTime } from '@/utils/index'
    export default {
        name: "dyDetailModal",
        props: {
          dyDetail: null,
          currentData:null,
        },
        watch:{
        dyDetail:function (e) {
            this.dialogVisible=e;
          },
          currentData:function (e) {
            this.getDicts("dynamic_type").then(response => {
            this.typeOptions = response.data;
          });
            getDynamic(e.id).then(response => {
              this.form = response.data;
              this.fileList=JSON.parse(this.form.picture);
            });
          },
        },
        data() {
          return {
            title:"动态详情",
            dialogVisible:false,
            imageView: process.env.VUE_APP_BASE_API,
             //动态类型字典
            typeOptions: [],
            //动态照片列表
            fileList:[],
            //表单参数
            form:{}
          }
        },
      methods: {
         parseTimeBefore(e){
          return formatTime((new Date(e)).getTime() / 1000);
        },
        handleClose() {
          this.$emit('closeDetail', false);
        },
      }
    }
</script>

<style scoped>

</style>
