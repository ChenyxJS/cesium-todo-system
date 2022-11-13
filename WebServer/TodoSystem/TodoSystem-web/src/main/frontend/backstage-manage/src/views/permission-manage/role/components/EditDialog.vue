<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="35%" top="6vh" :close-on-click-modal=false
               :close-on-press-escape=false>

      <el-form :model="form" ref="form" :rules="rules" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称"></el-input>
        </el-form-item>

        <el-form-item label="上级角色">
          <el-cascader
            style="width: 100%;"
            :key="cascaderKey"
            v-model="pChoose"
            :options="tree"
            :props="{checkStrictly:true}"
            placeholder="请选择上级权限"
            @change="handleChange"></el-cascader>
        </el-form-item>

        <el-form-item label="角色类型" prop="roleType">
          <el-select v-model="form.roleType" placeholder="请选择" style="width: 100%">
            <el-option
              v-for="item in roleTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="角色描述">
          <el-input v-model="form.roleRemark" placeholder="请输入角色描述"></el-input>
        </el-form-item>

        <el-form-item label="角色权限">
          <div class="tree-view">
            <el-tree
              v-loading="treeLoading"
              :props="props"
              :data="treeData"
              node-key="permId"
              :check-strictly="true"
              :default-expand-all="true"
              :default-checked-keys="defaultKeys"
              show-checkbox
              @check="checkChange"
              ref="tree">
              <div class="custom-tree-node" slot-scope="{ data }">
                <span>{{ data.permName }}</span>
                <el-tag v-if="data.permType === 'directory'" size="small">目录</el-tag>
                <el-tag v-else-if="data.permType === 'menu'" size="small" type="success">菜单</el-tag>
                <el-tag v-else-if="data.permType === 'button'" size="small" type="warning">按钮</el-tag>
                <el-tag v-else size="small" type="info">其他</el-tag>
              </div>
            </el-tree>
          </div>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitClick()" v-loading="submitting" :disabled="submitting">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import visible from '@/mixins/edit-dialog'
import {updateRole, insertRole, setRolePermission, getOperatePermission} from '@/api/permission-role'
import {mapGetters} from 'vuex'

export default {
  name: "EditDialog",
  mixins: [visible],
  props: {
    entity: {
      type: Object
    },
    operatorList: {
      type: Array
    },
    tree: {
      type: Array,
    },
    cascaderKey: {
      type: Number
    },
    roleTypes: {
      type: Array
    }
  },
  data() {
    const validateRoleName = (rule, value, callback) => {
      if (value.length < 1) {
        callback(new Error('请输入角色名称!'))
      } else if (value === '超级管理员') {
        callback(new Error('不可设为超级管理员！'))
      } else {
        callback()
      }
    };
    return {
      pChoose: [0],
      treeData: [],
      treeLoading: false,
      props: {
        label: 'permName',
        children: 'children'
      },
      defaultKeys: [],
      defaultKeysOld: [],
      submitting: false,
      form: {
        roleId: '',
        roleOperName: '',
        roleCode: 'admin',
        roleName: '',
        roleRemark: '',
        roleStatus: 1,
        roleType: '',
        rolePId: '',
        rolePCode: '0-',

      },

      rules: {
        roleName: [
          {required: true, validator: validateRoleName, trigger: 'blur'}
        ],
        roleType: [
          {required: true, message: '请选择角色类型！', trigger: 'change'}
        ]
      }
    }
  },
  watch: {
    entity(v) {
      if (v && v.roleId) {
        this.form.roleId = v.roleId;
        this.form.roleCode = v.roleCode;
        this.form.roleName = v.roleName;
        this.form.roleRemark = v.roleRemark;
        this.form.roleStatus = v.roleStatus;
        this.form.roleType = String(v.roleType);
        this.form.rolePId = v.rolePId;
        this.form.rolePCode = v.rolePCode;
        this.getPermissionTree()
      } else {
        this.form = {
          roleId: '',
          roleCode: 'admin',
          roleName: '',
          roleRemark: '',
          roleStatus: 1,
          roleType: '',
          rolePId: '',
          rolePCode: '0-',
        };
        this.getPermissionTree()
      }
      this.pChoose = this.handlePChoose(this.form.rolePCode);

    }
  },
  computed: {
    isEdit() {
      return this.entity && this.entity.roleId
    },
    dialogTitle() {
      return this.isEdit ? '编辑' : '添加'
    },
    ...mapGetters([
      'operatorInfo',
    ])
  },
  methods: {
    //根据PCode得到数组
    handlePChoose(str) {
      if (str.length === 0) {
        return []
      }
      let s = str.substring(0, str.length - 1);
      let arr = s.split('-');
      let res = [];
      arr.forEach(a => {
        res.push(parseInt(a))
      });
      return res;
    },
    //选择改变后改变pCode
    handleChange(value) {
      this.form.rolePId = this.pChoose[this.pChoose.length - 1];
      this.form.rolePCode = `${value.join('-')}-`
    },

    checkChange(a, b) {
      //根据oldList判断选择是添加了还是减少了
      //最终的结果是keyList
      //如果是增加了，根据目前的nodeList遍历里面的所有
      let oldList = this.defaultKeysOld;
      let pIdList = [];
      let nodeList = b.checkedNodes;
      let nodeKeyList = b.checkedKeys;
      if (oldList.length < nodeKeyList.length) {
        nodeList.forEach(n => {
          if (n.permPid !== 0) {
            let l = this.getPidListById(n.permPid);
            pIdList = pIdList.concat(l)
          }
        });
        pIdList = pIdList.concat(nodeKeyList)
      } else {
        //如果减少了，根据 a 找出这个下的所有的子集id
        //然后和现在的 keys 做交集操作
        let cList = [];
        let l = this.getCidListByObj(a);
        cList = cList.concat(l);
        nodeKeyList.forEach(k => {
          let isIn = false;
          cList.forEach(c => {
            if (k === c) {
              isIn = true;
            }
          });
          if (!isIn) {
            pIdList.push(k)
          }
        });
      }
      let obj = {};
      let keyList = [];
      for (let i of pIdList) {
        if (!obj[i]) {
          keyList.push(i);
          obj[i] = 1
        }
      }
      this.$refs.tree.setCheckedKeys(keyList);
      this.defaultKeysOld = keyList;
    },
    //根据id获取子辈id的list
    getCidListByObj(obj, childNodes = []) {
      function each(data) {
        data.forEach(e => {
          childNodes.push(e.permId);
          if (e.children && e.children.length > 0) {
            each(e.children)
          }
        })
      }

      if (obj.children && obj.children.length > 0) {
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
          if (e.children && e.children.length > 0) {
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
      getOperatePermission({
        orderItem: 'perm_order',
        orderType: 'asc',
        isTree: 'Y',
        page: 1,
        limit: 0,
        roleId: this.form.roleId,
      }).then(res => {
        this.treeLoading = false;
        if (res.success) {
          this.treeData = res.root;
          let list = this.getMaxFloor(res.root).list;
          this.defaultKeys = list;
          this.defaultKeysOld = list;
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

      function each(data, floor) {
        data.forEach(e => {
          //如果有条件，在这个地方加上
          if (e.roleHave) {
            list.push(e.permId);
          }
          e.floor = floor;
          if (floor > max) {
            max = floor
          }
          if (e.children !== null && e.children !== undefined && e.children.length > 0) {
            each(e.children, floor + 1)
          }
        })
      }

      each(treeData, 1);
      return {
        max: max,
        list: list
      }
    },
    submitClick() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          let data = {...this.form};
          this.submitting = true;
          (this.isEdit ? updateRole(data) : insertRole(data)).then(result => {
            if (result.success) {
              this.updateRolesPerm(this.isEdit ? data.roleId : result.tip);
            } else {
              this.$notify.error({title: '失败', message: (result.failReasonShow || this.isEdit ? '修改角色失败' : '新增角色失败')});
            }
          }).catch(error => {
            this.submitting = false;
            try {
              this.$notify.error({
                title: '发生错误',
                message: (`错误: ${error.response.statusCode}, ${error.response.statusText}`)
              });
            } catch (e) {
              this.$notify.error({title: '发生错误', message: (`错误: ${JSON.stringify(error)}`)});
            }
          })
        }
      })
    },
    updateRolesPerm(id) {
      //全部选中的节点
      let allCheckedKeys = this.$refs.tree.getCheckedKeys();
      let checkedKeys = [...allCheckedKeys];
      this.LOG.info([checkedKeys]);
      setRolePermission({
        roleId: id,
        ids: checkedKeys.join(',')
      }).then(result => {
        this.submitting = false;
        if (result.success) {
          this.$emit('change');
          this.isVisible = false;
          this.$refs['form'].resetFields();
        } else {
          this.$notify.error({title: '失败', message: (result.failReasonShow || this.isEdit ? '修改角色失败' : '新增角色失败')});
        }
      }).catch(error => {
        this.submitting = false;
        try {
          this.$notify.error({
            title: '发生错误',
            message: (`错误: ${error.response.statusCode}, ${error.response.statusText}`)
          });
        } catch (e) {
          this.$notify.error({title: '发生错误', message: (`错误: ${JSON.stringify(error)}`)});
        }
      });
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
