<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="活动标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
        <el-form-item label="活动状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择活动状态" clearable size="small">
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
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:activity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:activity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:activity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:activity:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动标题" align="center" prop="title" />
      <!-- <el-table-column label="活动链接" align="center" prop="links" /> -->
      <el-table-column label="活动链接"
        min-width="55">
        <template slot-scope="scope">
          <a :href="'http://'+scope.row.links"
            target="_blank"
            class="buttonText">{{scope.row.links}}</a>
        </template>
      </el-table-column>
      <el-table-column label="开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动状态" align="center" prop="status" :formatter="statusFormat" />
      <el-table-column label="发起人" align="center" prop="createBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:activity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:activity:remove']"
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

    <!-- 添加或修改活动管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="780px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
             <el-form-item label="活动标题" prop="title">
                 <el-input v-model="form.title" placeholder="请输入活动标题" />
             </el-form-item>
          </el-col>
          <el-col :span="12">
             <el-form-item label="活动链接" prop="links">
               <el-input v-model="form.links" placeholder="请输入活动链接" />
              </el-form-item>
          </el-col>
        </el-row>
       
        <!-- <el-form-item label="活动照片" prop="picture">
          <el-input v-model="form.picture" placeholder="请输入活动照片" />
        </el-form-item> -->
         <el-row>
          <el-col :span="12">
            <el-form-item label="开始时间" prop="startTime">
                <el-date-picker clearable size="small" style="width: 200px"
                    v-model="form.startTime"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="选择开始时间">
                </el-date-picker>
             </el-form-item>
          </el-col>
           <el-col :span="12">
              <el-form-item label="结束时间" prop="endTime">
                <el-date-picker clearable size="small" style="width: 200px"
                  v-model="form.endTime"
                  type="date"
                  value-format="yyyy-MM-dd"
                  placeholder="选择结束时间">
                </el-date-picker>
                </el-form-item>
           </el-col>
         </el-row>
        
        <el-row>
          <el-col :span="24">
             <el-form-item label="活动状态">
              <el-radio-group v-model="form.status">
                <el-radio
                  v-for="dict in statusOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
                </el-radio-group>
                 </el-form-item>
          </el-col>
        </el-row>
       <el-row>
         <el-col :span="24">
            <el-form-item label="活动内容" prop="content">
              <Editor v-model="form.content" />
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
import { listActivity, getActivity, delActivity, addActivity, updateActivity, exportActivity } from "@/api/system/activity";
import Editor from '@/components/Editor';
export default {
   components: {
    Editor
  },
  data() {
    return {
      path:"ws://127.0.0.1:8089/websocket/xupt",
      socket:"",
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
      // 活动管理表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 活动状态字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        status: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "活动标题不能为空", trigger: "blur" }
        ],
        links: [
          { required: true, message: "活动链接不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "活动内容不能为空", trigger: "blur" }
        ]
      }
    };
  },
   mounted () {
        // 初始化
        this.init();
    },
  created() {
    
    this.getList();
    this.getDicts("sys_notice_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    init: function () {
            if(typeof(WebSocket) === "undefined"){
                alert("您的浏览器不支持socket")
            }else{
                // 实例化socket
                this.socket = new WebSocket(this.path)
                // 监听socket连接
                this.socket.onopen = this.onopen
                // 监听socket错误信息
                this.socket.onerror = this.error
                // 监听socket消息
                this.socket.onmessage = this.getMessage
            }
        },
        onopen: function () {
            console.log("socket连接成功")
        },
        error: function () {
            console.log("连接错误")
        },
        getMessage: function (msg) {
            console.log(msg.data)
        },
        send: function () {
            this.socket.send(params)
        },
        close: function () {
            console.log("socket已经关闭")
        },
    destroyed () {
        // 销毁监听
        this.socket.onclose = this.close
    },
    /** 查询活动管理列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 活动状态字典翻译
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
        picture: undefined,
        links: undefined,
        startTime: undefined,
        endTime: undefined,
        status: "0",
        createBy: undefined,
        createById: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateByTime: undefined,
        delFlag: undefined,
        content: undefined
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
      this.title = "添加活动管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getActivity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动管理";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateActivity(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              } else {
                this.msgError(response.msg);
              }
            });
          } else {
            addActivity(this.form).then(response => {
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
      this.$confirm('是否确认删除活动管理编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delActivity(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有活动管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportActivity(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>