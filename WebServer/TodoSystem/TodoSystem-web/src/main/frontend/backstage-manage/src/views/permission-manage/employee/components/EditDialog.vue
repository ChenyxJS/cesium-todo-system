<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="35%" top="5vh" :close-on-click-modal=false
               :close-on-press-escape=false>
      <el-form :model="form" ref="form" :rules="rules" label-width="80px">
        <el-form-item label="所属公司" prop="empOperId">
          <el-select v-model="form.empOperId" placeholder="请选择" filterable style="width: 100%"
                     @change="getOperatorName">
            <el-option
              v-for="item in operatorList"
              :key="item.operId"
              :label="item.operName"
              :value="item.operId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="人员账号" prop="empAccount">
          <el-input v-model="form.empAccount" placeholder="请输入">
            <!--            <template slot="prepend">{{operCode}}_</template>-->
            <template slot="prepend">{{ operCode }}</template>

          </el-input>
        </el-form-item>
        <el-form-item label="人员密码" prop="empPassword" v-if="!isEdit">
          <el-input v-model="form.empPassword" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="人员姓名" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="人员编号" prop="empNumber">
          <el-input v-model="form.empNumber" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" prop="empPhone">
          <el-input v-model="form.empPhone" placeholder="请输入"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-switch style="margin-right: 5px;"
                     v-model="form.empStatus"
                     active-color="#13ce66"
                     :active-value="1"
                     inactive-color="#909399"
                     :inactive-value="0">
          </el-switch>
          <el-tag v-if="form.empStatus" size="small" type="success">在职</el-tag>
          <el-tag v-else size="small" type="info">离职</el-tag>
        </el-form-item>
      </el-form>
      <el-table
        ref="multipleTable"
        :data="roleList"
        tooltip-effect="dark"
        style="width: 100%"
        v-loading="roleLoading"
        border
        height="260"
        @selection-change="handleSelectionChange">
        <el-table-column
          align="center"
          type="selection"
          width="60">
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色名称">
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="isVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitClick()" v-loading="submitting" :disabled="submitting">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import visible from '@/mixins/edit-dialog'
import {updateEmployee, insertEmployee, setEmployeeRoles} from '@/api/permission-employee'
import {getRoleList} from '@/api/permission-role'
import {validatePhone} from "@/utils/validate";
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
    }
  },
  data() {
    return {
      submitting: false,
      form: {
        empId: '',
        empAccount: '',
        empPassword: '',
        empNumber: '',
        empName: '',
        empPhone: '',
        empStatus: 1,
        empOperId: '',
        empOperName: ''
      },
      rules: {
        empAccount: [
          {required: true, message: '人员账号不能为空！', trigger: 'blur'}
        ],
        empName: [
          {required: true, message: '人员姓名不能为空！', trigger: 'blur'}
        ],
        empNumber: [
          {required: true, message: '人员编号不能为空！', trigger: 'blur'}
        ],
        empPassword: [
          {required: true, message: '人员密码不能为空！', trigger: 'blur'}
        ],
        empPhone: [
          {required: true, validator: validatePhone, trigger: 'blur'}
        ],
        empOperId: [
          {required: true, message: '人员公司不能为空！', trigger: 'change'}
        ]
      },

      roleList: [],
      roleLoading: false,
      //打勾选中的角色
      chooseList: [],
      //公司编号
      operCode: ''
    }
  },
  watch: {
    entity(v) {
      if (v && v.empId) {
        this.form.empId = v.empId;
        this.form.empPassword = v.empPassword;
        this.form.empNumber = v.empNumber;
        this.form.empName = v.empName;
        this.form.empPhone = v.empPhone;
        this.form.empStatus = v.empStatus;
        this.form.empOperId = v.empOperId;
        this.form.empOperName = v.empOperName;
        this.getOperatorName(v.empOperId)
        if (v.empAccount) {
          let tempAccount = []
          // tempAccount = v.empAccount.split(`${this.operCode}_`)
          tempAccount = v.empAccount.split(`${this.operCode}`)

          this.form.empAccount = tempAccount[tempAccount.length - 1]
        }
        this.getAllRoles()

      } else {
        this.form = {
          empId: '',
          empAccount: '',
          empPassword: '',
          empNumber: '',
          empName: '',
          empPhone: '',
          empStatus: 1,
          empOperId: this.operatorInfo.operId,
          empOperName: this.operatorInfo.operName,
        };
        this.getOperatorName(this.operatorInfo.operId)
        this.getAllRoles()
      }
    }
  },
  computed: {
    ...mapGetters([
      'operatorInfo',
    ]),
    isEdit() {
      return this.entity && this.entity.empId
    },
    dialogTitle() {
      return this.isEdit ? '编辑' : '添加'
    }
  },
  methods: {
    getOperatorName(value) {
      let name = ''
      this.operatorList.forEach(item => {
        if (item.operId == value) {
          name = item.operName
          this.operCode = item.operCode
        }
      })
      this.form.empOperName = name
      this.getAllRoles()
    },
    //获取所有角色,里面含有
    getAllRoles() {
      this.roleLoading = true;
      getRoleList({
        empId: this.form.empId,
        'param.roleOperId': this.form.empOperId,
        page: 1,
        limit: 0
      }).then(res => {
        this.roleLoading = false;
        let root = res.root || [];
        this.roleList = root;
        let chooseList = [];
        root.forEach((r, index) => {
          if (r.empHave) {
            setTimeout(() => {
              this.$refs.multipleTable.toggleRowSelection(this.roleList[index]);
              chooseList.push(r)
            }, 1)
          }
        });
        this.chooseList = chooseList;
      })
    },
    handleSelectionChange(val) {
      this.chooseList = val;
    },

    submitClick() {
      let chooseList = this.chooseList;
      if (chooseList.length < 1) {
        this.$notify({
          title: '提示',
          message: "请选择一个角色!",
          type: 'warning'
        });
        return
      }
      this.$refs.form.validate((valid) => {
        if (valid) {
          let data = {...this.form};
          if (!this.isEdit) {
            // data.empAccount = `${this.operCode}_${data.empAccount}`
            data.empAccount = `${this.operCode}${data.empAccount}`

          }
          this.submitting = true;
          (this.isEdit ? updateEmployee(data) : insertEmployee(data)).then(result => {
            this.submitting = false;
            if (result.success) {
              this.setRoles(this.isEdit ? data.empId : result.tip);
            } else {
              this.$notify.error({
                title: '失败',
                message: (result.failReasonShow || (this.isEdit ? '修改信息失败' : '新增信息失败'))
              });
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
    setRoles(id) {
      //全部选中的节点
      let chooseList = this.chooseList;
      setEmployeeRoles({
        empId: id,
        roleIds: chooseList.map(c => {
          return c.roleId
        }).join(',')
      }).then(result => {
        this.submitting = false;
        if (result.success) {
          this.$emit('change');
          this.isVisible = false;
          this.$refs['form'].resetFields();
        } else {
          this.$notify.error({
            title: '失败',
            message: (result.failReasonShow || (this.isEdit ? '修改信息失败' : '新增信息失败'))
          });
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
</style>
