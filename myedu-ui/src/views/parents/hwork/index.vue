<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="作业名称" prop="hworkName">
        <el-input
          v-model="queryParams.hworkName"
          placeholder="请输入作业名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="完成状态" prop="hworkStatus">
        <el-select v-model="queryParams.hworkStatus" placeholder="请选择完成状态" clearable size="small">
          <el-option
            v-for="dict in hworkStatusOptions"
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
          v-hasPermi="['parents:hwork:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['parents:hwork:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['parents:hwork:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['parents:hwork:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="hworkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生名称" align="center" prop="studentName" />
      <el-table-column label="作业名称" align="center" prop="hworkName" />
      <el-table-column label="作业类型" align="center" prop="hworkType" />
      <el-table-column label="完成状态" align="center" prop="hworkStatus" :formatter="hworkStatusFormat" />
      <el-table-column label="作业内容" align="center" prop="hworkContent" />
      <el-table-column label="完成时间" align="center" prop="hworkTime" width="180">
        <template slot-scope="scope" v-if="scope.row.hworkTime!=null">
          <span>{{ parseTime(scope.row.hworkTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['parents:hwork:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['parents:hwork:remove']"
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

    <!-- 添加或修改学生作业对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="作业名称" prop="hworkName">
          <el-input v-model="form.hworkName" placeholder="请输入作业名称" />
        </el-form-item>
        <el-form-item label="作业类型" prop="hworkType">
          <el-input v-model="form.hworkType" placeholder="请输入作业类型" />
        </el-form-item>
        <el-form-item label="作业内容" prop="hworkContent">
          <el-input v-model="form.hworkContent" placeholder="请输入作业内容" />
        </el-form-item>
        <el-form-item label="完成状态">
          <el-radio-group v-model="form.hworkStatus">
            <el-radio
              v-for="dict in hworkStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="完成时间" prop="hworkTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.hworkTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="关联学生Id" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入关联学生Id" />
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
import { listHwork, getHwork, delHwork, addHwork, updateHwork, exportHwork } from "@/api/parents/hwork";

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
      // 学生作业表格数据
      hworkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 完成状态字典
      hworkStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        hworkType: undefined,
        hworkName: undefined,
        hworkStatus: undefined,
        hworkTime: undefined,
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
      this.hworkStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询学生作业列表 */
    getList() {
      this.loading = true;
      listHwork(this.queryParams).then(response => {
        this.hworkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 完成状态字典翻译
    hworkStatusFormat(row, column) {
      return this.selectDictLabel(this.hworkStatusOptions, row.hworkStatus);
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
        studentId: undefined,
        hworkType: undefined,
        hworkName: undefined,
        hworkStatus: "0",
        hworkTime: undefined,
        hworkContent: undefined,
        hworkImage: undefined,
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
      this.title = "添加学生作业";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHwork(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生作业";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateHwork(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addHwork(this.form).then(response => {
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
      this.$confirm('是否确认删除学生作业编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delHwork(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学生作业数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportHwork(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
