<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">
        <el-form-item label="人员姓名">
          <el-input v-model="param.empNameIsLike" placeholder="请输入" clearable />
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="param.empPhoneIsLike" placeholder="请输入" clearable />
        </el-form-item>
        <el-form-item>
          <el-button @click="doSearch()">查询</el-button>
          <permission-filter code="permissionEmployeeAdd" type="primary" @click.native="doAdd()" />
        </el-form-item>
      </el-form>
    </json-filter>

    <json-table ref="myJsonTable" :table-heads="tableHeads" :loading="listLoading" :table-data="tableData" :page="page" @current-change="currentPageChange">
      <template #empStatus="iData">
        <el-tag v-if="iData.rowData.scope.row.empStatus == 1" size="small" type="success">在职</el-tag>
        <el-tag v-else-if="iData.rowData.scope.row.empStatus == 0" size="small" type="info">离职</el-tag>
        <el-tag v-else size="small" type="info">未知</el-tag>
      </template>

      <template #empDbCreateTime="iData">
        <span>
          {{ iData.rowData.scope.row.empDbCreateTime | formatDate('yyyy-MM-dd hh:mm') }}
        </span>
      </template>

      <template #operations="iData">
        <permission-filter
          code="permissionEmployeeEdit"
          size="mini"
          type="warning"
          @click.native="doEdit(iData.rowData.scope.row)"
        />
        <permission-filter
          code="permissionEmployeeDel"
          size="mini"
          type="danger"
          @click.native="doDelete(iData.rowData.scope.row)"
        />
      </template>
    </json-table>
    <edit-dialog v-model="dialogShow" :entity="chooseEntity" :operator-list="operatorList" @change="handleChange()" />
  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import JsonTable from '@/components/JsonTable'
import PermissionFilter from '@/components/PermissionFilter'
import EditDialog from './components/EditDialog'
import { getEmployeeList, deleteEmployee } from '@/api/permission-employee'
import { getOperatorList } from '@/api/base-operators'

export default {
  components: {
    JsonFilter,
    JsonTable,
    PermissionFilter,
    EditDialog
  },
  filters: {},
  data() {
    return {
      titleCode: 'permissionEmployee',
      tableHeads: [
        {
          prop: 'empAccount',
          label: '人员账号'
        }, {
          prop: 'empName',
          label: '人员姓名'
        }, {
          prop: 'empNumber',
          label: '人员编号'
        }, {
          prop: 'empPhone',
          label: '联系方式'
        }, {
          prop: 'roleNames',
          label: '角色'
        }, {
          prop: 'empOperName',
          label: '所属公司'
        }, {
          slot: true,
          prop: 'empStatus',
          label: '状态'
        }, {
          slot: true,
          prop: 'empDbCreateTime',
          width: 160,
          label: '创建时间'
        }, {
          slot: true,
          fixed: 'right',
          prop: 'operations',
          label: '操作',
          width: 160
        }
      ],

      listLoading: false,
      tableData: [],

      param: {
        empNameIsLike: '',
        empPhoneIsLike: ''
      },

      page: {
        limit: 15,
        current: 1,
        total: 15
      },
      dialogShow: false,
      chooseEntity: null,
      operatorList: []
    }
  },
  computed: {},
  created() {
    // this.getTableData()
    // this.getOperatorList()
  },
  methods: {
    getOperatorList() {
      getOperatorList({ limit: 0, page: 1 }).then(res => {
        if (res.success) {
          this.operatorList = res.root
        } else {
          this.operatorList = []
        }
      })
    },
    // 获取表数据
    getTableData() {
      this.listLoading = true
      getEmployeeList({
        ...this.param,
        orderItem: 'emp_db_create_time',
        orderType: 'desc',
        page: this.page.current,
        limit: this.page.limit,
        isHaveRoleName: 'Y'
      }).then(res => {
        this.listLoading = false
        if (res.success) {
          this.tableData = res.root
          this.page.total = res.totalSize
        } else {
          this.tableData = []
          this.page.total = 15
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
          deleteEmployee({ id: e.empId }).then(result => {
            if (result.success) {
              this.$notify({ title: '成功', message: '操作成功!', type: 'success' })
              this.getTableData()
            } else {
              this.$notify.error({ title: '失败', message: result.failReasonShow || '操作失败' })
            }
            done()
          }).catch(error => {
            this.$notify.error({ title: '失败', message: '请求发生错误， 请重试' })
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
    }
  }
}
</script>

<style scoped>
</style>
