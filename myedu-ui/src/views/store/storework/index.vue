<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="作业名称" prop="workName">
        <el-input
          v-model="queryParams.workName"
          placeholder="请输入作业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作业标题" prop="workTitle">
        <el-input
          v-model="queryParams.workTitle"
          placeholder="请输入作业标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="作业状态" prop="workStatus">
        <el-select v-model="queryParams.workStatus" placeholder="请选择作业状态" clearable size="small">
          <el-option
            v-for="dict in workStatusOptions"
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
          v-hasPermi="['store:storework:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['store:storework:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:storework:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:storework:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="storeworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属门店" align="center" prop="storeName" />
      <el-table-column label="作业名称" align="center" prop="workName" />
      <el-table-column label="作业标题" align="center" prop="workTitle" />
      <el-table-column label="作业内容" align="center" prop="workContent" />
      <el-table-column label="作业状态" align="center" prop="workStatus" :formatter="workStatusFormat" />
      <el-table-column label="发布人" align="center" prop="createBy" />
      <el-table-column label="发布时间" align="center" prop="createTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['storework:storework:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['storework:storework:remove']"
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

    <!-- 添加或修改门店作业管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="所属门店" prop="storeId">
              <el-select v-model="form.storeId"  placeholder="请选择所属门店"  style="width: 100%;">
                <el-option
                  v-for="item in stores"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="选择学生" prop="stuId">
                <el-select v-model="form.stuId" multiple placeholder="选择学生"  style="width: 100%;">
                  <el-option
                    v-for="item in yunStuents"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
        <el-form-item label="作业名称" prop="workName">
          <el-input v-model="form.workName" placeholder="请输入作业名称" />
        </el-form-item>
        <el-form-item label="作业标题" prop="workTitle">
          <el-input v-model="form.workTitle" placeholder="请输入作业标题" />
        </el-form-item>
        <el-form-item label="作业内容" prop="workContent">
          <el-input v-model="form.workContent" placeholder="请输入作业内容" />
        </el-form-item>
        <el-form-item label="上传照片" prop="workPicture">
          <el-input v-model="form.workPicture" placeholder="请输入作业照片" />
        </el-form-item>
        <el-form-item label="作业状态">
          <el-radio-group v-model="form.workStatus">
            <el-radio
              v-for="dict in workStatusOptions"
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
import { listStorework, getStorework, delStorework, addStorework, updateStorework, exportStorework } from "@/api/store/storework";

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 学生选择
      yunStuents: [],
        //门店列表
      stores:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 门店作业管理表格数据
      storeworkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 作业状态字典
      workStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        workName: undefined,
        workTitle: undefined,
        workStatus: undefined,
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
    this.getDicts("completion_status").then(response => {
      this.workStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询门店作业管理列表 */
    getList() {
      this.loading = true;
      listStorework(this.queryParams).then(response => {
        this.storeworkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 作业状态字典翻译
    workStatusFormat(row, column) {
      return this.selectDictLabel(this.workStatusOptions, row.workStatus);
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
        workName: undefined,
        workTitle: undefined,
        workContent: undefined,
        workPicture: undefined,
        storeId: undefined,
        workStatus: "0",
        createBy: undefined,
        createTime: undefined,
        createById: undefined,
        updateBy: undefined,
        updateTime: undefined,
        updateById: undefined
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
      getStorework().then(response => {
        this.yunStuents=response.yunStuents;
        this.stores=response.stores;
        this.open = true;
        this.title = "添加门店作业管理";
      });
      
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStorework(id).then(response => {
        this.form = response.data;
        this.yunStuents=response.yunStuents;
        this.stores=response.stores;
        this.open = true;
        this.title = "修改门店作业管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStorework(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addStorework(this.form).then(response => {
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
      this.$confirm('是否确认删除门店作业管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStorework(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店作业管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStorework(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>