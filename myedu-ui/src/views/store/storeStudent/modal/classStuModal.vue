<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="50%"
    @close="handleClose">
    <el-card>

    </el-card>
    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
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

        },
      },
      data() {
        return {
          title:"分班设置",
          dialogVisible:false,
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
