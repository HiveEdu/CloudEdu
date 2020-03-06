<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>
      <div slot="header">
        <span>投诉信息</span>
      </div>
      <el-row>
        <el-col :span="12" >
          <el-row>
             <div class="text item" style="margin-left: 5%">投诉谁:{{ form.complaint }}</div>
          </el-row>
          <br>
           <el-row>
             <div class="text item" style="margin-left: 5%">投诉理由:{{ form.reason }}</div>
          </el-row>
        </el-col>
      </el-row>
    </el-card>
     <el-card>
      <div slot="header">
        <span>答复信息</span>
      </div>
      <el-row>
        <el-col :span="8" >
             <div class="text item">答复状态: 
            <span v-for="item in statusOptions">
               <span v-if="form.status==item.dictValue">
                 {{ item.dictLabel }}
               </span>
            </span>
          </div>
          <br>
        </el-col>
        <el-col :span="8" >
          <el-row>
             <div class="text item" style="margin-left: 5%">答复人:{{ form.replyBy }}</div>
          </el-row>
        </el-col>
        <el-col :span="8" >
          <el-row>
             <div class="text item" style="margin-left: 5%">答复时间:{{ form.replyTime }}</div>
          </el-row>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
           <br>
           <el-row>
             <div class="text item" style="margin-left: 5%">答复内容:{{ form.replyContent }}</div>
          </el-row>
        </el-col>
      </el-row>
    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import { getComplaint } from "@/api/parents/complaint";
    export default {
        name: "cDetailModal",
        props: {
          copmDetail: null,
          currentData:null,
        },
        watch:{
          copmDetail:function (e) {
            this.dialogVisible=e;
             this.getDicts("complaint_status").then(response => {
             this.statusOptions = response.data;
          });
          },
          currentData:function (e) {
            getComplaint(e.id).then(response => {
              this.form = response.data;
              
            });
          },
        },
        data() {
          return {
            title:"投诉详情",
            dialogVisible:false,
            defaultImg: 'this.src="' + require("@/assets/image/deaufalt.jpg") + '"',
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
