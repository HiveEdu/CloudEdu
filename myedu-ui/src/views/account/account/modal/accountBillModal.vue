<template>
  <el-dialog
    :title="title"
    :visible.sync="dialogVisible"
    width="40%"
    @close="handleClose">

    <el-table :data="accountBillData">
      <el-table-column property="changeType" label="种类" :formatter="changeTypeFormat" ></el-table-column>
      <el-table-column property="preAmount" label="变动前" ></el-table-column>
      <el-table-column property="cashAmount" label="变动后" ></el-table-column>
      <el-table-column property="uncashAmount" label="变动值" >
        <template slot-scope="scope">
          <span v-if="scope.row.changeType==1">+{{ scope.row.uncashAmount }}</span>
          <span v-else-if="scope.row.changeType==4">-{{ scope.row.uncashAmount }}</span>
          <span v-else>{{scope.row.uncashAmount }}</span>
        </template>

      </el-table-column>
      <el-table-column label="日期" align="center" prop="createTime">
        <template slot-scope="scope" v-if="scope.row.createTime!=null">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
    </el-table>

    <span slot="footer" class="dialog-footer">
    <el-button @click="handleClose">取 消</el-button>
  </span>
  </el-dialog>
</template>

<script>
    export default {
        name: "accountBillModal",
        props: {
          accountBill: null,
          currentData:null,
          accountBillList:null,
        },
        watch:{
          accountBill:function (e) {
            this.dialogVisible=e;
            this.getDicts("account_change_type").then(response => {
              this.changeTypeOptions = response.data;
            });
          },
          currentData:function (e) {
          },
          accountBillList:function (e) {
            this.accountBillData=e;
          },
        },
        data() {
          return {
            title: "账单",
            dialogVisible: false,
            accountBillData: [],
            // 账单类型字典
            changeTypeOptions: [],
          }
        },
        methods: {
          // 账单状态字典翻译
          changeTypeFormat(row, column) {
            return this.selectDictLabel(this.changeTypeOptions, row.changeType);
          },

          handleClose() {
            this.$emit('closeAccountBill', false);
          },
        }
    }
</script>

<style scoped>

</style>
