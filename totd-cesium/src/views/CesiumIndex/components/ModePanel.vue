<template>
  <div v-show="mode" class="mode-panel">
    <div class="preview">
      <el-icon v-if="mode === 'View'" :size="25" color="#409EFF" class="icon">
        <View />
      </el-icon>
      <el-icon
        v-else-if="mode === 'Editor'"
        :size="25"
        color="#409EFF"
        class="icon"
      >
        <Edit />
      </el-icon>
    </div>
    <el-divider direction="vertical" style="margin: 0;"/>
    <div class="select">
      <el-icon
        class="icon"
        @click="changeMode('View')"
        :size="25"
        :color="mode === 'View' ? '#409EFF' : '#000'"
      >
        <View />
      </el-icon>
      <el-icon
        class="icon"
        @click="changeMode('Editor')"
        :size="25"
        :color="mode === 'Editor' ? '#409EFF' : '#000'"
      >
        <Edit />
      </el-icon>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, ref } from "vue";
import { ElMessage } from "element-plus";

export default defineComponent({
  name: "ModePanel",
  setup() {
    let mode = ref<string>();
    const handler = ref();
    const initModePanel = (CController: any) => {
      handler.value = CController;
      mode.value = handler.value.getMode();
    };
    const changeMode = (m: string) => {
      if (m === "Editor") {
        ElMessage({
          showClose: true,
          type: "success",
          //   icon: "ChatLineRound",
          message: "已切换至编辑模式，请在地图上点击想要创建TOTD事件的地点",
        });
      } else {
        ElMessage({
          showClose: true,
          type: "success",
          //   icon: "ChatLineRound",
          message:
            "已切换至查看模式，点击地图上的标记即可查看对应的TODO事件信息",
        });
      }
      mode.value = m;
      handler.value.changeOperationMode(mode.value);
    };
    return { mode, initModePanel, changeMode };
  },
});
</script>

<style lang="scss" scoped>
.mode-panel {
  position: absolute;
  top: 10px;
  left: 10px;
  width: 35px;
  height: 35px;
  overflow: hidden;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  background-color: rgba(0, 0, 0, 0.3);
  border-radius: 5px;
  .preview {
    width: 35px;
    height: 35px;
    display: flex;
    justify-content: center;
    align-items: center;
    .icon {
      margin: 5px 10px;
    }
  }
  .select {
    width: 100px;
    display: flex;
    justify-content: space-around;
    align-items: center;
    .icon {
      color: #fff;
      padding: 5px;
      border-radius: 5px;
    }
    .icon:hover {
      background: #303030;
      color: #409EFF;
    }
  }
}
.mode-panel:hover {
  width: 125px;
  transition: all 0.3s;
}
</style>
