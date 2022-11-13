<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="50%" top="4vh" :close-on-click-modal=false
               :close-on-press-escape=false @close="dialogClose">
      <el-form :model="form" ref="form" :rules="rules" label-width="auto" style="display: flex;flex-wrap: wrap">

        <el-form-item label="社团名称" prop="clubName" style="width: 100%">
          <el-input v-model="form.clubName" placeholder="请输入"></el-input>
        </el-form-item>

        <el-form-item label="责任人" prop="clubManagerId" style="width: 100%">
          <el-select v-model="form.clubManagerId" clearable filterable placeholder="请选择" style="width: 100%">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.userRealName"
              :value="item.userId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="LOGO">
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
import {formatDate} from "@/utils";
import {getHttp, getToken} from "@/api/qiniu";
import {getUserList} from "@/api/base-user";
import {insertClub, updateClub} from "@/api/base-club";


export default {
  name: "EditDialog",
  mixins: [visible],
  props: {
    entity: {
      type: Object
    },
  },
  components: {'SelectAddress': () => import('@/components/SelectTown')},
  data() {
    return {
      submitting: false,
      form: {
        clubId: '',
        clubNumber: '',
        clubName: '',
        clubManagerId: '',
        clubManagerName: '',
        clubLogo: '',
      },
      rules: {
        clubName: [
          {required: true, message: '不能为空！', trigger: 'blur'}
        ],
        clubManagerId: [
          {required: true, message: '请选择！', trigger: 'change'}
        ],
      },
      //七牛云一系列参数
      postData: {
        token: '',
        key: ''
      },
      domain: '',
      actionUrl: '',
      imageUrl: '',
      // 选择的地址信息
      selectedAdddress: [],
      // 用户列表
      userList: [],
    }
  },
  watch: {
    entity(v) {
      this.getUserList();
      if (v && v.clubId) {
        this.form.clubId = v.clubId;
        this.form.clubManagerId = v.clubManagerId;
        this.form.clubManagerName = v.clubManagerName;
        this.form.clubLogo = v.clubLogo;
        this.form.clubNumber = v.clubNumber;
        this.form.clubName = v.clubName;
        this.imageUrl = v.clubLogo;
      } else {
        this.form = {
          clubId: '',
          clubNumber: '',
          clubName: '',
          clubManagerId: '',
          clubManagerName: '',
          clubLogo: '',
        };
      }
    },
  },
  computed: {
    isEdit() {
      return this.entity && this.entity.clubId
    },
    dialogTitle() {
      if (this.isVisible) {
        this.getHttp()
        this.getQiniuConfig()
      }
      return this.isEdit ? '编辑' : '添加'
    },
  },
  created() {
    this.getUserList()
  },
  methods: {
    getUserList() {
      getUserList().then(res => {
        this.userList = res.root
      })
    },
    getHttp() {
      let a = getHttp();
      this.actionUrl = a.url
    },
    getQiniuConfig() {
      getToken().then(response => {
        if (response.success) {
          this.postData.token = response.tip;
          response.root.forEach(item => {
            if (item.scvaCode === 'qny_domain') {
              this.domain = item.scvaValue || '';
            }
          })
        } else {
          this.$notify({title: '提示', message: '上传参数失败！', type: 'warning'});
        }
      })
    },
    submitClick() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let data = {...this.form};
          this.submitting = true;
          (this.isEdit ? updateClub(data) : insertClub(data)).then(result => {
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
    dialogClose() {
      this.$refs['form'].resetFields();
      this.selectedAdddress = []
      this.imageUrl = ''
    },
    getAddress(val) {
      this.selectedAdddress = val
    },
    handleAvatarSuccess(res, file) {
      //上传成功后在图片框显示图片
      this.imageUrl = this.domain + '/' + res.key
      this.form.clubLogo = this.domain + '/' + res.key
    },
    handleError(res) {   //显示错误
      this.LOG.info(['err --- ', res]);
    },

    beforeAvatarUpload(file) {    //在图片提交前进行验证
      this.postData.key = new Date().getTime() + file.name;
      const isJPG = file.type === 'image/jpeg'
      const isPNG = file.type === 'image/png'
      const isLt5M = (file.size / 1024 / 1024) < 5

      if (!isJPG && !isPNG) {
        this.$message.error('上传图片只能是 JPG/PNG 格式!')
      }
      if (!isLt5M) {
        this.$message.error('上传图片大小不能超过 5MB!')
      }
      return (isJPG || isPNG) && isLt5M
    },
  }
}
</script>

<style scoped>
>>> .el-dialog__body {
  padding: 20px;
}

>>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

>>> .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

>>> .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

>>> .avatar {
  width: 178px;
  height: 178px;
  display: block;
}

>>> .area-select {
  width: 100%;
  height: 36px;
}

>>> .area-selected-trigger {
  height: 100%;
  padding: 0 15px;
}

>>> .cascader-menu-list .cascader-menu-option.selected {
  color: #409EFF !important;
}

.split-line {
  margin: 0;
  margin-bottom: 22px;
}
.el-checkbox {
  text-align: right;
  width: 100%;
  padding-right: 10px;
}
</style>
