<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="账户id" prop="accountId">
        <el-input
          v-model="queryParams.accountId"
          placeholder="请输入账户id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)" prop="changeType">
        <el-select v-model="queryParams.changeType" placeholder="请选择类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)" clearable size="small">
          <el-option
            v-for="dict in changeTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="变动前总金额" prop="preAmount">
        <el-input
          v-model="queryParams.preAmount"
          placeholder="请输入变动前总金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="可提现发生金额" prop="cashAmount">
        <el-input
          v-model="queryParams.cashAmount"
          placeholder="请输入可提现发生金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="不可发生金额" prop="uncashAmount">
        <el-input
          v-model="queryParams.uncashAmount"
          placeholder="请输入不可发生金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)" prop="refId">
        <el-input
          v-model="queryParams.refId"
          placeholder="请输入关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建者" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="创建人Id" prop="createById">
        <el-input
          v-model="queryParams.createById"
          placeholder="请输入创建人Id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['account:account_change:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:account_change:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:account_change:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:account_change:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="account_changeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="账户id" align="center" prop="accountId" />
      <el-table-column label="类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)" align="center" prop="changeType" :formatter="changeTypeFormat" />
      <el-table-column label="变动前总金额" align="center" prop="preAmount" />
      <el-table-column label="可提现发生金额" align="center" prop="cashAmount" />
      <el-table-column label="不可发生金额" align="center" prop="uncashAmount" />
      <el-table-column label="关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)" align="center" prop="refId" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建人Id" align="center" prop="createById" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:account_change:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:account_change:remove']"
          >删除</el-button>
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

    <!-- 添加或修改账户资金变动流水对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="账户id" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入账户id" />
        </el-form-item>
        <el-form-item label="类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)">
          <el-select v-model="form.changeType" placeholder="请选择类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)">
            <el-option
              v-for="dict in changeTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="变动前总金额" prop="preAmount">
          <el-input v-model="form.preAmount" placeholder="请输入变动前总金额" />
        </el-form-item>
        <el-form-item label="可提现发生金额" prop="cashAmount">
          <el-input v-model="form.cashAmount" placeholder="请输入可提现发生金额" />
        </el-form-item>
        <el-form-item label="不可发生金额" prop="uncashAmount">
          <el-input v-model="form.uncashAmount" placeholder="请输入不可发生金额" />
        </el-form-item>
        <el-form-item label="关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)" prop="refId">
          <el-input v-model="form.refId" placeholder="请输入关联流水ID(change_type不同，对应不同流水表，如充值、支付、提现流水)" />
        </el-form-item>
        <el-form-item label="删除标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
        </el-form-item>
        <el-form-item label="创建人Id" prop="createById">
          <el-input v-model="form.createById" placeholder="请输入创建人Id" />
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
import { listAccount_change, getAccount_change, delAccount_change, addAccount_change, updateAccount_change, exportAccount_change } from "@/api/account/account_change";

export default {
  data() {
    return {
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
      // 账户资金变动流水表格数据
      account_changeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)字典
      changeTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        accountId: undefined,
        changeType: undefined,
        preAmount: undefined,
        cashAmount: undefined,
        uncashAmount: undefined,
        refId: undefined,
        createBy: undefined,
        createTime: undefined,
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
    this.getDicts("account_change_type").then(response => {
      this.changeTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询账户资金变动流水列表 */
    getList() {
      this.loading = true;
      listAccount_change(this.queryParams).then(response => {
        this.account_changeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 类型(1.充值 2.支付 3.退款 4.提现 5.内部调账)字典翻译
    changeTypeFormat(row, column) {
      return this.selectDictLabel(this.changeTypeOptions, row.changeType);
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
        accountId: undefined,
        changeType: undefined,
        preAmount: undefined,
        cashAmount: undefined,
        uncashAmount: undefined,
        refId: undefined,
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
      this.open = true;
      this.title = "添加账户资金变动流水";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAccount_change(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改账户资金变动流水";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAccount_change(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAccount_change(this.form).then(response => {
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
      this.$confirm('是否确认删除账户资金变动流水编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAccount_change(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有账户资金变动流水数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAccount_change(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>