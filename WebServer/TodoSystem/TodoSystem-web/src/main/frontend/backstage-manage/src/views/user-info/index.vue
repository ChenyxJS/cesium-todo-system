<template>
  <div class="user-info-view">
    <el-tabs v-model="activeName" tab-position="left">
      <el-tab-pane label="基本信息" name="first">
        <div class="content-view">
          <div class="content-item">
            <div class="label">用户账号：</div>
            <div class="value">{{ userInfo.userAccount }}</div>
          </div>
          <div class="content-item">
            <div class="label">用户姓名：</div>
            <div class="value">{{ userInfo.userRealName }}</div>
          </div>
          <div class="content-item">
            <div class="label">用户角色：</div>
            <el-tag v-for="role in roles" :key="role.roleId" style="margin-right: 5px">{{ role.roleName }}</el-tag>
          </div>
          <div class="content-item">
            <div class="label">用户机构：</div>
            <el-tag v-for="org in organizations" :key="org.orgId" style="margin-right: 5px">{{ org.orgName }}</el-tag>
          </div>
          <div class="content-item">
            <div class="label">联系方式：</div>
            <div class="value">
              <span v-if="!phoneIsEdit">{{ userInfo.userPhone }}</span>
              <el-input v-else v-model="phoneNumber" placeholder="请输入联系方式" />
            </div>
            <i v-if="!phoneIsEdit" class="el-icon-edit edit" @click="phoneIsEdit = true" />
            <el-button v-else type="primary" style="margin-left: 10px" :loading="submitting" :disabled="submitting" @click="submitPhone">提交</el-button>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <div class="content-view">
          <password />
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { updateLoginInfo } from '@/api/user'
import Password from './password/index'
export default {
  name: 'Index',
  components: {
    Password
  },
  computed: {
    ...mapGetters([
      'roles',
      'userInfo',
      'organizations'
    ])
  },
  data() {
    return {
      activeName: 'first',

      phoneNumber: '',

      phoneIsEdit: false,

      submitting: false
    }
  },
  created() {
    this.phoneNumber = this.userInfo.empPhone
  },
  methods: {
    // 提交联系方式
    submitPhone() {
      // 判断用户名是否是中文 
      const phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/
      const value = this.phoneNumber || ''
      if (value === '') {
        this.$notify({
          title: '提示',
          message: '联系方式不能为空！',
          type: 'warning'
        })
      } else if (!phoneReg.test(value)) {
        this.$notify({
          title: '提示',
          message: '请输入合法联系方式！',
          type: 'warning'
        })
      } else {
        this.submitting = true
        updateLoginInfo({
          userPhone: value
        }).then(res => {
          this.submitting = false
          if (res.success) {
            this.phoneIsEdit = false
            this.userInfo.empPhone = value
          } else {
            this.$notify.error({
              title: '错误',
              message: res.failReasonShow || '修改联系方式失败！'
            })
          }
        })
      }
    },
    

  }
}
</script>

<style scoped>
  .user-info-view {
    padding: 20px;
  }

  .content-view {
    width: 460px;
    height: 400px;
    border-radius: 8px;
    background: #f8f8f8;
    border: 1px solid #f0f0f0;
  }

  .content-item {
    display: flex;
    align-items: center;
    justify-content: flex-start;
    height: 60px;
  }

  .content-item .label {
    font-size: 17px;
    color: #333333;
    margin-right: 5px;
    width: 110px;
    text-align: right;
    font-weight: bold;
  }

  .content-item .value {
    font-size: 18px;
    color: #232323;
    width: 200px;
  }

  .content-item .edit {
    cursor: pointer;
  }

</style>
