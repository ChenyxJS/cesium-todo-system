<template>
  <ul v-show="PointTypeMap" class="filter-panel">
    <li @click="changeShowPointType('Emergency')">
      <i :style="{ background: PointTypeMapColor('Emergency') }"></i>
      <p>{{ PointTypeMapLabel("Emergency") }}</p>
    </li>
    <li @click="changeShowPointType('Unfinished')">
      <i :style="{ background: PointTypeMapColor('Unfinished') }"></i>
      <p>{{ PointTypeMapLabel("Unfinished") }}</p>
    </li>
    <li @click="changeShowPointType('Editor')">
      <i :style="{ background: PointTypeMapColor('Editor') }"></i>
      <p>{{ PointTypeMapLabel("Editor") }}</p>
    </li>
    <li @click="changeShowPointType('Done')">
      <i :style="{ background: PointTypeMapColor('Done') }"></i>
      <p>{{ PointTypeMapLabel("Done") }}</p>
    </li>
  </ul>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { ElMessage } from "element-plus";

export default defineComponent({
  name: "FilterPanel",
  setup() {
    let PointTypeMap = ref();
    const handler = ref();
    const initFilterPanel = (CController: any) => {
      handler.value = CController;
      PointTypeMap.value = handler.value.getPointType();
    };
    const changeShowPointType = (type: string) => {
      // 首先点击后更改当前点击TODO类型的状态
      PointTypeMap.value[type].status = !PointTypeMap.value[type].status;
      ElMessage({
        type: "success",
        message: PointTypeMap.value[type].status
          ? `显示${PointTypeMap.value[type].label}的TODO事件`
          : `隐藏${PointTypeMap.value[type].label}的TODO事件`,
      });
      // 修改显示的实体
      handler.value.changeShowPointType(type,PointTypeMap.value[type].status)
    };
    const PointTypeMapColor = (type: string) => {
      let color: string = "";
      if (PointTypeMap.value && PointTypeMap.value[type]) {
        if (PointTypeMap.value[type].status) {
          color = PointTypeMap.value[type].color;
        } else {
          color = "#fff";
        }
      } else {
        color = "#fff";
      }
      return color;
    };
    const PointTypeMapLabel = (type: string) => {
      let label: string = "";
      if (PointTypeMap.value && PointTypeMap.value[type]) {
        label = PointTypeMap.value[type].label;
      } else {
        label = "#fff";
      }
      return label;
    };
    return {
      PointTypeMap,
      initFilterPanel,
      changeShowPointType,
      PointTypeMapColor,
      PointTypeMapLabel,
    };
  },
});
</script>

<style lang="scss" scoped>
.filter-panel {
  position: absolute;
  top: 40px;
  left: 10px;
  width: 35px;
  height: 100px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 5px;

  li {
    width: 100%;
    height: 20px;
    overflow: hidden;
    display: flex;
    flex-direction: row;
    cursor: pointer;
    i {
      margin-left: 10px;
      min-width: 16px;
      width: 16px;
      height: 16px;
      border-radius: 50%;
      background: #fff;
      transition: all 0.5s ease;
    }
    p {
      flex: 1;
      min-width: 75px;
      font-size: 14px;
      text-indent: justify;
    }
  }
}
.filter-panel:hover {
  width: 100px;
  transition: all 0.5s ease;
}
</style>
