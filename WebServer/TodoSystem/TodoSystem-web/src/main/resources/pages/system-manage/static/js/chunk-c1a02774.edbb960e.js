(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c1a02774"],{"349c":function(e,t,a){"use strict";a.d(t,"d",(function(){return r})),a.d(t,"c",(function(){return n})),a.d(t,"b",(function(){return i})),a.d(t,"e",(function(){return l})),a.d(t,"f",(function(){return s})),a.d(t,"a",(function(){return c}));var o=a("b775");function r(e){return Object(o["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function n(e){return Object(o["a"])({url:"/admin/organization/selectOrgByQuery",method:"post",data:e})}function i(e){return Object(o["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function l(e){return Object(o["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function s(e){return Object(o["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function c(e){return Object(o["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},"76e4":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"list-view-layout"},[a("json-filter",{attrs:{code:e.titleCode}},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"学校码"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgCodeIsLike,callback:function(t){e.$set(e.param,"orgCodeIsLike",t)},expression:"param.orgCodeIsLike"}})],1),a("el-form-item",{attrs:{label:"学校名称"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgNameIsLike,callback:function(t){e.$set(e.param,"orgNameIsLike",t)},expression:"param.orgNameIsLike"}})],1),a("el-form-item",[a("el-button",{on:{click:function(t){return e.doSearch()}}},[e._v("查询")])],1)],1)],1),a("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"orgThemeColor",fn:function(t){return[a("el-color-picker",{on:{change:function(a){return e.changeColor(t.rowData.scope.row)}},model:{value:t.rowData.scope.row.orgThemeColor,callback:function(a){e.$set(t.rowData.scope.row,"orgThemeColor",a)},expression:"iData.rowData.scope.row.orgThemeColor"}})]}}])})],1)},r=[],n=a("5530"),i=a("4303"),l=a("261a"),s=a("e101"),c=a("349c"),u={components:{JsonFilter:i["a"],JsonTable:l["a"],PermissionFilter:s["a"]},filters:{},data:function(){return{titleCode:"systemTheme",tableHeads:[{prop:"orgCode",label:"学校码"},{prop:"orgName",label:"学校名称",overflowTooltip:"show"},{slot:!0,prop:"orgThemeColor",label:"主题颜色"}],listLoading:!1,tableData:[],param:{orgCodeIsLike:"",orgNameIsLike:""},page:{limit:15,current:1,total:15}}},computed:{},created:function(){this.getTableData()},methods:{getTableData:function(){var e=this;this.listLoading=!0,Object(c["d"])(Object(n["a"])(Object(n["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(t){e.listLoading=!1,t.success?(e.tableData=t.root,e.page.total=t.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:t.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},handleChange:function(){this.getTableData()},changeColor:function(e){var t=this,a={orgId:e.orgId,orgThemeColor:e.orgThemeColor};Object(c["f"])(a).then((function(e){t.submitting=!1,e.success?(t.$notify({title:"成功",message:"操作成功",type:"success"}),t.doSearch()):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"})}))}}},d=u,g=a("2877"),m=Object(g["a"])(d,o,r,!1,null,"098ef56e",null);t["default"]=m.exports}}]);