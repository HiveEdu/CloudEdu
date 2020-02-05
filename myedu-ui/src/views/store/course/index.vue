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
<!--        <el-form-item label="一对一" prop="isOneToOne">-->
<!--        <el-select v-model="queryParams.isOneToOne" placeholder="请选择是否一对一" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in isOneToOneOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
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
      <el-table-column label="封面" width="55" >
        　　<template slot-scope="scope">
        　　　　<img :src="viewImage+'/'+scope.row.picture" width="50" height="50" class="head_pic"/>
        　　</template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="类型" align="center" prop="classify" :formatter="classifyFormat" />
<!--      <el-table-column label="招生人数" align="center" prop="stuCount" />-->
<!--      <el-table-column label="招生对象" align="center" prop="gradeId" />-->
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
      <el-table-column label="操作" align="center" width="200" >
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
                v-hasPermi="['course:course:edit']"
                style="margin-top: 10px"
              >修改</el-button>
              <br>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['course:course:remove']"
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
          </el-col>
        </el-row>

          <el-row>
            <el-col :span="6">
              <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入课程名称" />
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="类型"  prop="classify">
                <el-select v-model="form.classify" placeholder="请选择课程类型">
                  <el-option
                    v-for="dict in classifyOptions"
                    :key="dict.dictValue"
                    :label="dict.dictLabel"
                    :value="dict.dictValue"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6" v-if="form.classify==0">
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
            </el-col>
            <el-col :span="6" v-if="form.classify!=3">
              <el-form-item label="一对一">
                <el-radio-group v-model="form.isOneToOne">
                  <el-radio
                    v-for="dict in isOneToOneOptions"
                    :key="dict.dictValue"
                    :label="dict.dictValue"
                  >{{dict.dictLabel}}</el-radio>
                </el-radio-group>
              </el-form-item>

            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="招生对象" prop="gradeId">
                <el-select v-model="form.gradeId" multiple placeholder="请选择招生对象"  style="width: 100%;">
                  <el-option
                    v-for="item in sysGrades"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-row>
                <el-col :span="23">
                  <el-form-item label="招生人数" prop="stuCount">
                    <el-input-number min="0" controls-position="right" v-model="form.stuCount" style="width: 100%;" placeholder="请输入招生人数" />
                  </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >人</span>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="开始时间" prop="cStarttime">
                <el-date-picker clearable size="small"
                                v-model="form.cStarttime"
                                type="date"
                                style="width: 100%;"
                                value-format="yyyy-MM-dd"
                                placeholder="选择开始时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="结束时间" prop="cEndtime">
                <el-date-picker clearable size="small"
                                v-model="form.cEndtime"
                                style="width: 100%;"
                                type="date"
                                value-format="yyyy-MM-dd"
                                placeholder="选择结束时间">
                </el-date-picker>
              </el-form-item>
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
          <el-row>
            <el-col :span="24">
              <el-form-item label="周次" prop="week">
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">
                  <el-checkbox-group v-model="checkedWeeks" @change="handleCheckedWeeksChange">
                    <el-checkbox v-for="week in weeks" :label="week" :key="week">{{week}}</el-checkbox>
                  </el-checkbox-group>
                </el-checkbox>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-row>
                <el-col :span="23">
                  <el-form-item label="总课时" prop="classAll">
                    <el-input-number min="0" controls-position="right" v-model="form.classAll" style="width: 100%;" placeholder="请输入总课时" />
                  </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >节</span>
                </el-col>
              </el-row>

            </el-col>
            <el-col :span="12">
              <el-row>
                <el-col :span="23">
                  <el-form-item label="课程费用" prop="courseCost">
                    <el-input-number min="0" @change="courseCostChange" controls-position="right" v-model="form.courseCost" style="width: 100%;" placeholder="请输入课程费用" />
                  </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >元</span>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-row>
                <el-col :span="23">
                    <el-form-item label="餐费" prop="meals">
                      <el-input-number  min="0" @change="mealsChange" controls-position="right" v-model="form.meals" style="width: 100%;" placeholder="请输入餐费" />
                    </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >元</span>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="12">
              <el-row>
                <el-col :span="23">
                  <el-form-item label="总费用" prop="totalCost">
                    <el-input-number min="0" controls-position="right" v-model="form.totalCost" style="width: 100%;" placeholder="请输入总费用" >
                    </el-input-number>
                  </el-form-item>
                </el-col>
                <el-col :span="1" style="margin-top:6px; ">
                  <span >元</span>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="课程内容" prop="content">
              <el-input v-model="form.content" type="textarea" placeholder="请输入课程内容" />
            </el-form-item>
          </el-col>
        </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="课程介绍" prop="introduce">
                <el-input v-model="form.introduce" type="textarea" placeholder="请输入课程介绍" />
              </el-form-item>
            </el-col>
          </el-row>
        <el-row>
            <el-form-item label="封面">
              <el-upload
                class="avatar-uploader"
                ref="upload"
                :headers="headers"
                :action="uploadImgUrl"
                :data="{'type':'course'}"
                :show-file-list="false"
                :on-success="onSuccess"
                :on-remove="handleRemove"
                :on-preview="handlePictureCardPreview"
                :before-upload="beforeUpload">
                <img v-if="form.picture" :src="viewImage+'/'+form.picture" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCourse, getCourse, delCourse, changeStatusOff,changeStatusOn,addCourse, updateCourse, exportCourse } from "@/api/store/course";
import { getToken } from '@/utils/auth'
import signUpModal from './modal/signUpModal'
import DetailModal from './modal/DetailModal'
const weekOptions = ['周一', '周二', '周三', '周四','周五','周六','周日'];
export default {
  components: { signUpModal,DetailModal},
  data() {
    return {
      courseDetail:false,
      signUp:false,
      courseData:null,
      // 招生对象选择
      sysGrades: [],
      //周次选择开始
      checkAll: false,
      checkedWeeks: [],
      weeks: weekOptions,
      isIndeterminate: true,
      //周次选择结束
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
      //门店列表
      stores:[],
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
        storeId:[
          { required: true, message: "所属门店不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "课程名称不能为空", trigger: "blur" }
        ],
        classify: [
          { required: true, message: "课程类型不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "课程内容不能为空", trigger: "blur" }
        ],
      },
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      viewImage: process.env.VUE_APP_BASE_API,
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      dialogImageUrl: '',
      dialogVisible: false,
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
      getCourse().then(response => {
        this.sysGrades = response.sysGrades;
        this.stores=response.stores;
        this.open = true;
        this.title = "添加课程";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCourse(id).then(response => {
        this.sysGrades = response.sysGrades;
        this.stores=response.stores;
        this.form = response.data;
        this.form.gradeId=JSON.parse(this.form.gradeId);
        this.checkedWeeks=JSON.parse(this.form.week);
        this.open = true;
        this.title = "修改课程";
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
    //图片上传开始
    beforeUpload(file){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.form.picture=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.form.picture=null;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //图片上传结束
    //餐费值改变事件
    mealsChange(e){
      if(this.form.courseCost==0){
        this.form.courseCost=0;
      }
      this.form.totalCost=e+this.form.courseCost;
    },
    //课程费值改变事件
    courseCostChange(e){
      if(this.form.meals==0){
        this.form.meals=0;
      }
      this.form.totalCost=e+this.form.meals;
    },
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
