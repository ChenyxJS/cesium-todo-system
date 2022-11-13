/* eslint-disable quotes */
<template>
  <div class="login-container">
    <video-bg :sources="['./video/bg-img.png']" img="./video/bg-img.png">
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        autocomplete="on"
        label-position="left"
      >
        <div class="title-container">
          <h3 class="title">Linda社团后台管理</h3>
        </div>

        <el-form-item prop="username">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            ref="username"
            v-model="loginForm.username"
            placeholder="账号"
            name="username"
            type="text"
            tabindex="1"
            autocomplete="on"
          />
        </el-form-item>

        <el-tooltip
          v-model="capsTooltip"
          content="键盘大写已开启"
          placement="right"
          manual
        >
          <el-form-item prop="password">
            <span class="svg-container">
              <svg-icon icon-class="password" />
            </span>
            <el-input
              :key="passwordType"
              ref="password"
              v-model="loginForm.password"
              :type="passwordType"
              placeholder="密码"
              name="password"
              tabindex="2"
              autocomplete="on"
              @keyup.native="checkCapslock"
              @blur="capsTooltip = false"
              @keyup.enter.native="handleLogin"
            />
            <span class="show-pwd" @click="showPwd">
              <svg-icon
                :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
              />
            </span>
          </el-form-item>
        </el-tooltip>
        <el-button
          :loading="loading"
          :disabled="loading"
          type="primary"
          style="width: 100%; height: 42px; margin-top: 20px"
          @click.native.prevent="handleLogin"
          >提 交
        </el-button>
      </el-form>
      <choose-role v-model="roleDialog" @change="roleChange"></choose-role>
    </video-bg>
  </div>
</template>

<script>
import { validUsername } from "@/utils/validate";
import ResizeMixin from "@/mixins/ResizeHandler";
import VideoBg from "vue-videobg";
import ChooseRole from "@/components/ChooseRole";
import {setLoginRole} from "@/api/user";
import {setRole} from "@/utils/cookie-role";

export default {
  name: "Login",
  components: { ChooseRole, VideoBg },
  mixins: [ResizeMixin],
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) {
        callback(new Error("请输入用户名"));
      } else {
        callback();
      }
    };
    const validatePassword = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error("请输入密码"));
      } else {
        callback();
      }
    };
    return {
      loginFormIndex: 1,
      getCodeSecond: 0,
      getCodeSecondTimer: null,

      loginForm: {
        username: "",
        password: "",
      },
      loginRules: {
        username: [
          { required: true, trigger: "blur", validator: validateUsername },
        ],
        password: [
          { required: true, trigger: "blur", validator: validatePassword },
        ],
      },

      passwordType: "password",
      capsTooltip: false,
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {},

      roleDialog: false,
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        const query = route.query;
        if (query) {
          this.redirect = query.redirect;
          this.otherQuery = this.getOtherQuery(query);
        }
      },
      immediate: true,
    },
  },
  created() {
    this.loading = false;
  },
  mounted() {},
  destroyed() {},
  methods: {


    checkCapslock({ shiftKey, key } = {}) {
      if (key && key.length === 1) {
        if (
          (shiftKey && key >= "a" && key <= "z") ||
          (!shiftKey && key >= "A" && key <= "Z")
        ) {
          this.capsTooltip = true;
        } else {
          this.capsTooltip = false;
        }
      }
      if (key === "CapsLock" && this.capsTooltip === true) {
        this.capsTooltip = false;
      }
    },
    showPwd() {
      if (this.passwordType === "password") {
        this.passwordType = "";
      } else {
        this.passwordType = "password";
      }
      this.$nextTick(() => {
        this.$refs.password.focus();
      });
    },

    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          this.loading = true;
          this.$store
            .dispatch("user/login", this.loginForm)
            .then((res) => {
              this.loading = false;
              this.$router.push({ path: "/dashboard" });
              // this.$store.dispatch("user/getOrganizations").then((roles) => {
              //   this.roleDialog = true;
              // });
            })
            .catch((res) => {
              this.loading = false;
              this.$message.error(res.failReasonShow);
            }).finally(() => {
              this.loading = false;
            });
        } else {
          return false;
        }
      });
    },

    roleChange() {
      this.$router.push({ path: "/dashboard" });
      this.roleDialog = false;
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== "redirect") {
          acc[cur] = query[cur];
        }
        return acc;
      }, {});
    },
  },
};
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #e8e8e8;
$light_gray: #333333;
$cursor: #333333;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(173, 173, 173, 0.4);
    background: #e8e8e8;
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #616f76;

.login-container {
  min-height: 100%;
  width: 100%;
  overflow: hidden;

  .login-form {
    position: absolute;
    top: calc(50vh - 240px);
    left: calc(50% - 230px);
    width: 460px;
    max-width: 100%;
    padding: 50px 80px;
    overflow: hidden;
    background: #ffffff;
    border-radius: 10px;
    box-shadow: 5px 5px 12px rgba(0, 0, 0, 0.5);

    .change-btn {
      width: 100%;
      text-align: right;

      span {
        font-size: 13px;
        color: #1890ff;
      }

      span:hover {
        color: #25aeff;
        cursor: pointer;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: #444444;
      margin: 0 auto 40px auto;
      letter-spacing: 2px;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .get-code {
    position: absolute;
    right: 10px;
    top: 8px;
    font-size: 14px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }
}
</style>
