(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4e76919f"],{2209:function(e,t,a){"use strict";a.d(t,"o",(function(){return o})),a.d(t,"p",(function(){return l})),a.d(t,"f",(function(){return n})),a.d(t,"m",(function(){return i})),a.d(t,"n",(function(){return r})),a.d(t,"h",(function(){return u})),a.d(t,"g",(function(){return s})),a.d(t,"r",(function(){return c})),a.d(t,"q",(function(){return d})),a.d(t,"e",(function(){return f})),a.d(t,"k",(function(){return p})),a.d(t,"i",(function(){return b})),a.d(t,"c",(function(){return m})),a.d(t,"l",(function(){return g})),a.d(t,"j",(function(){return v})),a.d(t,"b",(function(){return h})),a.d(t,"d",(function(){return w})),a.d(t,"a",(function(){return O}));var o=[{value:"0",label:"小学"},{value:"1",label:"初中"},{value:"2",label:"高中"}],l=[{value:"0",label:"公办"},{value:"1",label:"民办"},{value:"2",label:"行政单位"},{value:"3",label:"平台"}],n=[{value:"1",label:"男生宿舍"},{value:"2",label:"女生宿舍"},{value:"3",label:"楼号"},{value:"4",label:"楼层"}],i=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],r=[{value:"0",label:"单选"},{value:"1",label:"多选"}],u=[{value:"0",label:"临时"},{value:"1",label:"长期"},{value:"2",label:"学期"}],s=[{value:"1",label:"学校"},{value:"2",label:"年级"},{value:"3",label:"班级"},{value:"4",label:"个人"}],c=[{value:"1",label:"通用库"},{value:"2",label:"特色库"},{value:"3",label:"校规"},{value:"4",label:"班规"}],d=[{value:"1",label:"一级"},{value:"2",label:"二级"},{value:"3",label:"三级"}],f=[{value:"1",label:"年"},{value:"2",label:"月"},{value:"3",label:"周"},{value:"4",label:"日"}],p=[{label:"学校",value:1},{label:"年级",value:2},{label:"班级",value:3}],b=[{label:"年级",value:2},{label:"班级",value:3}],m=[{label:"班级",value:3}],g=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"其他教职工",value:17},{label:"心理辅导",value:18},{label:"家长",value:4}],v=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],h=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],w=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],O=[{label:"任课老师",value:14},{label:"家长",value:4}]},"349c":function(e,t,a){"use strict";a.d(t,"c",(function(){return l})),a.d(t,"b",(function(){return n})),a.d(t,"d",(function(){return i})),a.d(t,"e",(function(){return r})),a.d(t,"a",(function(){return u}));var o=a("b775");function l(e){return Object(o["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function n(e){return Object(o["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function i(e){return Object(o["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function r(e){return Object(o["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function u(e){return Object(o["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},"404b":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[a("el-form-item",{attrs:{label:"学校",prop:"notiOperatorId"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.notiContent,callback:function(t){e.$set(e.form,"notiContent",t)},expression:"form.notiContent"}})],1),a("el-form-item",{attrs:{label:"校区",prop:"notiOperatorId"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.notiContent,callback:function(t){e.$set(e.form,"notiContent",t)},expression:"form.notiContent"}})],1),a("el-form-item",{attrs:{label:"展示位置",prop:"notiOperatorId"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.notiContent,callback:function(t){e.$set(e.form,"notiContent",t)},expression:"form.notiContent"}})],1),a("el-form-item",{attrs:{label:"参数条件",prop:"notiOperatorId"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.notiContent,callback:function(t){e.$set(e.form,"notiContent",t)},expression:"form.notiContent"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},l=[],n=a("5530"),i=(a("159b"),a("ad78")),r={name:"EditDialog",mixins:[i["a"]],props:{entity:{type:Object}},components:{},data:function(){return{submitting:!1,form:{notiId:"",notiOperatorId:"",notiOperatorName:"",notiContent:""},selected2:"",rules:{notiOperatorId:[{required:!0,message:"请选择！",trigger:"change"}],notiContent:[{required:!0,message:"不能为空！",trigger:"blur"}]}}},watch:{entity:function(e){e&&e.notiId?(this.form.notiId=e.notiId,this.form.notiOperatorId=e.notiOperatorId,this.form.notiOperatorName=e.notiOperatorName,this.form.notiContent=e.notiContent):this.form={notiOperatorId:"",notiOperatorName:"",notiContent:""}}},computed:{isEdit:function(){return this.entity&&this.entity.notiId},dialogTitle:function(){return this.isEdit?"编辑":"添加"}},methods:{getOperatorName:function(e){var t="";this.operatorList.forEach((function(a){a.operId==e&&(t=a.operName)})),this.form.notiOperatorName=t},submitClick:function(){var e=this;this.$refs.form.validate((function(t){if(t){var a=Object(n["a"])({},e.form);e.submitting=!0,(e.isEdit?updateNotice(a):insertNotice(a)).then((function(t){e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})}))}}))}}},u=r,s=(a("d148"),a("2877")),c=Object(s["a"])(u,o,l,!1,null,"01eec96a",null);t["default"]=c.exports},"6a7e":function(e,t,a){"use strict";a.r(t);var o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"list-view-layout"},[a("json-filter",{attrs:{code:e.titleCode}},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"学校码"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgCodeIsLike,callback:function(t){e.$set(e.param,"orgCodeIsLike",t)},expression:"param.orgCodeIsLike"}})],1),a("el-form-item",{attrs:{label:"学校名称"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgNameIsLike,callback:function(t){e.$set(e.param,"orgNameIsLike",t)},expression:"param.orgNameIsLike"}})],1),a("el-form-item",[a("el-button",{on:{click:function(t){return e.doSearch()}}},[e._v("查询")])],1)],1)],1),a("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"orgLogo",fn:function(e){return[a("el-image",{staticStyle:{width:"100px",height:"100px"},attrs:{src:e.rowData.scope.row.orgLogo,fit:"contain"}})]}},{key:"orgLevel",fn:function(t){return[a("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.orgLevel,e.schoolLevelList)))])]}},{key:"orgType",fn:function(t){return[a("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.orgType,e.schoolTypeList)))])]}},{key:"operations",fn:function(t){return[a("permission-filter",{attrs:{code:"baseSchoolEdit",size:"mini",type:"warning"},nativeOn:{click:function(a){return e.doEdit(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"baseSchoolDelete",size:"mini",type:"danger"},nativeOn:{click:function(a){return e.doDelete(t.rowData.scope.row)}}})]}}])}),a("edit-dialog",{attrs:{entity:e.chooseEntity},on:{change:function(t){return e.handleChange()}},model:{value:e.dialogShow,callback:function(t){e.dialogShow=t},expression:"dialogShow"}}),a("import-dialog",{attrs:{title:"Excel文件导入",url:"/admin/organization/importExcel",formats:["xls","xlsx"]},on:{change:function(t){return e.doSearch()}},model:{value:e.importDialogShow,callback:function(t){e.importDialogShow=t},expression:"importDialogShow"}})],1)},l=[],n=a("5530"),i=(a("159b"),a("4303")),r=a("261a"),u=a("e101"),s=a("404b"),c=a("4f29"),d=a("2209"),f=a("349c"),p={components:{JsonFilter:i["a"],JsonTable:r["a"],PermissionFilter:u["a"],EditDialog:s["default"],ImportDialog:c["a"]},filters:{formatShowName:function(e,t){var a="";return t.forEach((function(t){e==t.value&&(a=t.label)})),a}},data:function(){return{titleCode:"systemScreen",tableHeads:[{prop:"orgCode",label:"学校码"},{prop:"orgName",label:"学校名称",overflowTooltip:"show"},{slot:!0,prop:"orgLogo",label:"LOGO",width:160},{slot:!0,prop:"orgLevel",label:"等级"},{slot:!0,prop:"orgType",label:"类别"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:160}],listLoading:!1,exportLoading:!1,importDialogShow:!1,tableData:[],schoolTypeList:d["p"],schoolLevelList:d["o"],param:{orgCodeIsLike:"",orgNameIsLike:""},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null}},computed:{},created:function(){this.getTableData()},methods:{getTableData:function(){var e=this;this.listLoading=!0,Object(f["c"])(Object(n["a"])(Object(n["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(t){e.listLoading=!1,t.success?(e.tableData=t.root,e.page.total=t.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:t.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},handleChange:function(){this.getTableData()}}},b=p,m=a("2877"),g=Object(m["a"])(b,o,l,!1,null,"a5a91d34",null);t["default"]=g.exports},7729:function(e,t,a){},d148:function(e,t,a){"use strict";a("7729")}}]);