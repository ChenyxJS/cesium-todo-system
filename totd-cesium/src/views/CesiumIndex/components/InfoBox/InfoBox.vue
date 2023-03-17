<!--
 * @Author: chenyx
 * @Date: 2023-03-17 19:20:08
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 00:22:27
 * @FilePath: /totd-cesium/src/views/CesiumIndex/components/InfoBox/InfoBox.vue
-->
<template>
  <div
    :id="`EntityInfoBox_${props.id}`"
    v-show="props.isShowInfoBox"
    class="EntityInfoBox"
  >
    <div class="EntityInfoBox_content">
      <div class="EntityInfoBox_title">
        <span>{{ todo.todoTitle }}</span>
      </div>
      <div class="EntityInfoBox_time">
        <countdown :count="timeDiff"></countdown>
      </div>
      <div class="EntityInfoBox_desc">
        {{ todo.todoDesc }}
      </div>
      <div class="EntityInfoBox_content-item">
        <span>详细地址：</span>
        <span class="EntityInfoBox_content-item-text">{{
          todo.todoAddress
        }}</span>
      </div>
      <div class="EntityInfoBox_content-item">
        <span>开始时间：</span>
        <span class="EntityInfoBox_content-item-text">{{
          formatDate(String(todo.todoStartTime))
        }}</span>
      </div>
      <div class="EntityInfoBox_content-item">
        <span>结束时间：</span>
        <span class="EntityInfoBox_content-item-text">{{
          formatDate(String(todo.todoEndTime))
        }}</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { formatDate } from "@/utils/index";
import countdown from "@/components/countdown.vue";
import { ref } from "vue";

const props = defineProps({
  id: {
    type: String,
    default: null,
  },
  isShowInfoBox: {
    type: Boolean,
    default: false,
  },
  todo: {
    type: Object,
    default: null,
  },
});

let timeDiff = ref(0);

function count_down() {
  // 计算当前时间和开始时间
  const now = new Date().getTime();
  const startTime = new Date(props.todo.todoStartTime).getTime();
  const endTime = new Date(props.todo.todoEndTime).getTime();
  // 计算时间差
  timeDiff.value = endTime - now;
  setTimeout(() => {
    timeDiff.value = timeDiff.value - 1;
    if (timeDiff.value < 0) {
      timeDiff.value = 0;
    }
  }, 1000);
}
count_down()


</script>

<style scoped>
.EntityInfoBox {
  display: block;
  width: 400px;
  font-size: 14px;
  color: #6c6e72;
  background: #fff;
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
  border-left: 20px solid #fff;
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
.EntityInfoBox_content-item {
  display: flex;
  flex-direction: column;
  margin: 5px;
}
.EntityInfoBox_content-item-text {
}
</style>
