<template>
  <div class="filter">
    <div class="title-div">
      <span v-if="showTitle">{{ getTitle }}</span>
    </div>
    <div>
      <slot />
    </div>
  </div>
</template>

<script>
import store from '@/store'

export default {
  name: 'JsonFilter',
  props: {
    code: {
      type: String,
      default: ''
    },
    showTitle: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {}
  },
  computed: {
    getTitle() {
      let title = ''
      if (window.STAGE === 'create') {
        title = this.$route.meta.title
      } else {
        const list = store.getters.permission_routes
        list.forEach(l => {
          if (this.code === l.permCode) {
            title = l.permName
          }
        })
      }
      return title
    }
  }
}
</script>

<style scoped lang="scss">
  .filter {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    padding: 10px 13px 0 13px;
    flex-shrink: 0;

    .title-div {
      flex-shrink: 0;
      line-height: 34px;
      margin-right: 10px;
      font-size: 22px;
      font-weight: 500;
      color: #2d344a;
    }
  }
</style>
<style scoped>
  >>> .el-form--inline .el-form-item {
    margin-bottom: 12px;
    margin-right: 8px;
  }

  >>> .el-button + .el-button {
    margin-left: 8px;
  }

  >>> .permission-filter + .el-button {
    margin-left: 8px;
  }

  >>> .el-button + .permission-filter {
    margin-left: 8px;
  }
</style>
