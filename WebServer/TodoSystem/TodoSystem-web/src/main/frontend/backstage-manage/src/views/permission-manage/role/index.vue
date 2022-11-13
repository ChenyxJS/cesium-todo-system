<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">
        <el-form-item>
          <el-button @click="doSearch()">刷新</el-button>
          <permission-filter code="permissionRoleAdd" type="primary" @click.native="doAdd()"/>
        </el-form-item>
      </el-form>
    </json-filter>

    <json-table ref="myJsonTable" :table-heads="tableHeads" :loading="listLoading" :table-data="tableData"
                @current-change="currentPageChange" :rowKey="'roleId'" :tableType="'none'">
      <template #roleCreateTime="iData">
        <span>
          {{ iData.rowData.scope.row.roleCreateTime | formatDate('yyyy-MM-dd hh:mm') }}
        </span>
      </template>

      <template #roleType="iData">
        <span>
          {{ iData.rowData.scope.row.roleType | formatRoleType(roleTypes)  }}
        </span>
      </template>

      <template #permission="iData">
        <el-button type="text" @click="doPermission(iData.rowData.scope.row)">查看</el-button>
      </template>
      <template #operations="iData">
        <permission-filter code="appletSetting" size="mini" type="primary"
                           @click.native="doAppletSet(iData.rowData.scope.row)"/>
        <permission-filter code="permissionRoleEdit" size="mini" type="warning"
                           @click.native="doEdit(iData.rowData.scope.row)"/>
        <permission-filter code="permissionRoleDel" size="mini" type="danger"
                           @click.native="doDelete(iData.rowData.scope.row)"/>
      </template>
    </json-table>

    <edit-dialog v-model="dialogShow" :entity="chooseEntity" :operator-list="companyList" :tree="chooseTree" :role-types="roleTypes"
                 :cascader-key="cascaderKey" @change="handleChange()"/>
    <permission-dialog v-model="permissionShow" :dialog-id="permissionId" flag="role"/>
    <applet-set v-model="appletSetShow" :entity="appletSetEntity"></applet-set>
  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import JsonTable from '@/components/JsonTable'
import PermissionDialog from '@/components/PermissionDialog'
import EditDialog from './components/EditDialog'
import AppletSet from "./components/AppletSet";
import PermissionFilter from '@/components/PermissionFilter'
import {getRoleList, deleteRole} from '@/api/permission-role'

export default {
  name: 'PermissionRole',
  components: {
    JsonFilter,
    JsonTable,
    PermissionFilter,
    EditDialog,
    PermissionDialog,
    AppletSet
  },
  data() {
    return {
      titleCode: 'permissionRole',
      tableHeads: [
        {
          headerAlign: 'left',
          align: 'left',
          prop: 'roleName',
          label: '角色名称',
          width: 180
        },
        {
          slot:true,
          prop: 'roleType',
          label: '角色类型',
          width: 180
        },
        {
          prop: 'roleRemark',
          label: '角色描述'
        },
        {
          slot: true,
          prop: 'roleCreateTime',
          label: '创建时间',
          width: 160
        }, {
          slot: true,
          prop: 'permission',
          label: '现有权限',
          width: 100
        }, {
          slot: true,
          fixed: 'right',
          prop: 'operations',
          label: '操作',
          width: 260
        }
      ],
      listLoading: false,
      tableData: [],
      companyList: [],

      param: {
        roleNameIsLike: ''
      },
      page: {
        limit: 15,
        current: 1,
        total: 15
      },
      chooseEntity: null,
      dialogShow: false,

      permissionId: 0,
      permissionShow: false,

      chooseTree: [],
      cascaderKey: 0,
      roleTypes:[
        {
          value: '0',
          label: '系统管理员'
        },
        {
          value: '1',
          label: '学生'
        },
        {
          value: '2',
          label: '家长'
        },
        {
          value: '3',
          label: '教职工'
        },
        {
          value: '4',
          label: '律师'
        },
        {
          value: '5',
          label: '行政单位'
        },
      ],

      appletSetShow:false,
      appletSetEntity:null,

    }
  },
  computed: {},
  watch: {
    dialogShow() {
      this.cascaderKey = new Date().getTime()
    }
  },
  created() {
    this.getTableData()
  },
  filters:{
    formatRoleType(type,types){
      let name = ''
      types.forEach(item=>{
        if (type == item.value ){
          name = item.label
        }
      })
      return name
    }
  },
  methods: {
    getTableData() {
      this.listLoading = true
      this.chooseTree = []
      getRoleList({
        page: 1,
        limit: 0,
        isTree: 'Y'
      }).then(res => {
        this.listLoading = false
        if (res.success) {
          this.tableData = res.root

          const p = {
            value: 0,
            label: '根角色',
            children: null
          }
          p.children = this.getChooseTree(res.root)
          this.chooseTree.push(p)

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
    // dialog 配套方法
    handleChange() {
      this.getTableData()
    },
    doAdd() {
      this.chooseEntity = this.chooseEntity === null ? {} : null
      this.dialogShow = true
    },
    doPermission(e) {
      this.permissionId = e.roleId
      this.permissionShow = true
    },
    doEdit(e) {
      //
      if (e.roleCode === 'super_admin') {
        this.$notify({
          title: '提示',
          message: ('超级管理员不可编辑！'),
          type: 'warning'
        })
        return
      }
      this.chooseEntity = e
      e.uniquelyIdentifies = new Date().getTime()
      this.dialogShow = true
    },
    doDelete(e) {
      if (e.roleCode === 'super_admin') {
        this.$notify({
          title: '提示',
          message: ('超级管理员不可删除！'),
          type: 'warning'
        })
        return
      }
      this.$confirm('确定删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action !== 'confirm') {
            done()
            return
          }
          deleteRole({id: e.roleId}).then(result => {
            if (result.success) {
              this.$notify({title: '成功', message: '操作成功!', type: 'success'})
              // 刷新数据
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
    getChooseTree(roles) {
      const res = []
      roles.forEach(item => {
        const tmp = {
          value: item.roleId,
          label: item.roleName
        }
        if (item.children && item.children.length > 0) {
          tmp.children = this.getChooseTree(item.children)
        }
        res.push(tmp)
      })
      return res
    },
    doAppletSet(e){
      this.appletSetEntity = e
      e.uniquelyIdentifies = new Date().getTime()
      this.appletSetShow = true
    }
  }
}
</script>

<style scoped>
</style>
