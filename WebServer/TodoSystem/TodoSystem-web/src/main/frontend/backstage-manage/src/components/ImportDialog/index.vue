<template>
  <div>
    <el-dialog title="Excel导入" :visible.sync="isVisible" width="30%" :close-on-click-modal=false
               :close-on-press-escape=false>

      <el-form label-width="90px">
        <el-form-item label="选择文件">
          <el-upload
            :action="baseUrl + url"
            :data="data"
            :before-upload="beforeUploadFile"
            :on-success="handleSuccess"
            :on-error="handleError"
            :file-list="fileList">
            <el-button type="primary">导入</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="导入模板" v-if="template != ''">
          <el-button type="text" @click="downTemplate">下载导入模板</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import visible from '@/mixins/edit-dialog'

  export default {
    name: "ImportDialog",
    mixins: [visible],
    components: {},
    props: {
      url: {
        type: String
      },
      template: {
        type: String,
        default: ""
      },
      data: {
        type: Object
      },
      formats: {
        type: Array
      }
    },
    data() {
      return {
        fileList: [],
      }
    },
    watch: {},
    computed: {
      baseUrl() {
        return this.BASEURL
      }
    },
    methods: {
      downTemplate(){
        window.open(this.template)
      },
      //文件上传部分
      beforeUploadFile(file) {
        let extension = file.name.substring(file.name.lastIndexOf('.') + 1);
        let size = file.size / 1024 / 1024;
        if (size > 10) {
          this.$notify.warning({
            title: '警告',
            message: `文件大小不得超过10M`
          });
          return false
        }
        let formats = this.formats;
        let flag = false;
        if (formats.length > 0) {
          formats.forEach(f => {
            if (f === extension) {
              flag = true
            }
          })
        }
        if (!flag) {
          this.$notify.warning({
            title: '警告',
            message: `只能上传后缀是 ${formats.join('、')} 的文件`
          });
          return flag
        }
        return true
      },
      // 文件上传成功时的钩子
      handleSuccess(res, file, fileList) {
        if (res.success) {
          if (res.failReasonShow == '' || res.failReasonShow == null) {
            this.$notify.success({
              title: '成功',
              message: '文件上传成功。'
            });
          } else {
            this.$notify.warning({
              title: '成功',
              message: res.failReasonShow || ''
            });
          }

          this.isVisible = false;
          this.$emit('change');
        } else {
          this.$notify.error({
            title: '失败',
            message: '文件上传失败,' + res.failReasonShow || ''
          });
        }
        this.fileList = [];

      },
      // 文件上传失败时的钩子
      handleError(err, file, fileList) {
        this.$notify.error({
          title: '错误',
          message: `文件上传失败`
        });
      },
    }
  }
</script>

<style scoped>

</style>

