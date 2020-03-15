<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="砍价课程" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入砍价活动名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="砍价状态 " prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择砍价状态 " clearable size="small">
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
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['bargain:bargain:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['bargain:bargain:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['bargain:bargain:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="bargainList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="砍价活动名称" align="center" prop="title" />
      <el-table-column label="砍价活动图片" align="center" prop="image" />
      <el-table-column label="单位名称" align="center" prop="unitName" />
      <el-table-column label="库存" align="center" prop="stock" />
      <el-table-column label="销量" align="center" prop="sales" />
      <el-table-column label="砍价开启时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="砍价结束时间" align="center" prop="stopTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.stopTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTimeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTimeDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTimeDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTimeDate) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="砍价产品名称" align="center" prop="storeName" />
      <el-table-column label="砍价金额" align="center" prop="price" />
      <el-table-column label="砍价商品最低价" align="center" prop="minPrice" />
      <el-table-column label="每次购买的砍价产品数量" align="center" prop="num" />
      <el-table-column label="用户每次砍价的最大金额" align="center" prop="bargainMaxPrice" />
      <el-table-column label="用户每次砍价的最小金额" align="center" prop="bargainMinPrice" />
      <el-table-column label="用户每次砍价的次数" align="center" prop="bargainNum" />
      <el-table-column label="砍价状态 " align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="砍价状态 " align="center" prop="description" />
      <el-table-column label="返多少积分" align="center" prop="giveIntegral" />
      <el-table-column label="砍价活动简介" align="center" prop="info" />
      <el-table-column label="成本价" align="center" prop="cost" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="是否推荐0不推荐1推荐" align="center" prop="isHot" />
      <el-table-column label="创建时间" align="center" prop="rule" />
      <el-table-column label="砍价产品浏览量" align="center" prop="look" />
      <el-table-column label="砍价产品分享量" align="center" prop="share" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['bargain:bargain:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['bargain:bargain:remove']"
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

    <!-- 添加或修改门店砍价活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="课程id" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程id" />
        </el-form-item>
        <el-form-item label="砍价活动名称" prop="title">
          <el-input v-model="form.title" placeholder="请输入砍价活动名称" />
        </el-form-item>
        <el-form-item label="单位名称" prop="unitName">
          <el-input v-model="form.unitName" placeholder="请输入单位名称" />
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input v-model="form.stock" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="销量必填" prop="sales">
          <el-input v-model="form.sales" placeholder="请输入销量必填" />
        </el-form-item>
        <el-form-item label="砍价开启时间" prop="startTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择砍价开启时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="砍价结束时间" prop="stopTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.stopTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择砍价结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTimeDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.startTimeDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTimeDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.endTimeDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="砍价产品名称" prop="storeName">
          <el-input v-model="form.storeName" placeholder="请输入砍价产品名称" />
        </el-form-item>
        <el-form-item label="砍价金额" prop="price">
          <el-input v-model="form.price" placeholder="请输入砍价金额" />
        </el-form-item>
        <el-form-item label="砍价商品最低价" prop="minPrice">
          <el-input v-model="form.minPrice" placeholder="请输入砍价商品最低价" />
        </el-form-item>
        <el-form-item label="每次购买的砍价产品数量" prop="num">
          <el-input v-model="form.num" placeholder="请输入每次购买的砍价产品数量" />
        </el-form-item>
        <el-form-item label="用户每次砍价的最大金额" prop="bargainMaxPrice">
          <el-input v-model="form.bargainMaxPrice" placeholder="请输入用户每次砍价的最大金额" />
        </el-form-item>
        <el-form-item label="用户每次砍价的最小金额" prop="bargainMinPrice">
          <el-input v-model="form.bargainMinPrice" placeholder="请输入用户每次砍价的最小金额" />
        </el-form-item>
        <el-form-item label="用户每次砍价的次数" prop="bargainNum">
          <el-input v-model="form.bargainNum" placeholder="请输入用户每次砍价的次数" />
        </el-form-item>
        <el-form-item label="砍价状态 ">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="返多少积分" prop="giveIntegral">
          <el-input v-model="form.giveIntegral" placeholder="请输入返多少积分" />
        </el-form-item>
        <el-form-item label="砍价活动简介" prop="info">
          <el-input v-model="form.info" placeholder="请输入砍价活动简介" />
        </el-form-item>
        <el-form-item label="成本价" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入成本价" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="是否推荐" prop="isHot">
          <el-radio-group v-model="form.isHot">
            <el-radio
              v-for="dict in isRecommendOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="砍价规则" prop="rule">
          <el-input v-model="form.rule" type="textarea" placeholder="请输入砍价规则" />
        </el-form-item>
        <el-form-item label="砍价产品浏览量" prop="look">
          <el-input v-model="form.look" placeholder="请输入砍价产品浏览量" />
        </el-form-item>
        <el-form-item label="砍价产品分享量" prop="share">
          <el-input v-model="form.share" placeholder="请输入砍价产品分享量" />
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
import { listBargain, getBargain, delBargain, addBargain, updateBargain, exportBargain } from "@/api/store/bargain";

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
      // 门店砍价活动表格数据
      bargainList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 砍价状态 字典
      statusOptions: [],
      // 是否推荐字典
      isRecommendOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        unitName: undefined,
        stock: undefined,
        sales: undefined,
        images: undefined,
        startTime: undefined,
        stopTime: undefined,
        status: undefined,
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
    this.getDicts("brgain_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("IsRecommend").then(response => {
      this.isRecommendOptions = response.data;
    });
  },
  methods: {
    /** 查询门店砍价活动列表 */
    getList() {
      this.loading = true;
      listBargain(this.queryParams).then(response => {
        this.bargainList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 砍价状态 字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    // 是否推荐字典翻译
    isRecommendFormat(row, column) {
      return this.selectDictLabel(this.isRecommendOptions, row.status);
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
        courseId: undefined,
        title: undefined,
        image: undefined,
        unitName: undefined,
        stock: undefined,
        sales: undefined,
        images: undefined,
        startTime: undefined,
        stopTime: undefined,
        startTimeDate: undefined,
        endTimeDate: undefined,
        storeName: undefined,
        price: undefined,
        minPrice: undefined,
        num: undefined,
        bargainMaxPrice: undefined,
        bargainMinPrice: undefined,
        bargainNum: undefined,
        status: "0",
        description: undefined,
        giveIntegral: undefined,
        info: undefined,
        cost: undefined,
        sort: undefined,
        isHot: undefined,
        delFlag: undefined,
        createTime: undefined,
        rule: undefined,
        look: undefined,
        share: undefined,
        createById: undefined,
        createBy: undefined,
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
      this.title = "添加门店砍价活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBargain(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门店砍价活动";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateBargain(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addBargain(this.form).then(response => {
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
      this.$confirm('是否确认删除门店砍价活动编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBargain(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有门店砍价活动数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBargain(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>