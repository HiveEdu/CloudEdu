<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="appID" prop="appId">
        <el-input
          v-model="queryParams.appId"
          placeholder="请输入应用ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商户号" prop="sysServiceProviderId">
        <el-input
          v-model="queryParams.sysServiceProviderId"
          placeholder="请输入商户号"
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
          v-hasPermi="['account:alipay:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:alipay:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:alipay:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:alipay:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="alipayList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="appID" align="center" prop="appId" />
      <el-table-column label="商户账号" align="center" prop="sysServiceProviderId" />
      <el-table-column label="异步回调" align="center" prop="notifyUrl" :show-overflow-tooltip="true"/>
<!--      <el-table-column label="私钥" align="center" prop="privateKey" :show-overflow-tooltip="true"/>-->
<!--      <el-table-column label="公钥" align="center" prop="publicKey" :show-overflow-tooltip="true"/>-->
      <el-table-column label="回调地址" align="center" prop="returnUrl" :show-overflow-tooltip="true"/>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope" v-if="scope.row.createTime!=null">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['account:alipay:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['account:alipay:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            @click="alipay(scope.row)"
          >支付测试</el-button>
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

    <!-- 添加或修改支付宝配置类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="appID" prop="appId">
          <el-input v-model="form.appId" placeholder="请输入应用ID" />
        </el-form-item>
        <el-form-item label="商户账号" prop="sysServiceProviderId">
          <el-input v-model="form.sysServiceProviderId" placeholder="请输入商户号" />
        </el-form-item>
        <el-form-item label="商户私钥" prop="privateKey">
          <el-input type="password" v-model="form.privateKey" placeholder="请输入私钥" />
        </el-form-item>
        <el-form-item label="支付宝公钥" prop="publicKey">
          <el-input type="password" v-model="form.publicKey" placeholder="请输入公钥" />
        </el-form-item>
        <el-form-item label="回调地址" prop="returnUrl">
          <el-input v-model="form.returnUrl" placeholder="请输入回调地址" />
        </el-form-item>
        <el-form-item label="异步通知" prop="notifyUrl">
          <el-input v-model="form.notifyUrl" placeholder="请输入异步回调" />
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
import { listAlipay, getAlipay, delAlipay, addAlipay, updateAlipay, exportAlipay ,toAliPay} from "@/api/account/alipay";

export default {
  data() {
    return {
      url: '',
      newWin:null,
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
      // 支付宝配置类表格数据
      alipayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appId: undefined,
        charset: undefined,
        format: undefined,
        gatewayUrl: undefined,
        notifyUrl: undefined,
        privateKey: undefined,
        publicKey: undefined,
        returnUrl: undefined,
        signType: undefined,
        sysServiceProviderId: undefined,
        createBy: undefined,
        createTime: undefined,
        createById: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        appID: [
          { required: true, message: "应用ID不能为空", trigger: "blur" }
        ],
        sysServiceProviderId: [
          { required: true, message: "商户账号不能为空", trigger: "blur" }
        ],
        privateKey: [
          { required: true, message: "商户私匙不能为空", trigger: "blur" }
        ],
        publicKey: [
          { required: true, message: "支付宝公匙不能为空", trigger: "blur" }
        ],
        returnUrl: [
          { required: true, message: "回调地址不能为空", trigger: "blur" }
        ],
        异步通知: [
          { required: true, message: "异步通知不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询支付宝配置类列表 */
    getList() {
      this.loading = true;
      listAlipay(this.queryParams).then(response => {
        this.alipayList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        appId: undefined,
        charset: undefined,
        format: undefined,
        gatewayUrl: undefined,
        notifyUrl: undefined,
        privateKey: undefined,
        publicKey: undefined,
        returnUrl: undefined,
        signType: undefined,
        sysServiceProviderId: undefined,
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
      getAlipay().then(response => {
        this.open = true;
        this.title = "添加支付宝配置类";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAlipay(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改支付宝配置类";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateAlipay(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addAlipay(this.form).then(response => {
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
      this.$confirm('是否确认删除支付宝配置类编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAlipay(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有支付宝配置类数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAlipay(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    /** 支付测试 */
    alipay(row){
      // 先打开一个空的新窗口，再请求
      this.newWin = window.open()
      let url = ''
      if (/(Android)/i.test(navigator.userAgent)) { // 判断是否为Android手机
        url = '/account/alipay/toPayAsWeb'
      } else if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) { // 判断是否为苹果手机
        url = '/account/alipay/toPayAsWeb'
      } else {
        url = '/account/alipay/toPayAsPC'
      }
      toAliPay(url, row).then(res => {
        this.loading = false
        this.url = res.url
      }).catch(err => {
        this.loading = false
      })
    },
  },
  watch: {
    url(newVal, oldVal) {
      if (newVal && this.newWin) {
        this.newWin.sessionStorage.clear()
        this.newWin.location.href = newVal
        // 重定向后把url和newWin重置
        this.url = ''
        this.newWin = null
      }
    }
  },
};
</script>
