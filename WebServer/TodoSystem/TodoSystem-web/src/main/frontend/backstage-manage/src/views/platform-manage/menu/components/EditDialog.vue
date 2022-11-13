<template>
  <div>
    <el-dialog :title="dialogTitle" :visible.sync="isVisible" width="40%" :close-on-click-modal=false :close-on-press-escape=false>

      <el-form :model="form" ref="form" :rules="rules" label-width="80px">
        <el-form-item>
          <el-radio-group v-model="form.permType">
            <el-radio-button label="directory">目录</el-radio-button>
            <el-radio-button label="menu">菜单</el-radio-button>
            <el-radio-button label="button">按钮</el-radio-button>
            <el-radio-button label="other">其他</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="权限名称" prop="permName">
          <el-input v-model="form.permName" placeholder="请输入权限名称"></el-input>
        </el-form-item>
        <el-form-item label="标识代码" prop="permCode">
          <el-input v-model="form.permCode" placeholder="请输入标识代码"></el-input>
        </el-form-item>
        <el-form-item v-if="form.permType === 'directory'" label="权限图标">
          <el-popover
            placement="bottom-start"
            width="460"
            trigger="click"
            @show="$refs['iconSelect'].reset()"
          >
            <IconSelect ref="iconSelect" @selected="selected"/>
            <el-input slot="reference" v-model="form.permIcon" placeholder="点击选择图标" readonly>
              <svg-icon
                v-if="form.permIcon"
                slot="prefix"
                :icon-class="form.permIcon"
                class="el-input__icon"
                style="height: 32px;width: 16px;"
              />
              <i v-else slot="prefix" class="el-icon-search el-input__icon"/>
            </el-input>
          </el-popover>
        </el-form-item>

        <el-form-item label="上级权限" v-if="tree.length > 0">
          <el-cascader
            style="width: 100%;"
            :key="cascaderKey"
            v-model="pChoose"
            :options="tree"
            :props="{checkStrictly:true}"
            placeholder="请选择上级权限"
            @change="handleChange"></el-cascader>
        </el-form-item>

        <el-form-item v-if="form.permType !== 'button' && form.permType !== 'other'" label="映射路径" prop="permUrl">
          <el-input v-model="form.permUrl" placeholder="请输入映射路径：/***"></el-input>
        </el-form-item>
        <el-form-item v-if="form.permType === 'button' || form.permType === 'menu'" label="权限接口">
          <el-input v-model="form.permControlUrl" placeholder="请输入权限接口：、分隔"></el-input>
        </el-form-item>

        <el-form-item label="权限状态">
          <el-switch
            style="margin-right: 5px;"
            v-model="form.permStatus"
            active-color="#13ce66"
            inactive-color="#909399"></el-switch>
          <el-tag v-if="form.permStatus" size="small" type="success">启用</el-tag>
          <el-tag v-else size="small" type="info">停用</el-tag>
          <el-tooltip class="item" effect="light" content="停用后此模块将不可用，也不显示。" placement="right">
            <i class="el-icon-info"></i>
          </el-tooltip>
        </el-form-item>
        <el-form-item label="是否显示">
          <el-switch
            style="margin-right: 5px;"
            v-model="form.permShow"
            active-color="#13ce66"
            inactive-color="#909399"></el-switch>
          <el-tag v-if="form.permShow" size="small" type="success">显示</el-tag>
          <el-tag v-else size="small" type="info">隐藏</el-tag>
          <el-tooltip class="item" effect="light" content="隐藏后此模块将不显示，但可用。" placement="right">
            <i class="el-icon-info"></i>
          </el-tooltip>
        </el-form-item>
        <el-form-item v-if="form.permType !== 'button'" label="排序" prop="permOrder">
          <el-input-number
            v-model="form.permOrder"
            :min="0"></el-input-number>
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
  import IconSelect from '@/components/IconSelect'
  import visible from '@/mixins/edit-dialog'
  import {insertPermission, updatePermission} from '@/api/system-menu'

  export default {
    name: "EditDialog",
    mixins: [visible],
    components: {
      IconSelect
    },
    props: {
      entity: {
        type: Object
      },
      tree: {
        type: Array,
      },
      cascaderKey:{
        type: Number
      }
    },
    data() {
      return {
        pChoose: [0],
        submitting: false,
        form: {
          permId: 0,
          permCode: '',
          permIcon: '',
          permName: '',
          permOrder: 0,
          permPcode: '',
          permPid: 0,
          permRemark: '',
          permShow: true,
          permStatus: true,
          permType: '',
          permUrl: '/',
          permControlUrl:''
        },
        rules: {
          permCode: [
            {required: true, message: '代码标识不能为空！', trigger: 'blur'}
          ],
          permName: [
            {required: true, message: '权限名称不能为空！', trigger: 'blur'}
          ],
          permUrl: [
            {required: true, message: '映射路径不能为空！', trigger: 'blur'}
          ],
          permOrder: [
            {required: true, message: '排序不能为空！', trigger: 'blur'}
          ]
        }
      }
    },
    watch: {
      entity(v) {
        if (v && v.permId) {
          this.form.permId = v.permId;
          this.form.permCode = v.permCode;
          this.form.permIcon = v.permIcon;
          this.form.permName = v.permName;
          this.form.permOrder = v.permOrder;
          this.form.permPcode = v.permPcode;
          this.form.permPid = v.permPid;
          this.form.permRemark = v.permRemark;
          this.form.permShow = v.permShow === 'Y';
          this.form.permStatus = v.permStatus === 'Y';
          this.form.permType = v.permType;
          this.form.permUrl = v.permUrl;
          this.form.permControlUrl = v.permControlUrl;
        } else {
          this.form = {
            permId: 0,
            permCode: '',
            permIcon: 'documentation',
            permName: '',
            permOrder: 0,
            permPcode: '0-',
            permPid: 0,
            permRemark: '',
            permShow: true,
            permStatus: true,
            permType: 'directory',
            permUrl: '/',
            permControlUrl:''
          };
        }
        this.pChoose = this.handlePChoose(this.form.permPcode);
      }
    },
    computed: {
      isEdit() {
        return this.entity && this.entity.permId
      },
      dialogTitle() {
        return this.isEdit ? '编辑' : '添加'
      }
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
        this.form.permPid = this.pChoose[this.pChoose.length - 1];
        this.form.permPcode = `${value.join('-')}-`
      },
      selected(name) {
        this.form.permIcon = name
      },
      submitClick() {
        this.$refs.form.validate((valid) => {
          if (valid) {
            let data = {...this.form};
            data.permShow = data.permShow ? 'Y' : 'N';
            data.permStatus = data.permStatus ? 'Y' : 'N';
            this.submitting = true;
            (this.isEdit ? updatePermission(data) : insertPermission(data)).then(result => {
              if (result.success) {
                this.submitting = false;
                this.isVisible = false;
                this.$emit('change');
                this.$refs['form'].resetFields();
              } else {
                this.$message.error(result.failReasonShow || this.isEdit ? '修改权限失败' : '新增权限失败')
              }
            }).catch(err =>{

            })
          }
        })
      },
    }
  }
</script>

<style scoped>
  .custom-tree-node {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-size: 14px;
    padding-right: 15px;
  }
</style>
