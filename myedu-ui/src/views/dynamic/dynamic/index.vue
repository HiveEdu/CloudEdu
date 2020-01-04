<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
        <el-form-item label="动态类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择动态类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建人Id" prop="createById">
        <el-input
          v-model="queryParams.createById"
          placeholder="请输入创建人Id"
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
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择创建时间">
        </el-date-picker>
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
          v-hasPermi="['dynamic:dynamic:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dynamic:dynamic:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dynamic:dynamic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['dynamic:dynamic:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="dynamicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column label="动态类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['dynamic:dynamic:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dynamic:dynamic:remove']"
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

    <!-- 添加或修改云托管动态管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="60%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="动态内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入动态内容" />
        </el-form-item>
        <el-form-item label="动态类型">
          <el-select v-model="form.type" placeholder="请选择动态类型">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图片上传">
          <el-upload
            ref="upload"
            :action="uploadImgUrl"
            list-type="picture-card"
            content-type="false"
            :headers="headers"
            :file-list="fileList"
            :show-file-list="true"
            :before-upload="beforeUpload"
            :on-change="onChange"
            :on-success="onSuccess"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            accept='.jpg,.jpeg,.png,.gif'
            >
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
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
import { listDynamic, getDynamic, delDynamic, addDynamic, updateDynamic, exportDynamic } from "@/api/dynamic/dynamic";
import { getToken } from '@/utils/auth'
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
      // 云托管动态管理表格数据
      dynamicList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 动态类型字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: undefined,
        createById: undefined,
        createBy: undefined,
        createTime: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      dialogImageUrl: '',
      dialogVisible: false,
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      fileList:null,
      fileListnew:[],
    };
  },
  created() {
    this.getList();
    this.getDicts("dynamic_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询云托管动态管理列表 */
    getList() {
      this.loading = true;
      listDynamic(this.queryParams).then(response => {
        this.dynamicList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 动态类型字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.$refs.upload.clearFiles();
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        content: undefined,
        picture: undefined,
        type: undefined,
        likes: undefined,
        comments: undefined,
        createById: undefined,
        createBy: undefined,
        createTime: undefined,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加云托管动态管理";
      this.fileListnew=[];
      this.$refs.upload.clearFiles();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.fileListnew=[];
      const id = row.id || this.ids
      getDynamic(id).then(response => {
        this.form = response.data;
        this.fileList=JSON.parse(this.form.picture);
        this.fileListnew=JSON.parse(this.form.picture);
        this.open = true;
        this.title = "修改云托管动态管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.form.picture=JSON.stringify(this.fileListnew);
            updateDynamic(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            this.form.picture=JSON.stringify(this.fileListnew);
            addDynamic(this.form).then(response => {
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
      this.$refs.upload.clearFiles();
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除云托管动态管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delDynamic(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有云托管动态管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportDynamic(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    beforeUpload(file){
    },
    onChange(file, fileList){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.fileList=fileList
        this.fileListnew.push({uid:file.uid,name:file.name,status:file.status,url:res.url})
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.fileList=fileList;
      for(let i=0;i<this.fileListnew.length;i++) {
        if (this.fileListnew[i].uid === file.uid) {
          this.fileListnew.splice(i);
        }
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  }
};
</script>
