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
      <el-form-item label="辅导对象" prop="gradeId">
        <el-input
          v-model="queryParams.gradeId"
          placeholder="请输入辅导对象"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="课程状态" prop="status">
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
          v-hasPermi="['hometeacher:course:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hometeacher:course:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hometeacher:course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hometeacher:course:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="courseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="课程名称" align="center" prop="name" />
      <el-table-column label="课时费" align="center" prop="courseCost" />
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
      <el-table-column label="授课区域" align="center" prop="region" />
      <el-table-column label="课程状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <el-button  size="mini" type="primary" @click="openDatail(scope.row)">详情</el-button>
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-button
                size="mini"
                type="success"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['hometeacher:course:edit']"
                style="margin-top: 10px"
              >修改</el-button>
              <br>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['hometeacher:course:remove']"
                style="margin-top: 10px"
              >删除</el-button>
              <br  v-if="scope.row.status==0">
              <el-button
                v-if="scope.row.status==0"
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="toSignUp(scope.row)"
                style="margin-top: 10px"
              >报名</el-button>
              <br v-if="scope.row.status==0">
              <el-button
                v-if="scope.row.status==0"
                size="mini"
                type="warning"
                icon="el-icon-edit"
                @click="changeStatus(scope.row,1)"
                style="margin-top: 10px"
              >下线</el-button>
              <br  v-if="scope.row.status==1">
              <el-button
                v-if="scope.row.status==1"
                size="mini"
                type="warning"
                icon="el-icon-edit"
                @click="changeStatus(scope.row,0)"
                style="margin-top: 10px"
              >上线</el-button>
              <br>

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
     <!--课程详情-->
    <DetailModal ref="DetailModal" :courseDetail="courseDetail" :currentData="currentData" @closeDetail="closeDetail"></DetailModal>
    <signUpModal ref="signUpModal" :signUp="signUp" :courseData="courseData" @closeSignUp="closeSignUp"></signUpModal>
    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="所属老师" prop="teacherId">
              <el-select v-model="form.teacherId"  placeholder="请选择所属注册老师"  style="width: 100%;">
                <el-option
                  v-for="item in teachers"
                  v-if="item.status==3"
                  :key="item.id"
                  :label="item.nickName"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="12">
            <el-form-item label="课程名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入课程名称" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="辅导对象" prop="gradeId">
              <el-select v-model="form.gradeId" multiple placeholder="请选择辅导对象"  style="width: 100%;">
                      <el-option
                        v-for="item in sysGrades"
                        :key="item.id"
                        :label="item.name"
                        :value="item.id"
                      ></el-option>
                    </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="cStarttime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.cStarttime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择开始时间">
              </el-date-picker>
            </el-form-item>
            </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="cEndtime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.cEndtime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择结束时间">
              </el-date-picker>
            </el-form-item>
            </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="课时费" prop="courseCost">
              <el-input-number  min="0" controls-position="right" @change="courseCostChange" v-model="form.courseCost" placeholder="每课时/元" /></el-form-item>
          </el-col>
           <el-col :span="12">
            <el-form-item label="总课时" prop="classAll">
            <el-input-number min="1" controls-position="right" v-model="form.classAll" @change="classAllChange" placeholder="请输入总课时" />节</el-form-item>
          </el-col>
        </el-row>
         <el-row>
           <el-col :span="12">
            <el-form-item label="总费用" prop="totalCost">
            <el-input-number controls-position="right" v-model="form.totalCost" placeholder="请输入总费用" />元</el-form-item>
          </el-col>
         </el-row>
        <el-row>
          <el-col :span="12">
              <el-form-item label="上课时间" prop="classTime">
                <el-time-select
                        v-model="form.classTime"
                        style="width: 100%;"
                        :picker-options="{
                        start: '08:30',
                        step: '00:15',
                        end: '23:30'
                        }"
                        placeholder="选择上课时间">
                      </el-time-select>
              </el-form-item>
              </el-col>
               <el-col :span="12">
              <el-row>
                <el-col :span="23">
                  <el-form-item label="时长" prop="classHour">
                    <el-input-number  min="0" controls-position="right" style="width: 100%;" v-model="form.classHour" placeholder="例:45分" />
                  </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >分</span>
                </el-col>
              </el-row>
            </el-col>
        </el-row>
        <el-form-item label="周次" prop="week">
          <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">
            <el-checkbox-group v-model="checkedWeeks" @change="handleCheckedWeeksChange">
              <el-checkbox v-for="week in weeks" :label="week" :key="week">{{week}}</el-checkbox>
            </el-checkbox-group>
          </el-checkbox>
        </el-form-item>
        <el-form-item label="辅导内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入课程内容" />
        </el-form-item>
        <el-form-item label="授课区域" prop="region">
          <el-input v-model="form.region" placeholder="请输入授课区域" />
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
import { listCourse, getCourse, delCourse, addCourse, updateCourse, exportCourse } from "@/api/hometeacher/course";
import{changeStatusOff,changeStatusOn}from "@/api/store/course";
import signUpModal from './modal1/signUpModal'
import DetailModal from './modal1/DetailModal'
const weekOptions = ['周一', '周二', '周三', '周四','周五','周六','周日'];
export default {
   components: { signUpModal,DetailModal},
  data() {
    return {
      courseDetail:false,
      signUp:false,
      courseData:null,
      // 课程状态字典
      statusOptions: [],
       //周次选择开始
      checkAll: false,
      checkedWeeks: [],
      weeks: weekOptions,
      isIndeterminate: true,
      // 招生对象选择
      sysGrades: [],
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
      //注册老师列表
      teachers:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        classify: undefined,
        isOneToOne: undefined,
        stuCount: undefined,
        gradeId: undefined,
        status: undefined,
      },
      // 表单参数
      form: {teacherId:null},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        region: [
          { required: true, message: "可授课不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "辅导内容不能为空", trigger: "blur" }
        ],
        courseCost: [
          { required: true, message: "课时费不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
      this.getDicts("is-one-to-one").then(response => {
      this.isOneToOneOptions = response.data;
    });
    this.getDicts("course_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
       //打开门店详情页面
    openDatail(row){
      this.currentData=row;
      this.courseDetail=true;
    },
    //关闭门店详情页面
    closeDetail(){
      this.courseDetail=false;
    },
    //打开课程报名页面
    toSignUp(row){
      this.courseData=row;
      this.signUp=true;
    },
    //关闭课程报名页面
    closeSignUp(){
      this.signUp=false;
    },
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCourse(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        createById: undefined,
        region: undefined
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
      getCourse().then(response => {
        this.sysGrades = response.sysGrades;
        this.teachers=response.teachers;
        this.form.courseCost=0;
        this.form.totalCost=0;
        this.form.classAll=0;
        this.open = true;
        this.title = "添加家教课程";
      });
    },
     handleCheckAllChange(val) {
      this.checkedWeeks= val ? weekOptions : [];
      this.isIndeterminate = false;
    },
    handleCheckedWeeksChange(value) {
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.weeks.length;
      this.isIndeterminate = checkedCount > 0 && checkedCount < this.weeks.length;
    },
     //课程费值改变事件
    courseCostChange(e){
      if(this.form.classAll==null){
          this.form.classAll=1;
      }
      this.form.totalCost=e*this.form.classAll;
    },
    classAllChange(e){
        if(this.form.courseCost==null){
          this.form.courseCost=1;
      }
        this.form.totalCost=e*this.form.courseCost;
    },
     /** 更改课程状态 */
    changeStatus(row,status) {
      const ids = row.id || this.ids;
      let courseStatus=null;
      if(status==0){
        courseStatus="上线"
      }else if(status==1){
        courseStatus="下线"
      }
      this.$confirm('是否确认'+courseStatus+'课程编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        if(status==0){
          return changeStatusOn(ids);
        }else if(status==1){
          return changeStatusOff(ids);
        }

      }).then(() => {
        this.getList();
        this.msgSuccess(courseStatus+"成功");
      }).catch(function() {});
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
     getCourse(id).then(response => {
        this.sysGrades = response.sysGrades;
        this.form = response.data;
        this.form.gradeId=JSON.parse(this.form.gradeId);
        this.checkedWeeks=JSON.parse(this.form.week);
        this.open = true;
        this.title = "修改家教课程";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.gradeId=JSON.stringify(this.form.gradeId);
          this.form.week=JSON.stringify(this.checkedWeeks);
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
            this.form.status=0;//默认在售状态
            this.form.courseType='2',//家教客户才能
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
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
