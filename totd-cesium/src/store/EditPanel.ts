/*
 * @Author: chenyx
 * @Date: 2023-03-19 00:31:13
 * @LastEditors: Do not edit
 * @LastEditTime: 2023-03-19 16:00:40
 * @FilePath: /totd-cesium/src/store/EditPanel.ts
 */
import { TODO } from "@/utils/Interface/TODO";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useEditPanelStore = defineStore("editPanel", {
  state: () => {
    return {
      visible: ref(false),
      // edit/create
      isEdit: ref(false),
      editEntity:{},
      positions:{},
      todo: ref<TODO>({
        todoId: 0,
        todoType: 1,
        todoTitle: "todoTitle",
        todoLng: 0,
        todoLat: 0,
        todoAlt: 0,
        todoAddress: "todoAddress",
        todoDesc: "todoDesc",
        todoStartTime: new Date(),
        todoEndTime: new Date(),
      }),
    };
  },
  actions:{
    closeEditPanel(){
      this.$reset()
    },
    initTodo(){
      this.todo.todoLng = this.positions.lng
      this.todo.todoLat = this.positions.lat
      this.todo.todoAlt = this.positions.alt
    }
  }
});
