<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="编号" prop="num">
        <el-input
          v-model="queryParams.num"
          placeholder="请输入订单编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择支付状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="orderType">
        <el-select v-model="queryParams.orderType" placeholder="请选择类型" clearable size="small">
          <el-option value="1" label="门店订单">门店订单</el-option>
          <el-option value="2" label="家教订单">家教订单</el-option>
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
<!--        <el-button-->
<!--          type="primary"-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['order:order:add']"-->
<!--        >新增</el-button>-->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="num" :formatter="orderTypeFormat"/>
<!--      <el-table-column label="类型" align="center" prop="orderType" :formatter="orderTypeFormat">-->
<!--      </el-table-column>-->
      <el-table-column v-if="storeShow" label="门店" align="center" prop="storeName" />
      <el-table-column v-else label="老师" align="center" prop="teacherName" />
      <el-table-column label="课程" align="center" prop="courseName" />
      <el-table-column label="学生" align="center" prop="studentAssName" />
      <el-table-column label="年级" align="center" prop="gradeName" />
      <el-table-column label="入学时间" align="center" prop="enrolTime" width="180">
        <template slot-scope="scope" v-if="scope.row.enrolTime!=null">
          <span>{{ parseTime(scope.row.enrolTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="用户" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTimeBefore (scope.row.createTime) }}</span>
        </template>
      </el-table-column>
       <el-table-column  label="操作" width="200" align="center">
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
                v-hasPermi="['order:order:edit']"
                >修改</el-button>
                <br>
                <el-button
                  style="margin-top: 10px"
                  size="mini"
                  type="danger"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['order:order:remove']"
                >删除</el-button>
                <br  v-if="scope.row.status==1||scope.row.status==0">
                <el-button
                  v-if="scope.row.status==1||scope.row.status==0"
                  style="margin-top: 10px;background-color: rgb(63, 18, 241);border-color:rgb(63, 18, 241);"
                  size="mini"
                  type="success"
                  icon="el-icon-success"
                  @click="openPay(scope.row)"
                >付款</el-button>
                  <br v-if="scope.row.status==4">
                  <el-button
                    v-if="scope.row.status==4"
                    style="margin-top: 10px;background-color: rgba(249, 20, 179, 0.98);border-color:rgb(63, 18, 241);"
                    size="mini"
                    type="success"
                    icon="el-icon-notebook-2"
                    @click="openComment(scope.row)"
                  >评论</el-button>
                  <br  v-if="scope.row.status==4">
                  <el-button
                    v-if="scope.row.status==4"
                    style="margin-top: 10px;background-color: rgba(249, 20, 179, 0.98);border-color:rgb(63, 18, 241);"
                    size="mini"
                    type="success"
                    icon="el-icon-notebook-2"
                    @click="openRefund(scope.row)"
                  >退款申请</el-button>
                  <br  v-if="scope.row.status==6">
                  <el-button
                    v-if="scope.row.status==6"
                    style="margin-top: 10px;background-color: rgba(249, 20, 179, 0.98);border-color:rgb(63, 18, 241);"
                    size="mini"
                    type="success"
                    icon="el-icon-notebook-2"
                    @click="refund(scope.row)"
                  >退款</el-button>
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
    <!-- 退款申请页面-->
    <RebundModal ref="RebundModal" :rebund="rebund" :currentData="currentData" @closeRebundModal="closeRebundModal"></RebundModal>
    <!-- 评论页面-->
    <CommentModal ref="CommentModal" :comment="comment" :currentData="currentData" @closeCommentModal="closeCommentModal"></CommentModal>
    <!-- 详情页面-->
    <DetailModal ref="DetailModal" :orderDetail="orderDetail" :currentData="currentData" @closeDetail="closeDetail"></DetailModal>
    <!-- 支付页面-->
    <PayModal ref="PayModal" :pay="pay" :currentData="currentData" @closePayModal="closePayModal"></PayModal>
    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="学生" prop="studentId">
              <el-select v-model="form.studentId"  placeholder="请输入关联学生"  style="width: 100%;">
                <el-option
                  v-for="item in students"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="年级" prop="greadId">
              <el-select v-model="form.greadId"  placeholder="请输入关联年级"  style="width: 100%;">
                <el-option
                  v-for="item in sysGrades"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
          <el-row>
          <el-col :span="12">
            <el-form-item label="托管时长" prop="managTime">
              <el-select style="width: 100%" v-model="form.managTime" placeholder="请选择托管时长">
                <el-option
                  v-for="dict in manageTimeOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否含餐">
              <el-radio-group v-model="form.isMeal">
                <el-radio
                  v-for="dict in isMealOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="入学时间" prop="enrolTime">
              <el-date-picker clearable size="small" style="width: 100%"
                v-model="form.enrolTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择入学时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件类型">
              <el-select style="width: 100%" v-model="form.certificateType" placeholder="请选择证类型">
                <el-option
                  v-for="dict in certificateTypeOptions"
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
              <el-form-item label="学生姓名" prop="studentName">
                <el-input v-model="form.studentName" placeholder="请输入学生姓名" />
              </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="证件号">
              <el-input v-model="form.certificateNum" placeholder="请选择证件号" />
          </el-form-item>
          </el-col>
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
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder ,rebund} from "@/api/order/order";
import DetailModal from './modal/DetailModal'
import PayModal from './modal/PayModal'
import { formatTime } from '@/utils/index'
import CommentModal from './modal/CommentModal'
import RebundModal from './modal/RebundModal'
export default {
  components: {DetailModal,PayModal,CommentModal,RebundModal},
  data() {
    return {
      pay:false,
      orderDetail:false,
      comment:false,
      rebund:false,
      currentData:null,
      storeShow:true,
      // 年级列表
      sysGrades: [],
      // 学生列表
      students:[],
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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否含餐字典
      isMealOptions: [],
      //托管时长字典
      manageTimeOptions:[],
      // 证件类型字典
      certificateTypeOptions: [],
      // 支付状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        num: undefined,
        studentId: undefined,
        storeId: undefined,
        courseId: undefined,
        greadId: undefined,
        managTime: undefined,
        isMeal: undefined,
        enrolTime: undefined,
        certificateType: undefined,
        studengName: undefined,
        certificateNum: undefined,
        totalMoney: undefined,
        status: undefined,
        orderType:undefined,
        createBy: undefined,
        createTime: undefined,
        studentAssName: undefined,
        delFlag: undefined,
        createById: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        studentId: [
          { required: true, message: "学生不能为空", trigger: "blur" }
        ],
        greadId: [
          { required: true, message: "年级不能为空", trigger: "blur" }
        ],
        enrolTime: [
          { required: true, message: "入学时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("is_meal").then(response => {
      this.isMealOptions = response.data;
    });
    this.getDicts("certificate_type").then(response => {
      this.certificateTypeOptions = response.data;
    });
    this.getDicts("order_status").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("manage_time").then(response => {
      this.manageTimeOptions = response.data;
    });

  },
  methods: {
    orderTypeFormat(row, column) {
      this.storeShow=false;
      if (row.orderType === '1') {
        this.storeShow=true;
      } else  {
        this.storeShow=false;
      }
      return row.num;
    },
    //退款
    refund(row){
      const ids = row.id || this.ids;
      this.$confirm('是否确认退款订单编号为"' + row.num+ '"的订单?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        rebund(ids).then(response => {
          if (response.code === 200) {
            this.msgSuccess("退款成功");
            this.getList();
          } else {
            this.msgError(response.msg);
          }
        });
      }).then(() => {
        this.getList();
      }).catch(function() {});
    },
    //打开退款申请页面
    openRefund(row){
      this.currentData=row;
      this.rebund=true;
    },
    //关闭退款申请页面
    closeRebundModal(){
      this.rebund=false;
      this.getList();
    },
    //打开评价页面
    openComment(row){
      this.currentData=row;
      this.comment=true;
    },
    //关闭评价页面
    closeCommentModal(){
      this.comment=false;
    },
    //打开支付页面
    openPay(row){
      this.currentData=row;
      this.pay=true;
    },
    //关闭支付页面
    closePayModal(){
      this.pay=false;
      this.getList();
    },
      //打开订单详情页面
    openDatail(row){
      this.currentData=row;
      this.orderDetail=true;
    },
    //关闭订单详情页面
    closeDetail(){
      this.orderDetail=false;
    },
    parseTimeBefore(e){
      return formatTime((new Date(e)).getTime() / 1000);
    },
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否含餐字典翻译
    isMealFormat(row, column) {
      return this.selectDictLabel(this.isMealOptions, row.isMeal);
    },
    // 证件号字典翻译
    certificateNumFormat(row, column) {
      return this.selectDictLabel(this.certificateNumOptions, row.certificateNum);
    },
    // 支付状态字典翻译
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
        num: undefined,
        studentId: undefined,
        storeId: undefined,
        courseId: undefined,
        greadId: undefined,
        managTime: undefined,
        isMeal: undefined,
        enrolTime: undefined,
        certificateType: undefined,
        studentName: undefined,
        certificateNum: undefined,
        totalMoney: undefined,
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
      getOrder().then(response => {
        this.sysGrades = response.sysGrades;
        this.students = response.students;
        this.open = true;
        this.title = "添加订单";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getOrder(id).then(response => {
        this.form = response.data;
        this.sysGrades = response.sysGrades;
        this.students = response.students;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateOrder(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addOrder(this.form).then(response => {
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
      this.$confirm('是否确认删除订单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOrder(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
