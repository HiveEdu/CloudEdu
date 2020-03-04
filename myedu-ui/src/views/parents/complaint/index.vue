<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="投诉谁" prop="complaint">
        <el-input
          v-model="queryParams.complaint"
          placeholder="请输入投诉谁"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投诉理由" prop="reason">
        <el-input
          v-model="queryParams.reason"
          placeholder="请输入投诉理由"
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
          v-hasPermi="['parents:complaint:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['parents:complaint:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['parents:complaint:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['parents:complaint:export']"
        >导出</el-button>
      </el-col>
    </el-row>
     <!-- 详情页面-->
    <cDetailModal ref="cDetailModal" :copmDetail="copmDetail" :currentData="currentData" @closeDetail="closeDetail"></cDetailModal>
    <!--答复页面-->
    <replyModal ref="replyModal" :reply="reply" :currentData="currentData" @closeReply="closeReply"></replyModal>
    <el-table v-loading="loading" :data="complaintList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投诉谁" align="center" prop="complaint" />
      <el-table-column label="投诉理由" align="center" prop="reason" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="操作" align="center" width="200" >
        <template slot-scope="scope">
          <el-button  size="mini" type="primary"  @click="openDatail(scope.row)">详情</el-button>
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-button
                size="mini"
                type="success"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['parents:complaint:edit']"
                style="margin-top: 10px"
              >修改</el-button>
              <br>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['parents:complaint:remove']"
                style="margin-top: 10px"
              >删除</el-button>
              <br v-if="scope.row.status==0">
              <el-button
                v-if="scope.row.status==0"
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="openReply(scope.row)"
                style="margin-top: 10px"
              >答复</el-button>
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

    <!-- 添加或修改投诉对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="投诉谁" prop="complaint">
          <el-input v-model="form.complaint" placeholder="请输入投诉谁" />
        </el-form-item>
        <el-form-item label="投诉理由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入投诉理由" />
        </el-form-item>
        <el-form-item label="投诉证据" prop="evidentImg">
          <el-input v-model="form.evidentImg" placeholder="请输入投诉理由" />
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
import { listComplaint, getComplaint, delComplaint, addComplaint, updateComplaint, exportComplaint } from "@/api/parents/complaint";
import replyModal from './modal/replyModal'
import cDetailModal from './modal/cDetailModal'
export default {
  components: { replyModal,cDetailModal},
  data() {
    return {
       copmDetail:false,
       //回复页面默认不打开
      reply:false,
      //当前行记录为空
      currentData:null,
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
      // 投诉表格数据
      complaintList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
       // 1 正常 0 未开启 -1 已无效字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        status: undefined,
        complaint: undefined,
        reason: undefined,
        evidentImg: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        complaint: [
          { required: true, message: "投诉谁不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "投诉理由不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
      this.getList();
      this.getDicts("complaint_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
     //打开订单详情页面
    openDatail(row){
      this.currentData=row;
      this.copmDetail=true;
    },
    //关闭订单详情页面
    closeDetail(){
      this.copmDetail=false;
    },
     //打开答复页面
    openReply(row){
      this.currentData=row;
      this.reply=true;
    },
    //关闭答复页面
    closeReply(){
      this.reply=false;
      this.getList();
    },
     // 0 投诉中 1 已答复 
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
    },
    /** 查询投诉列表 */
    getList() {
      this.loading = true;
      listComplaint(this.queryParams).then(response => {
        this.complaintList = response.rows;
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
        complaint: undefined,
        reason: undefined,
        evidentImg: undefined,
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
      this.title = "添加投诉";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComplaint(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改投诉";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateComplaint(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addComplaint(this.form).then(response => {
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
      this.$confirm('是否确认删除投诉编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delComplaint(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有投诉数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportComplaint(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>