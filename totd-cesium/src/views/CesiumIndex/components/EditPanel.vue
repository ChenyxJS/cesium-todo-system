<template>
  <div v-show="isShow" class="edit-panel">
    <el-icon @click="closeEditPanel" class="close"><Close /></el-icon>
    <div class="edit-panel_title">创建一个TODO事项吧</div>
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
            v-model="form.todoStartTime"
            type="datetime"
            placeholder="请选择开始时间"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-col :span="11">
          <el-date-picker
            value-format="YYYY-MM-DD HH:mm:ss"
            v-model="form.todoEndTime"
            type="datetime"
            placeholder="请选择结束时间"
          />
        </el-col>
      </el-form-item>
      <el-form-item label="详情信息">
        <el-input v-model="form.todoDesc" type="textarea" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">创 建</el-button>
        <el-button @click="closeEditPanel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref } from "vue";
import { TODOPointTypeMap } from "@/utils/baseData";
import { ElMessage } from "element-plus";
import * as Cesium from "cesium";
import { TODO } from "@/utils/Interface/TODO";
import { addTodoData } from "@/api/Todo/index";

export default defineComponent({
  name: "EditPanel",
  setup(props, cxt) {
    const _CController = ref();
    const editEntity = ref();
    let isShow = ref<boolean>();
    const TODOTypeMap = ref(TODOPointTypeMap);
    let form = ref<TODO>({
      todoId: 0,
      todoType: 1,
      todoTitle: "todoTitle",
      todoLng: 0,
      todoLat: 0,
      todoAlt: 0,
      todoAddress: "todoAddress",
      todoDesc: "todoDesc",
      todoStartTime: new Date(),
      todoEndTime: new Date(),
    });

    /**
     * @Descripttion: 初始化EditPanel
     * @msg:
     * @param {*} CController
     * @param {*} clickHandler
     * @return {*}
     */
    const initEditPanel = (CController: any, clickHandler: any) => {
      _CController.value = CController;
      // 监听左键抬起事件
      clickHandler.setInputAction((e: any) => {
        // 延迟0.1s，等到点击事件改变isShowEditPanel的值后，再获取isShowEditPanel值以及获取TODO类数据
        setTimeout(() => {
          editEntity.value = _CController.value.getSelectedEntity();
          form.value = _CController.value.getEditorEntityData();
          isShow.value = _CController.value.getIsShowEditPanel();
        }, 100);
      }, Cesium.ScreenSpaceEventType.LEFT_UP);
    };

    /**
     * @Descripttion: 关闭EditPanel
     * @msg:
     * @return {*}
     */
    const closeEditPanel = () => {
      isShow.value = false;
      _CController.value.closeEditPanel();
      // 移除编辑实体
      _CController.value.removeEntity(editEntity.value);
    };

    /**
     * @Descripttion: 提交创建请求
     * @msg:
     * @return {*}
     */
    const onSubmit = () => {
      addTodoData(form.value).then((res: any) => {
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
      initEditPanel,
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
