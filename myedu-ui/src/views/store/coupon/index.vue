<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入优惠券名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
     <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者用户"
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
          v-hasPermi="['store:coupon:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['store:coupon:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['store:coupon:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['store:coupon:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="couponList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="title" />
      <el-table-column label="优惠券面值" align="center" prop="couponPrice" />
      <el-table-column label="优惠券最低消费" align="center" prop="useMinPrice" />
      <el-table-column label="优惠券有效期限" align="center" prop="couponTime" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTimeBefore(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发布次数" align="center" prop="publishNum" />
      <el-table-column label="操作" align="center" width="200" >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="couponPublish(scope.row)"
          >
            发布
          </el-button>
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <el-button
                  size="mini"
                  type="primary"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                  v-hasPermi="['store:coupon:edit']"
                >修改</el-button>
                <br>
                <el-button
                  style="margin-top: 10px"
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['store:coupon:remove']"
                >删除</el-button>
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
    <!--优惠券发布页面-->
    <publishModal ref="publishModal" :publish="publish" :courseData="courseData" @closePublish="closePublish"></publishModal>
    <!-- 添加或修改店铺优惠券对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="优惠券名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入优惠券名称" />
        </el-form-item>
        <el-row>
          <el-col :span="23">
            <el-form-item label="优惠券面值" prop="couponPrice">
              <el-input-number  :min="0"  style="width: 100%;" controls-position="right"  v-model="form.couponPrice" placeholder="请输入兑换的优惠券面值" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="23">
            <el-form-item label="优惠券最低消费" prop="useMinPrice">
              <el-input-number  :min="0"  style="width: 100%;" controls-position="right"  v-model="form.useMinPrice" placeholder="请输入最低消费多少金额可用优惠券" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >元</span>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="23">
            <el-form-item label="优惠券有效期限" prop="couponTime">
              <el-input-number :min="0"  style="width: 100%;" controls-position="right" v-model="form.couponTime" placeholder="请输入优惠券有效期限" />
            </el-form-item>
          </el-col>
          <el-col :span="1" style="margin-top:6px; ">
            <span >天</span>
          </el-col>
        </el-row>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
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
import { listCoupon, getCoupon, delCoupon, addCoupon, updateCoupon, exportCoupon } from "@/api/store/coupon";
import { formatTime } from '@/utils/index'
import publishModal from './modal/publishModal'
export default {
  components: { publishModal },
  data() {
    return {
      publish:false,
      courseData:null,
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
      // 店铺优惠券表格数据
      couponList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        integral: undefined,
        couponPrice: undefined,
        useMinPrice: undefined,
        couponTime: undefined,
        sort: undefined,
        status: undefined,
        createBy: undefined,
        createTime: undefined,
        delFlag: undefined,
        createById: undefined,
        storeId: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "优惠券名称不能为空", trigger: "blur" }
        ],
        couponPrice: [
          { required: true, message: "优惠券面值不能为空", trigger: "blur" }
        ],
        useMinPrice: [
          { required: true, message: "优惠券最低消费不能为空", trigger: "blur" }
        ],
        couponTime: [
          { required: true, message: "优惠券有效期不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "优惠券状态不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("coupon_type").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    //打开优惠券发布页面
    couponPublish(row){
      this.courseData=row;
      this.publish=true;
    },
    //关闭优惠券发布页面
    closePublish(){
      this.publish=false;
      this.getList()
    },
    parseTimeBefore(e){
      return formatTime((new Date(e)).getTime() / 1000);
    },
    /** 查询店铺优惠券列表 */
    getList() {
      this.loading = true;
      listCoupon(this.queryParams).then(response => {
        this.couponList = response.rows;
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
        id: undefined,
        title: undefined,
        integral: undefined,
        couponPrice: undefined,
        useMinPrice: undefined,
        couponTime: undefined,
        sort: undefined,
        status: "0",
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        delFlag: undefined,
        createById: undefined,
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
      this.open = true;
      this.form.status="1";
      this.title = "添加店铺优惠券";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCoupon(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改店铺优惠券";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateCoupon(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addCoupon(this.form).then(response => {
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
      this.$confirm('是否确认删除店铺优惠券编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCoupon(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有店铺优惠券数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportCoupon(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
