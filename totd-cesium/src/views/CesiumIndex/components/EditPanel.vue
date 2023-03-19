<template>
  <div v-show="editPanelStore.visible" class="edit-panel">
    <el-icon @click="closeEditPanel" class="close"><Close /></el-icon>
    <div class="edit-panel_title">
      {{ editPanelStore.isEdit ? "编辑" : "创建" }}一个TODO事项
    </div>
    <el-form
      ref="formRef"
      :model="form"
      label-width="120px"
      class="edit-panel_form"
    >
      <el-form-item label="标 题">
        <el-input v-model="form.todoTitle" />
      </el-form-item>
      <el-row>
        <el-col :span="12">
          <el-form-item label="经 度">
            <el-input disabled v-model="form.todoLng" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="纬 度">
            <el-input disabled v-model="form.todoLat" />
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="地 点">
        <el-input v-model="form.todoAddress" />
      </el-form-item>
      <el-form-item label="状 态">
        <el-select v-model="form.todoType" placeholder="请选择事件类型">
          <el-option :label="TODOTypeMap['Editor'].label" :value="0" />
          <el-option :label="TODOTypeMap['Unfinished'].label" :value="1" />
          <el-option :label="TODOTypeMap['Done'].label" :value="2" />
          <el-option :label="TODOTypeMap['Emergency'].label" :value="3" />
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-col :span="11">
          <el-date-picker
            value-format="YYYY-MM-DD HH:mm:ss"
            v-model="startTime"
            type="datetime"
            placeholder="请选择开始时间"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-col :span="11">
          <el-date-picker
            value-format="YYYY-MM-DD HH:mm:ss"
            v-model="endTime"
            type="datetime"
            placeholder="请选择结束时间"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="详情信息">
        <el-input v-model="form.todoDesc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提 交</el-button>
        <el-button @click="closeEditPanel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref, watch } from "vue";
import { TODOPointTypeMap } from "@/utils/baseData";
import { ElMessage } from "element-plus";
import { TODO } from "@/utils/Interface/TODO";
import { addTodoData, updateTodoData } from "@/api/Todo/index";
import { useEditPanelStore } from "@/store/EditPanel";
import { formatDate } from "@/utils";

export default defineComponent({
  name: "EditPanel",
  props: ["todoData"],
  setup(props, cxt) {
    const _CController = ref();
    let isShow = ref<boolean>();
    const TODOTypeMap = ref(TODOPointTypeMap);
    const editPanelStore = useEditPanelStore();
    let startTime = ref('')
    let endTime = ref('')
    let form = ref<TODO>(editPanelStore.todo);

    watch(editPanelStore, (newVal, oldVal) => {
      form.value = newVal.todo;
      startTime.value = formatDate(String(newVal.todo.todoStartTime));
      endTime.value = formatDate(String(newVal.todo.todoEndTime));
    });

    /**
     * @description: 初始化EditPanel
     * @param {*} CController
     * @return {*}
     */
    const initEditPanel = (CController: any) => {
      _CController.value = CController;
    };
    /**
     * @Descripttion: 关闭EditPanel
     * @msg:
     * @return {*}
     */
    const closeEditPanel = () => {
      // 移除编辑实体
      _CController.value.removeEntity(editPanelStore.editEntity);
      editPanelStore.closeEditPanel();
    };

    /**
     * @Descripttion: 提交创建请求
     * @msg:
     * @return {*}
     */
    const onSubmit = () => {
      editPanelStore.isEdit
        ? updateTodoData({
          todoId: form.value.todoId,
          todoTitle: form.value.todoTitle,
          todoLng: form.value.todoLng,
          todoLat: form.value.todoLat,
          todoAddress: form.value.todoAddress,
          todoType: form.value.todoType,
          todoDesc: form.value.todoDesc,
          todoStartTime: startTime.value,
          todoEndTime: endTime.value
        }).then((res: any) => {
            if (res.success) {
              // 提交成功后提示一下
              ElMessage({
                showClose: true,
                type: "success",
                message: "操作成功！",
              });
              // 关闭EditPanel
              closeEditPanel();
              // 重新获取数据刷新实体数据
              cxt.emit("getTodoData");
            }
          })
        : addTodoData(form.value).then((res: any) => {
            if (res.success) {
              // 提交成功后提示一下
              ElMessage({
                showClose: true,
                type: "success",
                message: "操作成功！",
              });
              // 关闭EditPanel
              closeEditPanel();
              // 重新获取数据刷新实体数据
              cxt.emit("getTodoData");
            }
          });
    };
    
   
    return {
      form,
      TODOTypeMap,
      isShow,
      closeEditPanel,
      onSubmit,
      editPanelStore,
      initEditPanel,
      startTime,
      endTime,
    };
  },
});
</script>

<style lang="scss" scoped>
.edit-panel {
  position: absolute;
  top: calc(50% - 275px);
  left: calc(50% - 350px);
  width: 700px;
  min-height: 550px;
  min-width: 500px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background-color: #ffffff;
  border-radius: 10px;
  transition: all 0.3s ease-in;
  &_title {
    margin-top: 15px;
    color: #1d1d1d;
  }
  &_form {
    padding: 15px;
    flex: 1;
  }
  .close {
    position: absolute;
    top: 5px;
    right: 5px;
    color: #000;
  }
}
</style>
