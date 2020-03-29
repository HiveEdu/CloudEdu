<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入会员名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择会员类型" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="等级" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入会员等级"
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
          v-hasPermi="['dataBasic:memberLevel:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dataBasic:memberLevel:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dataBasic:memberLevel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['dataBasic:memberLevel:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="memberLevelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column ref="table" prop="图标" label="图标" width="60">
        <template slot-scope="scope">
          <img  :src="imageView+'/'+scope.row.icon" :onerror="defaultImg" alt="点击打开" class="el-avatar" style="border-radius:10px">
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="有效时间(天)" align="center" prop="validDate" />
      <el-table-column label="类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="永久会员" align="center" prop="isForever" :formatter="isForeverFormat" />
      <el-table-column label="会员等级" align="center" prop="grade" />
      <el-table-column label="享受折扣" align="center" prop="discount" />
      <el-table-column label="最低充值" align="center" prop="rule" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['dataBasic:memberLevel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dataBasic:memberLevel:remove']"
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

    <!-- 添加或修改会员等级对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="名称" prop="name">
              <el-input v-model="form.name" placeholder="请输入会员名称" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择会员类型">
                <el-option
                  v-for="dict in typeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="有效时间(天)" prop="validDate">
              <el-input-number :min="0" controls-position="right" v-model="form.validDate" placeholder="请输入有效时间(天)" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label=" 永久会员" prop="isForever">
              <el-select v-model="form.isForever" placeholder="请选择是否为永久会员">
                <el-option
                  v-for="dict in isForeverOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="会员等级" prop="grade">
              <el-input-number :min="0" controls-position="right" v-model="form.grade" placeholder="请输入会员等级" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="享受折扣" prop="discount">
              <el-input-number :min="0" controls-position="right"  v-model="form.discount" placeholder="请输入享受折扣" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="充值规则(>=)" prop="rule">
              <el-input-number :min="0" controls-position="right"  v-model="form.rule" placeholder="请输入享受折扣" />
            </el-form-item>
          </el-col>
        </el-row>
<!--        <el-form-item label="会员卡背景" prop="image">-->
<!--          <el-input v-model="form.image" placeholder="请输入会员卡背景" />-->
<!--        </el-form-item>-->
        <el-form-item label="会员图标" prop="icon">
<!--          <el-input v-model="form.icon" placeholder="请输入会员图标" />-->
          <el-upload
            class="avatar-uploader"
            ref="upload"
            :headers="headers"
            :action="uploadImgUrl"
            :data="{'type':'memberLeave'}"
            :show-file-list="false"
            :on-success="onSuccess"
            :on-remove="handleRemove"
            :on-preview="handlePictureCardPreview"
            :before-upload="beforeUpload">
            <img v-if="form.icon" :src="imageView+'/'+form.icon" :onerror="defaultImg" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="说明" prop="note">
          <el-input v-model="form.note" placeholder="请输入说明" />
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
import { listMemberLevel, getMemberLevel, delMemberLevel, addMemberLevel, updateMemberLevel, exportMemberLevel } from "@/api/dataBasic/memberLevel";
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
      // 会员等级表格数据
      memberLevelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 1门店会员2普通用户会员字典
      typeOptions: [],
      // 是否为永久会员字典
      isForeverOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        validDate: undefined,
        type: undefined,
        isForever: undefined,
        grade: undefined,
        discount: undefined,
        image: undefined,
        icon: undefined,
        note: undefined,
      },
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      imageView: process.env.VUE_APP_BASE_API,
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      dialogImageUrl: '',
      dialogVisible: false,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "会员名称不能为空", trigger: "blur" }
        ],        validDate: [
          { required: true, message: "有效时间(天)不能为空", trigger: "change" }
        ],        type: [
          { required: true, message: "会员类型不能为空", trigger: "blur" }
        ],        grade: [
          { required: true, message: "会员等级不能为空", trigger: "change" }
        ],        discount: [
          { required: true, message: "享受折扣不能为空", trigger: "change" }
        ],        rule: [
          { required: true, message: "充值规则", trigger: "change" }
        ],              type: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],         isForever: [
          { required: true, message: "永久会员不能为空", trigger: "blur" }
        ], }
    };

  },
  created() {
    this.getList();
    this.getDicts("member_type").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("isno").then(response => {
      this.isForeverOptions = response.data;
    });
  },
  methods: {
    /** 查询会员等级列表 */
    getList() {
      this.loading = true;
      listMemberLevel(this.queryParams).then(response => {
        this.memberLevelList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 1门店会员2普通用户会员字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 是否为永久会员字典翻译
    isForeverFormat(row, column) {
      return this.selectDictLabel(this.isForeverOptions, row.isForever);
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
        validDate: undefined,
        type: undefined,
        isForever: undefined,
        grade: undefined,
        discount: undefined,
        image: undefined,
        icon: undefined,
        note: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
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
      this.title = "添加会员等级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMemberLevel(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改会员等级";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateMemberLevel(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addMemberLevel(this.form).then(response => {
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
      this.$confirm('是否确认删除会员等级编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMemberLevel(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有会员等级数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMemberLevel(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    beforeUpload(file){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        //this.form.avatar=res.url
        this.form.icon=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.form.icon=null;
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
