<!--
 * @Author: chenyx
 * @Date: 2023-03-17 19:20:08
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-19 17:03:16
 * @FilePath: /totd-cesium/src/views/CesiumIndex/components/InfoBox/InfoBox.vue
-->
<template>
  <div
    :id="`EntityInfoBox_${props.id}`"
    v-show="infoBoxStore.visible"
    class="EntityInfoBox"
  >
    <div class="EntityInfoBox_content">
      <div class="EntityInfoBox_title">
        <span>{{ todo.todoTitle }}</span>
      </div>
      <div class="EntityInfoBox_time">
        <countdown
          v-if="props.todo.todoType !== 2 && props.todo.todoType !== 0"
          :count="state.timeDiff"
          :status="state.status"
        ></countdown>
      </div>
      <div class="EntityInfoBox_desc">
        {{ todo.todoDesc }}
      </div>
      <div class="EntityInfoBox_content_main">
        <div class="card">
          <div class="EntityInfoBox_content-time">
            <div>
              <span>开始时间：</span>
              <span class="EntityInfoBox_content-item-text">{{
                formatDate(String(todo.todoStartTime))
              }}</span>
            </div>
            <div>
              <span>结束时间：</span>
              <span class="EntityInfoBox_content-item-text">{{
                formatDate(String(todo.todoEndTime))
              }}</span>
            </div>
          </div>
          <div class="EntityInfoBox_content-item">
            <el-icon :size="16" color="#409EFC">
              <Location />
            </el-icon>
            <span class="EntityInfoBox_content-item-text">{{
              todo.todoAddress
            }}</span>
          </div>
          <div class="EntityInfoBox_content-options">
            <el-button
              v-if="props.todo.todoType !== 2 && props.todo.todoType !== 0"
              type="success"
              @click="finish_click"
              >完 成</el-button
            >
            <el-button
              v-if="props.todo.todoType !== 2"
              type="warning"
              @click="open_edit"
              >编 辑</el-button
            >
            <el-button type="danger" @click="delete_click">删 除</el-button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ElButton, ElMessageBox, ElMessage, ElIcon } from "element-plus";
import { formatDate } from "@/utils/index";
import countdown from "@/components/CountDown.vue";
import { onMounted, onUpdated, reactive } from "vue";
import { useInfoBoxStore } from "@/store/InfoBox";
import { deleteTodoData, updateTodoData } from "@/api/Todo/index";
import { useEditPanelStore } from "@/store/EditPanel";
import { Location } from "@element-plus/icons-vue";

const infoBoxStore = useInfoBoxStore();
const editPanelStore = useEditPanelStore();

const props = defineProps({
  id: {
    type: String,
    default: null,
  },
  todo: {
    type: Object,
    default: null,
  },
});

let state = reactive({
  timeDiff: 0,
  // 活动状态 0：还未开始，1：正在进行，2：已经结束
  status: 0,
  // 计时器
  timer: 0,
});

onMounted(() => {
  count_down();
});
onUpdated(() => {
  // 销毁计时器
  clearInterval(state.timer);
  count_down();
});

function count_down() {
  // 计算当前时间和开始时间
  const now = new Date().getTime() / 1000;
  const startTime = new Date(props.todo.todoStartTime).getTime() / 1000;
  const endTime = new Date(props.todo.todoEndTime).getTime() / 1000;
  // 计算时间差秒数
  if (now < startTime) {
    state.timeDiff = startTime - now;
    state.status = 0;
  } else if (startTime < now && now < endTime) {
    state.timeDiff = endTime - now;
    state.status = 1;
  } else {
    state.timeDiff = now - endTime;
    state.status = 2;
  }
  state.timer = setInterval(() => {
    state.timeDiff = state.timeDiff - 1;
  }, 1000);
}
function finish_click() {
  ElMessageBox.confirm("是否结束该事项?", "tip", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
  })
    .then(() => {
      updateTodoData({ todoId: props.todo.todoId, todoType: 2 }).then(
        (res: any) => {
          if (res.success) {
            ElMessage({
              type: "success",
              message: "您又完成了一件待办事项，继续加油",
            });
            infoBoxStore.visible = false;
          }
        }
      );
    })
    .catch(() => {});
}
function delete_click() {
  ElMessageBox.confirm("是否删除该事项?", "tip", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "error",
  })
    .then(() => {
      deleteTodoData({ id: props.todo.todoId })
        .then((res: any) => {
          if (res.success) {
            ElMessage({
              type: "success",
              message: "删除成功",
            });
            infoBoxStore.visible = false;
          }
        })
        .catch(() => {});
    })
    .catch((err) => {});
}
function open_edit() {
  infoBoxStore.visible = false;
  editPanelStore.todo = {
    todoId: props.todo.todoId,
    todoType: props.todo.todoType,
    todoTitle: props.todo.todoTitle,
    todoLng: props.todo.todoLng,
    todoLat: props.todo.todoLat,
    todoAlt: props.todo.todoAlt,
    todoAddress: props.todo.todoAddress,
    todoDesc: props.todo.todoDesc,
    todoStartTime: props.todo.todoStartTime,
    todoEndTime: props.todo.todoEndTime,
  };
  editPanelStore.visible = true;
  editPanelStore.isEdit = true;
}
</script>

<style lang="scss" scoped>
.EntityInfoBox {
  display: block;
  width: 400px;
  font-size: 14px;
  color: #6c6e72;
  background: #f8f8f8;
  border-radius: 10px;
  padding: 10px;
  position: absolute;
}
/* .EntityInfoBox::before{
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  width: 30px;
  height: 30px;
  border-radius: 0 10px 0 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 1;
} */
.EntityInfoBox::after {
  content: "";
  border-top: 0;
  border-left: 20px solid #f8f8f8;
  border-right: 0;
  border-bottom: 30px solid transparent;
  position: absolute;
  bottom: -30px;
  left: calc(50% - 15px);
  width: 0;
  height: 0;
}

.EntityInfoBox_content {
  width: 100%;
  margin-top: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
}
.EntityInfoBox .EntityInfoBox_title {
  width: 100%;
  font-size: 20px;
  font-weight: bold;
  color: #000000;
  text-align: start;
}
.EntityInfoBox_desc {
  width: 100%;
  max-height: 50px;
  font-size: 12px;
  color: #6c6e72;
  text-align: start;
  overflow: hidden;
  text-overflow: ellipsis;
}
.EntityInfoBox_content_main {
  width: 100%;
  margin: 0 auto;
}
.card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 5px;
  border-radius: 5px;
  background: #ffffff;
}
.EntityInfoBox_content-item {
  display: flex;
  margin: 5px;
  margin-bottom: 15px;
}
.EntityInfoBox_content-time {
  display: flex;
  div {
    margin: 5px;
    color: #a3a6ad;
    font-size: 8px;
  }
}
.EntityInfoBox_content-item-text {
  line-height: 16px;
}
</style>
