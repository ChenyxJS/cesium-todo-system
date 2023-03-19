/*
 * @Author: chenyx
 * @Date: 2023-03-18 22:32:08
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-18 23:18:52
 * @FilePath: /totd-cesium/src/store/InfoBox.ts
 */
import { defineStore } from "pinia";
import { ref } from "vue";

export const useInfoBoxStore = defineStore("infoBox",{
  state: () => {
    return {
      visible: ref(false),
    };
  }
});
