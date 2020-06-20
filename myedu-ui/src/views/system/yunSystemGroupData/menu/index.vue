<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
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
          v-hasPermi="['system:yunSystemGroupData:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:yunSystemGroupData:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:yunSystemGroupData:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:yunSystemGroupData:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="yunSystemGroupDataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="id" label="ID" />
      <el-table-column prop="map.name" label="分类名称" />
      <el-table-column prop="map.url" label="链接url" />
      <el-table-column prop="map.wxapp_url" label="小程序跳转page" />
      <!-- <el-table-column label="数据排序" align="center" prop="sort" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" /> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['yunSystemGroupData:yunSystemGroupData:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['yunSystemGroupData:yunSystemGroupData:remove']"
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

    <!-- 添加或修改首页导航按钮详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
       <el-form-item label="分类名称">
        <el-input v-model="form.name" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="跳转url">
        <el-input v-model="form.url" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="小程序跳转page">
        <el-input v-model="form.wxapp_url" style="width: 300px;" />
      </el-form-item>
      <el-form-item label="图片">
          <el-upload
            class="avatar-uploader"
            ref="upload"
            :headers="headers"
            :action="uploadImgUrl"
            :data="{'type':'banner'}"
            :show-file-list="false"
            :on-success="onSuccess"
            :on-remove="handleRemove"
            :on-preview="handlePictureCardPreview"
            :before-upload="beforeUpload">
            <img v-if="form.photo" :src="viewImage+'/'+form.imageArr" :onerror="defaultImg" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" style="width: 300px;"/>
        </el-form-item>
        <el-form-item label="是否显示">
          <el-radio-group v-model="form.isshow">
            <el-radio
              v-for="dict in statusOptions"
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
import { listYunSystemGroupData, getYunSystemGroupData, delYunSystemGroupData, addYunSystemGroupData, updateYunSystemGroupData, exportYunSystemGroupData } from "@/api/system/yunSystemGroupData";
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
       defaultImg: 'this.src="' + require("@/assets/image/deaufalt.jpg") + '"',
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
      // 首页导航按钮详情表格数据
      yunSystemGroupDataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
     form: {
        id: '',
        groupName: 'routine_home_menus',
        name: '',
        url: '',
        wxapp_url: '',
        pic: '',
        imageArr: '',
        sort: '',
        status: ''
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        groupName: undefined,
        value: undefined,
        addTime: undefined,
        sort: undefined,
        status: undefined
      },
      // 表单校验
      rules: {
       
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
    this.getDicts("is_show").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
     beforeInit() {
      this.url = '/system/yunSystemGroupData/list'
      const sort = 'id,desc'
     this.params = { page: this.page, size: this.size, sort: sort, groupName: 'routine_home_menus' }
      return true
    },
    /** 查询首页导航按钮详情列表 */
    getList() {
      this.loading = true;
      listYunSystemGroupData(this.queryParams).then(response => {
        this.yunSystemGroupDataList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
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
       // id: '',
        groupName: 'routine_home_menus',
        imageArr: '',
        value: '',
        addTime: '',
        sort: '',
        status: ''
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
      this.title = "添加首页导航按钮详情";
    },
    edit(data) {
      this.isAdd = false
      const _this = this.$refs.form
      _this.form = {
        id: data.id,
        groupName: data.groupName,
        name: data.map.name,
        url: data.map.url,
        wxapp_url: data.map.wxapp_url
      }
      _this.dialog = true
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getYunSystemGroupData(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改首页导航按钮详情";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateYunSystemGroupData(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addYunSystemGroupData(this.form).then(response => {
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
      this.$confirm('是否确认删除组合数据详情编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delYunSystemGroupData(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有首页导航按钮详情数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportYunSystemGroupData(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
     beforeUpload(file){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.form.photo=res.fileName;
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.form.photo=null;
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
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
</style>
