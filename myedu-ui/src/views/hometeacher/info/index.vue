<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px">
      <el-form-item label="授课科目" prop="course">
        <el-input
          v-model="queryParams.course"
          placeholder="请输入授课科目"
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
          v-hasPermi="['hometeacher:info:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hometeacher:info:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hometeacher:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hometeacher:info:export']"
        >导出</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="头像" align="center" prop="avatar" /> -->
       <el-table-column label="头像" width="55" >
        　　<template slot-scope="scope">
        　　　　<img :src="viewImage+'/'+scope.row.avatar" width="50" height="50" class="head_pic"/>
        　　</template>
      </el-table-column>
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="性别" align="center" prop="sex" :formatter="gendelFormat"/>
      <el-table-column label="省/市/区" align="center" prop="province" />
      <el-table-column label="学校" align="center" prop="school" />
      <el-table-column label="学历" align="center" prop="education" :formatter="EducationFormat"/>
      <el-table-column label="是否毕业" align="center" prop="isGraduate" :formatter="isOneToOneFormat"/>
       <el-table-column label="审核状态" align="center" prop="status" :formatter="checkFormat" />
      <el-table-column label="操作" align="center" width="200">
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
                v-hasPermi="['chometeacher:info:edit']"
                style="margin-top: 10px"
              >修改</el-button>
              <br>
              <el-button
                size="mini"
                type="danger"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['hometeacher:info:remove']"
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
     <!--课程详情-->
    <DetailModal ref="DetailModal" :infoDetail="infoDetail" :currentData="currentData" @closeDetail="closeDetail"></DetailModal>
    <!-- 添加或修改家教老师表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-steps :active="active">
          <el-step title="基本信息" icon="el-icon-edit" @click.native="stepClick(1)"></el-step>
          <el-step title="证件信息" icon="el-icon-upload" @click.native="stepClick(2)"></el-step>
          <el-step title="毕业信息" icon="el-icon-picture" @click.native="stepClick(3)"></el-step>
        </el-steps>
        <div v-if="active==1">
        <el-form-item label="授课科目" prop="courseId">
           <el-select v-model="form.courseId" multiple placeholder="请选择授课科目"  style="width: 100%;">
                  <el-option
                    v-for="item in sysCourses"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id"
                  ></el-option>
                </el-select>
        </el-form-item>
         <el-row>
           <el-col :span="16">
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
                <el-input v-model="form.address" placeholder="请输入您的详细地址" />
              </el-form-item>
           </el-col>
          </el-row>
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
          </el-row>
        <el-form-item label="成功案例" prop="experience">
          <el-input v-model="form.experience" type="textarea" placeholder="请输入教学经历" />
        </el-form-item>
        <el-form-item label="教学特点" prop="trait">
          <el-input v-model="form.trait" type="textarea" placeholder="请输入教学特点" />
        </el-form-item>
        <el-form-item label="奖励荣誉" prop="awards">
        <el-input v-model="form.awards" placeholder="请输入奖励荣誉" />
        </el-form-item>
         </div>
        <div v-if="active==2" style="margin-top: 30px">
        <el-form-item label="照片展示" prop="photos" style="margin-top: 80px">
          <el-upload
              ref="upload"
              :action="uploadImgUrl"
              list-type="picture-card"
              content-type="false"
              :headers="headers"
              :file-list="photosList"
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
        <el-form-item label="视频风采" prop="video">
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
        <div v-if="active==3" style="margin-top: 30px">
          <el-form-item label="是否毕业" prop="isGraduate">
            <el-radio-group v-model="form.isGraduate">
              <el-radio
                v-for="dict in isOneToOneOptions"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="学校" prop="school">
            <el-input v-model="form.school" placeholder="请输入学校" />
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-radio-group v-model="form.education">
              <el-radio
                v-for="dict in Educations"
                :key="dict.dictValue"
                :label="dict.dictValue"
              >{{dict.dictLabel}}</el-radio>
            </el-radio-group>
          </el-form-item>
           <el-form-item    label="学生证书" prop="credentials" v-if="form.isGraduate==1">
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
                  <img v-if="form.credentials" :src="imageView+'/'+form.credentials" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
           <el-form-item  label="毕业证书" prop="credentials" v-if="form.isGraduate==0">
            <el-upload
                  class="avatar-uploader"
                  ref="upload"
                  :headers="headers"
                  :action="uploadImgUrl"
                  :data="{'type':'info'}"
                  :show-file-list="false"
                  :on-success="onSuccessLogo"
                  :on-remove="handleRemoveLogo"
                  :on-preview="handlePictureCardPreviewLogo"
                  :before-upload="beforeUploadLogo">
                  <img v-if="form.credentials" :src="imageView+'/'+form.credentials" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>
           <el-form-item label="身份证" prop="identityCard">
          <el-upload
                  class="avatar-uploader"
                  ref="upload"
                  :headers="headers"
                  :action="uploadImgUrl"
                  :data="{'type':'info'}"
                  :show-file-list="false"
                  :on-success="onSuccessIDCard"
                  :on-remove="handleRemoveIDCard"
                  :on-preview="handlePictureCardPreviewIDCard"
                  :before-upload="beforeUploadIDCard">
                  <img v-if="form.identityCard" :src="imageView+'/'+form.identityCard" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
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
import { listInfo, getInfo, delInfo, addInfo, changeStatusOff,changeStatusOn,updateInfo, exportInfo } from "@/api/hometeacher/info";
import { getToken } from '@/utils/auth'
import {addressOptions} from '@/api/addressOptions'
import DetailModal from '../modal/DetailModal'
import reviewModal from '../modal/reviewModal'
import BaiduMap from 'vue-baidu-map'
import Vue from 'vue'
Vue.use(BaiduMap, {
  ak: '7ddN7rl0MKnrRAhxmZzEHVPObhlDUcdb'
});
export default {
  components: {reviewModal,DetailModal},
  data() {
    return {
      mapForAdd:false,
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
       //省市区文件数据
      addressOptions: addressOptions,
      cities:[],
      //审核页面默认不打开
      review:false,
       // 审核状态字典
      checkStatusOptions: [],
      infoDetail:false,
      dialogImageUrl: '',
      dialogVisible: false,
      //是否有视频
      videoFlag:false,
      //视频上传进度
      videoUploadPercent:0,
      //照片列表
      photosList:[],
      photosListNew:[],
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      imageView: process.env.VUE_APP_BASE_API,
      uploadImgUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传的图片服务器地址
       // 是否毕业字典
      isOneToOneOptions: [],
      // 老师性别字典
      gendelOptions: [],
      Educations: [],
       // 课程选择
      sysCourses: [],
      active: 1,
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
      // 家教老师表表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        courseId: undefined,
        school: undefined,
      },
       viewImage: process.env.VUE_APP_BASE_API,
      // 表单参数
      form: {},
      // 表单校验
      rules: {
         courseId:[
          { required: true, message: "所授课程不能为空", trigger: "blur" }
        ],
        school: [
          { required: true, message: "学校不能为空", trigger: "blur" }
        ],
        isGraduate: [
          { required: true, message: "是否毕业不能为空", trigger: "blur" }
        ],
        education: [
          { required: true, message: "学历不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
     this.getDicts("yun_store_check_status").then(response => {
      this.checkStatusOptions = response.data;
    });
    this.getDicts("sys_user_sex").then(response => {
      this.gendelOptions = response.data;
    });
     this.getDicts("is-one-to-one").then(response => {
      this.isOneToOneOptions = response.data;
     });
     this.getDicts("Education").then(response => {
      this.Educations = response.data;
     });
  },
  methods: {
    onChangeCitys(value){
      this.cities = value;
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
     // 老师性别字典翻译
    gendelFormat(row, column) {
      return this.selectDictLabel(this.gendelOptions, row.sex);
    },
     //打开老师详情页面
    openDatail(row){
      this.currentData=row;
      this.infoDetail=true;
    },
    //关闭教师详情页面
    closeDetail(){
      this.infoDetail=false;
    },
     // 审核状态字典翻译
    checkFormat(row, column) {
      return this.selectDictLabel(this.checkStatusOptions, row.status);
    },
    // 是否一对一字典翻译
    isOneToOneFormat(row, column) {
      return this.selectDictLabel(this.isOneToOneOptions, row.isGraduate);
    },
    // 学历字典翻译
    EducationFormat(row, column) {
      return this.selectDictLabel(this.Educations, row.education);
    },
    /** 查询家教老师表列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        userId: undefined,
        courseId: undefined,
        awards: undefined,
        experience: undefined,
        trait: undefined,
        photos: undefined,
        video: undefined,
        school: undefined,
        isGraduate: undefined,
        credentials: undefined,
        identityCard: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined,
        province: undefined,
        city: undefined,
        area: undefined,
        address: undefined,
        mapX: undefined,
        mapY: undefined,
        delFlag: undefined
      };
      this.resetForm("form");
    },
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
     /** 更改课程状态 */
    changeStatus(row,status) {
      const ids = row.id || this.ids;
      let courseStatus=null;
      if(status==3){
        courseStatus="上线"
      }else if(status==4){
        courseStatus="下线"
      }
      this.$confirm('是否确认'+courseStatus+'教师编号为"' + ids + '"的数据项?', "警告", {
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
    //证书上传开始
    beforeUploadLogo(file){
    },
    onSuccessLogo(res,file, fileList){
      if(res.code=="200"){
        this.form.credentials=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemoveLogo(file, fileList) {
      console.log(file, fileList);
      this.form.credentials=null;
    },
    handlePictureCardPreviewLogo(file) {
      // this.dialogImageUrl = file.url;
      // this.dialogVisible = true;
    },
    //身份证书上传开始
    beforeUploadIDCard(file){
    },
    onSuccessIDCard(res,file, fileList){
      if(res.code=="200"){
        this.form.identityCard=res.fileName
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemoveIDCard(file, fileList) {
      console.log(file, fileList);
      this.form.identityCard=null;
    },
    handlePictureCardPreviewIDCard(file) {
      // this.dialogImageUrl = file.url;
      // this.dialogVisible = true;
    },

    //照片墙上传开始
    beforeUpload(file){
    },
    onChange(file, fileList){
    },
    onSuccess(res,file, fileList){
      if(res.code=="200"){
        this.photosList=fileList
        this.photosListNew.push({uid:file.uid,name:file.name,status:file.status,url:res.fileName})
        this.msgSuccess("上传成功");
      }else{
        this.msgError("上传失败");
      }
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      this.photosList=fileList;
      for(let i=0;i<this.photosListNew.length;i++) {
        if (this.photosListNew[i].uid === file.uid) {
          this.photosListNew.splice(i);
        }
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //营业执照上传结束
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
      this.photosList=[];
      this.photosListNew=[];
      this.reset();
      getInfo().then(response => {
        this.sysCourses = response.sysCourses;
        this.open = true;
        this.title = "添加家教老师表";
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInfo(id).then(response => {
        this.form = response.data;
        this.cities=JSON.parse(this.form.province);
        this.form.courseId=JSON.parse(this.form.courseId);
        this.sysCourses = response.sysCourses;
        this.photosList=JSON.parse(this.form.photos);
        this.photosListNew=JSON.parse(this.form.photos);
        if(this.photosList!=null){
         for(let i=0;i<this.photosList.length;i++) {
          if(this.photosList[i].url!=null){
            this.photosList[i].url=this.imageView+"/"+this.photosList[i].url;
          }
         }
        }
        this.centerStr.lng=this.form.mapX;
        this.centerStr.lat=this.form.mapY;
        this.open = true;
        this.title = "修改家教老师表";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.courseId=JSON.stringify(this.form.courseId);
          if (this.form.id != undefined) {
            this.form.province=JSON.stringify(this.cities);
            this.form.photos=JSON.stringify(this.photosListNew);
            this.form.mapX=this.centerStr.lng;
            this.form.mapY=this.centerStr.lat;
            updateInfo(this.form).then(response => {
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
            this.form.photos=JSON.stringify(this.photosListNew);
            this.form.mapX=this.centerStr.lng;
            this.form.mapY=this.centerStr.lat;
            this.form.photos=JSON.stringify(this.photosListNew);
            addInfo(this.form).then(response => {
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
      this.$confirm('是否确认删除家教老师表编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
     next() {
      if (this.active++ > 2) this.active = 0;
    },
    stepClick(active){
      this.active=active;
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有家教老师表数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
<style scoped>
 .map {
    width: 200%;
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
