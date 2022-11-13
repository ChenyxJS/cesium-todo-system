(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-58a2a7f0"],{1166:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t}}},[a("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[a("el-form-item",{attrs:{label:"学校",prop:"scclOrgId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请输入关键词"},on:{change:e.changeSchool},model:{value:e.form.scclOrgId,callback:function(t){e.$set(e.form,"scclOrgId",t)},expression:"form.scclOrgId"}},e._l(e.schoolList,(function(e){return a("el-option",{key:e.orgId,attrs:{label:e.orgName,value:e.orgId}})})),1)],1),a("el-form-item",{attrs:{label:"等级",prop:"scclLevel"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.scclLevel,callback:function(t){e.$set(e.form,"scclLevel",t)},expression:"form.scclLevel"}})],1),a("el-form-item",{attrs:{label:"年级",prop:"scclYear"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.scclYear,callback:function(t){e.$set(e.form,"scclYear",t)},expression:"form.scclYear"}})],1),a("el-form-item",{attrs:{label:"班级名称",prop:"scclName"}},[a("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.scclName,callback:function(t){e.$set(e.form,"scclName",t)},expression:"form.scclName"}})],1),a("el-form-item",{attrs:{label:"班主任",prop:"scclTeacherId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{filterable:"",placeholder:"请输入关键词",loading:e.loading},on:{change:e.getTeacherMessage},model:{value:e.form.scclTeacherId,callback:function(t){e.$set(e.form,"scclTeacherId",t)},expression:"form.scclTeacherId"}},e._l(e.teacherList,(function(e){return a("el-option",{key:e.userId,attrs:{label:e.userRealName,value:e.userId}})})),1)],1),a("el-form-item",{attrs:{label:"班主任手机号",prop:"scclTeacherPhone"}},[a("el-input",{attrs:{disabled:""},model:{value:e.form.scclTeacherPhone,callback:function(t){e.$set(e.form,"scclTeacherPhone",t)},expression:"form.scclTeacherPhone"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),a("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},r=[],c=a("5530"),o=(a("159b"),a("ad78")),n=a("8be8"),s=a("c633"),i=a("2209"),u=a("ed08"),d={name:"EditDialog",mixins:[o["a"]],props:{entity:{type:Object},schoolList:{type:Array}},components:{},data:function(){return{submitting:!1,loading:!1,form:{scclId:"",scclOrgId:"",scclCode:"",scclName:"",scclYear:"",scclTeacherId:"",scclTeacherPhone:"",scclLevel:""},teacherList:[],schoolLevelList:i["r"],rules:{scclOrgId:[{required:!0,message:"请选择！",trigger:"change"}],scclTeacherId:[{required:!0,message:"请选择！",trigger:"change"}],scclName:[{required:!0,message:"不能为空！",trigger:"blur"}],scclYear:[{required:!0,message:"不能为空！",trigger:"blur"}],scclTeacherPhone:[{required:!1,message:"不能为空！",trigger:"blur"}],scclLevel:[{required:!0,message:"不能为空！",trigger:"blur"}]}}},watch:{entity:function(e){e&&e.scclId?(this.form.scclId=e.scclId,this.form.scclOrgId=e.scclOrgId,this.form.scclCode=e.scclCode,this.form.scclName=e.scclName,this.form.scclYear=e.scclYear,this.form.scclTeacherId=e.scclTeacherId,this.form.scclTeacherPhone=e.scclTeacherPhone,this.form.scclLevel=e.scclLevel,this.getTeacherList()):this.form={scclOrgId:"",scclCode:"",scclName:"",scclYear:"",scclTeacherId:"",scclTeacherPhone:"",scclLevel:""}}},computed:{isEdit:function(){return this.entity&&this.entity.scclId},dialogTitle:function(){return this.isEdit?"编辑":"添加"}},methods:{getTeacherList:function(e){var t=this;this.form.scclOrgId?(this.loading=!0,Object(s["e"])({page:1,limit:0,schoolId:this.form.scclOrgId,"param.userPosition":"班主任"}).then((function(e){if(t.loading=!1,e.success){var a=Object(u["h"])(e.root,"userId");t.teacherList=a}else t.teacherList=[]})).catch((function(e){t.loading=!1}))):this.$message.warning("请先选择学校!")},getTeacherMessage:function(e){var t=this;this.teacherList.forEach((function(a){e==a.userId&&(t.form.scclTeacherPhone=a.userPhone)}))},changeSchool:function(e){this.getOrgMessage(e),this.getTeacherList()},getOrgMessage:function(e){for(var t=this.schoolList,a=0;a<t.length;a++)if(t[a].orgId==e)for(var l=0;l<this.schoolLevelList.length;l++)t[a].orgLevel==this.schoolLevelList[l].value&&(this.form.scclLevel=this.schoolLevelList[l].label)},submitClick:function(){var e=this;this.$refs.form.validate((function(t){if(t){var a=Object(c["a"])({},e.form);e.submitting=!0,(e.isEdit?Object(n["h"])(a):Object(n["f"])(a)).then((function(t){e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})}))}}))}}},f=d,h=a("2877"),m=Object(h["a"])(f,l,r,!1,null,"282d9e5a",null);t["default"]=m.exports},2145:function(e,t,a){},2209:function(e,t,a){"use strict";a.d(t,"r",(function(){return l})),a.d(t,"s",(function(){return r})),a.d(t,"m",(function(){return c})),a.d(t,"f",(function(){return o})),a.d(t,"o",(function(){return n})),a.d(t,"p",(function(){return s})),a.d(t,"i",(function(){return i})),a.d(t,"h",(function(){return u})),a.d(t,"u",(function(){return d})),a.d(t,"t",(function(){return f})),a.d(t,"e",(function(){return h})),a.d(t,"l",(function(){return m})),a.d(t,"j",(function(){return b})),a.d(t,"c",(function(){return p})),a.d(t,"n",(function(){return g})),a.d(t,"k",(function(){return v})),a.d(t,"b",(function(){return L})),a.d(t,"d",(function(){return O})),a.d(t,"a",(function(){return I})),a.d(t,"q",(function(){return k})),a.d(t,"g",(function(){return y}));var l=[{value:"0",label:"小学"},{value:"1",label:"初中"},{value:"2",label:"高中"}],r=[{value:"0",label:"公办"},{value:"1",label:"民办"}],c=[{value:"2",label:"行政单位"},{value:"3",label:"平台"},{value:"4",label:"律师事务所"}],o=[{value:"1",label:"男生宿舍"},{value:"2",label:"女生宿舍"},{value:"3",label:"楼号"},{value:"4",label:"楼层"}],n=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],s=[{value:"0",label:"单选"},{value:"1",label:"多选"}],i=[{value:"0",label:"临时"},{value:"1",label:"长期"},{value:"2",label:"学期"}],u=[{value:"1",label:"学校"},{value:"2",label:"年级"},{value:"3",label:"班级"},{value:"4",label:"个人"}],d=[{value:"1",label:"通用库"},{value:"2",label:"特色库"},{value:"3",label:"校规"},{value:"4",label:"班规"}],f=[{value:"1",label:"一级"},{value:"2",label:"二级"},{value:"3",label:"三级"}],h=[{value:"1",label:"年"},{value:"2",label:"月"},{value:"3",label:"周"},{value:"4",label:"日"}],m=[{label:"学校",value:1},{label:"年级",value:2},{label:"班级",value:3}],b=[{label:"年级",value:2},{label:"班级",value:3}],p=[{label:"班级",value:3}],g=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"其他教职工",value:17},{label:"心理辅导",value:18},{label:"家长",value:4}],v=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],L=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],O=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],I=[{label:"任课老师",value:14},{label:"家长",value:4}],k=[{value:"0",label:"未投票"},{value:"1",label:"通过"},{value:"2",label:"投票中"}],y=[{value:0,label:"待审核"},{value:1,label:"通过"},{value:2,label:"不通过"}]},"349c":function(e,t,a){"use strict";a.d(t,"d",(function(){return r})),a.d(t,"c",(function(){return c})),a.d(t,"b",(function(){return o})),a.d(t,"e",(function(){return n})),a.d(t,"f",(function(){return s})),a.d(t,"a",(function(){return i}));var l=a("b775");function r(e){return Object(l["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function c(e){return Object(l["a"])({url:"/admin/organization/selectOrgByQuery",method:"post",data:e})}function o(e){return Object(l["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function n(e){return Object(l["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function s(e){return Object(l["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function i(e){return Object(l["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},"8be8":function(e,t,a){"use strict";a.d(t,"c",(function(){return r})),a.d(t,"d",(function(){return c})),a.d(t,"b",(function(){return o})),a.d(t,"f",(function(){return n})),a.d(t,"h",(function(){return s})),a.d(t,"a",(function(){return i})),a.d(t,"g",(function(){return u})),a.d(t,"e",(function(){return d}));var l=a("b775");function r(e){return Object(l["a"])({url:"/admin/schoolClass/selectByQuery",method:"post",data:e})}function c(e){return Object(l["a"])({url:"/admin/schoolClass/selectYearByOrgId",method:"post",data:e})}function o(e){return Object(l["a"])({url:"/admin/schoolClass/exportExcel",method:"post",data:e})}function n(e){return Object(l["a"])({url:"/admin/schoolClass/addSchoolClass",method:"post",data:e})}function s(e){return Object(l["a"])({url:"/admin/schoolClass/updateSchoolClass",method:"post",data:e})}function i(e){return Object(l["a"])({url:"/admin/schoolClass/deleteSchoolClass",method:"post",data:e})}function u(e){return Object(l["a"])({url:"/admin/schoolClass/selectClassByUserId",method:"post",data:e})}function d(e){return Object(l["a"])({url:"/admin/schoolClass/selectResponsibeByGradeOrClass",method:"post",data:e})}},a7b3:function(e,t,a){"use strict";a.r(t);var l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"list-view-layout"},[a("json-filter",{attrs:{code:e.titleCode}},[a("el-form",{attrs:{inline:!0}},[a("el-form-item",{attrs:{label:"学校码"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclOrgCodeIsLike,callback:function(t){e.$set(e.param,"scclOrgCodeIsLike",t)},expression:"param.scclOrgCodeIsLike"}})],1),a("el-form-item",{attrs:{label:"学校名称"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclOrgNameIsLike,callback:function(t){e.$set(e.param,"scclOrgNameIsLike",t)},expression:"param.scclOrgNameIsLike"}})],1),a("el-form-item",{attrs:{label:"班级码"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclCodeIsLike,callback:function(t){e.$set(e.param,"scclCodeIsLike",t)},expression:"param.scclCodeIsLike"}})],1),a("el-form-item",{attrs:{label:"班级名称"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclNameIsLike,callback:function(t){e.$set(e.param,"scclNameIsLike",t)},expression:"param.scclNameIsLike"}})],1),a("el-form-item",{attrs:{label:"年级"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclYearIsLike,callback:function(t){e.$set(e.param,"scclYearIsLike",t)},expression:"param.scclYearIsLike"}})],1),a("el-form-item",{attrs:{label:"等级"}},[a("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.scclLevelIsLike,callback:function(t){e.$set(e.param,"scclLevelIsLike",t)},expression:"param.scclLevelIsLike"}})],1),a("el-form-item",[a("el-button",{on:{click:function(t){return e.doSearch()}}},[e._v("查询")]),a("permission-filter",{attrs:{code:"baseClassAdd",type:"primary"},nativeOn:{click:function(t){return e.doAdd()}}}),a("permission-filter",{attrs:{code:"baseClassImport",type:"primary"},nativeOn:{click:function(t){return e.doImport()}}}),a("permission-filter",{attrs:{code:"baseClassExport",type:"primary",loading:e.exportLoading,disabled:e.exportLoading},nativeOn:{click:function(t){return e.doExport()}}})],1)],1)],1),a("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"operations",fn:function(t){return[a("permission-filter",{attrs:{code:"baseClassEdit",size:"mini",type:"warning"},nativeOn:{click:function(a){return e.doEdit(t.rowData.scope.row)}}}),a("permission-filter",{attrs:{code:"baseClassDelete",size:"mini",type:"danger"},nativeOn:{click:function(a){return e.doDelete(t.rowData.scope.row)}}})]}}])}),a("edit-dialog",{attrs:{entity:e.chooseEntity,"school-list":e.schoolList},on:{change:function(t){return e.handleChange()}},model:{value:e.dialogShow,callback:function(t){e.dialogShow=t},expression:"dialogShow"}}),a("import-dialog",{attrs:{title:"Excel文件导入",url:"/admin/schoolClass/importExcel",template:"/template/class.xlsx",formats:["xls","xlsx"]},on:{change:function(t){return e.doSearch()}},model:{value:e.importDialogShow,callback:function(t){e.importDialogShow=t},expression:"importDialogShow"}})],1)},r=[],c=a("5530"),o=a("4303"),n=a("261a"),s=a("e101"),i=a("1166"),u=a("8be8"),d=a("4f29"),f=a("349c"),h={components:{JsonFilter:o["a"],JsonTable:n["a"],PermissionFilter:s["a"],EditDialog:i["default"],ImportDialog:d["a"]},filters:{},data:function(){return{titleCode:"baseClass",tableHeads:[{prop:"scclCode",label:"班级码"},{prop:"scclName",label:"班级名称",overflowTooltip:"show"},{prop:"scclTeacherName",label:"班主任"},{prop:"scclTeacherPhone",label:"班主任手机号"},{prop:"scclLevel",label:"等级"},{overflowTooltip:"show",prop:"scclOrgName",label:"学校名称"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:160}],listLoading:!1,exportLoading:!1,importDialogShow:!1,tableData:[],schoolList:[],param:{scclOrgCodeIsLike:"",scclOrgNameIsLike:"",scclCodeIsLike:"",scclNameIsLike:"",scclYearIsLike:"",scclLevelIsLike:""},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null}},computed:{},created:function(){this.getTableData(),this.getSchoolList()},methods:{getSchoolList:function(){var e=this;Object(f["d"])({page:1,limit:0}).then((function(t){t.success?e.schoolList=t.root:e.schoolList=[]}))},getTableData:function(){var e=this;this.listLoading=!0,Object(u["c"])(Object(c["a"])(Object(c["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(t){e.listLoading=!1,t.success?(e.tableData=t.root,e.page.total=t.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:t.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},doDelete:function(e){var t=this;this.$confirm("确定删除？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",beforeClose:function(a,l,r){"confirm"===a?Object(u["a"])({scclId:e.scclId}).then((function(e){e.success?(t.$notify({title:"成功",message:"操作成功!",type:"success"}),t.getTableData()):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"}),r()})).catch((function(e){t.$notify.error({title:"失败",message:"请求发生错误， 请重试"}),r()})):r()}})},handleChange:function(){this.getTableData()},doAdd:function(){this.chooseEntity=null===this.chooseEntity?{}:null,this.dialogShow=!0},doEdit:function(e){this.chooseEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.dialogShow=!0},doExport:function(){var e=this;this.exportLoading||(this.exportLoading=!0,Object(u["b"])(Object(c["a"])({},this.param)).then((function(t){if(e.exportLoading=!1,t.success){var a=e.BASEFILEURL;window.open(a+t.tip)}else e.$notify({title:"提示",message:t.failReasonShow||"Excel 导出错误！",type:"warning"})})))},doImport:function(){this.importDialogShow=!0}}},m=h,b=(a("dfde"),a("2877")),p=Object(b["a"])(m,l,r,!1,null,"38f2b6fa",null);t["default"]=p.exports},c633:function(e,t,a){"use strict";a.d(t,"e",(function(){return r})),a.d(t,"d",(function(){return c})),a.d(t,"c",(function(){return o})),a.d(t,"i",(function(){return n})),a.d(t,"h",(function(){return s})),a.d(t,"b",(function(){return i})),a.d(t,"g",(function(){return u})),a.d(t,"f",(function(){return d})),a.d(t,"a",(function(){return f}));var l=a("b775");function r(e){return Object(l["a"])({url:"/admin/teacherStaff/getTeacherStaff",method:"post",data:e})}function c(e){return Object(l["a"])({url:"/admin/teacherStaff/exportExcel",method:"post",data:e})}function o(e){return Object(l["a"])({url:"/admin/teacherStaff/deleteTeacherRole",method:"post",data:e})}function n(e){return Object(l["a"])({url:"/admin/teacherStaff/updateTeacherStaff",method:"post",data:e})}function s(e){return Object(l["a"])({url:"/admin/teacherStaff/addTeacherStaff",method:"post",data:e})}function i(e){return Object(l["a"])({url:"/admin/teacherStaff/adjustPosition",method:"post",data:e})}function u(e){return Object(l["a"])({url:"/admin/teacherStaff/getTeacherStaffRole",method:"post",data:e})}function d(e){return Object(l["a"])({url:"/admin/teacherStaff/selectTeacherByQuery",method:"post",data:e})}function f(e){return Object(l["a"])({url:"/admin/teacherStaff/addTeacherRole",method:"post",data:e})}},dfde:function(e,t,a){"use strict";a("2145")}}]);