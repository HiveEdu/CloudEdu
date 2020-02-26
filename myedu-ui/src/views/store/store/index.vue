<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="名称" prop="name">
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
      <el-form-item label="负责人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建者用户"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
       <el-form-item label="状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择状态">
            <el-option
              v-for="dict in checkStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
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
      <el-table-column ref="table" prop="logo" label="logo" width="60">
        <template slot-scope="scope">
            <img  :src="imageView+'/'+scope.row.logo" :onerror="defaultImg" alt="点击打开" class="el-avatar" style="border-radius:10px">
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="负责人" align="center" prop="manager" />
      <el-table-column label="电话" align="center" prop="managerPhone" />
      <el-table-column label="省/市/区" align="center" prop="province" />
<!--      <el-table-column label="市" align="center" prop="city" />-->
<!--      <el-table-column label="区" align="center" prop="area" />-->
      <el-table-column label="门店详细地址" align="center" prop="address" />
      <el-table-column label="审核状态" align="center" prop="status" :formatter="checkFormat" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="操作" align="center" width="200" >
        <template slot-scope="scope">
          <el-button  size="mini" type="primary"  @click="openStoreDatail(scope.row)">详情</el-button>
          <el-dropdown size="mini" split-button type="primary" trigger="click">
            操作
            <el-dropdown-menu slot="dropdown">
              <el-button
                size="mini"
                type="success"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['course:course:edit']"
                style="margin-top: 10px"
              >修改</el-button>
              <br>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['course:course:remove']"
                style="margin-top: 10px"
              >删除</el-button>
              <br v-if="scope.row.status==0">
              <el-button
                v-if="scope.row.status==0"
                size="mini"
                type="primary"
                icon="el-icon-edit"
                @click="openReview(scope.row)"
                style="margin-top: 10px"
              >审核</el-button>
              <br  v-if="scope.row.status==3">
              <el-button
                v-if="scope.row.status==3"
                size="mini"
                type="warning"
                icon="el-icon-edit"
                @click="changeStatus(scope.row,4)"
                style="margin-top: 10px"
              >下线</el-button>
              <br v-if="scope.row.status==4 || scope.row.status==1">
              <el-button
                v-if="scope.row.status==4 || scope.row.status==1"
                size="mini"
                type="warning"
                icon="el-icon-edit"
                @click="changeStatus(scope.row,3)"
                style="margin-top: 10px"
              >上线</el-button>
              <br>

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
    <!--审核页面-->
    <reviewModal ref="reviewModal" :review="review" :currentData="currentData" @closeReview="closeReview"></reviewModal>
    <!--门店详情-->
    <storeDetailModal ref="storeDetailModal" :storeDetail="storeDetail" :currentData="currentData" @closeStoreDetail="closeStoreDetail"></storeDetailModal>
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
                <el-form-item label="类型">
                  <el-checkbox-group v-model="form.storeTypeIds">
                    <el-checkbox-button
                      v-for="item in storeTypes"
                      :label="item.id"
                      :key="item.id">{{item.name}}
                    </el-checkbox-button>
                  </el-checkbox-group>
                </el-form-item>
            </el-col>
          </el-row>

           <el-row>
            <el-col :span="8">
                <el-form-item label="标签">
                  <el-checkbox-group v-model="form.storeLabelIds">
                    <el-checkbox-button
                      v-for="item in storeLabels"
                      :label="item.id"
                      :key="item.id">{{item.name}}
                    </el-checkbox-button>
                  </el-checkbox-group>
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
<!--              <baidu-map class="map" :center="map.center" :zoom="map.zoom" @ready="handler">-->
<!--                &lt;!&ndash;缩放&ndash;&gt;-->
<!--                <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>-->
<!--                &lt;!&ndash;定位&ndash;&gt;-->
<!--                <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true" :autoLocation="true"></bm-geolocation>-->
<!--                &lt;!&ndash;点&ndash;&gt;-->
<!--                <bm-marker :position="map.center" :dragging="map.dragging" animation="BMAP_ANIMATION_DROP">-->
<!--                  &lt;!&ndash;提示信息&ndash;&gt;-->
<!--                  <bm-info-window :show="map.show">Hello~</bm-info-window>-->
<!--                </bm-marker>-->
<!--              </baidu-map>-->
          <el-row>
            <el-col :span="8">
              <el-form-item label="经度" prop="address">
                <el-input v-model="centerStr.lng" />
              </el-form-item>
            </el-col>
            <el-col :span="16">
              <el-form-item label="纬度" prop="address">
                <el-input v-model="centerStr.lat" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="地图">
                <baidu-map class="map" :center="centerStr" :zoom="zoom"
                           :scroll-wheel-zoom="true"
                           @ready="handler"
                           @click="mapclick">
                  <bm-local-search :keyword="keyword" :location="location" :auto-viewport="autoViewport":panel="panel" :select-first-result="selectFirstResult":pagecapacity="pageCapacity"></bm-local-search>
                  <bm-navigation anchor="BMAP_ANCHOR_TOP_LEFT"></bm-navigation>
                  <bm-geolocation
                    anchor="BMAP_ANCHOR_BOTTOM_RIGHT"
                    :showAddressBar="true"
                    :autoLocation="true"
                    :show="false"
                   ></bm-geolocation>
                </baidu-map>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="logo">
                <el-upload
                  class="avatar-uploader"
                  ref="upload"
                  :headers="headers"
                  :action="uploadImgUrl"
                  :data="{'type':'store'}"
                  :show-file-list="false"
                  :on-success="onSuccessLogo"
                  :on-remove="handleRemoveLogo"
                  :on-preview="handlePictureCardPreviewLogo"
                  :before-upload="beforeUploadLogo">
                  <img v-if="form.logo" :src="imageView+'/'+form.logo" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
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
          <el-form-item label="健康证" style="margin-top: 80px">
            <el-upload
              ref="upload"
              :action="uploadImgUrl"
              list-type="picture-card"
              content-type="false"
              :headers="headers"
              :file-list="healthsList"
              :show-file-list="true"
              :before-upload="beforeUploadh"
              :on-change="onChangeh"
              :on-success="onSuccessh"
              :on-preview="handlePictureCardPreviewh"
              :on-remove="handleRemoveh"
              accept='.jpg,.jpeg,.png,.gif'
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisibleh">
              <img width="100%" :src="dialogImageUrlh" alt="">
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
          <el-form-item label="视频上传" prop="video">
            <el-upload
              class="avatar-uploader el-upload--text"
              :action="uploadImgUrl"
              :headers="headers"
              :show-file-list="false"
              :on-success="handleVideoSuccess"
              :before-upload="beforeUploadVideo"
              :on-progress="uploadVideoProcess">
              <video v-if="form.video !='' && videoFlag == false" :src="imageView+'/'+form.video" class="avatar" controls="controls">您的浏览器不支持视频播放</video>
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
import { listStore, getStore, delStore, addStore, changeStatusOff,changeStatusOn,updateStore, exportStore } from "@/api/store/store";
import {addressOptions} from '@/api/addressOptions'
import reviewModal from './modal/reviewModal'
import storeDetailModal from './modal/storeDetailModal'
import { getToken } from '@/utils/auth'
// import BaiduMap from 'vue-baidu-map/components/map/Map.vue'
import BaiduMap from 'vue-baidu-map'
import Vue from 'vue'
Vue.use(BaiduMap, {
  ak: '7ddN7rl0MKnrRAhxmZzEHVPObhlDUcdb'
});
export default {
  components: { reviewModal,storeDetailModal },
  data() {
    return {
      defaultImg: 'this.src="' + require("@/assets/image/deaufalt.jpg") + '"',
      storeDetail:false,
       //审核页面默认不打开
      review:false,
      //当前行记录为空
      currentData:null,
      keyword: "", //百度地图搜索值
      location: "", //百度地图默认优先检索地区
      autoViewport:true,//百度地图检索结束后是否自动调整地图视野
      panel:false,//百度地图是否选展现检索结果面板
      selectFirstResult:true,//百度地图是否选择第一个检索结果
      pageCapacity:1,
      zoom:15,
      centerStr: {
        lng: "",
        lat: ""
      },
      // 审核状态字典
      checkStatusOptions: [],
      mapForAdd:false,
      //门店类型列表
      storeTypes:[],
      //标签类型列表
      storeLabels:[],
      //是否有视频
      videoFlag:false,
      //视频上传进度
      videoUploadPercent:0,
      //营业执照列表
      licenseList:[],
      licenseListNew:[],
      //健康证
      healthsList:[],
      healthsListNew:[],
      //照片墙列表
      photosList:[],
      photosListNew:[],
      active: 1,
      //省市区文件数据
      addressOptions: addressOptions,
      cities:[],
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
        status: undefined,
        createBy: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      dialogImageUrl: '',
      dialogVisible: false,
      dialogImageUrlh: '',
      dialogVisibleh: false,
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
      imageView: process.env.VUE_APP_BASE_API,
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      dialogImageUrl1: '',
      dialogVisible1: false,
    };
  },
  created() {
    this.getList();
    this.getDicts("yun_store_check_status").then(response => {
      this.checkStatusOptions = response.data;
    });
  },
  methods: {
    //打开门店详情页面
    openStoreDatail(row){
      this.currentData=row;
      this.storeDetail=true;
    },
    //关闭门店详情页面
    closeStoreDetail(){
      this.storeDetail=false;
    },
    //打开审核页面
    openReview(row){
      this.currentData=row;
      this.review=true;
    },
    //关闭审核页面
    closeReview(){
      this.review=false;
      this.getList();
    },
    /** 查询门店列表 */
    getList() {
      this.loading = true;
      listStore(this.queryParams).then(response => {
        this.storeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 审核状态字典翻译
    checkFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.status);
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
        healths: undefined,
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
        createById: undefined,
        storeTypeIds:[],
        storeLabelIds:[],
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
      this.licenseList=[];
      this.licenseListNew=[];
      this.photosList=[];
      this.photosListNew=[];
      this.photosList=[];
      this.photosListNew=[];
      this.active=1;
      getStore().then(response => {
        this.storeTypes = response.storeTypes;
        this.storeLabels = response.storeLabels;
        this.open = true;
        this.title = "添加门店";
      });
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
        if(this.form.photos!=null){
          this.photosList=JSON.parse(this.form.photos);
          this.photosListNew=JSON.parse(this.form.photos);
          for(let i=0;i<this.photosList.length;i++) {
            if(this.photosList[i].url!=null){
              this.photosList[i].url=this.imageView+"/"+this.photosList[i].url;
            }
          }
        }
        if(this.form.license!=null){
          this.licenseList=JSON.parse(this.form.license);
          this.licenseListNew=JSON.parse(this.form.license);
          for(let i=0;i<this.licenseList.length;i++) {
            if(this.licenseList[i].url!=null){
              this.licenseList[i].url=this.imageView+"/"+this.licenseList[i].url;
            }
          }
        }
        if(this.form.healths!=null){
          this.healthsList=JSON.parse(this.form.healths);
          this.healthsListNew=JSON.parse(this.form.healths);
          for(let i=0;i<this.healthsList.length;i++) {
            if(this.healthsList[i].url!=null){
              this.healthsList[i].url=this.imageView+"/"+this.healthsList[i].url;
            }
          }
        }
        this.storeTypes = response.storeTypes;
        this.storeLabels = response.storeLabels;
        this.form.storeTypeIds = response.storeTypeIds;
        this.form.storeLabelIds = response.storeLabelIds;
        this.centerStr.lng=this.form.mapX;
        this.centerStr.lat=this.form.mapY;
        this.open = true;
        this.title = "修改门店";
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
            this.form.healths=JSON.stringify(this.healthsListNew);
            this.form.mapX=this.centerStr.lng;
            this.form.mapY=this.centerStr.lat;
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
            this.form.healths=JSON.stringify(this.healthsListNew);
            this.form.mapX=this.centerStr.lng;
            this.form.mapY=this.centerStr.lat;
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
    /** 更改课程状态 */
    changeStatus(row,status) {
      const ids = row.id || this.ids;
      let courseStatus=null;
      if(status==3){
        courseStatus="上线"
      }else if(status==4){
        courseStatus="下线"
      }
      this.$confirm('是否确认'+courseStatus+'门店编号为"' + ids + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        if(status==3){
          return changeStatusOn(ids);
        }else if(status==4){
          return changeStatusOff(ids);
        }

      }).then(() => {
        this.getList();
        this.msgSuccess(courseStatus+"成功");
      }).catch(function() {});
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
    //logo上传开始
    beforeUploadLogo(file){
    },
    onSuccessLogo(res,file, fileList){
      if(res.code=="200"){
        this.form.logo=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemoveLogo(file, fileList) {
      console.log(file, fileList);
      this.form.logo=null;
    },
    handlePictureCardPreviewLogo(file) {
      // this.dialogImageUrl = file.url;
      // this.dialogVisible = true;
    },
    //图片上传结束
    //营业执照上传开始
    beforeUpload(file){
    },
    onChange(file, fileList){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.licenseList=fileList
        this.licenseListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.fileName})
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
    //健康证上传开始
    beforeUploadh(file){
    },
    onChangeh(file, fileList){
    },
    onSuccessh(res,file, fileList){
      if(res.code=="200"){
        this.healthsList=fileList
        this.healthsListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.fileName})
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemoveh(file, fileList) {
      console.log(file, fileList);
      this.healthsList=fileList;
      for(let i=0;i<this.healthsListNew.length;i++) {
        if (this.healthsListNew[i].uid === file.uid) {
          this.healthsListNew.splice(i);
        }
      }
    },
    handlePictureCardPreviewh(file) {
      this.dialogImageUrlh = file.url;
      this.dialogVisibleh = true;
    },
    //健康证上传结束
    //照片墙上传开始
    beforeUpload1(file){
    },
    onChange1(file, fileList){
    },
    onSuccess1(res,file, fileList){
      if(res.code=="200"){
        this.photosList=fileList
        this.photosListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.fileName})
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
        this.form.video = res.fileName;
      }else{
        this.$message.error('视频上传失败，请重新上传！');
      }
    },
    //上传视频结束
    handler({BMap, map}) {
      var geolocation = new BMap.Geolocation();
      let _this = this;
      geolocation.getCurrentPosition(function (r) {
        _this.centerStr.lat=r.latitude;
        _this.centerStr.lng=r.longitude;
        // _this.cities=[];
        // _this.cities.push(rs.address.province);
        // _this.cities.push(rs.address.city);
        // _this.cities.push(rs.address.district);
      })
    },
    mapclick(e){
      const { lng, lat } = e.target.getCenter();
      this.centerStr.lng = lng;
      this.centerStr.lat = lat;
      this.zoom = e.target.getZoom();
      let geocoder= new BMap.Geocoder();  //创建地址解析器的实例
      geocoder.getLocation(e.point,rs=>{
        //地址描述(string)=
        // console.log(rs.address);    //这里打印可以看到里面的详细地址信息，可以根据需求选择想要的
        // console.log(rs.addressComponents);//结构化的地址描述(object)
        // console.log(rs.addressComponents.province); //省
        // console.log(rs.addressComponents.city); //城市
        // console.log(rs.addressComponents.district); //区县
        // console.log(rs.addressComponents.street); //街道
        // console.log(rs.addressComponents.streetNumber); //门牌号
        // console.log(rs.surroundingPois); //附近的POI点(array)
        // console.log(rs.business); //商圈字段，代表此点所属的商圈(string)
        this.form.address=rs.addressComponents.street+rs.addressComponents.streetNumber;
        this.cities=[];
        this.cities.push(rs.addressComponents.province);
        this.cities.push(rs.addressComponents.city);
        this.cities.push(rs.addressComponents.district);
      });
    },
  }
};
</script>
<style scoped>
  .map {
    width: 100%;
    height: 400px;
  }
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
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }
  .el-icon-arrow-down {
    font-size: 12px;
  }
</style>
