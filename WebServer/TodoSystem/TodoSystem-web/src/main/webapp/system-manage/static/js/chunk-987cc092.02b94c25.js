(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-987cc092"],{"0715":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[a("el-form-item",{attrs:{label:"人员",prop:"aureTranAfterUser"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请输入关键词"},model:{value:e.form.aureTranAfterUser,callback:function(t){e.$set(e.form,"aureTranAfterUser",t)},expression:"form.aureTranAfterUser"}},e._l(e.teacherList,(function(e){return a("el-option",{key:e.userId,attrs:{label:e.userRealName,value:e.userId}})})),1)],1),a("el-form-item",{attrs:{label:"原因",prop:"aureReason"}},[a("el-input",{attrs:{placeholder:"请输入",type:"textarea",rows:4},model:{value:e.form.aureReason,callback:function(t){e.$set(e.form,"aureReason",t)},expression:"form.aureReason"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},i=[],n=a("5530"),o=a("ad78"),s=a("3c68"),l=a("c633"),c={name:"EntrustDialog",mixins:[o["a"]],props:{entity:{type:Object}},components:{},data:function(){return{submitting:!1,form:{aureLinkObjectId:"",aureReason:"",aureTranAfterUser:""},temaOrgId:"",teacherList:[],rules:{aureReason:[{required:!0,message:"不能为空！",trigger:"blur"}],aureTranAfterUser:[{required:!0,message:"请选择！",trigger:"change"}]}}},watch:{entity:function(e){e&&e.temaId?(this.form.aureLinkObjectId=e.temaId,this.form.aureReason="",this.form.aureTranAfterUser="",this.temaOrgId=e.temaOrgId,this.getTeacherList()):this.form={aureLinkObjectId:"",aureReason:"",aureTranAfterUser:""}}},computed:{dialogTitle:function(){return"委托"}},methods:{getTeacherList:function(){var e=this;Object(l["c"])({limit:0,page:1,schoolId:this.temaOrgId}).then((function(t){t.success?e.teacherList=t.root:e.teacherList=[]}))},submitClick:function(){var e=this;this.$refs.form.validate((function(t){if(t){var a=Object(n["a"])({},e.form);e.submitting=!0,Object(s["c"])(a).then((function(t){e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})}))}}))}}},u=c,d=(a("b9a3"),a("2877")),m=Object(d["a"])(u,r,i,!1,null,"8c944c60",null);t["default"]=m.exports},2209:function(e,t,a){"use strict";a.d(t,"o",(function(){return r})),a.d(t,"p",(function(){return i})),a.d(t,"f",(function(){return n})),a.d(t,"m",(function(){return o})),a.d(t,"n",(function(){return s})),a.d(t,"h",(function(){return l})),a.d(t,"g",(function(){return c})),a.d(t,"r",(function(){return u})),a.d(t,"q",(function(){return d})),a.d(t,"e",(function(){return m})),a.d(t,"k",(function(){return f})),a.d(t,"i",(function(){return p})),a.d(t,"c",(function(){return h})),a.d(t,"l",(function(){return b})),a.d(t,"j",(function(){return g})),a.d(t,"b",(function(){return y})),a.d(t,"d",(function(){return v})),a.d(t,"a",(function(){return I}));var r=[{value:"0",label:"小学"},{value:"1",label:"初中"},{value:"2",label:"高中"}],i=[{value:"0",label:"公办"},{value:"1",label:"民办"},{value:"2",label:"行政单位"},{value:"3",label:"平台"}],n=[{value:"1",label:"男生宿舍"},{value:"2",label:"女生宿舍"},{value:"3",label:"楼号"},{value:"4",label:"楼层"}],o=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],s=[{value:"0",label:"单选"},{value:"1",label:"多选"}],l=[{value:"0",label:"临时"},{value:"1",label:"长期"},{value:"2",label:"学期"}],c=[{value:"1",label:"学校"},{value:"2",label:"年级"},{value:"3",label:"班级"},{value:"4",label:"个人"}],u=[{value:"1",label:"通用库"},{value:"2",label:"特色库"},{value:"3",label:"校规"},{value:"4",label:"班规"}],d=[{value:"1",label:"一级"},{value:"2",label:"二级"},{value:"3",label:"三级"}],m=[{value:"1",label:"年"},{value:"2",label:"月"},{value:"3",label:"周"},{value:"4",label:"日"}],f=[{label:"学校",value:1},{label:"年级",value:2},{label:"班级",value:3}],p=[{label:"年级",value:2},{label:"班级",value:3}],h=[{label:"班级",value:3}],b=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"其他教职工",value:17},{label:"心理辅导",value:18},{label:"家长",value:4}],g=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],y=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],v=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],I=[{label:"任课老师",value:14},{label:"家长",value:4}]},3123:function(e,t,a){"use strict";a.d(t,"b",(function(){return i})),a.d(t,"a",(function(){return n}));var r=a("b775");function i(){return Object(r["a"])({url:"/system/getQiNiuUploadToken",method:"post"})}function n(){var e=["http://up.qiniup.com","https://up.qiniup.com"],t={url:e[0]},a=document.location.protocol;return t.url="https:"===a?e[1]:e[0],t}},"349c":function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"b",(function(){return n})),a.d(t,"d",(function(){return o})),a.d(t,"e",(function(){return s})),a.d(t,"a",(function(){return l}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},"3c68":function(e,t,a){"use strict";a.d(t,"f",(function(){return i})),a.d(t,"h",(function(){return n})),a.d(t,"j",(function(){return o})),a.d(t,"b",(function(){return s})),a.d(t,"d",(function(){return l})),a.d(t,"i",(function(){return c})),a.d(t,"c",(function(){return u})),a.d(t,"g",(function(){return d})),a.d(t,"e",(function(){return m})),a.d(t,"a",(function(){return f}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/teachManage/selectByQuery",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/teachManage/add",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/teachManage/updateById",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/teachManage/delete",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/teachManage/exportExcel",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/admin/teachManage/publish",method:"post",data:e})}function u(e){return Object(r["a"])({url:"/admin/teachManage/entrust",method:"post",data:e})}function d(e){return Object(r["a"])({url:"/admin/teachManage/execute",method:"post",data:e})}function m(e){return Object(r["a"])({url:"/admin/auditRecord/selectByExample",method:"post",data:e})}function f(e){return Object(r["a"])({url:"/admin/teachManage/selectTeachAuditRecord",method:"post",data:e})}},"40a0":function(e,t,a){},"47a6":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"list-view-layout"},[a("json-filter",{attrs:{code:e.titleCode}},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"学校码"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaOrgCodeIsLike,callback:function(t){e.$set(e.param,"temaOrgCodeIsLike",t)},expression:"param.temaOrgCodeIsLike"}})],1),a("el-form-item",{attrs:{label:"学校名称"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaOrgNameIsLike,callback:function(t){e.$set(e.param,"temaOrgNameIsLike",t)},expression:"param.temaOrgNameIsLike"}})],1),a("el-form-item",{attrs:{label:"性质"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaTypeIsLike,callback:function(t){e.$set(e.param,"temaTypeIsLike",t)},expression:"param.temaTypeIsLike"}})],1),a("el-form-item",{attrs:{label:"标题"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaTitleIsLike,callback:function(t){e.$set(e.param,"temaTitleIsLike",t)},expression:"param.temaTitleIsLike"}})],1),a("el-form-item",{attrs:{label:"责任岗位"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaRoleNameIsLike,callback:function(t){e.$set(e.param,"temaRoleNameIsLike",t)},expression:"param.temaRoleNameIsLike"}})],1),a("el-form-item",{attrs:{label:"教育范围"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.temaRangeIsLike,callback:function(t){e.$set(e.param,"temaRangeIsLike",t)},expression:"param.temaRangeIsLike"}})],1),a("el-form-item",[a("el-button",{on:{click:function(t){return e.doSearch()}}},[e._v("查询")]),a("permission-filter",{attrs:{code:"educationPlanAdd",type:"primary"},nativeOn:{click:function(t){return e.doAdd()}}}),a("permission-filter",{attrs:{code:"educationPlanImport",type:"primary"},nativeOn:{click:function(t){return e.doImport()}}}),a("permission-filter",{attrs:{code:"educationPlanExport",loading:e.exportLoading,disabled:e.exportLoading},nativeOn:{click:function(t){return e.doExport()}}})],1)],1)],1),a("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"temaLinkItem",fn:function(t){return[a("el-popover",{attrs:{placement:"right",width:"200",trigger:"click"}},[a("el-table",{attrs:{data:t.rowData.scope.row.teachWarningManages,border:""}},[a("el-table-column",{attrs:{property:"tewaLinkItemTitle","show-overflow-tooltip":"",label:"行为概述",align:"center"}})],1),a("el-button",{attrs:{slot:"reference",size:"medium",type:"text"},slot:"reference"},[e._v("查看")])],1)]}},{key:"temaCloseTime",fn:function(t){return[a("span",[e._v(" "+e._s(e._f("formatDate")(t.rowData.scope.row.temaCloseTime,"yyyy-MM-dd hh:mm:ss"))+" ")])]}},{key:"temaType",fn:function(t){return[a("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.temaType,e.educationTypeList)))])]}},{key:"temaRange",fn:function(t){return[a("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.temaRange,e.educationRangeList)))])]}},{key:"operations",fn:function(t){return[a("permission-filter",{attrs:{code:"educationPlanImplement",size:"mini",type:"success"},nativeOn:{click:function(a){return e.doExecute(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"educationPlanIssue",size:"mini",type:"primary"},nativeOn:{click:function(a){return e.doIssue(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"educationPlanEntrust",size:"mini",type:"warning"},nativeOn:{click:function(a){return e.doEntrust(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"educationPlanEdit",size:"mini",type:"warning"},nativeOn:{click:function(a){return e.doEdit(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"educationPlanDelete",size:"mini",type:"danger"},nativeOn:{click:function(a){return e.doDelete(t.rowData.scope.row)}}})]}}])}),a("edit-dialog",{attrs:{entity:e.chooseEntity,"school-list":e.schoolList,"education-type-list":e.educationTypeList,"education-range-list":e.educationRangeList,"role-list":e.roleList},on:{change:function(t){return e.handleChange()}},model:{value:e.dialogShow,callback:function(t){e.dialogShow=t},expression:"dialogShow"}}),a("import-dialog",{attrs:{title:"Excel文件导入",url:"/admin/teachManage/importExcel",formats:["xls","xlsx"]},on:{change:function(t){return e.doSearch()}},model:{value:e.importDialogShow,callback:function(t){e.importDialogShow=t},expression:"importDialogShow"}}),a("execute-dialog",{attrs:{entity:e.chooseExecuteEntity},on:{change:function(t){return e.doSearch()}},model:{value:e.executeDialogShow,callback:function(t){e.executeDialogShow=t},expression:"executeDialogShow"}}),a("entrust-dialog",{attrs:{entity:e.chooseEntrustEntity},on:{change:function(t){return e.doSearch()}},model:{value:e.entrustDialogShow,callback:function(t){e.entrustDialogShow=t},expression:"entrustDialogShow"}})],1)},i=[],n=a("5530"),o=(a("159b"),a("4303")),s=a("261a"),l=a("e101"),c=a("570d"),u=a("3c68"),d=a("349c"),m=a("4f29"),f=a("2209"),p=a("f1b9"),h=a("b32c"),b=a("0715"),g={components:{JsonFilter:o["a"],JsonTable:s["a"],PermissionFilter:l["a"],EditDialog:c["default"],ImportDialog:m["a"],ExecuteDialog:h["default"],EntrustDialog:b["default"]},filters:{formatShowName:function(e,t){var a="";return t.forEach((function(t){e==t.value&&(a=t.label)})),a}},data:function(){return{titleCode:"educationPlan",tableHeads:[{prop:"orgCode",label:"学校码"},{prop:"orgName",label:"学校名称"},{slot:!0,prop:"temaType",label:"性质"},{prop:"temaTitle",label:"标题"},{slot:!0,prop:"temaCloseTime",label:"执行截止时间"},{slot:!0,prop:"temaLinkItem",label:"关联条目",width:100},{prop:"roleName",label:"责任岗位"},{prop:"temaResponsibeName",label:"责任人"},{slot:!0,prop:"temaRange",label:"教育管理范围(目标学生)"},{prop:"temaCycle",label:"教育管理周期"},{prop:"temaTimes",label:"频次"},{overflowTooltip:"show",prop:"temaDesc",label:"备注"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:180}],listLoading:!1,exportLoading:!1,importDialogShow:!1,executeDialogShow:!1,tableData:[],schoolList:[],warningDatabaseList:[],roleList:[],educationTypeList:f["h"],educationRangeList:f["g"],param:{temaTitleIsLike:"",temaTypeIsLike:"",temaOrgCodeIsLike:"",temaOrgNameIsLike:"",temaRangeIsLike:"",temaRoleNameIsLike:""},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null,chooseExecuteEntity:null,entrustDialogShow:!1,chooseEntrustEntity:null}},computed:{},created:function(){this.getTableData(),this.getSchoolList(),this.getRoleList()},methods:{getRoleList:function(){var e=this;Object(p["d"])({page:1,limit:0,"param.roleType":"3"}).then((function(t){t.success?e.roleList=t.root:e.roleList=[]}))},getSchoolList:function(){var e=this;Object(d["c"])({limit:0,page:1}).then((function(t){t.success?e.schoolList=t.root:e.schoolList=[]}))},getTableData:function(){var e=this;this.listLoading=!0,Object(u["f"])(Object(n["a"])(Object(n["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(t){e.listLoading=!1,t.success?(e.tableData=t.root,e.page.total=t.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:t.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},doDelete:function(e){var t=this;this.$confirm("确定删除？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",beforeClose:function(a,r,i){"confirm"===a?Object(u["b"])({temaId:e.temaId}).then((function(e){e.success?(t.$notify({title:"成功",message:"操作成功!",type:"success"}),t.getTableData()):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"}),i()})).catch((function(e){t.$notify.error({title:"失败",message:"请求发生错误， 请重试"}),i()})):i()}})},handleChange:function(){this.getTableData()},doAdd:function(){this.chooseEntity=null===this.chooseEntity?{}:null,this.dialogShow=!0},doEdit:function(e){this.chooseEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.dialogShow=!0},doExport:function(){var e=this;this.exportLoading||(this.exportLoading=!0,Object(u["d"])(Object(n["a"])({},this.param)).then((function(t){if(e.exportLoading=!1,t.success){var a=e.BASEFILEURL;window.open(a+t.tip)}else e.$notify({title:"提示",message:t.failReasonShow||"Excel 导出错误！",type:"warning"})})))},doImport:function(){this.importDialogShow=!0},doIssue:function(e){var t=this;this.$confirm("确定下发？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",beforeClose:function(a,r,i){"confirm"===a?Object(u["i"])({temaId:e.temaId}).then((function(e){e.success?(t.$notify({title:"成功",message:"操作成功!",type:"success"}),t.getTableData()):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"}),i()})).catch((function(e){t.$notify.error({title:"失败",message:"请求发生错误， 请重试"}),i()})):i()}})},doExecute:function(e){this.chooseExecuteEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.executeDialogShow=!0},doEntrust:function(e){this.chooseEntrustEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.entrustDialogShow=!0}}},y=g,v=a("2877"),I=Object(v["a"])(y,r,i,!1,null,"02845116",null);t["default"]=I.exports},"53e1":function(e,t,a){"use strict";a("e924")},"570d":function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"50%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t},close:e.dialogClose}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[a("el-form-item",{attrs:{label:"主题内容",prop:"temaTitle"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.temaTitle,callback:function(t){e.$set(e.form,"temaTitle",t)},expression:"form.temaTitle"}})],1),a("el-form-item",{attrs:{label:"性质",prop:"temaType"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.form.temaType,callback:function(t){e.$set(e.form,"temaType",t)},expression:"form.temaType"}},e._l(e.educationTypeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),a("el-form-item",{attrs:{label:"学校",prop:"temaOrgId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请输入关键词"},on:{change:e.getAllData},model:{value:e.form.temaOrgId,callback:function(t){e.$set(e.form,"temaOrgId",t)},expression:"form.temaOrgId"}},e._l(e.schoolList,(function(e){return a("el-option",{key:e.orgId,attrs:{label:e.orgName,value:e.orgId}})})),1)],1),a("el-form-item",{attrs:{label:"教育管理范围",prop:"temaRange"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},on:{change:e.changeRange},model:{value:e.form.temaRange,callback:function(t){e.$set(e.form,"temaRange",t)},expression:"form.temaRange"}},e._l(e.educationRangeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),2==e.form.temaRange?a("el-form-item",{attrs:{label:"年级",prop:"temaGradeIds"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请先选择学校后并输入关键词",disabled:!e.form.temaOrgId},model:{value:e.form.temaGradeIds,callback:function(t){e.$set(e.form,"temaGradeIds",t)},expression:"form.temaGradeIds"}},e._l(e.gradeList,(function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1):e._e(),3==e.form.temaRange?a("el-form-item",{attrs:{label:"班级",prop:"temaClassIds"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请先选择学校后并输入关键词",disabled:!e.form.temaOrgId},on:{change:function(t){e.entryIds=[]}},model:{value:e.form.temaClassIds,callback:function(t){e.$set(e.form,"temaClassIds",t)},expression:"form.temaClassIds"}},e._l(e.classList,(function(e){return a("el-option",{key:e.scclId,attrs:{label:e.scclName,value:e.scclId}})})),1)],1):e._e(),4==e.form.temaRange?a("el-form-item",{attrs:{label:"个人"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{multiple:"",placeholder:"请先选择学校后并输入关键词",filterable:"","collapse-tags":"",disabled:!e.form.temaOrgId},model:{value:e.studentIds,callback:function(t){e.studentIds=t},expression:"studentIds"}},e._l(e.studentList,(function(e){return a("el-option",{key:e.studentId,attrs:{label:e.studentName,value:e.studentId}})})),1)],1):e._e(),a("el-form-item",{attrs:{label:"关联条目类型"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请先选择教育管理范围",disabled:!e.form.temaRange},on:{change:e.changeEntryType},model:{value:e.form.temaWadbType,callback:function(t){e.$set(e.form,"temaWadbType",t)},expression:"form.temaWadbType"}},e._l(e.warningDatabaseTypeList,(function(t){return a("el-option",{key:t.value,attrs:{label:t.label,value:t.value,disabled:4==t.value&&(1==e.form.temaRange||2==e.form.temaRange||4==e.form.temaRange)}})})),1)],1),a("el-form-item",{attrs:{label:"关联条目"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{multiple:"",placeholder:"请先选择关联条目类型",disabled:!e.form.temaWadbType,"collapse-tags":""},model:{value:e.entryIds,callback:function(t){e.entryIds=t},expression:"entryIds"}},e._l(e.warningDatabaseList,(function(t){return a("el-option",{key:t.wadbId,attrs:{label:t.wadbNumber,value:t.wadbId}},[a("span",{staticStyle:{float:"left"}},[e._v(e._s(t.wadbSummary))]),a("span",{staticStyle:{float:"right",color:"#8492a6","font-size":"13px","margin-right":"15px"}},[e._v(e._s(t.wadbNumber))])])})),1)],1),a("el-form-item",{attrs:{label:"责任人",prop:"temaResponsibeId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请先选择学校后并输入关键词",disabled:!e.form.temaOrgId},model:{value:e.form.temaResponsibeId,callback:function(t){e.$set(e.form,"temaResponsibeId",t)},expression:"form.temaResponsibeId"}},e._l(e.teacherList,(function(e){return a("el-option",{key:e.userId,attrs:{label:e.userRealName,value:e.userId}})})),1)],1),a("el-form-item",{attrs:{label:"教育管理周期",prop:"temaCycle"}},[a("el-input-number",{attrs:{min:1},model:{value:e.form.temaCycle,callback:function(t){e.$set(e.form,"temaCycle",t)},expression:"form.temaCycle"}})],1),a("el-form-item",{attrs:{label:"频次",prop:"temaTimes"}},[a("el-input-number",{attrs:{min:1},model:{value:e.form.temaTimes,callback:function(t){e.$set(e.form,"temaTimes",t)},expression:"form.temaTimes"}})],1),a("el-form-item",{attrs:{label:"执行时间",prop:"temaExecuteTime"}},[a("el-date-picker",{attrs:{type:"datetime","value-format":"yyyy-MM-dd hh:mm:ss",placeholder:"选择日期时间"},model:{value:e.form.temaExecuteTime,callback:function(t){e.$set(e.form,"temaExecuteTime",t)},expression:"form.temaExecuteTime"}})],1),a("el-form-item",{attrs:{label:"结束时间",prop:"temaCloseTime"}},[a("el-date-picker",{attrs:{type:"datetime","value-format":"yyyy-MM-dd hh:mm:ss",placeholder:"选择日期时间"},model:{value:e.form.temaCloseTime,callback:function(t){e.$set(e.form,"temaCloseTime",t)},expression:"form.temaCloseTime"}})],1),a("el-form-item",{attrs:{label:"备注",prop:"temaDesc"}},[a("el-input",{attrs:{placeholder:"请输入",type:"textarea",rows:2},model:{value:e.form.temaDesc,callback:function(t){e.$set(e.form,"temaDesc",t)},expression:"form.temaDesc"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},i=[],n=a("5530"),o=a("2909"),s=(a("159b"),a("d3b7"),a("6062"),a("3ca3"),a("ddb0"),a("a15b"),a("ad78")),l=a("3c68"),c=a("8be8"),u=a("f2dd"),d=a("c633"),m=a("ed08"),f=a("2209"),p=a("dff1"),h={name:"EditDialog",mixins:[s["a"]],props:{entity:{type:Object},schoolList:{type:Array},educationTypeList:{type:Array},educationRangeList:{type:Array},roleList:{type:Array}},components:{},data:function(){return{submitting:!1,form:{temaId:"",temaCode:"",temaType:"",temaTitle:"",temaLinkItemIds:"",temaRange:"",temaOrgId:"",temaGradeIds:"",temaClassIds:"",temaStudentIds:"",temaResponsibeId:"",temaDesc:"",temaExecuteTime:"",temaCloseTime:"",temaTimes:"",temaCycle:"",temaWadbType:""},entryIds:[],studentIds:[],classIds:[],yearIds:[],studentList:[],classList:[],gradeList:[],teacherList:[],warningDatabaseList:[],warningDatabaseTypeList:f["r"],rules:{temaOrgId:[{required:!0,message:"请选择！",trigger:"change"}],temaGradeIds:[{required:!0,message:"请选择！",trigger:"change"}],temaClassIds:[{required:!0,message:"请选择！",trigger:"change"}],temaResponsibeId:[{required:!0,message:"请选择！",trigger:"change"}],temaRange:[{required:!0,message:"请选择！",trigger:"change"}],temaType:[{required:!0,message:"请选择！",trigger:"change"}],temaTitle:[{required:!0,message:"不能为空！",trigger:"blur"}],temaExecuteTime:[{required:!0,message:"请选择！",trigger:"change"}],temaCloseTime:[{required:!0,message:"请选择！",trigger:"change"}],temaWadbType:[{required:!0,message:"请选择！",trigger:"change"}]}}},watch:{entity:function(e){if(e&&e.temaId){if(this.form.temaId=e.temaId,this.form.temaWadbType=e.temaWadbType?String(e.temaWadbType):"",this.form.temaCode=e.temaCode,this.form.temaType=e.temaType+"",this.form.temaTitle=e.temaTitle,this.form.temaLinkItemIds=e.temaLinkItemIds,this.form.temaRange=e.temaRange+"",this.form.temaOrgId=e.temaOrgId,this.form.temaGradeIds=e.temaGradeIds,this.form.temaClassIds=e.temaClassIds,this.form.temaStudentIds=e.temaStudentIds,this.form.temaResponsibeId=e.temaResponsibeId,this.form.temaDesc=e.temaDesc,this.form.temaExecuteTime=e.temaExecuteTime?Object(m["d"])(e.temaExecuteTime,"yyyy-MM-dd hh:mm:ss"):"",this.form.temaCloseTime=e.temaCloseTime?Object(m["d"])(e.temaCloseTime,"yyyy-MM-dd hh:mm:ss"):"",this.form.temaTimes=e.temaTimes,this.form.temaCycle=e.temaCycle,e.teachWarningManages&&Array.isArray(e.teachWarningManages)){var t=[];e.teachWarningManages.forEach((function(e){t.push(e.tewaLinkItemId)})),this.entryIds=t}if(e.teachStudentManages&&Array.isArray(e.teachStudentManages)){var a=[];e.teachStudentManages.forEach((function(e){a.push(e.testStudentId)})),this.studentIds=a}this.getClassList(),this.getStudentList(),this.getTeacherList(),this.getWarningDatabaseList()}else this.form={temaCode:"",temaType:"",temaTitle:"",temaLinkItemIds:"",temaRange:"",temaOrgId:"",temaGradeIds:"",temaClassIds:"",temaStudentIds:"",temaResponsibeId:"",temaDesc:"",temaExecuteTime:"",temaCloseTime:"",temaTimes:"",temaCycle:"",temaWadbType:""}},warningSearchData:function(){this.getWarningDatabaseList()}},computed:{isEdit:function(){return this.entity&&this.entity.temaId},dialogTitle:function(){return this.isEdit?"编辑":"添加"},warningSearchData:function(){return{temaOrgId:this.form.temaOrgId,temaWadbType:this.form.temaWadbType,temaClassIds:this.form.temaClassIds}}},methods:{changeRange:function(){this.form.temaClassIds="",this.form.temaGradeIds="",this.studentIds=[],this.entryIds=[]},changeEntryType:function(){this.warningDatabaseList=[],this.entryIds=[]},getWarningDatabaseList:function(){var e=this;this.warningSearchData.temaWadbType&&Object(p["c"])({page:1,limit:0,wadbType:this.warningSearchData.temaWadbType,schoolId:this.warningSearchData.temaOrgId,wadbClassId:4==this.warningSearchData.temaWadbType?this.warningSearchData.temaClassIds:""}).then((function(t){t.success?e.warningDatabaseList=t.root:e.warningDatabaseList=[]}))},getAllData:function(){this.entryIds=[],this.getClassList(),this.getStudentList(),this.getTeacherList()},getStudentList:function(){var e=this;Object(u["b"])({page:1,limit:0,"param.studentOrgId":this.form.temaOrgId}).then((function(t){t.success?e.studentList=t.root:e.studentList=[]}))},getClassList:function(){var e=this;Object(c["c"])({page:1,limit:0,"param.scclOrgId":this.form.temaOrgId}).then((function(t){if(t.success){e.classList=t.root;var a=[];t.root.forEach((function(e){a.unshift(e.scclYear)})),a=Object(o["a"])(new Set(a));var r=[];a.forEach((function(e){r.push({label:e+"级",value:e})})),e.gradeList=r}else e.classList=[]}))},getTeacherList:function(){var e=this;Object(d["c"])({page:1,limit:0,schoolId:this.temaOrgId}).then((function(t){t.success?e.teacherList=t.root:e.teacherList=[]}))},submitClick:function(){var e=this;4!=this.form.temaRange||0!==this.studentIds.length?this.$refs.form.validate((function(t){if(t){var a=Object(n["a"])({},e.form);a.temaLinkItemIds=e.entryIds.join(","),a.temaStudentIds=e.studentIds.join(","),e.submitting=!0,(e.isEdit?Object(l["j"])(a):Object(l["h"])(a)).then((function(t){e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})}))}})):this.$message.warning("至少选择一位学生")},dialogClose:function(){this.$refs["form"].resetFields(),this.entryIds=[]}}},b=h,g=(a("53e1"),a("2877")),y=Object(g["a"])(b,r,i,!1,null,"f6e2116c",null);t["default"]=y.exports},"681d":function(e,t,a){"use strict";a("40a0")},"8be8":function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"b",(function(){return n})),a.d(t,"d",(function(){return o})),a.d(t,"f",(function(){return s})),a.d(t,"a",(function(){return l})),a.d(t,"e",(function(){return c}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/schoolClass/selectByQuery",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/schoolClass/exportExcel",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/schoolClass/addSchoolClass",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/schoolClass/updateSchoolClass",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/schoolClass/deleteSchoolClass",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/admin/schoolClass/selectClassByUserId",method:"post",data:e})}},b32c:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[a("el-form-item",{attrs:{label:"时间",prop:"tereCreateTime"}},[a("el-date-picker",{staticStyle:{width:"100%"},attrs:{type:"datetime","value-format":"yyyy-MM-dd hh:mm:ss",placeholder:"选择日期时间"},model:{value:e.form.tereCreateTime,callback:function(t){e.$set(e.form,"tereCreateTime",t)},expression:"form.tereCreateTime"}})],1),a("el-form-item",{attrs:{label:"地点",prop:"tereAddress"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.tereAddress,callback:function(t){e.$set(e.form,"tereAddress",t)},expression:"form.tereAddress"}})],1),a("el-form-item",{attrs:{label:"详情概述",prop:"tereDesc"}},[a("el-input",{attrs:{placeholder:"请输入",type:"textarea",rows:4},model:{value:e.form.tereDesc,callback:function(t){e.$set(e.form,"tereDesc",t)},expression:"form.tereDesc"}})],1),a("el-form-item",{attrs:{label:"图片"}},[a("el-upload",{attrs:{action:e.actionUrl,data:e.postData,"list-type":"picture-card","on-preview":e.handlePictureCardPreview,"on-remove":e.handleRemove,"on-success":e.handleAvatarSuccess,"on-error":e.handleError,"before-upload":e.beforeAvatarUpload}},[a("i",{staticClass:"el-icon-plus"})]),a("el-dialog",{attrs:{visible:e.dialogImageVisible,"append-to-body":""},on:{"update:visible":function(t){e.dialogImageVisible=t}}},[a("img",{attrs:{width:"100%",src:e.dialogImageUrl,alt:""}})])],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},i=[],n=a("5530"),o=(a("159b"),a("a15b"),a("b0c0"),a("a434"),a("ad78")),s=a("3c68"),l=a("3123"),c=a("ed08"),u={name:"ExecuteDialog",components:{},mixins:[o["a"]],props:{entity:{type:Object}},data:function(){return{submitting:!1,form:{temaId:"",tereCreateTime:"",tereAddress:"",tereDesc:"",images:""},postData:{token:"",key:""},domain:"",actionUrl:"",imageUrl:"",dialogImageUrl:"",dialogImageVisible:!1,fileList:[],rules:{tereCreateTime:[{required:!0,message:"请选择！",trigger:"change"}],tereAddress:[{required:!0,message:"不能为空！",trigger:"blur"}],tereDesc:[{required:!0,message:"不能为空！",trigger:"blur"}]}}},computed:{isEdit:function(){return this.entity&&this.entity.temaId},dialogTitle:function(){return this.isVisible&&(this.getHttp(),this.getQiniuConfig()),"执行"}},watch:{entity:function(e){this.imageUrl="",this.selectedAdddress=[],e&&e.temaId?(this.form.temaId=e.temaId,this.form.tereCreateTime=e.tereCreateTime?Object(c["d"])(e.quesPublicTime,"yyyy-MM-dd hh:mm:ss"):"",this.form.tereAddress=e.tereAddress,this.form.tereDesc=e.tereDesc):this.form={tereCreateTime:"",tereAddress:"",tereDesc:"",images:""}}},methods:{getHttp:function(){var e=Object(l["a"])();this.actionUrl=e.url},getQiniuConfig:function(){var e=this;Object(l["b"])().then((function(t){t.success?(e.postData.token=t.tip,t.root.forEach((function(t){"qny_domain"===t.scvaCode&&(e.domain=t.scvaValue||"")}))):e.$notify({title:"提示",message:"上传参数失败！",type:"warning"})}))},submitClick:function(){var e=this;this.$refs.form.validate((function(t){if(t){e.submitting=!0;var a=Object(n["a"])({},e.form),r=[];e.fileList.forEach((function(e){r.push(e.url)})),a.images=r.join(","),Object(s["g"])(a).then((function(t){e.selectedAdddress=[],e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})})).catch((function(t){e.selectedAdddress=[],e.submitting=!1,e.$notify.error({title:"失败",message:result.failReasonShow||"操作失败"})}))}}))},handleAvatarSuccess:function(e,t,a){this.fileList.push({uid:t.uid,url:this.domain+"/"+e.key})},handleError:function(e){this.LOG.info(["err --- ",e])},beforeAvatarUpload:function(e){this.postData.key=(new Date).getTime()+e.name;var t="image/jpeg"===e.type,a="image/png"===e.type,r=e.size/1024/1024<5;return t||a||this.$message.error("上传图片只能是 JPG/PNG 格式!"),r||this.$message.error("上传图片大小不能超过 5MB!"),(t||a)&&r},handleRemove:function(e,t){var a=-1;this.fileList.forEach((function(t,r){e.uid===t.uid&&(a=r)})),-1!==a&&this.fileList.splice(a,1)},handlePictureCardPreview:function(e){this.dialogImageUrl=e.url,this.dialogImageVisible=!0}}},d=u,m=(a("681d"),a("2877")),f=Object(m["a"])(d,r,i,!1,null,"0752c0ff",null);t["default"]=f.exports},b9a3:function(e,t,a){"use strict";a("fa9b")},c633:function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"b",(function(){return n})),a.d(t,"f",(function(){return o})),a.d(t,"e",(function(){return s})),a.d(t,"a",(function(){return l})),a.d(t,"d",(function(){return c}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/teacherStaff/getTeacherStaff",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/teacherStaff/exportExcel",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/teacherStaff/updateTeacherStaff",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/teacherStaff/addTeacherStaff",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/teacherStaff/adjustPosition",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/admin/teacherStaff/getTeacherStaffRole",method:"post",data:e})}},dff1:function(e,t,a){"use strict";a.d(t,"c",(function(){return i})),a.d(t,"d",(function(){return n})),a.d(t,"e",(function(){return o})),a.d(t,"a",(function(){return s})),a.d(t,"b",(function(){return l}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/warningDatabase/getData",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/warningDatabase/add",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/warningDatabase/update",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/warningDatabase/delete",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/warningDatabase/export",method:"post",data:e})}},e924:function(e,t,a){},f1b9:function(e,t,a){"use strict";a.d(t,"d",(function(){return i})),a.d(t,"e",(function(){return n})),a.d(t,"h",(function(){return o})),a.d(t,"a",(function(){return s})),a.d(t,"g",(function(){return l})),a.d(t,"c",(function(){return c})),a.d(t,"f",(function(){return u})),a.d(t,"b",(function(){return d}));var r=a("b775");function i(e){return Object(r["a"])({url:"admin/role/selectByExample",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/role/add",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/role/update",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/role/delete",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/permission/settingPermissionToRole",method:"post",data:e})}function c(e){return Object(r["a"])({url:"/admin/permission/selectByExample",method:"post",data:e})}function u(e){return Object(r["a"])({url:"/admin/miniRolePermission/setting",method:"post",data:e})}function d(e){return Object(r["a"])({url:"/admin/miniRolePermission/selectByExample",method:"post",data:e})}},f2dd:function(e,t,a){"use strict";a.d(t,"b",(function(){return i})),a.d(t,"a",(function(){return n})),a.d(t,"e",(function(){return o})),a.d(t,"d",(function(){return s})),a.d(t,"c",(function(){return l}));var r=a("b775");function i(e){return Object(r["a"])({url:"/admin/student/getStudentInfo",method:"post",data:e})}function n(e){return Object(r["a"])({url:"/admin/student/exportExcel",method:"post",data:e})}function o(e){return Object(r["a"])({url:"/admin/student/updateStudent",method:"post",data:e})}function s(e){return Object(r["a"])({url:"/admin/student/relatedParent",method:"post",data:e})}function l(e){return Object(r["a"])({url:"/admin/student/addStudent",method:"post",data:e})}},fa9b:function(e,t,a){}}]);