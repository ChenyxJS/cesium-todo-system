<!--
 * @Author: chenyx
 * @Date: 2023-03-18 00:06:35
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 16:50:43
 * @FilePath: /totd-cesium/src/components/countdown.vue
-->
<template>
  <!-- 倒计时 -->
  <div
    class="countdown"
    :class="{'before':props.status===0,'ing':props.status===1,'after':props.status===2}"
  >
    <span>{{ time() }}</span>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, watch } from "vue";

const props = defineProps({
  count: {
    type: Number,
    default: ref(0),
  },
  status: {
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
const state = reactive({
  day: 0,
  hour: 0,
  minute: 0,
  second: 0,
});

watch(props, (newVal, oldVal) => {
  // 根据秒数计算出天时分秒
  let day = Math.floor(newVal.count / 86400);
  let hour = Math.floor((newVal.count - day * 86400) / 3600);
  let minute = Math.floor((newVal.count - day * 86400 - hour * 3600) / 60);
  // 计算秒数不保留小数点
  let second = Math.floor(
    newVal.count - day * 86400 - hour * 3600 - minute * 60
  );

  state.day = day;
  state.hour = hour;
  state.minute = minute;
  state.second = second;
});

const time = () => {
  if (props.show) {
    let pre = "";
    if (props.status === 0) {
      pre = "距离活动开始还有";
    } else if (props.status === 1) {
      pre = "距离活动结束还有";
    } else {
      pre = "活动已超时";
    }
    return `${pre}${state.day}天${state.hour}小时${state.minute}分${state.second}秒`;
  } else {
    return "";
  }
};
</script>

<style scoped>
.countdown {
  font-size: 10px;
}
.before {
  color: #409eff;
}
.after {
  color: #f56c6c;
}
.ing {
  color: #67c23a;
}
</style>
