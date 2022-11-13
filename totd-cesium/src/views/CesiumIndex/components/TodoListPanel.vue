<template>
  <div class="todo-list-panel">
    <el-icon class="close"><Plus /></el-icon>
    <!-- <json-table
      ref="myJsonTable"
      :table-heads="tableHeads"
      :table-data="tableData"
      :page="page"
      :table-height="300"
      @current-change="currentPageChange"
    >
    </json-table> -->
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import JsonTable from "@/components/JsonTable/index.vue";
import { ElMessage } from "element-plus";

export default defineComponent({
  name: "TodoListPanel",
  components: {
    JsonTable,
},
  setup() {
    const handler = ref();
    let tableData = ref();
    let tableHeads = ref([
      {
        dataType: "url",
        prop: "clubLogo",
        label: "logo",
      },
      {
        prop: "clubName",
        label: "社团名称",
      },
      {
        prop: "clubManagerName",
        label: "社长",
      },
      {
        dataType: "date",
        prop: "clubCreateTime",
        label: "创建时间",
      },
      {
        slot: true,
        fixed: "right",
        prop: "operations",
        label: "操作",
        width: 260,
      },
    ]);
    let page = ref({
      limit: 15,
      current: 1,
      total: 15,
    });
    const initTodoListPanel = (CController: any, data?: any) => {
      handler.value = CController;
      tableData.value = data;
    };
    const currentPageChange = (current: any) => {
      page.value.current = current;
    };

    return {
      initTodoListPanel,
      currentPageChange,
      tableData,
      tableHeads,
      page,
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
  overflow: hidden;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  background-color: rgba(255, 255, 255, 0.7);
  border-radius: 0 15px 0 0;
  .close{
    position: absolute;
    top: 5px;
    right: 5px;
    font-size: 24px;
    color: #141414;
  }
}
.todo-list-panel:hover {
  width: 350px;
  height: 70vh;
  transition: all .5s;
  .close{
    transform: rotate(-45deg);
    transition: all  .5s ease-in-out;
  }
}
</style>
