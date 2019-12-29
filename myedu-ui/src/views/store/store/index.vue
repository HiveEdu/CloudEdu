<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="门店名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入门店名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店logo" prop="logo">
        <el-input
          v-model="queryParams.logo"
          placeholder="请输入门店logo"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店负责人" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入门店负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人电话" prop="managerPhone">
        <el-input
          v-model="queryParams.managerPhone"
          placeholder="请输入负责人电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店照片墙" prop="photos">
        <el-input
          v-model="queryParams.photos"
          placeholder="请输入门店照片墙"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店宣传视频地址" prop="video">
        <el-input
          v-model="queryParams.video"
          placeholder="请输入门店宣传视频地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店营业执照" prop="license">
        <el-input
          v-model="queryParams.license"
          placeholder="请输入门店营业执照"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店详细地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入门店详细地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店地图坐标经纬度详情x" prop="lat">
        <el-input
          v-model="queryParams.lat"
          placeholder="请输入门店地图坐标经纬度详情x"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门店地图坐标经纬度详情y" prop="lon">
        <el-input
          v-model="queryParams.lon"
          placeholder="请输入门店地图坐标经纬度详情y"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="门店类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择门店类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
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
          v-hasPermi="['store:store:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['store:store:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:store:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:store:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="storeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="门店名称" align="center" prop="name" />
      <el-table-column label="门店logo" align="center" prop="logo" />
      <el-table-column label="门店负责人" align="center" prop="manager" />
      <el-table-column label="负责人电话" align="center" prop="managerPhone" />
      <el-table-column label="门店照片墙" align="center" prop="photos" />
      <el-table-column label="门店宣传视频地址" align="center" prop="video" />
      <el-table-column label="门店营业执照" align="center" prop="license" />
      <el-table-column label="门店详细地址" align="center" prop="address" />
      <el-table-column label="门店地图坐标经纬度详情x" align="center" prop="lat" />
      <el-table-column label="门店地图坐标经纬度详情y" align="center" prop="lon" />
      <el-table-column label="门店类型" align="center" prop="type" />
      <el-table-column label="创建人Id" align="center" prop="createById" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['store:store:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['store:store:remove']"
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

    <!-- 添加或修改门店对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="门店名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入门店名称" />
        </el-form-item>
        <el-form-item label="门店logo" prop="logo">
          <el-input v-model="form.logo" placeholder="请输入门店logo" />
        </el-form-item>
        <el-form-item label="门店负责人" prop="manager">
          <el-input v-model="form.manager" placeholder="请输入门店负责人" />
        </el-form-item>
        <el-form-item label="负责人电话" prop="managerPhone">
          <el-input v-model="form.managerPhone" placeholder="请输入负责人电话" />
        </el-form-item>
        <el-form-item label="门店照片墙" prop="photos">
          <el-input v-model="form.photos" placeholder="请输入门店照片墙" />
        </el-form-item>
        <el-form-item label="门店宣传视频地址" prop="video">
          <el-input v-model="form.video" placeholder="请输入门店宣传视频地址" />
        </el-form-item>
        <el-form-item label="门店营业执照" prop="license">
          <el-input v-model="form.license" placeholder="请输入门店营业执照" />
        </el-form-item>
        <el-form-item label="门店详细地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入门店详细地址" />
        </el-form-item>
        <el-form-item label="门店地图坐标经纬度详情x" prop="lat">
          <el-input v-model="form.lat" placeholder="请输入门店地图坐标经纬度详情x" />
        </el-form-item>
        <el-form-item label="门店地图坐标经纬度详情y" prop="lon">
          <el-input v-model="form.lon" placeholder="请输入门店地图坐标经纬度详情y" />
        </el-form-item>
        <el-form-item label="门店类型">
          <el-select v-model="form.type" placeholder="请选择门店类型">
            <el-option label="请选择字典生成" value="" />
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
import { listStore, getStore, delStore, addStore, updateStore, exportStore } from "@/api/store/store";

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
      // 门店表格数据
      storeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: undefined,
        logo: undefined,
        manager: undefined,
        managerPhone: undefined,
        photos: undefined,
        video: undefined,
        license: undefined,
        address: undefined,
        lat: undefined,
        lon: undefined,
        type: undefined,
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
    /** 查询门店列表 */
    getList() {
      this.loading = true;
      listStore(this.queryParams).then(response => {
        this.storeList = response.rows;
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
        logo: undefined,
        manager: undefined,
        managerPhone: undefined,
        photos: undefined,
        video: undefined,
        license: undefined,
        address: undefined,
        lat: undefined,
        lon: undefined,
        type: undefined,
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
      this.title = "添加门店";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStore(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateStore(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addStore(this.form).then(response => {
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
      this.$confirm('是否确认删除门店编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStore(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStore(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>