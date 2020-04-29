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
          <div class="text item">评价课程: {{ form.courseName }}</div>
        </el-col>
         <el-col :span="12">
          <div class="text item">评价人: {{ form.createBy }}</div>
        </el-col>
      </el-row>
    </el-card>
    <el-card>
       <div slot="header">
        <span>内容信息</span>
      </div>
       <el-row>
         <el-col :span="12">
          <div class="text item">评价内容: {{ form.content }}</div>
        </el-col>
      </el-row>
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getComment } from "@/api/store/comment";
  import { formatTime } from '@/utils/index'
    export default {
        name: "DetailModal",
        props: {
          commentDetail: null,
          currentData:null,
        },
        watch:{
        commentDetail:function (e) {
            this.dialogVisible=e;
          },
          currentData:function (e) {
            this.getDicts("comment_type").then(response => {
           this.typeOptions = response.data;
          });
            getComment(e.id).then(response => {
              this.form = response.data;
            });
          },
        },
        data() {
          return {
            title:"课程评价详情",
            dialogVisible:false,
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
