<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="40%" top="3vh" :close-on-click-modal=false :close-on-press-escape=false>

      <el-table ref="multipleTable" :data="appletSetData" border tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange"  v-loading="tableLoading">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column prop="mrpeName" label="名称" align="center" ></el-table-column>
        <el-table-column prop="mrpeCode" label="标识代码" align="center"></el-table-column>
      </el-table>

      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">取 消</el-button>
        <el-button type="primary" @click="setAppletPermission()" v-loading="submitting" :disabled="submitting">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import visible from '@/mixins/edit-dialog'
import JsonTable from '@/components/JsonTable'
import {appletSetData} from "../data/index";
import {setAppletPermission,getAppletPermission} from "@/api/permission-role";

export default {
  name: "appletSet",
  mixins: [visible],
  props: {
    entity: {
      type: Object
    },

  },
  components:{
    JsonTable
  },
  data() {
    return {
      submitting: false,
      form: {
        roleId: '',
      },
      multipleSelection: [],
      //小程序配置数据
      appletSetData,
      tableLoading:false

    }
  },
  watch: {
    entity(v) {
      if (v && v.roleId) {
        this.form.roleId = v.roleId;
        this.getAppletPermission(v.roleId)
      } else {
        this.form = {
          roleId: '',
        };
      }
    }
  },
  computed: {
    dialogTitle() {
      return '小程序配置'
    },
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    setAppletPermission(){
      let data = {
        roleId:this.form.roleId,
        json:JSON.stringify(this.multipleSelection)
      }
      this.submitting = true
      setAppletPermission(data).then(res=>{
        this.submitting = false
        if (res.success) {
          this.$notify({title: '成功', message: '操作成功!', type: 'success'})
          this.isVisible = false
        } else {
          this.$notify.error({title: '失败', message: res.failReasonShow || '操作失败'})
        }
      }).catch(e=>{
        this.submitting = false
        this.$notify.error({title: '失败', message: '请求发生错误， 请重试'})
      })
    },
    getAppletPermission(id){
      this.tableLoading = true
      getAppletPermission({
        'param.mrpeRoleId':id,
        page:1,
        limit:0
      }).then(res=>{
        this.tableLoading = false
        let selectRows = []
        if (res.success){
          for (let i = 0;i<res.root.length;i++){
            for (let j = 0;j<this.appletSetData.length;j++){
              if (res.root[i].mrpeCode === this.appletSetData[j].mrpeCode){
                selectRows.push(this.appletSetData[j])
              }
            }
          }
          this.toggleSelection(selectRows)
        }else{
          this.$refs.multipleTable.clearSelection();
        }
      }).catch(e=>{
        this.tableLoading = false
      })
    }
  }
}
</script>

<style scoped>
.tree-view {
  width: 100%;
  min-height: 120px;
  max-height: 400px;
  overflow-y: auto;
}

.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 15px;
}

>>> .el-dialog__body {
  padding: 20px;
}
</style>
