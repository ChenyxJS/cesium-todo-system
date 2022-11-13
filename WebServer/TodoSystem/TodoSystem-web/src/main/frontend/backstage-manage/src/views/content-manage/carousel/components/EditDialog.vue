<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="50%" top="3vh" :close-on-click-modal=false :close-on-press-escape=false>
      <el-form :model="form" ref="form" :rules="rules" label-width="100px">
        <el-form-item label="社团名称" prop="caroClubId">
          <el-select v-model="form.caroClubId" placeholder="请选择">
            <el-option
              v-for="item in clubList"
              :key="item.clubId"
              :label="item.clubName"
              :value="item.clubId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="类型" prop="caroType" v-if="this.form.caroOrgId !== ''">
          <el-select v-model="form.caroType" placeholder="请选择">
            <el-option
              v-for="item in typeOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="链接地址" prop="caroLinkAddress" v-if="form.caroType === 1">
          <el-input v-model="form.caroLinkAddress"></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="caroOrderNumber">
          <el-input-number v-model="form.caroOrderNumber" :min="0"></el-input-number>
        </el-form-item>

        <el-form-item label="图片">
          <el-upload
            class="avatar-uploader"
            :action="actionUrl"
            :data="postData"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :on-error="handleError"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="展示状态" prop="caroStatus">
          <el-switch style="margin-right: 5px;"
                     v-model="form.caroStatus"
                     active-color="#13ce66"
                     :active-value="1"
                     inactive-color="#909399"
                     :inactive-value="0">
          </el-switch>
          <el-tag v-if="form.caroStatus" size="small" type="success">展示</el-tag>
          <el-tag v-else size="small" type="info">不展示</el-tag>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitClick()" v-loading="submitting" :disabled="submitting">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import visible from '@/mixins/edit-dialog'
  import {getHttp, getToken} from "@/api/qiniu";
  import {insertCarousel,updateCarousel} from "@/api/content-carousel";
  import {validURL} from "@/utils/validate";
  import {mapGetters} from "vuex";
  import {getClubList} from "@/api/base-club";


  export default {
    name: "EditDialog",
    mixins: [visible],
    props: {
      entity: {
        type: Object
      },
      operatorList:{
        type:Array
      }
    },
    data() {
      return {
        submitting: false,
        form: {
          caroId: '',
          caroOrgId: '',
          caroImgUrl: '',
          caroType: 0,
          caroOrderNumber:'',
          caroStatus: 1,
          caroLinkAddress:'',
          caroLinkId:''
        },
        //七牛云一系列参数
        postData: {
          token: '',
          key:''
        },
        domain:'',
        actionUrl:'',
        imageUrl:'',

        rules: {
          caroOrgId: [
            {required: true, message: '请输入学校名称！', trigger: 'blur'}
          ],
          articleOrgId: [
            {required: true, message: '请输入类型', trigger: 'blur'}
          ],
          caroLinkId: [
            {required: true, message: '请选择文章', trigger: 'blur'}
          ],
          caroLinkAddress: [
            {required: true, message: '不能为空', trigger: 'blur'},
            {validator: validURL, trigger: 'blur'}
          ],
        },
        typeOptions: [{
        value: 0,
        label: '纯图'
      }, {
        value: 1,
        label: '链接'
      }, {
        value: 2,
        label: '系统文章'
      }],
        clubList:[],
      }
    },
    watch: {
      entity(v) {
        this.imageUrl = ''
        if (v && v.caroId) {
          this.form.caroOrgId = v.caroOrgId;
          this.form.caroImgUrl = v.caroImgUrl;
          this.imageUrl = v.caroImgUrl;
          this.form.caroType = v.caroType
          this.form.caroOrderNumber = v.caroOrderNumber;
          this.form.caroStatus = v.caroStatus;
          this.form.caroLinkAddress = v.caroLinkAddress;
          this.form.caroLinkId = v.caroLinkId;
          this.form.caroId = v.caroId;
        } else {
          this.form = {
            caroOrgId: '',
            caroImgUrl: '',
            caroType: 0,
            caroOrderNumber:'',
            caroStatus: 1,
            caroLinkAddress:'',
            caroLinkId:''
          };
        }
      }
    },
    created() {
      this.getClubList()
    },
    computed: {
      ...mapGetters(['userInfo']),
      isEdit() {
        return this.entity && this.entity.caroId
      },
      dialogTitle() {
        if(this.isVisible){
          this.$nextTick(() => {
            this.$refs.form.clearValidate()
          })
          this.getHttp()
          this.getQiniuConfig()
        }
        return this.isEdit ? '编辑' : '添加'
      }
    },
    methods: {
      getHttp(){
        let a = getHttp();
        this.actionUrl = a.url
      },

      getQiniuConfig(){
        getToken().then(response => {
          if(response.success){
            this.postData.token = response.tip;
            response.root.forEach(item=>{
              if (item.scvaCode === 'qny_domain'){
                this.domain = item.scvaValue||'';
              }
            })
          }else{
            this.$notify({title: '提示', message: '上传参数失败！', type: 'warning'});
          }
        })
      },

      handleAvatarSuccess(res, file) {
        //上传成功后在图片框显示图片
        this.imageUrl = this.domain+'/'+res.key
        this.form.caroImgUrl = this.domain+'/'+res.key
      },

      handleError(res) {   //显示错误
        this.LOG.info(['err --- ', res]);
      },

      beforeAvatarUpload(file) {    //在图片提交前进行验证
        this.postData.key = new Date().getTime() + file.name;
        const isJPG = file.type === 'image/jpeg'
        const isPNG = file.type === 'image/png'
        const isLt5M = (file.size / 1024 / 1024) < 5

        if (!isJPG&&!isPNG) {
          this.$message.error('上传图片只能是 JPG/PNG 格式!')
        }
        if (!isLt5M) {
          this.$message.error('上传图片大小不能超过 5MB!')
        }
        return (isJPG || isPNG) && isLt5M
      },

      submitClick() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let data = {...this.form};
            if(!data.caroImgUrl){
              this.$message.warning('请上传图片！')
              return
            }
            this.submitting = true;
            (this.isEdit ? updateCarousel(data) : insertCarousel(data)).then(result => {
              this.submitting = false;
              if (result.success) {
                this.$refs['form'].resetFields();
                this.$notify({title: '成功', message: '操作成功', type: 'success'});
                this.$emit('change');
                this.isVisible = false;
              } else {
                this.$notify.error({title: '失败', message: (result.failReasonShow || '操作失败')});
              }
            })
          }
        })
      },

      getClubList() {
        getClubList({page:1,limit:0}).then(res => {
          if (res.success) {
            this.clubList = res.root
          }
        });
      },
    }
  }
</script>

<style scoped>
  .tree-view {
    width: 100%;
    min-height: 120px;
    max-height: 330px;
    overflow-y: auto;
  }

  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 15px;
  }
  >>>.el-dialog__body{
    padding: 20px;
  }
  >>>.distpicker-address-wrapper select {
    padding: .35rem .75rem;
    height: 36px;
    font-size: .9rem;
  }
  >>>.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  >>>.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  >>>.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  >>>.avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
