<template>
    <div class="list-view-table">
      <div class="json-table-view">
        <el-table
          ref="jsonTable"
          stripe
          size="small"
          scrollbar-always-on
          @row-click="rowClick"
          :data="tableData"
          :border="true"
          style="width: 100%"
          height="600"
        >
          <el-table-column fixed prop="todoTitle" label="标题" width="180" />
          <el-table-column prop="todoAddress" label="地点" width="180" />
          <el-table-column
            prop="todoType"
            label="类型"
            width="180"
            :filters="typeFilters"
            :filter-method="filterType"
            filter-placement="bottom-end"
          >
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-tag
                  :type="getTODOPointType(scope.row.todoType).type"
                  disable-transitions
                  >{{ getTODOPointType(scope.row.todoType).label }}</el-tag
                >
              </div>
            </template>
          </el-table-column>
  
          <el-table-column prop="todoDesc" label="详情" width="180" />
  
          <el-table-column
            sortable
            prop="todoStartTime"
            label="开始时间"
            width="180"
          >
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{
                  formatDate(scope.row.todoStartTime)
                }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column
            sortable
            prop="todoEndTime"
            label="结束时间"
            width="180"
          >
            <template #default="scope">
              <div style="display: flex; align-items: center">
                <el-icon><timer /></el-icon>
                <span style="margin-left: 10px">{{
                  formatDate(scope.row.todoEndTime)
                }}</span>
              </div>
            </template></el-table-column
          >
        </el-table>
      </div>
      <div v-if="page !== null" class="page">
        <el-pagination
          small
          :hide-on-single-page="false"
          prev-text="上一页"
          next-text="下一页"
          :current-page.sync="page.current"
          :page-size="page.limit"
          :total="page.total"
          layout="prev, pager, next, total, slot, jumper"
          @current-change="onCurrentPageChange"
        >
        </el-pagination>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { formatDate } from "@/utils/index";
  import { defineComponent, onMounted, reactive, ref } from "vue";
  import { TODOPointTypeMap, getTODOPointType } from "@/utils/baseData";
  import { TODO } from "@/utils/Interface/TODO";
  
  export default defineComponent({
    name: "JsonTable",
    props: ["page", "tableData"],
  
    setup(props, cxt) {
      onMounted(() => {
        getTODOPointTypeMapList();
      });
      let typeFilters: any = [];
      const onCurrentPageChange = () => {};
      const rowClick = (row: any) => {
        cxt.emit("rowClick", row);
      };
      const filterType = (value: number, row: TODO) => {
        return row.todoType === value;
      };
      const getTODOPointTypeMapList = () => {
        let map: any = TODOPointTypeMap;
        for (let key in map) {
          typeFilters.push({
            text: map[key].label,
            value: map[key].id,
          });
        }
      };
      return {
        onCurrentPageChange,
        rowClick,
        formatDate,
        filterType,
        getTODOPointType,
        typeFilters,
        TODOPointTypeMap,
      };
    },
  });
  </script>
  
  <style lang="scss" scoped>
  .list-view-table {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    overflow: hidden;
    .json-table-view {
      flex: 1;
      overflow: auto;
    }
  
    .page {
      display: flex;
      justify-content: flex-end;
      height: 40px;
    }
  }
  </style>
  