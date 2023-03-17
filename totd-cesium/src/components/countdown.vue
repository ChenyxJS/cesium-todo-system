<!--
 * @Author: chenyx
 * @Date: 2023-03-18 00:06:35
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 00:30:29
 * @FilePath: /totd-cesium/src/components/countdown.vue
-->
<template>
  <!-- 倒计时 -->
  <div class="countdown">
    <span>{{ time() }}</span>
    <!-- <span>{{ hour }}</span>
    <span>{{ minute }}</span>
    <span>{{ second }}</span> -->
  </div>
</template>

<script setup lang="ts">

const props = defineProps({
  count: {
    type: Number,
    default: 0,
  },
  interval: {
    type: Number,
    default: 1000,
  },
  format: {
    type: String,
    default: "HH:mm:ss",
  },
  style: {
    type: String,
    default: "",
  },
  className: {
    type: String,
    default: "",
  },
  show: {
    type: Boolean,
    default: true,
  },
});
// 计算时间差的天数
const day = Math.floor(props.count / (1000 * 3600 * 24));
// 计算时间差的小时数
const hour = Math.floor((props.count - day * 24 * 3600 * 1000) / (1000 * 3600));
// 计算时间差的分钟数
const minute = Math.floor(
  (props.count - day * 24 * 3600 * 1000 - hour * 3600 * 1000) / (1000 * 60)
);
// 计算时间差的秒数
const second = Math.floor(
  (props.count -
    day * 24 * 3600 * 1000 -
    hour * 3600 * 1000 -
    minute * 60 * 1000) /
    1000
);
const time = ()=>{
    if (props.show) {
      return `${day}天${hour}小时${minute}分${second}秒`;
    } else {
      return "";
    }
}

</script>

<style></style>
