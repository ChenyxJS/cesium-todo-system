<template>
  <div id="todo-list-panel" class="todo-list-panel">
    <div class="top">
      <div class="tip">TODO列表</div>
      <el-icon @click="showPanel" class="close-icon"><Plus /></el-icon>
    </div>
    <div class="filter">
      <el-row>
        <el-col>
          <el-input style="margin-left:10px" clearable v-model="filterForm.title" placeholder="请输入TODO标题">
            <template #prepend>
              <el-button icon="Search" />
            </template>
          </el-input>
        </el-col>
      </el-row>
    </div>
    <json-table
      ref="myJsonTable"
      :table-data="tableData"
      :page="page"
      :table-height="300"
      @current-change="currentPageChange"
      @row-click="flyToLonLat"
    >
    </json-table>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import JsonTable from "./TodoListTable.vue";
import { ElMessage } from "element-plus";
import { Page } from "@/utils/Interface/Page";
import { TODO } from "@/utils/Interface/TODO";

export default defineComponent({
  name: "TodoListPanel",
  components: {
    JsonTable,
  },
  setup() {
    let isShow = ref<boolean>(false);
    const handler = ref();
    let tableData = ref();
    let filterForm = ref({
      title: "",
    });
    let page = ref<Page>({
      limit: 15,
      current: 1,
      total: 15,
    });
    const initTodoListPanel = (CController: any, data?: any) => {
      handler.value = CController;
      initData();
    };
    const initData = (data?: any, p?: Page) => {
      tableData.value = data;
      if (p) page.value = p;
    };
    const currentPageChange = (current: any) => {
      page.value.current = current;
    };
    const flyToLonLat = (todo: TODO) => {
      handler.value.viewerFlyToLngLat(
        `todo${todo.todoType}${todo.todoId}`,
        todo.todoLng,
        todo.todoLat
      );
    };
    const showPanel = () => {
      let el = document.getElementById("todo-list-panel");
      if (isShow.value) {
        el?.classList.add("todo-list-panel_open");
      } else {
        el?.classList.remove("todo-list-panel_open");
      }
      isShow.value = !isShow.value;
    };
    return {
      initTodoListPanel,
      currentPageChange,
      flyToLonLat,
      initData,
      showPanel,
      tableData,
      page,
      filterForm,
    };
  },
});
</script>

<style lang="scss" scoped>
.todo-list-panel {
  position: absolute;
  bottom: 0;
  left: 10;
  width: 35px;
  height: 35px;
  max-width: 500px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  background: #fff;
  border-radius: 0 15px 0 0;
  .top {
    width: 100%;
    height: 40px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 10px;
    .tip {
      display: none;
      width: 0;
    }
    .close-icon {
      margin-top: 5px;
      margin-left: 5px;
      margin-right: 5px;
      font-size: 24px;
      color: #141414;
    }
  }
  .filter {
    width: 100%;
    margin-bottom: 10px;
    display: flex;
    justify-content: flex-start;
  }
}
.todo-list-panel_open {
  width: 500px;
  height: auto;
  transition: all 0.5s;
  .top .close-icon {
    transform: rotate(-45deg);
    transition: all 0.5s ease-in-out;
  }
  .top .tip {
    width: auto;
    font-size: 14px;
    display: block;
    padding: 10px;
    color: #a3a6ad;
    overflow: hidden;
  }
}
</style>
