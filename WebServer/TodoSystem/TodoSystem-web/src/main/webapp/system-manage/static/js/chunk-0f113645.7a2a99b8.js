(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0f113645"],{1009:function(e,r,t){},2767:function(e,r,t){"use strict";t.r(r);var a=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",[t("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(r){e.isVisible=r}}},[t("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[t("el-form-item",{attrs:{label:"用户昵称",prop:"userNickname"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userNickname,callback:function(r){e.$set(e.form,"userNickname",r)},expression:"form.userNickname"}})],1),t("el-form-item",{attrs:{label:"真实姓名",prop:"userRealName"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userRealName,callback:function(r){e.$set(e.form,"userRealName",r)},expression:"form.userRealName"}})],1),t("el-form-item",{attrs:{label:"性别："}},[t("el-switch",{staticStyle:{"margin-right":"5px"},attrs:{"active-color":"blue","active-value":1,"inactive-color":"pink","inactive-value":2},model:{value:e.form.userSex,callback:function(r){e.$set(e.form,"userSex",r)},expression:"form.userSex"}}),1===e.form.userSex?t("el-tag",{attrs:{size:"small"}},[e._v("男")]):t("el-tag",{attrs:{size:"small",type:"danger"}},[e._v("女")])],1),t("el-form-item",{attrs:{label:"手机号",prop:"userPhone"}},[t("el-input",{attrs:{placeholder:"请输入",disabled:!!e.isEdit},model:{value:e.form.userPhone,callback:function(r){e.$set(e.form,"userPhone",r)},expression:"form.userPhone"}})],1),t("el-form-item",{attrs:{label:"身份证号",prop:"userIdNumber"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userIdNumber,callback:function(r){e.$set(e.form,"userIdNumber",r)},expression:"form.userIdNumber"}})],1),t("el-form-item",{attrs:{label:"密码",prop:e.userPassword}},[t("el-input",{attrs:{placeholder:"修改密码"},model:{value:e.form.userPassword,callback:function(r){e.$set(e.form,"userPassword",r)},expression:"form.userPassword"}})],1),t("el-form-item",{attrs:{label:"专业方向",prop:"userCompanyPosition"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userCompanyPosition,callback:function(r){e.$set(e.form,"userCompanyPosition",r)},expression:"form.userCompanyPosition"}})],1),t("el-form-item",{attrs:{label:"单位",prop:"userCompanyName"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userCompanyName,callback:function(r){e.$set(e.form,"userCompanyName",r)},expression:"form.userCompanyName"}})],1),t("el-form-item",{attrs:{label:"级别",prop:"userLawyerLevel"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userLawyerLevel,callback:function(r){e.$set(e.form,"userLawyerLevel",r)},expression:"form.userLawyerLevel"}})],1),t("el-form-item",{attrs:{label:"省/市/区"}},[t("select-address",{attrs:{address:e.form.pcaa,clearable:!0,"is-three-level":!0},on:{getAddress:e.getAddress}})],1),t("el-form-item",{attrs:{label:"详细地址",prop:"userAddress"}},[t("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.userAddress,callback:function(r){e.$set(e.form,"userAddress",r)},expression:"form.userAddress"}})],1),t("el-form-item",{attrs:{label:"角色",prop:"roleId"}},[t("el-select",{attrs:{placeholder:"请选择角色"},on:{focus:e.getRoleList},model:{value:e.form.roleId,callback:function(r){e.$set(e.form,"roleId",r)},expression:"form.roleId"}},e._l(e.roleList,(function(e){return t("el-option",{key:e.roleId,attrs:{label:e.roleName,value:e.roleId}})})),1)],1),t("el-form-item",{attrs:{label:"机构",prop:"orgId"}},[t("el-select",{attrs:{placeholder:"请选择学校"},on:{focus:e.getSchoolList},model:{value:e.form.orgId,callback:function(r){e.$set(e.form,"orgId",r)},expression:"form.orgId"}},e._l(e.schoolList,(function(e){return t("el-option",{key:e.orgId,attrs:{label:e.orgName,value:e.orgId}})})),1)],1)],1),t("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(r){e.isVisible=!1}}},[e._v("取 消")]),t("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(r){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},s=[],o=t("1da1"),i=t("5530"),n=(t("96cf"),t("ad78")),l=t("0eb1"),u=t("b6de"),c=t("d1e5"),m=t("61f7"),d=t("f46a"),p=t("349c"),f={name:"EditDialog",mixins:[n["a"]],props:{entity:{type:Object}},components:{SelectAddress:l["default"]},data:function(){return{submitting:!1,form:{userPassword:"",userSex:1,userNickname:"",userRealName:"",userPhone:"",userIdNumber:"",userProvince:"",userCity:"",userArea:"",userAddress:"",pcaa:[],roleId:"",orgId:""},schoolList:[],roleList:[],rules:{userRealName:[{required:!0,message:"不能为空！",trigger:"blur"}],userPhone:[{required:!0,message:"不能为空！",trigger:"blur"},{validator:m["e"],trigger:"blur"}],userIdNumber:[{required:!0,message:"不能为空！",trigger:"blur"},{validator:m["d"],trigger:"blur"}],userCompanyName:[{required:!0,message:"不能为空！",trigger:"blur"}],userCompanyPosition:[{required:!0,message:"不能为空！",trigger:"blur"}],userDepartmentName:[{required:!0,message:"不能为空！",trigger:"blur"}],userLawyerLevel:[{required:!0,message:"不能为空！",trigger:"blur"}],pcaa:[{required:!0,message:"不能为空！",trigger:"blur"}],userNickname:[{required:!0,message:"不能为空！",trigger:"blur"}],roleId:[{required:!0,message:"不能为空！",trigger:"blur"}],orgId:[{required:!0,message:"不能为空！",trigger:"blur"}],userPassword:[{required:!0,message:"不能为空！",trigger:"blur"}],userAddress:[{required:!0,message:"不能为空！",trigger:"blur"}]}}},watch:{entity:function(e){e&&e.userId?(this.form.userId=e.userId,this.form.roleId=e.roleId,this.form.orgId=e.orgId,this.form.userRealName=e.userRealName,this.form.userPhone=e.userPhone,this.form.userIdNumber=e.userIdNumber,this.form.userCompanyPosition=e.userCompanyPosition,this.form.userCompanyName=e.userCompanyName,this.form.userLawyerLevel=e.userLawyerLevel,this.form.userSex=e.userSex,this.form.userNickname=e.userNickname,this.form.userRealName=e.userRealName,this.form.userAddress=e.userAddress,null!==e.userProvince&&null!==e.userCity&&null!==e.userArea&&(this.form.pcaa[0]=e.userProvince,this.form.pcaa[1]=e.userCity,this.form.pcaa[2]=e.userArea)):this.form={userPassword:"",userSex:1,userNickname:"",userRealName:"",userPhone:"",userIdNumber:"",userProvince:"",userCity:"",userArea:"",userAddress:"",roleId:"",orgId:"",pcaa:[]}}},created:function(){this.getRoleList(),this.getSchoolList()},computed:{isEdit:function(){return this.entity&&this.entity.userId},dialogTitle:function(){return this.isEdit?"编辑":"添加"},userPassword:function(){return this.isEdit?"":"userPassword"}},methods:{submitClick:function(){var e=this;this.$refs.form.validate((function(r){if(r){var t=e.isEdit?Object(i["a"])(Object(i["a"])({},c["a"].do(e.form)),{},{"param.userProvince":e.form.pcaa[0],"param.userCity":e.form.pcaa[1],"param.userArea":e.form.pcaa[2]}):Object(i["a"])(Object(i["a"])({},e.form),{},{userProvince:e.form.pcaa[0],userCity:e.form.pcaa[1],userArea:e.form.pcaa[2]});e.submitting=!0,(e.isEdit?Object(u["d"])(t):Object(u["c"])(t)).then((function(r){e.submitting=!1,r.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:r.failReasonShow||"操作失败"})}))}}))},getAddress:function(e){this.form.pcaa=e},getRoleList:function(){var e=this;return Object(o["a"])(regeneratorRuntime.mark((function r(){var t,a,s;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return r.next=2,Object(d["d"])({"param.roleType":4});case 2:t=r.sent,a=t.success,s=t.root,a&&(e.roleList=s);case 6:case"end":return r.stop()}}),r)})))()},getSchoolList:function(){var e=this;return Object(o["a"])(regeneratorRuntime.mark((function r(){var t,a,s;return regeneratorRuntime.wrap((function(r){while(1)switch(r.prev=r.next){case 0:return r.next=2,Object(p["c"])({page:1,limit:0});case 2:t=r.sent,a=t.success,s=t.root,a&&(e.schoolList=s);case 6:case"end":return r.stop()}}),r)})))()}}},b=f,h=(t("72f3"),t("2877")),g=Object(h["a"])(b,a,s,!1,null,"de8547f2",null);r["default"]=g.exports},"349c":function(e,r,t){"use strict";t.d(r,"c",(function(){return s})),t.d(r,"b",(function(){return o})),t.d(r,"d",(function(){return i})),t.d(r,"e",(function(){return n})),t.d(r,"a",(function(){return l}));var a=t("b775");function s(e){return Object(a["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function o(e){return Object(a["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function i(e){return Object(a["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function n(e){return Object(a["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function l(e){return Object(a["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},"72f3":function(e,r,t){"use strict";t("1009")},"7bc9":function(e,r,t){"use strict";t.r(r);var a=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"list-view-layout"},[t("json-filter",{attrs:{code:e.titleCode}},[t("el-form",{attrs:{inline:!0}},[t("el-form-item",{attrs:{label:"省/市/区",prop:"userPcaa"}},[t("select-address",{attrs:{address:e.param.userPcaa,"is-three-level":!0,clearable:!0},on:{getAddress:e.getAddress}})],1),t("el-form-item",{attrs:{label:"专业方向"}},[t("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.userCompanyPositionIsLike,callback:function(r){e.$set(e.param,"userCompanyPositionIsLike",r)},expression:"param.userCompanyPositionIsLike"}})],1),t("el-form-item",{attrs:{label:"姓名"}},[t("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.userRealNameIsLike,callback:function(r){e.$set(e.param,"userRealNameIsLike",r)},expression:"param.userRealNameIsLike"}})],1),t("el-form-item",{attrs:{label:"手机号"}},[t("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.userPhoneIsLike,callback:function(r){e.$set(e.param,"userPhoneIsLike",r)},expression:"param.userPhoneIsLike"}})],1),t("el-form-item",{attrs:{label:"单位"}},[t("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.userCompanyNameIsLike,callback:function(r){e.$set(e.param,"userCompanyNameIsLike",r)},expression:"param.userCompanyNameIsLike"}})],1),t("el-form-item",{attrs:{label:"级别"}},[t("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.userLawyerLevelIsLike,callback:function(r){e.$set(e.param,"userLawyerLevelIsLike",r)},expression:"param.userLawyerLevelIsLike"}})],1),t("el-form-item",[t("el-button",{on:{click:function(r){return e.doSearch()}}},[e._v("查询")]),t("permission-filter",{attrs:{code:"userLawyerAdd",type:"primary"},nativeOn:{click:function(r){return e.doAdd()}}}),t("permission-filter",{attrs:{code:"userLawyerImport",type:"primary"},nativeOn:{click:function(r){return e.doImport()}}}),t("permission-filter",{attrs:{code:"userLawyerExport",type:"primary"},nativeOn:{click:function(r){return e.doExport()}}})],1)],1)],1),t("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"operations",fn:function(r){return[t("permission-filter",{attrs:{code:"userLawyerEdit",size:"mini",type:"warning"},nativeOn:{click:function(t){return e.doEdit(r.rowData.scope.row)}}})]}},{key:"userCreateTime",fn:function(r){return[t("span",[e._v(e._s(e._f("formatDate")(r.rowData.scope.row.userCreateTime,"yyyy-MM-dd HH:mm:ss")))])]}}])}),t("edit-dialog",{attrs:{entity:e.chooseEntity},on:{change:function(r){return e.handleChange()}},model:{value:e.dialogShow,callback:function(r){e.dialogShow=r},expression:"dialogShow"}}),t("import-dialog",{attrs:{title:"Excel文件导入",url:"/admin/lawyer/importExcel",formats:["xls","xlsx"]},on:{change:function(r){return e.doSearch()}},model:{value:e.importDialogShow,callback:function(r){e.importDialogShow=r},expression:"importDialogShow"}})],1)},s=[],o=t("5530"),i=t("4303"),n=t("0eb1"),l=t("261a"),u=t("e101"),c=t("2767"),m=t("4f29"),d=t("b6de"),p={components:{JsonFilter:i["a"],JsonTable:l["a"],PermissionFilter:u["a"],EditDialog:c["default"],ImportDialog:m["a"],SelectAddress:n["default"]},data:function(){return{titleCode:"userLawyer",tableHeads:[{prop:"userRealName",label:"姓名"},{prop:"userPhone",label:"手机号"},{prop:"userIdNumber",label:"身份证号"},{prop:"userCompanyPosition",label:"专业方向"},{prop:"userCompanyName",label:"单位"},{prop:"userLawyerLevel",label:"级别"},{prop:"userProvince",label:"省"},{prop:"userCity",label:"市"},{prop:"userArea",label:"区"},{slot:!0,prop:"userCreateTime",label:"创建时间"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:180}],listLoading:!1,tableData:[],param:{userPcaa:[],userRealNameIsLike:"",userPhoneIsLike:"",userCompanyNameIsLike:"",userLawyerLevelIsLike:""},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null,importDialogShow:!1}},computed:{},created:function(){this.getTableData()},methods:{getTableData:function(){var e=this;this.listLoading=!0,Object(d["b"])(Object(o["a"])(Object(o["a"])({},this.param),{},{"param.userProvince":this.param.userPcaa[0],"param.userCity":this.param.userPcaa[1],"param.userArea":this.param.userPcaa[2],page:this.page.current,limit:this.page.limit})).then((function(r){e.listLoading=!1,r.success?(e.tableData=r.root,e.page.total=r.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:r.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},handleChange:function(){this.getTableData()},doEdit:function(e){this.chooseEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.dialogShow=!0},doImport:function(){this.importDialogShow=!0},doExport:function(){var e=this;this.exportLoading||(this.exportLoading=!0,Object(d["a"])(Object(o["a"])(Object(o["a"])({},this.param),{},{"param.userProvince":this.param.userPcaa[0],"param.userCity":this.param.userPcaa[1],"param.userArea":this.param.userPcaa[2],isExport:"Y"})).then((function(r){if(e.exportLoading=!1,r.success){var t=e.BASEFILEURL;window.open(t+r.tip)}else e.$notify({title:"提示",message:r.failReasonShow||"Excel 导出错误！",type:"warning"})})))},getAddress:function(e){this.param.userPcaa=e},doAdd:function(){this.chooseEntity=null===this.chooseEntity?{}:null,this.dialogShow=!0}}},f=p,b=(t("f0c8"),t("2877")),h=Object(b["a"])(f,a,s,!1,null,"3eabb17c",null);r["default"]=h.exports},8510:function(e,r,t){},b6de:function(e,r,t){"use strict";t.d(r,"b",(function(){return s})),t.d(r,"a",(function(){return o})),t.d(r,"d",(function(){return i})),t.d(r,"c",(function(){return n}));var a=t("b775");function s(e){return Object(a["a"])({url:"/admin/lawyer/getLawyerUser",method:"post",data:e})}function o(e){return Object(a["a"])({url:"/admin/lawyer/exportExcel",method:"post",data:e})}function i(e){return Object(a["a"])({url:"/admin/lawyer/updateLawyer",method:"post",data:e})}function n(e){return Object(a["a"])({url:"/admin/lawyer/addLawyer",method:"post",data:e})}},d1e5:function(e,r,t){"use strict";t("159b");var a={do:function(e){var r=arguments.length>1&&void 0!==arguments[1]?arguments[1]:[],t={},a=function(a){var s=!0;r.forEach((function(e){e===a&&(s=!1)})),s?t["param.".concat(a)]=e[a]:t[a]=e[a]};for(var s in e)a(s);return t}};r["a"]=a},f0c8:function(e,r,t){"use strict";t("8510")},f46a:function(e,r,t){"use strict";t.d(r,"c",(function(){return s})),t.d(r,"b",(function(){return o})),t.d(r,"e",(function(){return i})),t.d(r,"a",(function(){return n})),t.d(r,"d",(function(){return l}));var a=t("b775");function s(e){return Object(a["a"])({url:"/admin/departmentUser/getDepartmentUser",method:"post",data:e})}function o(e){return Object(a["a"])({url:"/admin/departmentUser/exportExcel",method:"post",data:e})}function i(e){return Object(a["a"])({url:"/admin/departmentUser/updateDepartmentUser",method:"post",data:e})}function n(e){return Object(a["a"])({url:"/admin/departmentUser/addDepartmentUser",method:"post",data:e})}function l(e){return Object(a["a"])({url:"/admin/role/selectByExample",method:"post",data:e})}}}]);