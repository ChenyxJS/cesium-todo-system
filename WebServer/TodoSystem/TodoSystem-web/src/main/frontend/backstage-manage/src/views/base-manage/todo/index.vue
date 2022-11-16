<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">

        <el-form-item label="标题名称">
          <el-input v-model="param.clubNameIsLike" placeholder="请输入" clearable/>
        </el-form-item>

        <el-form-item>
          <el-button @click="doSearch()">查询</el-button>
          <permission-filter code="baseClubAdd" type="primary" @click.native="doAdd()"/>
        </el-form-item>

      </el-form>
    </json-filter>

    <json-table ref="myJsonTable" :table-heads="tableHeads" :loading="listLoading" :table-data="tableData"
                :page="page"
                @current-change="currentPageChange">

      <template #operations="iData">
        <permission-filter code="baseClubEdit" size="mini" type="warning"
                           @click.native="doEdit(iData.rowData.scope.row)"/>
        <permission-filter code="baseClubDelete" size="mini" type="danger"
                           @click.native="doDelete(iData.rowData.scope.row)"/>
      </template>
    </json-table>
    <edit-dialog v-model="dialogShow" :entity="chooseEntity"
                 @change="handleChange()"/>

  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import JsonTable from '@/components/JsonTable'
import PermissionFilter from '@/components/PermissionFilter'
import EditDialog from './components/EditDialog'
import {deleteClub, getClubList} from "@/api/base-todo";

export default {
  components: {
    JsonFilter,
    JsonTable,
    PermissionFilter,
    EditDialog,
  },
  filters: {
    formatShowName(type, list) {
      let name = ''
      list.forEach(item => {
        if (type === item.value) {
          name = item.label
        }
      })
      return name
    }
  },
  data() {
    return {
      titleCode: 'baseClub',
      tableHeads: [
        {
          prop: 'todoTitle',
          label: '标题'
        },
        {
          prop: 'todoAddress',
          label: '地点'
        },
        {
          prop: 'todoDesc',
          label: '详情'
        },
        {
          dataType: 'date',
          prop: 'todoStartTime',
          label: '开始时间'
        },
        {
          dataType: 'date',
          prop: 'todoEndTime',
          label: '开始时间'
        },
        {
          dataType: 'date',
          prop: 'todoCreateTime',
          label: '创建时间'
        },
        {
          slot: true,
          fixed: 'right',
          prop: 'operations',
          label: '操作',
          width: 260
        }
      ],
      listLoading: false,
      tableData: [],
      param: {
        clubNameIsLike: '',
      },
      page: {
        limit: 15,
        current: 1,
        total: 15
      },
      dialogShow: false,
      chooseEntity: null,
    }
  },
  computed: {},
  watch: {
    dialogShow(v) {
      if (!v) {
        this.chooseEntity = null
      }
    },
  },
  created() {
    this.getTableData()
  },
  methods: {
    // 获取表数据
    getTableData() {
      this.listLoading = true
      getClubList({
        ...this.param,
        page: this.page.current,
        limit: this.page.limit
      }).then(res => {
        this.listLoading = false
        if (res.success) {
          this.tableData = res.root
          this.page.total = res.totalSize
        } else {
          this.tableData = []
          this.page.total = 0
          this.$notify({
            title: '提示',
            message: (res.failReasonShow || '未获取到数据'),
            type: 'warning'
          })
        }
      })
    },
    currentPageChange(current) {
      this.page.current = current
      this.getTableData()
    },


    doSearch() {
      this.page.current = 1
      this.getTableData()
    },
    doDelete(e) {
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action !== 'confirm') {
            done()
            return
          }
          deleteClub({id: e.clubId}).then(result => {
            if (result.success) {
              this.$notify({title: '成功', message: '操作成功!', type: 'success'})
              // 判断删除的是否为该分页的最后一条数据
              if (this.page.current !== 1 && this.tableData.length === 1) {
                this.page.current = this.page.current - 1
              }
              this.getTableData()
            } else {
              this.$notify.error({title: '失败', message: result.failReasonShow || '操作失败'})
            }
            done()
          }).catch(error => {
            this.$notify.error({title: '失败', message: '请求发生错误， 请重试'})
            done()
          })
        }
      })
    },
    handleChange() {
      this.getTableData()
    },
    doAdd() {
      this.chooseEntity = this.chooseEntity === null ? {} : null
      this.dialogShow = true
    },
    doEdit(e) {
      this.chooseEntity = e
      e.uniquelyIdentifies = new Date().getTime()
      this.dialogShow = true
    },
  }
}
</script>

<style scoped>
</style>
