<template>
  <div>
    <el-dialog :title="showTitle" :visible.sync="isVisible" :width="device !== 'mobile' ? '35%':'90%'" center :close-on-press-escape="false" :show-close="canClose" :close-on-click-modal="false">
      <el-steps  :active="step" finish-status="success" style="width: 100%">
        <el-step title="选择机构"></el-step>
        <el-step title="选择角色"></el-step>
      </el-steps>
      <div class="role-list" v-if="step === 1">
        <div class="role-item" :class="{mobile:device === 'mobile'}" v-for="r in organizations" :key="r.orgId">
          <div class="inner" @click="chooseOrganization(r.orgId)">
            <span>{{r.orgName}}</span>
          </div>
        </div>
      </div>
      <div class="role-list" v-else-if="step === 2">
        <div class="role-item" :class="{mobile:device === 'mobile'}" v-for="r in roles" :key="r.roleId">
          <div class="inner" @click="chooseRole(r.roleId)">
            <span>{{r.roleName}}</span>
          </div>
        </div>
      </div>

      <div slot="footer">
        <el-button v-if="step === 2" type="info" plain round @click="goBack">上一步</el-button>
        <el-button v-if="flag === 'login'" type="info" plain round @click="cancelLogin">取消登录</el-button>
        <el-button v-if="flag === 'change'" type="info" plain round @click="cancelChange">取消切换</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {setRole, removeRole} from '@/utils/cookie-role'
  import {setLoginOrganization,setLoginRole} from "@/api/user";
  import visible from '@/mixins/edit-dialog'
  import { mapGetters } from 'vuex'
  export default {
    name: "ChooseRole",
    mixins: [visible],
    data() {
      return {
        step:1
      }
    },
    props: {
      canClose: {
        type: Boolean,
        default: false
      },
      showTitle: {
        type: String,
        default: "选择登录角色"
      },
      //标识，是登录的还是切换
      flag:{
        type: String,
        default: "login"
      }
    },
    computed: {
      ...mapGetters([
        'roles',
        'device',
        'organizations'
      ])
    },
    methods:{
      chooseRole(roleId){
        setLoginRole({roleId:roleId}).then(res=>{
          if (res.success){
            setRole(roleId);
            this.$emit('change');
          }
        })
      },
      //取消登录
      cancelLogin(){
        this.isVisible = false;
        removeRole()
      },
      //取消切换
      cancelChange(){
        this.isVisible = false;
      },

      //返回上一步
      goBack(){
        this.step--
      },

      chooseOrganization(id){
        setLoginOrganization({orgId:id}).then(res=>{
          if (res.success){
            this.step ++
            this.$store.dispatch('user/getRoles',id)
          }else{

          }
        })
      }
    }
  }
</script>

<style lang="scss" scoped>

  .role-list{
    width: 100%;
    display: flex;
    flex-wrap: wrap;

    .role-item{
      position: relative;
      width: 33.33%;
      height: 0;
      padding-top: 20%;
      font-size: 15px;

      .inner{
        position: absolute;
        top: 8px;
        left: 8px;
        right: 8px;
        bottom: 8px;
        display: flex;
        align-items: center;
        justify-content: center;
        border: 1px solid #409EFF;
        border-radius: 5px;
        color: #409EFF;
      }
      .inner:hover{
        background: #409EFF;
        color: #ffffff;
        cursor: pointer;
      }
    }

    .role-item.mobile{
      position: relative;
      width: 100%;
      height: 0;
      padding-top: 25%;
      font-size: 14px;
    }
  }
</style>
