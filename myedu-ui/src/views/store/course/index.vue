<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入课程名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="类型" prop="classify">
          <el-select v-model="queryParams.classify" placeholder="请选择课程类型" clearable size="small">
            <el-option
              v-for="dict in classifyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="一对一" prop="isOneToOne">
        <el-select v-model="queryParams.isOneToOne" placeholder="请选择是否一对一" clearable size="small">
          <el-option
            v-for="dict in isOneToOneOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择课程状态" clearable size="small">
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
          v-hasPermi="['course:course:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['course:course:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['course:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['course:course:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="类型" align="center" prop="classify" :formatter="classifyFormat" />
      <el-table-column label="招生人数" align="center" prop="stuCount" />
      <el-table-column label="招生对象" align="center" prop="gradeId" />
      <el-table-column label="开始时间" align="center" prop="cStarttime" width="180">
        <template slot-scope="scope" v-if="scope.row.cStarttime!=null">
          <span>{{ parseTime(scope.row.cStarttime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="cEndtime" width="180">
        <template slot-scope="scope" v-if="scope.row.cEndtime!=null">
          <span>{{ parseTime(scope.row.cEndtime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程内容" align="center" prop="content" />
      <el-table-column label="课程状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['course:course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['course:course:remove']"
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

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入课程名称" />
        </el-form-item>
        <el-form-item label="课程类型">
          <el-select v-model="form.classify" placeholder="请选择课程类型">
            <el-option
              v-for="dict in classifyOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="托管类型">
          <el-select v-model="form.reclassifyColl" placeholder="请选择托管类型">
            <el-option
              v-for="dict in reclassifyCollOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否一对一">
          <el-radio-group v-model="form.isOneToOne">
            <el-radio
              v-for="dict in isOneToOneOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="招生人数" prop="stuCount">
          <el-input v-model="form.stuCount" placeholder="请输入招生人数" />
        </el-form-item>
        <el-form-item label="招生对象" prop="gradeId">
          <el-input v-model="form.gradeId" placeholder="请输入招生对象" />
        </el-form-item>
        <el-form-item label="开始时间" prop="cStarttime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.cStarttime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="cEndtime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.cEndtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="课程费用" prop="courseCost">
          <el-input v-model="form.courseCost" placeholder="请输入课程费用" />
        </el-form-item>
        <el-form-item label="餐费" prop="meals">
          <el-input v-model="form.meals" placeholder="请输入餐费" />
        </el-form-item>
        <el-form-item label="课程介绍" prop="introduce">
          <el-input v-model="form.introduce" placeholder="请输入课程介绍" />
        </el-form-item>
        <el-form-item label="课程封面" prop="picture">
          <el-input v-model="form.picture" placeholder="请输入课程封面" />
        </el-form-item>
        <el-form-item label="门店Id" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入门店Id" />
        </el-form-item>
        <el-form-item label="上课时间" prop="classTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.classTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择上课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上课时长" prop="classHour">
          <el-input v-model="form.classHour" placeholder="请输入上课时长" />
        </el-form-item>
        <el-form-item label="总课时" prop="classAll">
          <el-input v-model="form.classAll" placeholder="请输入总课时" />
        </el-form-item>
        <el-form-item label="总费用" prop="totalCost">
          <el-input v-model="form.totalCost" placeholder="请输入总费用" />
        </el-form-item>
        <el-form-item label="上课周期" prop="week">
          <el-input v-model="form.week" placeholder="请输入上课周期" />
        </el-form-item>
        <el-form-item label="课程内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入课程内容" />
        </el-form-item>
        <el-form-item label="课程状态">
          <el-select v-model="form.status" placeholder="请选择课程状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
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
import { listCourse, getCourse, delCourse, addCourse, updateCourse, exportCourse } from "@/api/store/course";

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
      // 课程表格数据
      courseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 课程类型字典
      classifyOptions: [],
      // 托管类型字典
      reclassifyCollOptions: [],
      // 是否一对一字典
      isOneToOneOptions: [],
      // 课程状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        classify: undefined,
        reclassifyColl: undefined,
        isOneToOne: undefined,
        stuCount: undefined,
        gradeId: undefined,
        cStarttime: undefined,
        cEndtime: undefined,
        courseCost: undefined,
        meals: undefined,
        introduce: undefined,
        picture: undefined,
        storeId: undefined,
        classTime: undefined,
        classHour: undefined,
        classAll: undefined,
        totalCost: undefined,
        week: undefined,
        content: undefined,
        status: undefined,
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
    this.getDicts("course_type").then(response => {
      this.classifyOptions = response.data;
    });
    this.getDicts("managed_type").then(response => {
      this.reclassifyCollOptions = response.data;
    });
    this.getDicts("is-one-to-one").then(response => {
      this.isOneToOneOptions = response.data;
    });
    this.getDicts("course_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 课程类型字典翻译
    classifyFormat(row, column) {
      return this.selectDictLabel(this.classifyOptions, row.classify);
    },
    // 托管类型字典翻译
    reclassifyCollFormat(row, column) {
      return this.selectDictLabel(this.reclassifyCollOptions, row.reclassifyColl);
    },
    // 是否一对一字典翻译
    isOneToOneFormat(row, column) {
      return this.selectDictLabel(this.isOneToOneOptions, row.isOneToOne);
    },
    // 课程状态字典翻译
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
        name: undefined,
        classify: undefined,
        reclassifyColl: undefined,
        isOneToOne: "0",
        stuCount: undefined,
        gradeId: undefined,
        cStarttime: undefined,
        cEndtime: undefined,
        courseCost: undefined,
        meals: undefined,
        introduce: undefined,
        picture: undefined,
        storeId: undefined,
        classTime: undefined,
        classHour: undefined,
        classAll: undefined,
        totalCost: undefined,
        week: undefined,
        content: undefined,
        status: undefined,
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
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCourse(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCourse(this.form).then(response => {
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
      this.$confirm('是否确认删除课程编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCourse(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有课程数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCourse(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
