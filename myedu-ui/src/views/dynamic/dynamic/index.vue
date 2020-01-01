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
      <el-table-column label="动态类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="动态内容" align="center" prop="content" />
      <el-table-column label="点赞数量" align="center" prop="likes" />
      <el-table-column label="评论数量" align="center" prop="comments" />
      <el-table-column label="创建者" align="center" prop="createBy" />
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
          <el-input v-model="form.content"  type="textarea"
                    :rows="4" placeholder="请输入动态内容" />
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
        <!--            :action="uploadAction" :fileList="fileList"-->、
        <el-form-item label="照片上传">
<!--          <el-upload-->
<!--            action="#"-->
<!--            list-type="picture-card"-->
<!--            :auto-upload="false">-->
<!--            <i slot="default" class="el-icon-plus"></i>-->
<!--            <div slot="file" slot-scope="{file}">-->
<!--              <img-->
<!--                class="el-upload-list__item-thumbnail"-->
<!--                :src="file.url" alt=""-->
<!--              >-->
<!--              <span class="el-upload-list__item-actions">-->
<!--        <span-->
<!--          class="el-upload-list__item-preview"-->
<!--          @click="handlePictureCardPreview(file)"-->
<!--        >-->
<!--          <i class="el-icon-zoom-in"></i>-->
<!--        </span>-->
<!--        <span-->
<!--          v-if="!disabled"-->
<!--          class="el-upload-list__item-delete"-->
<!--          @click="handleDownload(file)"-->
<!--        >-->
<!--          <i class="el-icon-download"></i>-->
<!--        </span>-->
<!--        <span-->
<!--          v-if="!disabled"-->
<!--          class="el-upload-list__item-delete"-->
<!--          @click="handleRemove(file)"-->
<!--        >-->
<!--          <i class="el-icon-delete"></i>-->
<!--        </span>-->
<!--      </span>-->
<!--            </div>-->
<!--          </el-upload>-->
<!--          <el-dialog :visible.sync="dialogVisible">-->
<!--            <img width="100%" :src="dialogImageUrl" alt="">-->
<!--          </el-dialog>-->


          <el-upload
            action="#"
            list-type="picture-card"
            :auto-upload="false"
            :show-file-list="true"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :on-change="uploadImg"
            :before-upload="beforeUpload"
            :on-success="successUpload">
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
import { listDynamic, getDynamic, delDynamic, addDynamic, updateDynamic, exportDynamic ,
  uploadImage} from "@/api/dynamic/dynamic";

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
      fileList: null,
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false
    };
  },
  created() {
    this.getList();
    this.getDicts("dynamic_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  computed:{
    uploadAction:function () {
      return "/dynamic/dynamic/upload";
    }
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
      this.title = "添加动态";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDynamic(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改动态";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
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
            addDynamic(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                // let formData = new FormData();
                // formData.append("imagefiles", this.fileList);
                // uploadImage(formData).then(response => {
                //   if (response.code === 200) {
                //     this.msgSuccess("修改成功");
                //   } else {
                //     this.msgError(response.msg);
                //   }
                // });
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
    // 上传预处理
    beforeUpload(file) {
      if (file.type.indexOf("image/") == -1) {
        this.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
      } else {
        alert("图片预处理")
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
          alert("onload--"+file.name);
          //this.options.img = reader.result;
        };
      }
    },

    /** 上传图片 */
    uploadImg (file, fileList) {
      // this.fileList=fileList;
      // alert(this.fileList);
      let formData = new FormData();
      formData.append("imagefile", file);
      uploadImage(formData).then(response => {
        if (response.code === 200) {
          this.msgSuccess("修改成功");
        } else {
          this.msgError(response.msg);
        }
      });
    },
    /** 上传成功 */
    successUpload(res,file, fileList) {
      alert("successUpload--"+file.name);
    },
    /** 移除照片 */
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    /** 预览照片 */
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    /** 下载照片 */
    handleDownload(file) {
      console.log(file);
    }
  }
};
</script>
