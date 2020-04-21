<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="20%"
    @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-select v-model="form.classId" placeholder="请选择班级">
          <el-option
            v-for="item in storeClass"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import {getStoreClassByStoreId} from "@/api/store/storeStudent";
    export default {
        name: "classStuModal",
        props: {
          classStu: null,
          currentData:null,
        },
      watch:{
        classStu:function (e) {
          this.dialogVisible=e;
        },
        currentData:function (e) {
          getStoreClassByStoreId(e.storeId).then(response => {
            this.storeClass=response.data;
          });
        },
      },
      data() {
        return {
          title:"分班设置",
          dialogVisible:false,
          storeClass:[],
          form:{
            classId:null,
          },
          // 表单校验
          rules: {
            classId: [
              { required: true, message: "请选择班级", trigger: "blur" }
            ],
          }
        }
      },
      methods: {
        handleClose() {
          this.$emit('closeClassStuModal', false);
        },
      }
    }
</script>

<style scoped>

</style>
