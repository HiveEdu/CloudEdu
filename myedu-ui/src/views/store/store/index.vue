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
      <el-form-item label="负责人" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入门店负责人"
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
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="负责人" align="center" prop="manager" />
      <el-table-column label="电话" align="center" prop="managerPhone" />
      <el-table-column label="省/市/区" align="center" prop="province" />
<!--      <el-table-column label="市" align="center" prop="city" />-->
<!--      <el-table-column label="区" align="center" prop="area" />-->
      <el-table-column label="门店详细地址" align="center" prop="address" />
      <el-table-column label="创建者" align="center" prop="createBy" />
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
    <el-dialog :title="title" :visible.sync="open" width="70%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-steps :active="active">
          <el-step title="基本信息" icon="el-icon-edit" @click.native="stepClick(1)"></el-step>
          <el-step title="证件信息" icon="el-icon-upload" @click.native="stepClick(2)"></el-step>
          <el-step title="宣传信息" icon="el-icon-picture" @click.native="stepClick(3)"></el-step>
        </el-steps>
        <div v-if="active==1">
          <el-row style="margin-top: 30px">
            <el-col :span="8">
              <el-form-item label="门店名称" prop="name">
                <el-input v-model="form.name" placeholder="请输入门店名称" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="负责人" prop="manager">
                <el-input v-model="form.manager" placeholder="请输入门店负责人" />
              </el-form-item>
            </el-col>
            <el-col :span="8">
              <el-form-item label="电话" prop="managerPhone">
                <el-input v-model="form.managerPhone" placeholder="请输入负责人电话" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
           <el-col :span="8">
              <el-form-item label="地址" prop="province">
                <el-cascader
                  :options="addressOptions"
                  v-model="cities"
                  @change="onChangeCitys"
                ></el-cascader>
              </el-form-item>
           </el-col>
           <el-col :span="16">
              <el-form-item label="街道" prop="address">
                <el-input v-model="form.address" placeholder="请输入门店详细地址" />
              </el-form-item>
           </el-col>
          </el-row>
        </div>
        <div v-if="active==2" style="margin-top: 30px">
          <el-form-item label="营业执照" style="margin-top: 80px">
            <el-upload
              ref="upload"
              :action="uploadImgUrl"
              list-type="picture-card"
              content-type="false"
              :headers="headers"
              :file-list="licenseList"
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
        </div>
        <div v-if="active==3" style="margin-top: 30px">
          <el-form-item label="照片墙" style="margin-top: 80px">
            <el-upload
              ref="upload"
              :action="uploadImgUrl"
              list-type="picture-card"
              content-type="false"
              :headers="headers"
              :file-list="photosList"
              :show-file-list="true"
              :before-upload="beforeUpload1"
              :on-change="onChange1"
              :on-success="onSuccess1"
              :on-preview="handlePictureCardPreview1"
              :on-remove="handleRemove1"
              accept='.jpg,.jpeg,.png,.gif'
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible1">
              <img width="100%" :src="dialogImageUrl1" alt="">
            </el-dialog>
          </el-form-item>
<!--          <el-form-item label="视频" prop="video">-->
<!--            <el-input v-model="form.video" placeholder="请输入门店宣传视频地址" />-->
<!--          </el-form-item>-->
          <el-form-item label="视频上传" prop="video">
            <!-- action必选参数, 上传的地址 -->
            <el-upload
              class="avatar-uploader el-upload--text"
              :action="uploadImgUrl"
              :headers="headers"
              :show-file-list="false"
              :on-success="handleVideoSuccess"
              :before-upload="beforeUploadVideo"
              :on-progress="uploadVideoProcess">
              <video v-if="form.video !='' && videoFlag == false" :src="form.video" class="avatar" controls="controls">您的浏览器不支持视频播放</video>
              <i v-else-if="form.video =='' && videoFlag == false" class="el-icon-plus avatar-uploader-icon"></i>
              <el-progress v-if="videoFlag == true" type="circle" :percentage="videoUploadPercent" style="margin-top:30px;"></el-progress>
            </el-upload>
            <P class="text">请保证视频格式正确</P>
          </el-form-item>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStore, getStore, delStore, addStore, updateStore, exportStore } from "@/api/store/store";
import {addressOptions} from '@/api/addressOptions'
import { getToken } from '@/utils/auth'
export default {
  data() {
    return {
      //是否有视频
      videoFlag:false,
      //视频上传进度
      videoUploadPercent:0,
      //营业执照列表
      licenseList:[],
      licenseListNew:[],
      //照片墙列表
      photosList:[],
      photosListNew:[],
      active: 1,
      //省市区文件数据
      addressOptions: addressOptions,
      cities:['陕西省','西安市','未央区'],
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
        manager: undefined,
        createBy: undefined,
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
      dialogImageUrl1: '',
      dialogVisible1: false,
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
        province: undefined,
        city: undefined,
        area: undefined,
        address: undefined,
        mapX: undefined,
        mapY: undefined,
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
      this.licenseList=[];
      this.licenseListNew=[];
      this.photosList=[];
      this.photosListNew=[];
      this.active=1;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStore(id).then(response => {
        this.form = response.data;
        if(this.form.province!=null){
          this.cities=JSON.parse(this.form.province);
        }
        this.open = true;
        this.title = "修改门店";
        if(this.form.license!=null){
          this.licenseList=JSON.parse(this.form.license);
          this.licenseListNew=JSON.parse(this.form.license);
        }
        if(this.form.photos!=null){
          this.photosList=JSON.parse(this.form.photos);
          this.photosListNew=JSON.parse(this.form.photos);
        }
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            this.form.province=JSON.stringify(this.cities);
            this.form.license=JSON.stringify(this.licenseListNew);
            this.form.photos=JSON.stringify(this.photosListNew);
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
            this.form.province=JSON.stringify(this.cities);
            this.form.license=JSON.stringify(this.licenseListNew);
            this.form.photos=JSON.stringify(this.photosListNew);
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
      this.$refs.upload.clearFiles();
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
    },
    next() {
      if (this.active++ > 2) this.active = 0;
    },
    stepClick(active){
      this.active=active;
    },
    onChangeCitys(value){
      this.cities = value;
    },
    //营业执照上传开始
    beforeUpload(file){
    },
    onChange(file, fileList){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.licenseList=fileList
        this.licenseListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.url})
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.licenseList=fileList;
      for(let i=0;i<this.licenseListNew.length;i++) {
        if (this.licenseListNew[i].uid === file.uid) {
          this.licenseListNew.splice(i);
        }
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //营业执照上传结束
    //照片墙上传开始
    beforeUpload1(file){
    },
    onChange1(file, fileList){
    },
    onSuccess1(res,file, fileList){
      if(res.code=="200"){
        this.photosList=fileList
        this.photosListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.url})
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove1(file, fileList) {
      console.log(file, fileList);
      this.photosList=fileList;
      for(let i=0;i<this.photosListNew.length;i++) {
        if (this.photosListNew[i].uid === file.uid) {
          this.photosListNew.splice(i);
        }
      }
    },
    handlePictureCardPreview1(file) {
      this.dialogImageUrl1 = file.url;
      this.dialogVisible1 = true;
    },
    //照片墙上传结束
    //上传视频开始
    beforeUploadVideo(file) {
      const isLt10M = file.size / 1024 / 1024  < 10;
      if (['video/mp4', 'video/ogg', 'video/flv','video/avi','video/wmv','video/rmvb'].indexOf(file.type) == -1) {
        this.$message.error('请上传正确的视频格式');
        return false;
      }
      // if (!isLt10M) {
      //   this.$message.error('上传视频大小不能超过10MB哦!');
      //   return false;
      // }
    },
    uploadVideoProcess(event, file, fileList){
      this.videoFlag = true;
      this.videoUploadPercent = file.percentage.toFixed(0);
    },
    handleVideoSuccess(res, file) {                               //获取上传图片地址
      this.videoFlag = false;
      this.videoUploadPercent = 0;
      if(res.code == 200){
        this.form.video = res.url;
      }else{
        this.$message.error('视频上传失败，请重新上传！');
      }
    },
    //上传视频结束
  }
};
</script>
