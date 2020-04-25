<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="65px">
      <el-form-item label="门店" prop="storeName">
        <el-input
          v-model="queryParams.storeName"
          placeholder="请输入门店名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学生" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择学生状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['store:storeStudent:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['store:storeStudent:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:storeStudent:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:storeStudent:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="storeStudentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="门店" align="center" prop="storeName" />
      <el-table-column label="班级" align="center" prop="className" />
      <el-table-column label="学生" align="center" prop="studentName" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="签到次数" align="center" prop="sigin" />
      <el-table-column label="签退次数" align="center" prop="sigout" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['store:storeStudent:edit']"-->
<!--          >修改</el-button>-->
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item  v-if="scope.row.status!=2">
                <el-button
                  size="mini"
                  type="success"
                  icon="el-icon-edit"
                  @click="changeStatus(scope.row,2)"
                  style="margin-top: 10px"
                >在校</el-button>
              </el-dropdown-item>
              <el-dropdown-item  v-if="scope.row.status==2">
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="changeStatus(scope.row,3)"
                  style="margin-top: 10px"
                >离校</el-button>
              </el-dropdown-item>
              <el-dropdown-item  v-if="scope.row.status==2">
                <el-button
                  size="mini"
                  type="warning"
                  icon="el-icon-edit"
                  @click="sign(scope.row,2)"
                  style="margin-top: 10px"
                >签到</el-button>
              </el-dropdown-item>
              <el-dropdown-item  v-if="scope.row.status==2">
                <el-button
                  size="mini"
                  type="warning"
                  icon="el-icon-edit"
                  @click="sign(scope.row,3)"
                  style="margin-top: 10px"
                >签退</el-button>
              </el-dropdown-item>
              <el-dropdown-item  v-if="scope.row.status==3">
                <el-button
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['store:storeStudent:remove']"
                  style="margin-top: 10px"
                >删除</el-button>
              </el-dropdown-item>
              <el-dropdown-item >
                <el-button
                  size="mini"
                  type="success"
                  icon="el-icon-edit"
                  @click="changeClass(scope.row)"
                  style="margin-top: 10px"
                >班级</el-button>
              </el-dropdown-item>
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

    <!--分班页面-->
    <classStuModal ref="classStuModal" :classStu="classStu" :currentData="currentData" @closeClassStuModal="closeClassStuModal"></classStuModal>

    <!-- 添加或修改门店学生管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门店id" prop="storeId">
          <el-input v-model="form.storeId" placeholder="请输入门店id" />
        </el-form-item>
        <el-form-item label="学生id" prop="stuId">
          <el-input v-model="form.stuId" placeholder="请输入学生id" />
        </el-form-item>
        <el-form-item label="学生状态">
          <el-select v-model="form.status" placeholder="请选择学生状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="签到类型" prop="signinType">
          <el-input v-model="form.signinType" placeholder="请输入签到类型" />
        </el-form-item>
        <el-form-item label="分配班级" prop="clbum">
          <el-input v-model="form.clbum" placeholder="请输入分配班级" />
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
import { listStoreStudent, getStoreStudent, delStoreStudent, addStoreStudent, updateStoreStudent, exportStoreStudent,
  changeStatusOff,changeStatusOn,sigint,sigout} from "@/api/store/storeStudent";
import classStuModal from './modal/classStuModal'
export default {
  components: { classStuModal},
  data() {
    return {
      classStu:false,
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
      // 门店学生管理表格数据
      storeStudentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 学生状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        storeId: undefined,
        stuId: undefined,
        status: undefined,
        signinType: undefined,
        clbum: undefined,
        createBy: undefined,
        createTime: undefined,
        delFlag: undefined,
        createById: undefined,
        storeName:undefined,
        studentName:undefined,
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
    this.getDicts("store_stu_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 分班 */
    changeClass(row){
      this.currentData=row;
      this.classStu=true;
    },
    closeClassStuModal(){
      this.classStu=false;
      this.getList();
    },
    /** 更改门店学生状态 */
    changeStatus(row,status) {
      const ids = row.id || this.ids;
      let studentStatus=null;
      if(status==2){
        studentStatus="在校"
      }else if(status==3){
        studentStatus="离校"
      }
      this.$confirm('是否确认'+studentStatus+'门店编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        if(status==2){
          return changeStatusOn(ids);
        }else if(status==3){
          return changeStatusOff(ids);
        }

      }).then(() => {
        this.getList();
        this.msgSuccess(studentStatus+"成功");
      }).catch(function() {});
    },
    //签到签退
    sign(row,status){
      const ids = row.id || this.ids;
      let studentStatus=null;
      if(status==2){
        studentStatus="签到"
      }else if(status==3){
        studentStatus="签退"
      }
      this.$confirm('是否确认'+studentStatus+'学生编为"' + row.studentName + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        if(status==2){
          return sigint(ids);
        }else if(status==3){
          return sigout(ids);
        }

      }).then(() => {
        this.getList();
        this.msgSuccess(studentStatus+"成功");
      }).catch(function() {});
    },
    /** 查询门店学生管理列表 */
    getList() {
      this.loading = true;
      listStoreStudent(this.queryParams).then(response => {
        this.storeStudentList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 学生状态字典翻译
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
        storeId: undefined,
        stuId: undefined,
        status: undefined,
        signinType: undefined,
        clbum: undefined,
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
      this.title = "添加门店学生管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStoreStudent(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店学生管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStoreStudent(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addStoreStudent(this.form).then(response => {
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
      this.$confirm('是否确认删除门店学生管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStoreStudent(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店学生管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStoreStudent(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style scoped>
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
