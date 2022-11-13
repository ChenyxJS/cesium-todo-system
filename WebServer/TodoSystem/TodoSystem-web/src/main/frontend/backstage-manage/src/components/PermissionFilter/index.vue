<template>
  <div v-if="isShow()" class="permission-filter filter-margin" :class="{out:size !== 'mini'}">
    <div v-if="permissionType === 'button'" :class="{margin:size === 'mini'}">
      <el-button :type="type" :icon="icon" :size="size" :plain="plain" :round="round" :circle="circle" :loading="loading" :disabled="disabled">{{ buttonName }}</el-button>
    </div>
    <div v-else>
      <slot />
    </div>
  </div>
</template>

<script>
import store from '@/store'
export default {
  name: 'PermissionFilter',
  props: {
    // 权限标识代码
    code: '',
    // 过滤权限的类型
    permissionType: {
      type: String,
      default: 'button'
    },
    // el-button 基本属性
    size: {
      type: String,
      default: 'medium'
    },
    type: {
      type: String,
      default: 'primary'
    },
    icon: '',
    plain: {
      type: Boolean,
      default: false
    },
    round: {
      type: Boolean,
      default: false
    },
    circle: {
      type: Boolean,
      default: false
    },
    disabled:{
      type: Boolean,
      default: false
    },
    loading:{
      type: Boolean,
      default: false
    },
  },
  data() {
    return {
      buttonName: '按钮'
    }
  },
  computed: {
  },
  methods: {
    isShow() {
      const list = store.getters.permission_buttons
      let show = false
      if (window.STAGE === 'create') {
        show = true
      } else {
        list.forEach(l => {
          if (this.code === l.permCode) {
            show = true
            if (this.permissionType === 'button') {
              this.buttonName = l.permName
            }
          }
        })
      }
      return show
    }
  }
}
</script>

<style scoped>
  .permission-filter{
    display: inline-block;
  }
  .out+.out{
    margin-left: 8px;
  }
  .margin{
    margin: 3px;
  }
</style>
