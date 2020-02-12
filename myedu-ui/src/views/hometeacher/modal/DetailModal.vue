<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>基本信息</span>
      </div>
      <el-row>
        <el-col :span="12">
         <div class="text item">所授课程:
           <span v-for="item in sysCourses">
               <span v-for="item1 in form.courseId" v-if="item1==item.id">
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
          <el-col :span=12>
              <div class="text item">学校: {{ form.school }}
              </div>
          </el-col>
          <el-col :span="12">
              <div class="text item">学历: 
                <span v-for="item in Educations">
                <span v-if="form.education==item.dictValue">
                  {{ item.dictLabel }}
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
                <div class="text item">获得荣誉: {{ form.awards }}
                </div>
            </el-col>
      </el-row>
      <el-row>
        <br>
      </el-row>
      <el-row>
          <el-col :span="8">
            <div class="text item">是否毕业: 
              <span v-for="item in isOneToOneOptions">
                <span v-if="form.isGraduate==item.dictValue">
                  {{ item.dictLabel }}
                </span>
              </span>
            </div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>照片信息</span>
      </div>
       <el-carousel >
          <el-carousel-item v-for="item in photosList" :key="item">
            <img  :src="imageView+'/'+item.url" alt="" height="100%" width="100%">
          </el-carousel-item>
        </el-carousel>
    </el-card>
    <el-card>
      <div slot="header">
        <span>教学介绍信息</span>
      </div>
      <div class="text item">教学特点: {{ form.trait }}</div>
      <div class="text item">教学经历: {{ form.experience }}</div>   
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getInfo } from "@/api/hometeacher/info";
    export default {
        name: "DetailModal",
        props: {
          infoDetail: null,
          currentData:null,
        },
        watch:{
          infoDetail:function (e) {
            this.dialogVisible=e;
            this.getDicts("Education").then(response => {
            this.Educations = response.data;
          });
          this.getDicts("is-one-to-one").then(response => {
            this.isOneToOneOptions = response.data;
          });
          },
          currentData:function (e) {
            getInfo(e.id).then(response => {
              this.form = response.data;
              this.courseId = response.courseId;
              this.sysCourses = response.sysCourses;
              this.photosList=JSON.parse(this.form.photos);
            });
          },
        },
        data() {
          return {
            title:"教师信息详情",
            dialogVisible:false,
             // 课程选择
             sysCourses: [],
             //照片列表
             photosList:[],
             // 是否毕业字典
             isOneToOneOptions: [],
             imageView: process.env.VUE_APP_BASE_API,
             //表单参数
             form:{}
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

</style>
