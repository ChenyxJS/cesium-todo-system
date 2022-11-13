<template>
  <div class="password-view">
    <div class="form-view">
      <div class="form-center">
        <el-form :model="form" ref="form" :rules="rules" label-width="100px">
          <el-form-item label="旧密码" prop="oldPassWord">
            <el-input v-model="form.oldPassWord" placeholder="请输入旧密码" type="password" auto-complete="new-password"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newPassWord">
            <el-input v-model="form.newPassWord" placeholder="请输入新密码" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="confirmPassWord">
            <el-input v-model="form.confirmPassWord" placeholder="请输入确认密码" type="password"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="form-bottom">
        <el-button type="primary" style="width: 160px" @click="submitClick()" :loading="submitting" :disabled="submitting">提 交</el-button>
      </div>
    </div>
  </div>
</template>

<script>
  import {updatePassWord} from '@/api/user'

  export default {
    data() {
      let validateNewPassWord = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码！'));
        } else if (value === this.form.oldPassWord) {
          callback(new Error('新密码不得与旧密码一致！'));
        } else {
          callback();
        }
      };
      let validateConfirmPassWord = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码！'));
        } else if (value !== this.form.newPassWord) {
          callback(new Error('两次输入密码不一致！'));
        } else {
          callback();
        }
      };
      return {
        form: {
          oldPassWord: '',
          newPassWord: '',
          confirmPassWord: '',
        },
        submitting: false,
        rules: {
          oldPassWord: [
            {required: true, message: '不能为空！', trigger: 'blur'}
          ],
          newPassWord: [
            {required: true, validator: validateNewPassWord, trigger: 'blur'}
          ],
          confirmPassWord: [
            {required: true, validator: validateConfirmPassWord, trigger: 'blur'}
          ],
        }
      }
    },
    computed: {},
    created() {
    },
    methods: {
      submitClick() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            this.submitting = true;
            updatePassWord({
              ...this.form,
            }).then(res => {
              this.submitting = false;
              if (res.success) {
                this.$notify({
                  title: '成功',
                  message: '修改密码成功！',
                  type: 'success'
                });
                this.$refs['form'].resetFields();
              } else {
                this.$refs['form'].resetFields();
                this.$notify.error({
                  title: '失败',
                  message: res.failReasonShow || '修改密码失败！'
                });
              }
            }).catch(error => {
              this.form = {
                oldPassWord: '',
                newPassWord: '',
                confirmPassWord: '',
              };
              this.submitting = false;
              try {
                this.$message.error(`请求发生错误, 错误: ${error.response.statusCode}, ${error.response.statusText}`)
              } catch (e) {
                this.$message.error(`发生错误, 错误: ${JSON.stringify(error)}`)
              }
            })
          }
        })
      },
    }
  }
</script>

<style scoped>
  .password-view {
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
  }

  .form-view {
    margin-top: 60px;
    width: 400px;
    border-radius: 5px;
    box-sizing: border-box;
    display: flex;
    flex-direction: column;
  }


  .form-center {
    padding: 15px;
    width: 100%;
    box-sizing: border-box;
  }

  .form-bottom {
    padding: 10px;
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

</style>
