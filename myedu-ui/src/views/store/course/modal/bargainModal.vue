<template>
 <el-dialog :title="title"   :visible.sync="dialogVisible" @close="handleClose" width="800px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="砍价标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入砍价标题" style="width: 500px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="库存" prop="stock">
              <el-input-number v-model="form.stock" placeholder="请输入库存" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单位名称" prop="unitName">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" style="width: 200px"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="销量必填" prop="sales">
              <el-input-number v-model="form.sales" placeholder="请输入销量必填" />
            </el-form-item>
          </el-col>
           <el-col :span="12"> 
          <el-form-item label="是否推荐" prop="isHot">
            <el-radio-group v-model="form.isHot">
            <el-radio
              v-for="dict in isRecommendOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
            <el-form-item label="开启时间" prop="startTime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.startTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择砍价开启时间">
              </el-date-picker>
            </el-form-item>
         </el-col>
         <el-col :span="12">
            <el-form-item label="结束时间" prop="stopTime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.stopTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择砍价结束时间">
              </el-date-picker>
            </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
            <el-form-item label="砍价名称" prop="storeName">
              <el-input v-model="form.storeName" placeholder="请输入砍价产品名称" style="width: 300px;"/>
            </el-form-item>
         </el-col>
         <el-col :span="12">
          <el-form-item label="砍价金额" prop="price">
            <el-input-number v-model="form.price" placeholder="请输入砍价金额" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
          <el-form-item label="砍价商品最低价" prop="minPrice">
            <el-input-number v-model="form.minPrice" placeholder="请输入砍价商品最低价" />
          </el-form-item>
         </el-col>
         <el-col :span="12">
          <el-form-item label="每次购买的砍价产品数量" prop="num">
            <el-input-number v-model="form.num" placeholder="请输入每次购买的砍价产品数量" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
          <el-form-item label="用户每次砍价的最大金额" prop="bargainMaxPrice">
            <el-input-number  v-model="form.bargainMaxPrice" placeholder="请输入用户每次砍价的最大金额" />
          </el-form-item>
         </el-col>
         <el-col :span="12">
          <el-form-item label="用户每次砍价的最小金额" prop="bargainMinPrice">
            <el-input-number  v-model="form.bargainMinPrice" placeholder="请输入用户每次砍价的最小金额" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
          <el-form-item label="用户每次砍价的次数" prop="bargainNum">
            <el-input-number v-model="form.bargainNum" placeholder="请输入用户每次砍价的次数" />
          </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item label="返多少积分" prop="giveIntegral">
            <el-input-number  v-model="form.giveIntegral" placeholder="请输入返多少积分" />
           </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="24">
            <el-form-item label="砍价状态 ">
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
            <el-form-item label="砍价活动简介" prop="info">
              <el-input v-model="form.info" type="textarea" placeholder="请输入砍价活动简介" style="width: 500px;"/>
            </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12"> 
          <el-form-item label="成本价" prop="cost">
            <el-input-number   v-model="form.cost" placeholder="请输入成本价" />
          </el-form-item>
         </el-col>
         <el-col :span="12"> 
          <el-form-item label="排序" prop="sort">
            <el-input-number  v-model="form.sort" placeholder="请输入排序" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
            <el-col :span="12"> 
                <el-form-item label="浏览量" prop="look">
                  <el-input-number  v-model="form.look" placeholder="请输入砍价产品浏览量" />
                </el-form-item>
            </el-col>
            <el-col :span="12"> 
                <el-form-item label="分享量" prop="share">
                  <el-input-number  v-model="form.share" placeholder="请输入砍价产品分享量" />
                </el-form-item>
            </el-col>
            </el-row>
        <el-row>
          <el-form-item label="砍价规则" prop="rule">
              <!-- 图片上传组件辅助 -->
              <el-upload
                class="avatar-uploader quill-img"
                :action="uploadImgUrl"
                name="file"
                :headers="headers"
                :show-file-list="false"
                :on-success="quillImgSuccess"
                :on-error="uploadError"
                :before-upload="quillImgBefore"
                accept='.jpg,.jpeg,.png,.gif'
              ></el-upload>

              <!-- 富文本组件 -->
              <quill-editor
                class="editor"
                v-model="form.rule"
                ref="quillEditor"
                :options="editorOption"
                @blur="onEditorBlur($event)"
                @focus="onEditorFocus($event)"
                @change="onEditorChange($event)"
              ></quill-editor>
          </el-form-item>
        </el-row>
             <el-form-item label="砍价详情" prop="description">
            <!-- 图片上传组件辅助 -->
            <el-upload
              class="avatar-uploader quill-img"
              :action="uploadImgUrl"
              name="file"
              :headers="headers"
              :show-file-list="false"
              :on-success="quillImgSuccess"
              :on-error="uploadError"
              :before-upload="quillImgBefore"
              accept='.jpg,.jpeg,.png,.gif'
            ></el-upload>

            <!-- 富文本组件 -->
            <quill-editor
              class="editor"
              v-model="form.description"
              ref="quillEditor"
              :options="editorOption"
              @blur="onEditorBlur($event)"
              @focus="onEditorFocus($event)"
              @change="onEditorChange($event)"
            ></quill-editor>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="handleClose">取 消</el-button>
      </div>
    </el-dialog>
</template>

<script>
  import { getCourse } from "@/api/store/course";
  import { listBargain, getBargain, delBargain, addBargain, updateBargain, exportBargain } from "@/api/store/bargain";
  import { quillEditor } from "vue-quill-editor";
  import imageResize  from 'quill-image-resize-module'
  // 工具栏配置
  const toolbarOptions = [
    ["bold", "italic", "underline", "strike"],       // 加粗 斜体 下划线 删除线
    ["blockquote", "code-block"],                    // 引用  代码块
    [{ list: "ordered" }, { list: "bullet" }],       // 有序、无序列表
    [{ indent: "-1" }, { indent: "+1" }],            // 缩进
    [{ size: ["small", false, "large", "huge"] }],   // 字体大小
    [{ header: [1, 2, 3, 4, 5, 6, false] }],         // 标题
    [{ color: [] }, { background: [] }],             // 字体颜色、字体背景颜色
    [{ align: [] }],                                 // 对齐方式
    ["clean"],                                       // 清除文本格式
    ["link", "image", "video"]                       // 链接、图片、视频
  ];

  // import { ImageDrop } from 'quill-image-drop-module'; // 拖动加载图片组件。
  import Quill from 'quill'
  import "quill/dist/quill.core.css";
  import "quill/dist/quill.snow.css";
  import "quill/dist/quill.bubble.css"; 
  Quill.register('modules/imageResize', imageResize)
// Quill.register('modules/imageDrop', ImageDrop);
   export default {
        name: "bargainModal",
        props: {
          courseBargain: null,
          currentData:null,
                  /* 编辑器的内容 */
          value: {
            type: String
          },
          /* 图片大小 */
          maxSize: {
            type: Number,
            default: 4000 //kb
          }
        },
        components: {quillEditor},
        watch:{
          courseBargain:function (e) {
            this.dialogVisible=e;
            this.getDicts("course_type").then(response => {
            this.classifyOptions = response.data;
          });
          this.getDicts("managed_type").then(response => {
            this.reclassifyCollOptions = response.data;
          });
          this.getDicts("is-one-to-one").then(response => {
            this.isOneToOneOptions = response.data;
          });
           this.getDicts("brgain_status").then(response => {
           this.statusOptions = response.data;
          });
           this.getDicts("IsRecommend").then(response => {
           this.isRecommendOptions = response.data;
          });
          },
          currentData:function (e) {
            getCourse(e.id).then(response => {
              this.form.courseId = response.data.id;
              this.form.storeName=response.data.name;
              this.form.info = response.data.introduce;
              this.form.stock = response.data.classAll;
              this.form.price=response.data.courseCost;
            });
          },
        },
        data() {
          return {
            title:"开始砍价",
            dialogVisible:false,
            statusOptions:[],
            isRecommendOptions:[],
            //门店列表
            stores:[],
           // 表单参数
            form: {
              stock: 0,
              storeName:null,
              giveIntegral: 0,
              sort: 0,
              status: "1",
              bargainMinPrice:0.1,
              price: 0,
              minPrice: 0,
              num: 0,
              bargainMaxPrice: 0,
              bargainMinPrice: 0,
              bargainNum: 0,
              cost: 0,
              look:0,
              share:0,
              isHot:'1',
              unitName:'节',
              description: this.value,
              rule: this.value,
            },
            // 表单校验
            rules: {
            }
          }
        },
      methods: {
        handleClose() {
          this.$emit('closeBargain', false);
          
        },
         //富文本事件开始
    onEditorBlur() {
      //失去焦点事件
    },
    onEditorFocus() {
      //获得焦点事件
    },
    onEditorChange() {
      //内容改变事件
      this.$emit("input", this.content);
    },

    // 富文本图片上传前
    quillImgBefore(file) {
      let fileType = file.type;
      if(fileType === 'image/jpeg' || fileType === 'image/png'){
        return true;
      }else {
        this.$message.error('请插入图片类型文件(jpg/jpeg/png)');
        return false;
      }
    },

    quillImgSuccess(res, file) {
      // res为图片服务器返回的数据
      // 获取富文本组件实例
      let quill = this.$refs.quillEditor.quill;
      // 如果上传成功
      if (res.code == 200) {
        // 获取光标所在位置
        let length = quill.getSelection().index1;
        // 插入图片  res.url为服务器返回的图片地址
        quill.insertEmbed(length, "image", res.url);
        // 调整光标到最后
        quill.setSelection(length + 1);
      } else {
        this.$message.error("图片插入失败");
      }
    },
    // 富文本图片上传失败
    uploadError() {
      // loading动画消失
      this.$message.error("图片插入失败");
    },
    //富文本事件结束
    
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
            addBargain(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增砍价成功");
                this.open = false;
                this.$emit('closeBargain', false);
              } else {
                this.msgError(response.msg);
              }
            });
          }

      });
    },
      }
    }
</script>

<style scoped>
 .editor {
    line-height: normal !important;
    height: 250px;
  }
</style>
