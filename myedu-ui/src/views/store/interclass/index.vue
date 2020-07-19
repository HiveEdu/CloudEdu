<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="班级名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入班级名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="课程类型" prop="classType">
        <el-select v-model="queryParams.classType" placeholder="请选择课程类型" clearable size="small">
          <el-option
            v-for="dict in classTypeOptions"
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
          v-hasPermi="['store:interclass:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['store:interclass:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:interclass:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:interclass:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="interclassList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="所属门店" align="center" prop="storeName" />
      <!-- <el-table-column label="班级图标" align="center" prop="classLogo" /> -->
        <el-table-column ref="table" prop="logo" label="Logo" width="60">
        <template slot-scope="scope">
          <img  :src="viewImage+'/'+scope.row.logo" :onerror="defaultImg" alt="点击打开" class="el-avatar" style="border-radius:10px">
        </template>
      </el-table-column>
      <el-table-column label="班级名称" align="center" prop="name" />
      <!-- <el-table-column label="开课日期" align="center" prop="openDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.openDate) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="主教老师" align="center" prop="masterTeacher" />
      <el-table-column label="助教老师" align="center" prop="assistantTeacher" />
      <!-- <el-table-column label="上课时间" align="center" prop="classTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.classTime) }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="时长" align="center" prop="duration" />
      <el-table-column label="总课时" align="center" prop="totalCalssHour" />
      <el-table-column label="课程类型" align="center" prop="classType" :formatter="classTypeFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['store:interclass:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['store:interclass:remove']"
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

    <!-- 添加或修改门店兴趣班对话框 -->
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
        <!-- <el-form-item label="班级图标" prop="logo">
          <el-input v-model="form.logo" placeholder="请输入班级图标" />
        </el-form-item> -->
          <el-form-item label="班级图标">
          <el-upload
            class="avatar-uploader"
            ref="upload"
            :headers="headers"
            :action="uploadImgUrl"
            :data="{'type':'stuAvatar'}"
            :show-file-list="false"
            :on-success="onSuccess"
            :on-remove="handleRemove"
            :on-preview="handlePictureCardPreview"
            :before-upload="beforeUpload">
            <img v-if="form.logo" :src="viewImage+'/'+form.logo" :onerror="defaultImg" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="辅导内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入辅导内容" />
        </el-form-item>
        <el-form-item label="班级名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入班级名称" />
        </el-form-item>
        <el-form-item label="开课日期" prop="openDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.openDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开课日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="主教老师" prop="masterTeacher">
          <el-input v-model="form.masterTeacher" placeholder="请输入主教老师" />
        </el-form-item>
        <el-form-item label="助教老师" prop="assistantTeacher">
          <el-input v-model="form.assistantTeacher" placeholder="请输入助教老师" />
        </el-form-item>
        <el-form-item label="上课时间" prop="classTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.classTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择上课时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="时长" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入时长" />
        </el-form-item>
        <el-form-item label="总课时" prop="totalCalssHour">
          <el-input v-model="form.totalCalssHour" placeholder="请输入总课时" />
        </el-form-item>
        <el-form-item label="课程说明" prop="courseDesc">
          <el-input v-model="form.courseDesc" placeholder="请输入课程说明" />
        </el-form-item>
        <el-form-item label="课程类型">
          <el-radio-group v-model="form.classType">
            <el-radio
              v-for="dict in classTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上课周期" prop="week">
          <el-input v-model="form.week" placeholder="请输入上课周期" />
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
import { listInterclass, getInterclass, delInterclass, addInterclass, updateInterclass, exportInterclass } from "@/api/store/interclass";
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      //默认图片
       defaultImg: 'this.src="' + require("@/assets/image/deaufalt.jpg") + '"',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
        //门店列表
      stores:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 总条数
      total: 0,
      // 门店兴趣班表格数据
      interclassList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 课程类型字典
      classTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        classType: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
         name: [
          { required: true, message: "班级名称不能为空", trigger: "blur" }
        ],
         storeId:[
          { required: true, message: "所属门店不能为空", trigger: "blur" }
        ]
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
    this.getDicts("interest_course_type").then(response => {
      this.classTypeOptions = response.data;
    });
  },
  methods: {
    /** 查询门店兴趣班列表 */
    getList() {
      this.loading = true;
      listInterclass(this.queryParams).then(response => {
        this.interclassList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 课程类型字典翻译
    classTypeFormat(row, column) {
      return this.selectDictLabel(this.classTypeOptions, row.classType);
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
        logo: undefined,
        content: undefined,
        name: undefined,
        openDate: undefined,
        masterTeacher: undefined,
        assistantTeacher: undefined,
        classTime: undefined,
        duration: undefined,
        totalCalssHour: undefined,
        courseDesc: undefined,
        classType: "0",
        delFlag: undefined,
        createById: undefined,
        createBy: undefined,
        createTime: undefined,
        updateById: undefined,
        updateBy: undefined,
        updateTime: undefined,
        week: undefined,
        storeId: undefined
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
       getInterclass().then(response => {
        this.stores=response.stores;
        this.open = true;
        this.title = "添加门店兴趣班";
      });
      
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInterclass(id).then(response => {
        this.stores=response.stores;
        this.form = response.data;
        this.open = true;
        this.title = "修改门店兴趣班";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateInterclass(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addInterclass(this.form).then(response => {
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
      this.$confirm('是否确认删除门店兴趣班编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInterclass(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店兴趣班数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInterclass(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
     beforeUpload(file){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
       //this.form.avatar=res.url
        this.form.logo=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.form.logo=null;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
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
</style>