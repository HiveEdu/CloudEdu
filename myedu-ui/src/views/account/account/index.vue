<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="账号" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入用户账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['account:account:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:account:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:account:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:account:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="accountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="总金额" align="center" prop="totalAmount" />
      <el-table-column label="可提现金额" align="center" prop="caseAmount" />
      <el-table-column label="不可提现金额" align="center" prop="uncaseAmount" />
      <el-table-column label="冻结金额" align="center" prop="freezeCaseAmount" />
      <el-table-column label="不可提现冻结金额" align="center" prop="freezeUncaseAmount" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" width="200" >
        <template slot-scope="scope">
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleUpdate(scope.row)"-->
          <!--            v-hasPermi="['account:account:edit']"-->
          <!--          >修改</el-button>-->
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-delete"-->
          <!--            @click="handleDelete(scope.row)"-->
          <!--            v-hasPermi="['account:account:remove']"-->
          <!--          >删除</el-button>-->
          <el-button  size="mini" type="primary"
                      @click="openAccountBill(scope.row)">
            账单</el-button>
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="openRecharge(scope.row)"
              >充值</el-button>
              <br>
              <el-button
                size="mini"
                type="primary"
                icon="el-icon-delete"
                @click="opneWithdraw(scope.row)"
              >提现</el-button>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>


    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!--充值页面-->
    <rechargeModal ref="rechargeModal" :recharge="recharge" :currentData="currentData" @closeRecharge="closeRecharge"></rechargeModal>
    <!--提现页面-->
    <withdrawModal ref="withdrawModal" :withdraw="withdraw" :currentData="currentData" @closeWithdraw="closeWithdraw"></withdrawModal>
    <!--账单页面-->
    <accountBillModal ref="accountBillModal" :accountBill="accountBill" :accountBillList="accountBillList" :currentData="currentData" @closeAccountBill="closeAccountBill"></accountBillModal>
    <!-- 添加或修改账户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="23">
            <el-form-item label="总金额" prop="totalAmount">
              <el-input-number min="0" controls-position="right" style="width: 100%;" v-model="form.totalAmount" placeholder="请输入总金额" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row v-if="form.id!=null">
          <el-col :span="23">
            <el-form-item label="可提金额" prop="caseAmount" >
              <el-input-number min="0" controls-position="right" style="width: 100%;" v-model="form.caseAmount" placeholder="请输入可提现金额" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row v-if="form.id!=null">
          <el-col :span="23">
            <el-form-item label="不可提现金额" prop="uncaseAmount" >
              <el-input-number min="0" controls-position="right"  style="width: 100%;" v-model="form.uncaseAmount" placeholder="请输入不可提现金额" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row v-if="form.id!=null">
          <el-col :span="23">
              <el-form-item label="冻结金额" prop="freezeCaseAmount" >
                <el-input-number min="0" controls-position="right" style="width: 100%;" v-model="form.freezeCaseAmount" placeholder="请输入冻结金额" />
              </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row v-if="form.id!=null">
          <el-col :span="23">
            <el-form-item label="不可提现冻结金额" prop="freezeUncaseAmount" >
              <el-input-number min="0" controls-position="right" style="width: 100%;" v-model="form.freezeUncaseAmount" placeholder="请输入不可提现冻结金额" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-form-item label="状态" v-if="form.id!=null">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listAccount, getAccount, delAccount, addAccount, updateAccount, exportAccount } from "@/api/account/account";
import rechargeModal from './modal/rechargeModal'
import withdrawModal from './modal/withdrawModal'
import accountBillModal from './modal/accountBillModal'
export default {
  components: { rechargeModal,withdrawModal,accountBillModal },
  data() {
    return {
      //账单列表
      accountBillList:null,
      //账单页面默认不打开
      accountBill:false,
      //提现页面默认不打开
      withdraw:false,
      //充值页面默认不打开
      recharge:false,
      //当前行记录为空
      currentData:null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 账户管理表格数据
      accountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        totalAmount: undefined,
        caseAmount: undefined,
        uncaseAmount: undefined,
        freezeCaseAmount: undefined,
        freezeUncaseAmount: undefined,
        status: undefined,
        createBy: undefined,
        createTime: undefined,
        delFlag: undefined,
        createById: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    //打開账单页面
    openAccountBill(row){
      this.currentData=row;
      getAccount(row.id).then(response => {
        this.accountBillList=response.WITHDRAWLIST;
        this.accountBill=true;
      });
    },
    //关闭账单页面
    closeAccountBill(){
      this.accountBill=false;
      this.getList();
    },
    //打开提现页面
    opneWithdraw(row){
      this.currentData=row;
      this.withdraw=true;
    },
    //关闭提现页面
    closeWithdraw(){
      this.withdraw=false;
      this.getList();
    },
    //打开充值页面
    openRecharge(row){
      this.currentData=row;
      this.recharge=true;
    },
    //关闭充值页面
    closeRecharge(){
      this.recharge=false;
      this.getList();
    },
    /** 查询账户管理列表 */
    getList() {
      this.loading = true;
      listAccount(this.queryParams).then(response => {
        this.accountList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        totalAmount: undefined,
        caseAmount: undefined,
        uncaseAmount: undefined,
        freezeCaseAmount: undefined,
        freezeUncaseAmount: undefined,
        status: "0",
        remark: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        delFlag: undefined,
        createById: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getAccount().then(response => {
        this.open = true;
        this.title = "添加账户";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAccount(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账户";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAccount(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAccount(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除账户管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAccount(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有账户管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAccount(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
