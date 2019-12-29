<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="考试分数" prop="mark">
        <el-input
          v-model="queryParams.mark"
          placeholder="请输入考试分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试科目" prop="examtname">
        <el-input
          v-model="queryParams.examtname"
          placeholder="请输入考试科目"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考试时间" prop="examtime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.examtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择考试时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="照片地址" prop="examimage">
        <el-input
          v-model="queryParams.examimage"
          placeholder="请输入照片地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label=" 创建id" prop="createById">
        <el-input
          v-model="queryParams.createById"
          placeholder="请输入 创建id"
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
          v-hasPermi="['score:score:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['score:score:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['score:score:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['score:score:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="scoreList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="成绩id" align="center" prop="scoreId" />
      <el-table-column label="考试分数" align="center" prop="mark" />
      <el-table-column label="考试科目" align="center" prop="examtname" />
      <el-table-column label="考试时间" align="center" prop="examtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.examtime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="评论" align="center" prop="remark" />
      <el-table-column label="照片地址" align="center" prop="examimage" />
      <el-table-column label=" 创建id" align="center" prop="createById" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['score:score:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['score:score:remove']"
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

    <!-- 添加或修改学生成绩对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="考试分数" prop="mark">
          <el-input v-model="form.mark" placeholder="请输入考试分数" />
        </el-form-item>
        <el-form-item label="考试科目" prop="examtname">
          <el-input v-model="form.examtname" placeholder="请输入考试科目" />
        </el-form-item>
        <el-form-item label="考试时间" prop="examtime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.examtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择考试时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="照片地址" prop="examimage">
          <el-input v-model="form.examimage" placeholder="请输入照片地址" />
        </el-form-item>
        <el-form-item label=" 创建id" prop="createById">
          <el-input v-model="form.createById" placeholder="请输入 创建id" />
        </el-form-item>
        <el-form-item label="更新标志" prop="delFlag">
          <el-input v-model="form.delFlag" placeholder="请输入更新标志" />
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
import { listScore, getScore, delScore, addScore, updateScore, exportScore } from "@/api/parents/score";

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
      // 学生成绩表格数据
      scoreList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        mark: undefined,
        examtname: undefined,
        examtime: undefined,
        examimage: undefined,
        createById: undefined,
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
  },
  methods: {
    /** 查询学生成绩列表 */
    getList() {
      this.loading = true;
      listScore(this.queryParams).then(response => {
        this.scoreList = response.rows;
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
        scoreId: undefined,
        mark: undefined,
        examtname: undefined,
        examtime: undefined,
        remark: undefined,
        examimage: undefined,
        createById: undefined,
        createBy: undefined,
        updateBy: undefined,
        updateTime: undefined,
        delFlag: undefined
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
      this.ids = selection.map(item => item.scoreId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学生成绩";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scoreId = row.scoreId || this.ids
      getScore(scoreId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学生成绩";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scoreId != undefined) {
            updateScore(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addScore(this.form).then(response => {
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
      const scoreIds = row.scoreId || this.ids;
      this.$confirm('是否确认删除学生成绩编号为"' + scoreIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delScore(scoreIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有学生成绩数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportScore(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>