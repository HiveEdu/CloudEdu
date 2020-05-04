<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="设备名称" prop="equName">
        <el-input
          v-model="queryParams.equName"
          placeholder="请输入设备名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备型号" prop="model">
        <el-input
          v-model="queryParams.model"
          placeholder="请输入设备型号"
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
          v-hasPermi="['store:equ:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['store:equ:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:equ:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:equ:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="equList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属门店" align="center" prop="storeName" />
      <el-table-column label="设备名称" align="center" prop="equName" />
      <el-table-column label="设备型号" align="center" prop="model" />
      <el-table-column label="设备地址" align="center" prop="address" />
      <el-table-column label="登录名" align="center" prop="userName" />
      <el-table-column label="登录密码" align="center" prop="passward" />
      <el-table-column label="端口" align="center" prop="port" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="是否可用" align="center" prop="isuse" :formatter="isuseFormat" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['store:equ:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['store:equ:remove']"
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

    <!-- 添加或修改门店设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="所属门店" prop="storeId">
          <el-select v-model="form.storeId"  placeholder="请选择所属门店"  style="width: 100%;">
            <el-option
              v-for="item in stores"
              v-if="item.status==3"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备名称" prop="equName">
          <el-input v-model="form.equName" placeholder="请输入设备名称" />
        </el-form-item>
        <el-form-item label="设备型号" prop="model">
          <el-input v-model="form.model" placeholder="请输入设备型号" />
        </el-form-item>
        <el-form-item label="设备地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入设备地址" />
        </el-form-item>
        <el-form-item label="设备登录名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入设备登录名" />
        </el-form-item>
        <el-form-item label="设备登录密码" prop="passward">
          <el-input v-model="form.passward" placeholder="请输入设备登录密码" />
        </el-form-item>
        <el-form-item label="端口" prop="port">
          <el-input v-model="form.port" placeholder="请输入端口" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否可用">
          <el-radio-group v-model="form.isuse">
            <el-radio
              v-for="dict in isuseOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { listEqu, getEqu, delEqu, addEqu, updateEqu, exportEqu } from "@/api/store/equ";

export default {
  data() {
    return {
      //门店列表
      stores:[],
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
      // 门店设备表格数据
      equList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态(1在线2离线)字典
      statusOptions: [],
      // 是否可用字典
      isuseOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeId: undefined,
        equName: undefined,
        model: undefined,
        address: undefined,
        userName: undefined,
        status: undefined,
        isuse: undefined,
        createBy: undefined,
        createById: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        storeId: [
          { required: true, message: "所属门店不能为空", trigger: "blur" }
        ],        equName: [
          { required: true, message: "设备名称不能为空", trigger: "blur" }
        ],        address: [
          { required: true, message: "设备地址不能为空", trigger: "blur" }
        ],        userName: [
          { required: true, message: "设备登录名不能为空", trigger: "blur" }
        ],        passward: [
          { required: true, message: "设备登录密码不能为空", trigger: "blur" }
        ],        port: [
          { required: true, message: "端口不能为空", trigger: "blur" }
        ],      }
    };
  },
  created() {
    this.getList();
    this.getDicts("equ_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("is_used").then(response => {
      this.isuseOptions = response.data;
    });
  },
  methods: {
    /** 查询门店设备列表 */
    getList() {
      this.loading = true;
      listEqu(this.queryParams).then(response => {
        this.equList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态(1在线2离线)字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 是否可用字典翻译
    isuseFormat(row, column) {
      return this.selectDictLabel(this.isuseOptions, row.isuse);
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
        storeId: undefined,
        equName: undefined,
        model: undefined,
        address: undefined,
        userName: undefined,
        passward: undefined,
        port: undefined,
        status: "0",
        isuse: "0",
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
      getEqu().then(response => {
        this.stores=response.stores;
        this.open = true;
        this.title = "添加门店设备";
      });

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEqu(id).then(response => {
        this.form = response.data;
        this.stores=response.stores;
        this.open = true;
        this.title = "修改门店设备";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateEqu(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addEqu(this.form).then(response => {
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
      this.$confirm('是否确认删除门店设备编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEqu(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店设备数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEqu(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
