<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="35%" top="6vh" :close-on-click-modal=false
               :close-on-press-escape=false>

      <div class="tree-view">
        <el-tree
          v-loading="treeLoading"
          :props="props"
          :data="treeData"
          node-key="permId"
          :check-strictly="true"
          :default-expand-all="true"
          :default-checked-keys="defaultKeys"
          ref="tree">
          <div class="custom-tree-node" slot-scope="{ data }">
            <div class="blue">
              <span class="gray" :class="{black:data.roleHave || data.operatorHave}">{{ data.permName }} </span>
              <i v-if="data.roleHave || data.operatorHave" class="el-icon-success success"></i>
              <i v-else class="el-icon-error gray"></i>
            </div>
            <el-tag v-if="data.permType === 'directory'" size="small">目录</el-tag>
            <el-tag v-else-if="data.permType === 'menu'" size="small" type="success">菜单</el-tag>
            <el-tag v-else-if="data.permType === 'button'" size="small" type="warning">按钮</el-tag>
            <el-tag v-else size="small" type="info">其他</el-tag>
          </div>
        </el-tree>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import visible from '@/mixins/edit-dialog'
  import {getOperatePermission} from '@/api/permission-role'

  export default {
    name: "PermissionDialog",
    mixins: [visible],
    props: {
      dialogId: {
        type: Number
      },
      //用来区分查询 运营商(operator)的 还是角色(role)的
      flag: {
        type: String
      }
    },
    data() {
      return {
        treeData: [],
        treeLoading: false,
        props: {
          label: 'permName',
          children: 'children'
        },
        defaultKeys: [],
      }
    },
    watch: {
      dialogId(v) {
        if (v) {
          this.getPermissionTree()
        }
      }
    },
    computed: {
      dialogTitle() {
        return '现有权限'
      }
    },
    methods: {
      //根据id获取子辈id的list
      getCidListByObj(obj, childNodes = []) {
        function each(data) {
          data.forEach(e => {
            childNodes.push(e.permId);
            if (e.children !== null && e.children.length > 0) {
              each(e.children)
            }
          })
        }

        if (obj.children !== null && obj.children.length > 0) {
          each(obj.children);
        }
        return childNodes;
      },
      //根据id获取祖辈id的list
      getPidListById(id, parentNodes = []) {
        let tree = this.treeData;
        let obj = this.findObjById(id, tree);
        if (id !== 0) {
          parentNodes.push(id);
          if (obj !== null && obj.permPid !== 0) {
            this.getPidListById(obj.permPid, parentNodes);
          }
        }
        return parentNodes;
      },
      findObjById(id, tree) {
        let obj = null;
        if (id === 0) {
          return obj;
        }

        function each(data) {
          data.forEach(e => {
            if (id === e.permId) {
              obj = e;
            }
            if (e.children !== null && e.children.length > 0) {
              each(e.children)
            }
          })
        }

        each(tree);
        return obj;
      },

      //获取权限树
      getPermissionTree() {
        this.treeLoading = true;
        let data = {
          orderItem: 'perm_order',
          orderType: 'asc',
          isTree: 'Y',
          page:1,
          limit:0,
        };
        if(this.flag === 'operator'){
          data.childOperatorId = this.dialogId
        }else{
          data.roleId = this.dialogId
        }
        getOperatePermission(data).then(res => {
          this.treeLoading = false;
          if (res.success) {
            this.treeData = res.root;
            let list = this.getMaxFloor(res.root).list;
            this.defaultKeys = list;
          } else {
            this.$notify({
              title: '提示',
              message: (res.failReasonShow || '未获取到权限数据'),
              type: 'warning'
            });
          }
        })
      },

      //权限树处理，获取默认的打钩的
      getMaxFloor(treeData) {
        let floor = 0;
        let max = 0;
        let list = [];

        function each(data, floor, flag) {
          data.forEach(e => {
            //如果有条件，在这个地方加上
            if (flag === 'operator' && e.operatorHave) {
              list.push(e.permId);
            }else if(flag === 'role' && e.roleHave){
              list.push(e.permId);
            }
            e.floor = floor;
            if (floor > max) {
              max = floor
            }
            if (e.children !== null && e.children !== undefined && e.children.length > 0) {
              each(e.children, floor + 1, flag)
            }
          })
        }

        each(treeData, 1, this.flag);
        return {
          max: max,
          list: list
        }
      },
    }
  }
</script>

<style scoped>
  .tree-view {
    width: 100%;
    min-height: 300px;
    max-height: 500px;
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
  >>>.el-dialog__body{
    padding: 20px;
  }
  .gray{
    color: #909399;
  }
  .black{
    color: #303133;
  }
  .success{
    color: #409EFF;
  }
</style>
