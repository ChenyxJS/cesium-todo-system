<template>
  <div class="list-view-layout">
    <json-filter :code="titleCode">
      <el-form :inline="true">
        <el-form-item>
          <permission-filter code="platformMenuAdd" @click.native="doAdd()" />
        </el-form-item>
      </el-form>
    </json-filter>
    <list-table :loading="listLoading" style="overflow-y: auto">
      <el-table :border="true" style="width: 100%;margin-bottom: 5px;" :data="tableData" row-key="permId" :tree-props="treeProps">
        <el-table-column prop="permName" label="权限名称" width="180" />
        <el-table-column prop="permIcon" label="图标" align="center" header-align="center" width="50">
          <template slot-scope="scope">
            <svg-icon v-if="scope.row.permType === 'directory'" :icon-class="scope.row.permIcon" />
          </template>
        </el-table-column>
        <el-table-column prop="permName" label="权限类型" align="center" header-align="center" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.permType === 'directory'" size="small">目录</el-tag>
            <el-tag v-else-if="scope.row.permType === 'menu'" size="small" type="success">菜单</el-tag>
            <el-tag v-else-if="scope.row.permType === 'button'" size="small" type="warning">按钮</el-tag>
            <el-tag v-else size="small" type="info">其他</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="permCode" label="标识代码" align="center" header-align="center" width="200" />
        <el-table-column prop="permUrl" label="映射路径">
          <template slot-scope="scope">
            <span>{{ scope.row.permUrl === '/'?'无':scope.row.permUrl }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="permStatus" label="权限状态" align="center" header-align="center" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.permStatus === 'Y'" size="small" type="success">启用</el-tag>
            <el-tag v-else-if="scope.row.permStatus === 'N'" size="small" type="info">停用</el-tag>
            <el-tag v-else size="small" type="info">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="permShow" label="是否显示" align="center" header-align="center" width="80">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.permShow === 'Y'" size="small" type="success">显示</el-tag>
            <el-tag v-else-if="scope.row.permShow === 'N'" size="small" type="info">隐藏</el-tag>
            <el-tag v-else size="small" type="info">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="permOrder" label="排序" align="center" header-align="center" width="50" />
        <el-table-column label="操作" align="center" width="160">
          <div slot-scope="scope">
            <permission-filter code="platformMenuEdit" size="mini" type="warning" @click.native="doEdit(scope.row)" />
            <permission-filter code="platformMenuDel" size="mini" type="danger" @click.native="doDelete(scope.row)" />
          </div>
        </el-table-column>
      </el-table>
    </list-table>
    <edit-dialog v-model="dialogShow" :entity="chooseEntity" :tree="chooseTree" :cascader-key="cascaderKey" @change="handleChange()" />
  </div>
</template>

<script>
import JsonFilter from '@/components/JsonFilter'
import ListTable from '@/components/ListTable'
import EditDialog from './components/EditDialog'
import PermissionFilter from '@/components/PermissionFilter'
import { getSystemAllPermission, deletePermission } from '@/api/system-menu'

export default {
  name: 'SystemMenu',
  components: {
    JsonFilter,
    ListTable,
    PermissionFilter,
    EditDialog
  },
  data() {
    return {
      titleCode: 'platformMenu',
      listLoading: false,
      tableData: [],
      treeProps: {
        children: 'children'
      },
      chooseEntity: null,
      chooseTree: [],
      dialogShow: false,
      cascaderKey: 0
    }
  },
  watch: {
    dialogShow() {
      this.cascaderKey = new Date().getTime()
    }
  },
  created() {
    this.getAllPermission()
  },
  methods: {
    getAllPermission() {
      this.listLoading = true
      this.chooseTree = []
      getSystemAllPermission({
        isTree: 'Y'
      }).then(res => {
        this.listLoading = false
        if (res.success) {
          this.tableData = res.root
          const p = {
            value: 0,
            label: '根权限',
            children: null
          }
          p.children = this.getChooseTree(res.root)
          this.chooseTree.push(p)
        } else {
          this.$notify({
            title: '警告',
            message: res.failReasonShow || '未获取到数据',
            type: 'warning'
          })
        }
      })
    },
    // 权限树处理，获取没有禁用的、把button和other过滤掉的树
    getChooseTree(routes) {
      const res = []
      routes.forEach(route => {
        const tmp = {
          value: route.permId,
          label: route.permName
        }
        if (route.permType !== 'button' && route.permType !== 'other' && route.permStatus === 'Y') {
          if (route.children && route.children.length > 0) {
            if (route.children[0].permType !== 'button' && route.children[0].permType !== 'other') {
              tmp.children = this.getChooseTree(route.children)
            }
          }
          res.push(tmp)
        }
      })
      return res
    },

    // dialog 配套方法
    handleChange() {
      this.getAllPermission()
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
          deletePermission({ id: e.permId }).then(result => {
            if (result.success) {
              this.$message.success('删除成功')
              // 刷新数据
              this.getAllPermission()
            } else {
              this.$message.error(result.failReasonShow || '删除失败')
            }
            done()
          }).catch(error => {
            this.$message.error('请求发生错误， 请重试')
            done()
          })
        }
      })
    }
  }
}
</script>
