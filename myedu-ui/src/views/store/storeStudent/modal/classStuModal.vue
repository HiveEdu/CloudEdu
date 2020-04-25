<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="20%"
    @close="handleClose">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px">
      <el-form-item label="所属班级" prop="classId">
        <el-select v-model="form.classId" placeholder="请选择班级">
          <el-option
            v-for="item in storeClass"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
    <el-button type="primary" @click="submitForm">确 定</el-button>
  </span>
  </el-dialog>
</template>

<script>
  import {getStoreClassByStoreId,setClassStu} from "@/api/store/storeStudent";
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
          var _that = this;
          getStoreClassByStoreId(e.storeId).then(response => {
            this.storeClass=response.data;
              _that.form.stuId=e.stuId;
              _that.form.storeId=e.storeId;
              _that.form.classId=Number(e.classId);
          });
        },
      },
      data() {
        return {
          title:"分班设置",
          dialogVisible:false,
          storeClass:[],
          form:{
            storeId:null,
            classId:null,
            stuId:null,
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
        submitForm: function() {
          this.$refs["form"].validate(valid => {
            if (valid) {
              setClassStu(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("设置成功");
                  this.handleClose();
                } else {
                  this.msgError(response.msg);
                }
              });
            }
          });

        },
      }
    }
</script>

<style scoped>

</style>
