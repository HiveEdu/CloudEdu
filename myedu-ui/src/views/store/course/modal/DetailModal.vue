<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>课程信息</span>
      </div>
      <el-row>
        <el-col :span="24">
          <div class="text item">课程名称: {{ form.name }}</div>
        </el-col>     
      </el-row>
      <el-row>
        <br>
      </el-row>
       <el-row>
         <el-col :span="12">
          <div class="text item">课程类型:
            <span v-for="item in classifyOptions">
               <span v-if="form.classify==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="12" v-if="form.classify==0">
          <div class="text item">托管类型:
            <span v-for="item in reclassifyCollOptions">
               <span v-if="form.reclassifyColl==item.dictValue">
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
          <el-col :span="12">
            <div class="text item">开始时间: {{ parseTime(form.cStarttime) }}</div>
          </el-col>
          <el-col :span="12">
            <div class="text item">结束时间: {{ parseTime(form.cEndtime )}}</div>
          </el-col>
      </el-row>
      <el-row>
        <br>
      </el-row>
      <el-row>
         <el-col :span="12">
          <div class="text item">课程状态: 
            <span v-for="item in statusOptions">
               <span v-if="form.status==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="text item">是否一对一: 
            <span v-for="item in isOneToOneOptions">
               <span v-if="form.isOneToOne==item.dictValue">
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
          <div class="text item">课程内容: {{ form.content }}</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>报名信息</span>
      </div>
        <el-row>
         <el-col :span="12">
          <div class="text item">招生对象: 
            <span v-for="item in sysGrades">
               <span v-for="item1 in form.gradeId" v-if="item1==item.id">
                 {{ item.name }}
               </span>
            </span>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="text item">招生人数: {{ form.stuCount }}人</div>
        </el-col>
      </el-row>
       <el-row>
        <br>
      </el-row>
      <el-row>
         <el-col :span="12">
          <div class="text item">上课时间: {{ form.classTime }}</div>
        </el-col>
        <el-col :span="12">
          <div class="text item">上课时长: {{ form.classHour }}分</div>
        </el-col>
      </el-row>
       <el-row>
        <br>
      </el-row>
      <el-row>
         <el-col :span="24">
          <div class="text item">周次: {{ form.week }}</div>
        </el-col>
      </el-row>
       <el-row>
        <br>
      </el-row>
      <el-row>
         <el-col :span="8">
          <div class="text item">总课时: 共{{ form.classAll }}节</div>
        </el-col>
        <el-col :span="8">
          <div class="text item">餐费: {{ form.meals }}元</div>
        </el-col>
        <el-col :span="8">
          <div class="text item">课程费用: {{ form.courseCost }}元</div>
        </el-col>
      </el-row>
       <el-row>
        <br>
      </el-row>
      <el-row>
         <el-col :span="24">
          <div class="text item">总费用: {{ form.totalCost }}元</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
      <div slot="header">
        <span>课程介绍信息</span>
      </div>
      <div class="text item">课程介绍: {{ form.introduce }}</div>   
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getCourse } from "@/api/store/course";
    export default {
        name: "DetailModal",
        props: {
          courseDetail: null,
          currentData:null,
        },
        watch:{
          courseDetail:function (e) {
            this.dialogVisible=e;
            this.getDicts("course_type").then(response => {
            this.classifyOptions = response.data;
          });
          this.getDicts("managed_type").then(response => {
            this.reclassifyCollOptions = response.data;
          });
          this.getDicts("is-one-to-one").then(response => {
            this.isOneToOneOptions = response.data;
          });
          this.getDicts("course_status").then(response => {
            this.statusOptions = response.data;
          });
          },
          currentData:function (e) {
            getCourse(e.id).then(response => {
              this.form = response.data;
              this.gradeId = response.gradeId;
              this.sysGrades = response.sysGrades;
            });
          },
        },
        data() {
          return {
            title:"课程详情",
            dialogVisible:false,
             sysGrades: [],
            // 课程类型字典
            classifyOptions: [],
            // 托管类型字典
            reclassifyCollOptions: [],
            // 是否一对一字典
            isOneToOneOptions: [],
            // 课程状态字典
            statusOptions: [],
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
