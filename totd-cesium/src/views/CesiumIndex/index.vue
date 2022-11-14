<template>
  <div id="cesiumContainer" ref="cesiumContainer"></div>
  <ModePanel ref="ModePanelRef" />
  <ShowLngLat ref="ShowLngLatRef" />
  <FilterPanel ref="FilterPanelRef" />
  <TodoListPanel ref="TodoListPanelRef" />
  <EditPanel @getTodoData="getTodoData" ref="EditPanelRef" />
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from "vue";
import CesiumController from "@/utils/Cesium/index";
import ModePanel from "./components/ModePanel.vue";
import ShowLngLat from "@/components/ShowLngLat.vue";
import FilterPanel from "./components/FilterPanel.vue";
import TodoListPanel from "./components/TodoListPanel.vue";
import EditPanel from "./components/EditPanel.vue";
import { getAllTodoData } from "@/api/Todo/index";
import { Page } from "@/utils/Interface/Page";


export default defineComponent({
  name: "CesiumIndex",
  components: {
    ModePanel,
    ShowLngLat,
    FilterPanel,
    TodoListPanel,
    EditPanel,
  },
  setup() {
    const CController = CesiumController;
    const viewer = ref();
    const ShowLngLatRef = ref();
    const ModePanelRef = ref();
    const FilterPanelRef = ref();
    const TodoListPanelRef = ref();
    const EditPanelRef = ref();

    onMounted(() => {
      init();
    });
    const init = () => {
      // 初始化Cesium控制器
      viewer.value = CController.init("cesiumContainer");
      // 注册点击事件
      const clickHandler = CController.clickEvent();
      // 隐藏logo
      viewer.value._cesiumWidget._creditContainer.style.display = "none";
      // 初始化操作模式组件
      ModePanelRef.value.initModePanel(CController);
      // 初始化TODO筛选组件
      FilterPanelRef.value.initFilterPanel(CController);
      // 初始化TODO列表组件
      TodoListPanelRef.value.initTodoListPanel(CController);
      // 初始化编辑窗口组件
      EditPanelRef.value.initEditPanel(CController, clickHandler);
      // 初始化实时坐标组件
      ShowLngLatRef.value.initCesiumHandler(viewer.value);
      // 获取数据
      getTodoData();
    };

    const getTodoData = () => {
      getAllTodoData({
        page: 1,
        limit: 0,
      }).then((res: any) => {
        if (res.success) {
          CController.initEntityMap(res.root);
          let page: Page = {
            limit: 10,
            current: 1,
            total: res.totalSize,
          };
          TodoListPanelRef.value.initData(res.root,page);
        }
      });
    };

    return {
      viewer,
      ShowLngLatRef,
      ModePanelRef,
      FilterPanelRef,
      TodoListPanelRef,
      EditPanelRef,
      getTodoData,
    };
  },
});
</script>

<style lang="scss" scoped>
#cesiumContainer {
  width: 100%;
  height: 100vh;
  overflow: hidden;
}
// 修改Cesium自带地址搜索框样式
:deep(.cesium-viewer-geocoderContainer .cesium-geocoder-input) {
  border-radius: 5px;
  position: relative;
  top: 10px;
  right: 10px;
}
:deep(.cesium-geocoder-searchButton) {
  border-radius: 5px;
  position: absolute;
  top: 11px;
  right: 11px;
}
.EntityInfoBox {
  width: 240px;
  height: 100px;
  background: #ffffff;
  border-radius: 5px;
  padding: 10px;
  position: absolute;
  top: 50%;
  left: 50%;
}

.EntityInfoBox .EntityInfoBox_title {
  font-size: 14px;
  font-weight: bold;
  color: #000000;
}
</style>
