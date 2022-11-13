<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="40%" top="4vh" :close-on-click-modal=false
               :close-on-press-escape=false>
      <el-form :model="form" ref="form" :rules="rules" label-width="auto">

        <el-form-item label="通知社团" prop="noticeClubId">
          <el-select v-model="form.noticeClubId" placeholder="请选择" collapse-tags
                     style="width: 100%">
            <el-option
              v-for="item in clubList"
              :key="item.clubId"
              :label="item.clubName"
              :value="item.clubId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="通知标题" prop="noticeTitle">
          <el-input v-model="form.noticeTitle" placeholder="请输入"></el-input>
        </el-form-item>

        <el-form-item label="内容" prop="noticeContent">
          <el-input v-model="form.noticeContent" placeholder="请输入" type="textarea"></el-input>
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
import {mapGetters} from "vuex";
import {insertNotice} from "@/api/content-notice";
import {getClubList} from "@/api/base-club";

export default {
  name: "EditDialog",
  mixins: [visible],
  props: {
    entity: {
      type: Object
    }
  },
  data() {
    return {
      submitting: false,
      form: {
        noticeTitle: '',
        noticeClubId: 0,
        noticeType: '0',
        noticeContent: '',
      },

      rules: {
        noticeClubId: [
          {required: true, message: '请选择社团', trigger: 'change'}
        ],
        noticeTitle: [
          {required: true, message: '请输入通知标题', trigger: 'change'}
        ],
        noticeContent: [
          {required: true, message: '请输入文章内容', trigger: 'change'}
        ],
      },
      clubList: [],
    }
  },
  watch: {
    entity(v) {
      if (v && v.notiId) {
      } else {
        this.form = {
          noticeTitle: '',
          noticeClubId: '',
          noticeType: '0',
          noticeContent: '',
        }
      }
    }
  },
  created() {
    this.getClubList()
  },
  mounted() {
  },
  computed: {
    ...mapGetters([
      'userInfo',
      'roleInfo',
      'operatorInfo',
      'organizations'
    ]),

    isEdit() {
      return this.entity && this.entity.notiId
    },

    dialogTitle() {
      if (this.visible) {
        this.$nextTick(() => {
          this.$refs.form.clearValidate()
        })
      }
      return this.isEdit ? '编辑' : '添加'
    },
  },
  methods: {
    getClubList() {
      getClubList({page: 1, limit: 0}).then(res => {
        if (res.success) {
          this.clubList = res.root
        }
      });
    },
    /*提交按钮*/
    submitClick() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let data = {
            ...this.form
          }
          this.submitting = true
          insertNotice(data).then(result => {
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
  }
}
</script>

<style scoped>

</style>
