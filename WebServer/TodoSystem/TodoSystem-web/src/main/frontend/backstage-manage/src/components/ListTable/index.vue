<template>
  <div class="list-view-table">
    <div class="table-wrap">
      <div style="overflow: auto" :class="[autoHeight ? 'auto-height' : 'full-absolute']" v-loading="loading"
           element-loading-text="拼命加载中"
           element-loading-spinner="el-icon-loading"
           element-loading-background="rgba(255, 255, 255, 0.6)">
        <slot></slot>
      </div>
    </div>

    <div class="page" v-if="page !== null">
      <el-pagination
        prev-text="上一页"
        next-text="下一页"
        :current-page.sync="page.current"
        :page-size="page.limit"
        :total="page.total"
        @current-change="onCurrentPageChange"
        layout="prev, pager, next, slot, jumper">
        <span class="common-pagination__total-pages">共{{totalPages}}页</span>
      </el-pagination>
    </div>
  </div>
</template>

<script>
  export default {
    name: "ListTable",
    data(){
      return {

      }
    },
    props: {
      // 分页信息: { current, limit, total }
      page: {
        type: Object,
        default: null
      },
      // 加载
      loading: {
        type: Boolean,
        default: false
      },
      // 是否自动高度, 否则自动扩充, 自动扩充时，使用absolute, 需要在外层增加一个relative， 并且需要在slot元素设置高度100%;
      autoHeight: {
        type: Boolean,
        default: false
      },
    },
    computed: {
      totalPages() {
        return this.page ? Math.ceil(this.page.total / this.page.limit) : 0
      }
    },
    methods: {
      onCurrentPageChange(e) {
        this.$emit('current-change', e)
      }
    }
  }
</script>

<style lang="scss" scoped>
  .full-absolute {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    height: 100%;
    width: 100%;
  }
  .auto-height {
    height: auto;
  }
  .list-view-table {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: stretch;
    justify-content: flex-start;
    margin-left: 13px;
    margin-right: 13px;
    flex: 1;
    .table-wrap {
      flex: 1;
      position: relative;
      overflow-y: auto;
      >>> .el-loading-mask {
        background-color: transparent !important;
      }
    }
    .page{
      flex-shrink: 0;
    }
  }
  .page-container {
    flex-shrink: 0;
  }
  .common-pagination__total-pages{
    margin-left: 24px;
    color: #c1c1c1;
  }
</style>
