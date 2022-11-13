<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">
        <el-form-item label="社团" prop="caroClubId">
          <el-select v-model="param.caroClubId" placeholder="请选择">
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
          <permission-filter code="contentCarouselAdd" type="primary" @click.native="doAdd()"/>
        </el-form-item>
      </el-form>
    </json-filter>

    <json-table ref="myJsonTable" :table-heads="tableHeads" :loading="listLoading" :table-data="tableData" :page="page"
                @current-change="currentPageChange">

      <template #operations="iData">
        <permission-filter code="contentCarouselEdit" size="mini" type="warning"
                           @click.native="doEdit(iData.rowData.scope.row)"/>
        <permission-filter code="contentCarouselDelete" size="mini" type="danger"
                           @click.native="doDelete(iData.rowData.scope.row)"/>
      </template>
    </json-table>

    <edit-dialog v-model="dialogShow" :entity="chooseEntity" @change="handleChange()"/>
  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import JsonTable from '@/components/JsonTable'
import PermissionFilter from '@/components/PermissionFilter'
import EditDialog from './components/EditDialog'
import {getCarouselList, deleteCarousel} from "@/api/content-carousel";
import {getClubList} from "@/api/base-club";

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
      titleCode: 'contentCarousel',
      tableHeads: [
        {
          prop: 'caroClubName',
          label: '社团名称'
        },
        {
          dataType: 'url',
          prop: 'caroImgUrl',
          label: '图片'
        },
        {
          prop: 'caroOrderNumber',
          label: '排序'
        },
        {
          dataType: 'date',
          prop: 'caroCreateTime',
          label: '创建时间'
        },
        {
          slot: true,
          fixed: 'right',
          prop: 'operations',
          label: '操作',
          width: 180
        }
      ],

      listLoading: false,
      tableData: [],

      param: {
        caroClubId: ''
      },
      clubList: [],
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
  created() {
    this.getClubList()
    this.getTableData()
  },
  watch: {
    dialogShow(v) {
      if (!v) {
        this.chooseEntity = null
      }
    },
  },
  methods: {
    // 获取表数据
    getTableData() {
      this.listLoading = true
      getCarouselList({
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
          deleteCarousel({id: e.caroId}).then(result => {
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
    doAdd() {
      this.chooseEntity = this.chooseEntity === null ? {} : null
      this.dialogShow = true
    },
    doEdit(e) {
      this.chooseEntity = e
      e.uniquelyIdentifies = new Date().getTime()
      this.dialogShow = true
    },
    getClubList() {
      getClubList({page: 1, limit: 0}).then(res => {
        if (res.success) {
          this.clubList = res.root
        }
      });
    },
  }
}
</script>

<style scoped>
</style>
