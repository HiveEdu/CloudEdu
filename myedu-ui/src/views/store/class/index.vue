<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="课程名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入课程名称"
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
          v-hasPermi="['yun_class:class:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['yun_class:class:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['yun_class:class:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['yun_class:class:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="classList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="课程名称" align="center" prop="name" />
      <el-table-column label="课程类型" align="center" prop="classify" />
      <el-table-column label="托管类型" align="center" prop="reclassifyColl" />
      <el-table-column label="是否一对一" align="center" prop="isOneToOne" />
      <el-table-column label="招生人数" align="center" prop="stuCount" />
      <el-table-column label="招生对象" align="center" prop="gradeId" />
      <el-table-column label="开始时间" align="center" prop="cStarttime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cStarttime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="cEndtime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cEndtime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程费用" align="center" prop="courseCost" />
      <el-table-column label="餐费" align="center" prop="meals" />
      <el-table-column label="课程介绍" align="center" prop="introduce" />
      <el-table-column label="课程封面" align="center" prop="picture" />
      <el-table-column label="门店Id" align="center" prop="storeId" />
      <el-table-column label="上课时间" align="center" prop="classTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.classTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上课时长" align="center" prop="classHour" />
      <el-table-column label="总课时" align="center" prop="classAll" />
      <el-table-column label="总费用" align="center" prop="totalCost" />
      <el-table-column label="上课周期" align="center" prop="week" />
      <el-table-column label="课程内容" align="center" prop="content" />
      <el-table-column label="课程状态" align="center" prop="status" />
      <el-table-column label="创建人Id" align="center" prop="createById" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['yun_class:class:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['yun_class:class:remove']"
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

    <!-- 添加或修改课程设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-steps :active="active">
          <el-step title="选择课程类型" icon="el-icon-edit" @click.native="stepClick(1)"></el-step>
          <el-step title="课程设置信息" icon="el-icon-upload" @click.native="stepClick(2)"></el-step>
          <el-step title="课程介绍" icon="el-icon-picture" @click.native="stepClick(3)"></el-step>
        </el-steps>
        <div v-if="active==1">
          <el-form-item label="课程类型" prop="classify">
            <el-input v-model="form.classify" placeholder="请输入课程类型" />
          </el-form-item>
        </div>
        <div v-if="active==2">
          <el-form-item label="课程名称" prop="name">
            <el-input v-model="form.name" placeholder="请输入课程名称" />
          </el-form-item>
          <el-form-item label="托管类型" prop="reclassifyColl">
            <el-input v-model="form.reclassifyColl" placeholder="请输入托管类型" />
          </el-form-item>
          <el-form-item label="是否一对一" prop="isOneToOne">
            <el-input v-model="form.isOneToOne" placeholder="请输入是否一对一" />
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
        </div>
         <div v-if="active==3">
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
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listClass, getClass, delClass, addClass, updateClass, exportClass } from "@/api/store/class";

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
      active: 1,
      // 课程设置表格数据
      classList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
  },
  methods: {
    /** 查询课程设置列表 */
    getList() {
      this.loading = true;
      listClass(this.queryParams).then(response => {
        this.classList = response.rows;
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
        status: "0",
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
      this.title = "添加课程设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClass(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改课程设置";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateClass(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addClass(this.form).then(response => {
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
      this.$confirm('是否确认删除课程设置编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delClass(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有课程设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportClass(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
     next() {
      if (this.active++ > 2) this.active = 0;
    },
    stepClick(active){
      this.active=active;
    },
  }
};
</script>