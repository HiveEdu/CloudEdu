<template>
 <el-dialog :title="title"   :visible.sync="dialogVisible" @close="handleClose" width="800px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="砍价课程名称" prop="title">
              <el-input v-model="form.name" placeholder="请输入砍价活动名称" style="width: 500px;"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单位名称" prop="unitName">
              <el-input v-model="form.unitName" placeholder="请输入单位名称" />
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
            <el-form-item label="销量必填" prop="sales">
              <el-input-number v-model="form.sales" placeholder="请输入销量必填" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
            <el-form-item label="砍价开启时间" prop="startTime">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.startTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择砍价开启时间">
              </el-date-picker>
            </el-form-item>
         </el-col>
         <el-col :span="12">
            <el-form-item label="砍价结束时间" prop="stopTime">
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
            <el-form-item label="开始时间" prop="startTimeDate">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.startTimeDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择开始时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="结束时间" prop="endTimeDate">
              <el-date-picker clearable size="small" style="width: 200px"
                v-model="form.endTimeDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择结束时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
            <el-form-item label="砍价产品名称" prop="storeName">
              <el-input v-model="form.storeName" placeholder="请输入砍价产品名称" style="width: 300px;"/>
            </el-form-item>
         </el-col>
         <el-col :span="12">
          <el-form-item label="砍价金额" prop="price">
            <el-input-number v-model="form.courseCost" placeholder="请输入砍价金额" />
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
            <el-input-number min="0.1" v-model="form.bargainMaxPrice" placeholder="请输入用户每次砍价的最大金额" />
          </el-form-item>
         </el-col>
         <el-col :span="12">
          <el-form-item label="用户每次砍价的最小金额" prop="bargainMinPrice">
            <el-input-number min="0" v-model="form.bargainMinPrice" placeholder="请输入用户每次砍价的最小金额" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12">
          <el-form-item label="用户每次砍价的次数" prop="bargainNum">
            <el-input-number min="0" v-model="form.bargainNum" placeholder="请输入用户每次砍价的次数" />
          </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item label="返多少积分" prop="giveIntegral">
            <el-input-number min="0" v-model="form.giveIntegral" placeholder="请输入返多少积分" />
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
              <el-input v-model="form.content" placeholder="请输入砍价活动简介" style="width: 500px;"/>
            </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12"> 
          <el-form-item label="成本价" prop="cost">
            <el-input-number  min="0" v-model="form.cost" placeholder="请输入成本价" />
          </el-form-item>
         </el-col>
         <el-col :span="12"> 
          <el-form-item label="排序" prop="sort">
            <el-input-number min="0" v-model="form.sort" placeholder="请输入排序" />
          </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="24"> 
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
         <el-col :span="24"> 
            <el-form-item label="砍价规则" prop="rule">
              <el-input v-model="form.rule" type="textarea" placeholder="请输入砍价规则" style="width: 500px;"/>
            </el-form-item>
         </el-col>
        </el-row>
        <el-row>
         <el-col :span="12"> 
            <el-form-item label="砍价产品浏览量" prop="look">
              <el-input-number min="0" v-model="form.look" placeholder="请输入砍价产品浏览量" />
            </el-form-item>
         </el-col>
         <el-col :span="12"> 
            <el-form-item label="砍价产品分享量" prop="share">
              <el-input-number min="0" v-model="form.share" placeholder="请输入砍价产品分享量" />
            </el-form-item>
         </el-col>
        </el-row>
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
    export default {
        name: "bargainModal",
        props: {
          courseBargain: null,
          currentData:null,
        },
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
          this.getDicts("course_status").then(response => {
            this.statusOptions = response.data;
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
              this.gradeId = response.gradeId;
              this.sysGrades = response.sysGrades;
              this.stores=response.sysGrades;
            });
          },
        },
        data() {
          return {
            title:"开始砍价",
            dialogVisible:false,
            //  sysGrades: [],
            // // 课程类型字典
            // classifyOptions: [],
            // // 托管类型字典
            // reclassifyCollOptions: [],
            // // 是否一对一字典
            // isOneToOneOptions: [],
            // 课程状态字典
            statusOptions: [],
            //门店列表
            stores:[],
           // 表单参数
            form: {
              // giveIntegral: 0,
              // sort: 0
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

</style>
