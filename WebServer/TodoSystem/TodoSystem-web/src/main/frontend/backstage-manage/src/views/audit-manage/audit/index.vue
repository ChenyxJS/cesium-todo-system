<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">

        <el-form-item label="社团" prop="caroClubId">
          <el-select v-model="param.audiClubId" placeholder="请选择">
            <el-option
              v-for="item in clubList"
              :key="item.clubId"
              :label="item.clubName"
              :value="item.clubId">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button @click="doSearch()">查询</el-button>
        </el-form-item>

      </el-form>
    </json-filter>

    <json-table ref="myJsonTable" :table-heads="tableHeads" :loading="listLoading" :table-data="tableData"
                :page="page"
                @current-change="currentPageChange">

      <template #operations="iData">
        <permission-filter v-if="iData.rowData.scope.row.audiStatus==0" code="auditAuditAgree" size="mini"
                           type="success"
                           @click.native="doAudit(iData.rowData.scope.row,1)"/>
        <permission-filter v-if="iData.rowData.scope.row.audiStatus==0" code="auditAuditReject" size="mini"
                           type="danger"
                           @click.native="doAudit(iData.rowData.scope.row,2)"/>
      </template>
    </json-table>


  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import JsonTable from '@/components/JsonTable'
import PermissionFilter from '@/components/PermissionFilter'
import {getClubList} from "@/api/base-club";
import {getAuditList, updateAudit} from "@/api/audit-audit";

export default {
  components: {
    JsonFilter,
    JsonTable,
    PermissionFilter,
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
      titleCode: 'auditAudit',
      tableHeads: [
        {
          prop: 'audiClubName',
          label: '社团名称'
        },
        {
          dataType: "tag",
          tagLabelMap: {
            1: '入团申请',
          },
          tagTypeMap: {
            0: 'info',
          },
          prop: 'audiType',
          label: '审核类型'
        },
        {
          prop: 'audiUserName',
          label: '申请人'
        },
        {
          dataType: 'date',
          prop: 'audiCreateTime',
          label: '申请时间'
        },
        {
          dataType: "tag",
          tagLabelMap: {
            0: '待审核',
            1: '审核通过',
            2: '审核不通过'
          },
          tagTypeMap: {
            0: 'info',
            1: 'success',
            2: 'danger'
          },
          prop: 'audiStatus',
          label: '审核状态'
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
        audiClubId: '',
      },
      page: {
        limit: 15,
        current: 1,
        total: 15
      },
      clubList: [],
    }
  },
  computed: {},
  watch: {

  },
  created() {
    this.getClubList()
    this.getTableData()
  },
  methods: {
    getClubList() {
      getClubList({page: 1, limit: 0}).then(res => {
        if (res.success) {
          this.clubList = res.root
        }
      });
    },
    // 获取表数据
    getTableData() {
      this.listLoading = true
      getAuditList({
        "param.audiClubId": this.param.audiClubId,
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
    doAudit(e, type) {
      let tip = ''
      if (type === 1) {
        tip = '确认通过审核?'
      } else {
        tip = '确认拒绝审核?'
      }
      this.$confirm(tip, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action !== 'confirm') {
            done()
            return
          }
          updateAudit({audiId: e.audiId, audiStatus: type,audiClubName:e.audiClubName,audiClubId:e.audiClubId,audiLinkId:e.audiLinkId}).then(result => {
            if (result.success) {
              this.$notify({title: '成功', message: '操作成功!', type: 'success'})
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

  }
}
</script>

<style scoped>
</style>
