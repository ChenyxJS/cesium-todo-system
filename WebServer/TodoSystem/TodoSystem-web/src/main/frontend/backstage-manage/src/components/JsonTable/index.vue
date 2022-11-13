<template>
  <div class="list-view-table">
    <div class="json-table-view">
      <el-table
        ref="jsonTable"
        v-loading="loading"
        :data="tableData"
        :border="border"
        :stripe="stripe"
        :show-header="showHeader"
        :height="tableHeight"
        :row-key="rowKey"
        :default-sort="{prop: propName, order: 'descending'}"
        style="width: 100%;"
        @selection-change="handleSelectionChange"
      >
        <el-table-column v-if="tableType === 'expand'" type="expand" align="center" header-align="center" width="50">
          <template slot-scope="scope">
            <slot name="tableType" :rowData="{ scope }"/>
          </template>
        </el-table-column>
        <el-table-column v-else-if="tableType !== 'none'" :type="canCatchDel ? tableType : 'index'"
                         :fixed="tableType === 'selection' && canCatchDel ? 'left':''"
                         :label="tableType === 'index'|| !canCatchDel ? '#':''" align="center" header-align="center"
                         width="50"/>

        <el-table-column
          v-for="(col,index) in tableHeads"
          :key="index"
          :align="col.align || 'center'"
          :header-align="col.headerAlign || 'center'"
          :prop="col.prop"
          :fixed="col.fixed"
          :sortable="col.isSort"
          :label="col.label"
          :width="col.width || ''"
          :show-overflow-tooltip="col.overflowTooltip === 'show'"
          :min-width="col.minWidth || ''"
        >
          <template v-if="col.prop === 'operations'" slot="header">
            <span v-if="!canCatchDel || selections.length < 1 || !selectionButtonShow"
                  style="line-height: 28px">{{ col.label }}</span>
            <el-button v-else size="mini" :type="selectionButtonType" @click="selectionDoClick">{{
                selectionButtonName
              }}
            </el-button>
          </template>
          <!--     在tableHeads的列上加上：（只能选择其中一个，不加则直接显示返回数据）
                  solt:true 自定义列
                  dataType:date 格式化时间  ====>  日期格式 dateFormat:'yyyy-MM-dd hh:mm:ss' (默认值)
                  dataType:percentage 格式化百分数  ====>  百分数格式（小数点后保留几位） decimal:0 (默认值)
                  dataType:tag 标签
                    ====>  （类型是tag时必须传）
                      标签名map：根据列值显示对应标签名
                      ==> tagLabelMap:{  0: "否", 1: "是" }
                      标签类型map： 根据列值显示对应标签颜色
                      ==>tagTypeMap:{ 0: "info",1: ""}
                  dataType:url 链接（图片）-->
          <template slot-scope="scope">
            <span v-if="col.dataType === 'date'">{{
                scope.row[col.prop] | formatDate(col.dateFormat ? col.dateFormat : 'yyyy-MM-dd hh:mm:ss')
              }}</span>
            <span
              v-else-if="col.dataType === 'percentage'">{{
                Number(scope.row[col.prop] * 100).toFixed(col.decimal ? col.decimal : 0) + "%"
              }}</span>
            <el-image v-else-if="col.dataType === 'url'" style="width: 100px; height: 100px" :src="scope.row[col.prop]"
                      fit="contain"></el-image>
            <el-tag
              v-else-if="col.dataType === 'tag'"
              :type="col.tagTypeMap[scope.row[col.prop]]?col.tagTypeMap[scope.row[col.prop]]:'success'"
              effect="dark">
              {{ col.tagLabelMap[scope.row[col.prop]]?col.tagLabelMap[scope.row[col.prop]]:scope.row[col.prop] }}
            </el-tag>
            <span v-else-if="!col.slot">{{ scope.row[col.prop] }}</span>
            <slot v-else :name="col.prop" :rowData="{ scope }"/>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-if="page !== null" class="page">
      <el-pagination
        prev-text="上一页"
        next-text="下一页"
        :current-page.sync="page.current"
        :page-size="page.limit"
        :total="page.total"
        layout="prev, pager, next, total, slot, jumper"
        @current-change="onCurrentPageChange"
      >
        <span class="common-pagination__total-pages">共{{

          }}页</span>
      </el-pagination>
    </div>
  </div>
</template>

<script>
import store from '@/store'
import {formatDate} from "@/filters";

export default {
  name: 'JsonTable',
  props: {
    // 斑马线
    stripe: {
      type: Boolean,
      default: false
    },
    // 边框
    border: {
      type: Boolean,
      default: true
    },
    // 是否显示表头
    showHeader: {
      type: Boolean,
      default: true
    },
    // 设置默认排序列(排序时，需要在要排序的列的tableHeads上加上isSort: true)
    propName: {
      type: String
    },

    // 表格第一列类型：
    // 1、selection：多选；
    // 2、expand：展开行；
    // 3、index：序号（default）
    // 4、none：什么也没有
    tableType: {
      type: String,
      default: 'index'
    },
    // 表格多选的时候用到，即按钮的权限编号
    selectionCode: {
      type: String
    },
    // 表格多选的时候必填，一般都是批量删除所需的主键
    rowKey: {
      type: String,
      default: null
    },
    selectionButtonShow: {
      type: Boolean,
      default: false // 默认不显示批量操作按钮
    },
    selectionButtonName: {
      type: String,
      default: '批量操作'
    },
    selectionButtonType: {
      type: String,
      default: 'danger'
    },
    // 表格列值配置列表，item 字段有：{ slot,prop,label,width,min-width,align(default center),header-align(default center),fixed }
    // 一般地最后一列fixed:'right'；表格多选的时候第一列fixed:'left'，操作列fixed:'right'。
    tableHeads: {
      type: Array
    },
    tableData: {
      type: Array
    },
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


  },
  data() {
    return {
      tableHeight: 0,

      // 多选时候的相关数据
      selections: []
    }
  },
  computed: {
    totalPages() {
      return this.page ? Math.ceil(this.page.total / this.page.limit) : 0
    },
    // 获取权限是否可以显示批量删除按钮
    canCatchDel() {
      const list = store.getters.permission_buttons
      const code = this.selectionCode
      const type = this.tableType
      let flag = false
      if (type !== 'selection') {
        return flag
      }
      list.forEach(l => {
        if (code === l.permCode) {
          flag = true
        }
      })
      return flag
    }
  },

  watch: {
    tableHeight(val) {
      this.tableHeight = window.innerHeight - this.$refs.jsonTable.$el.offsetTop - 130
      // 监听窗口大小变化
      const self = this
      window.onresize = function () {
        self.tableHeight = window.innerHeight - self.$refs.jsonTable.$el.offsetTop - 130
      }
    },
    // tableData(v) {
    //   if (v.length === 0) {
    //     if (this.page.current !== 1) {
    //       let current = this.page.current - 1
    //       this.$emit('current-change', current)
    //     }
    //   }
    // },
  },
  mounted() {
    this.$nextTick(function () {
      this.tableHeight = window.innerHeight - this.$refs.jsonTable.$el.offsetTop - 130
      // 监听窗口大小变化
      const self = this
      window.onresize = function () {
        self.tableHeight = window.innerHeight - self.$refs.jsonTable.$el.offsetTop - 130
      }
    })
    // this.$refs.table.$el.offsetTop：表格距离浏览器的高度
  },
  methods: {
    // 换页的事件
    onCurrentPageChange(e) {
      this.$emit('current-change', e)
    },

    // 多选时候的相关方法
    handleSelectionChange(val) {
      this.LOG.info(['表格多选对象 --- ', val])
      const s = []
      const key = this.rowKey
      val.forEach(v => {
        s.push(v[key])
      })
      this.LOG.info(['表格多选处理 --- ', s])
      this.selections = s
      this.$emit('selection-do', this.selections.join(','))
    },
    cancelSelection() {
      this.$refs.jsonTable.clearSelection()
      this.selections = []
    },
    selectionDoClick() {
      this.$emit('selection-do', this.selections.join(','))
    }
  }
}
</script>

<style lang="scss" scoped>
.list-view-table {
  flex: 1;
  display: flex;
  flex-direction: column;

  .json-table-view {
    flex: 1;
    padding: 0 10px;
    box-sizing: border-box;
  }

  .page {
    flex-shrink: 0;
    display: flex;
    height: 40px;

    .common-pagination__total-pages {
      margin-left: 18px;
      color: #797979;
    }
  }
}

</style>
<style scoped>
>>> .el-pagination__total {
  margin-left: 18px;
}

>>> .el-table--border th.gutter:last-of-type {
  display: table-cell !important;
}
</style>
