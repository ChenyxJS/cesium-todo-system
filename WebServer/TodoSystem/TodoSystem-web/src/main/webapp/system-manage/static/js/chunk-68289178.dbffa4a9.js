(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68289178"],{2209:function(e,t,o){"use strict";o.d(t,"o",(function(){return a})),o.d(t,"p",(function(){return r})),o.d(t,"f",(function(){return l})),o.d(t,"m",(function(){return i})),o.d(t,"n",(function(){return n})),o.d(t,"h",(function(){return s})),o.d(t,"g",(function(){return u})),o.d(t,"r",(function(){return c})),o.d(t,"q",(function(){return d})),o.d(t,"e",(function(){return g})),o.d(t,"k",(function(){return f})),o.d(t,"i",(function(){return p})),o.d(t,"c",(function(){return m})),o.d(t,"l",(function(){return b})),o.d(t,"j",(function(){return h})),o.d(t,"b",(function(){return v})),o.d(t,"d",(function(){return y})),o.d(t,"a",(function(){return w}));var a=[{value:"0",label:"小学"},{value:"1",label:"初中"},{value:"2",label:"高中"}],r=[{value:"0",label:"公办"},{value:"1",label:"民办"},{value:"2",label:"行政单位"},{value:"3",label:"平台"}],l=[{value:"1",label:"男生宿舍"},{value:"2",label:"女生宿舍"},{value:"3",label:"楼号"},{value:"4",label:"楼层"}],i=["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"],n=[{value:"0",label:"单选"},{value:"1",label:"多选"}],s=[{value:"0",label:"临时"},{value:"1",label:"长期"},{value:"2",label:"学期"}],u=[{value:"1",label:"学校"},{value:"2",label:"年级"},{value:"3",label:"班级"},{value:"4",label:"个人"}],c=[{value:"1",label:"通用库"},{value:"2",label:"特色库"},{value:"3",label:"校规"},{value:"4",label:"班规"}],d=[{value:"1",label:"一级"},{value:"2",label:"二级"},{value:"3",label:"三级"}],g=[{value:"1",label:"年"},{value:"2",label:"月"},{value:"3",label:"周"},{value:"4",label:"日"}],f=[{label:"学校",value:1},{label:"年级",value:2},{label:"班级",value:3}],p=[{label:"年级",value:2},{label:"班级",value:3}],m=[{label:"班级",value:3}],b=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"其他教职工",value:17},{label:"心理辅导",value:18},{label:"家长",value:4}],h=[{label:"年级主任",value:9},{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],v=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],y=[{label:"班主任",value:12},{label:"任课老师",value:14},{label:"家长",value:4}],w=[{label:"任课老师",value:14},{label:"家长",value:4}]},3123:function(e,t,o){"use strict";o.d(t,"b",(function(){return r})),o.d(t,"a",(function(){return l}));var a=o("b775");function r(){return Object(a["a"])({url:"/system/getQiNiuUploadToken",method:"post"})}function l(){var e=["http://up.qiniup.com","https://up.qiniup.com"],t={url:e[0]},o=document.location.protocol;return t.url="https:"===o?e[1]:e[0],t}},"349c":function(e,t,o){"use strict";o.d(t,"c",(function(){return r})),o.d(t,"b",(function(){return l})),o.d(t,"d",(function(){return i})),o.d(t,"e",(function(){return n})),o.d(t,"a",(function(){return s}));var a=o("b775");function r(e){return Object(a["a"])({url:"/admin/organization/selectByQuery",method:"post",data:e})}function l(e){return Object(a["a"])({url:"/admin/organization/exportExcel",method:"post",data:e})}function i(e){return Object(a["a"])({url:"/admin/organization/addOrgnization",method:"post",data:e})}function n(e){return Object(a["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:e})}function s(e){return Object(a["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:e})}},4080:function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",[o("el-dialog",{attrs:{title:e.dialogTitle,visible:e.isVisible,width:"40%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(t){e.isVisible=t}}},[o("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"auto"}},[o("el-form-item",{attrs:{label:"学校名称",prop:"orgName"}},[o("el-input",{attrs:{placeholder:"请输入"},model:{value:e.form.orgName,callback:function(t){e.$set(e.form,"orgName",t)},expression:"form.orgName"}})],1),o("el-form-item",{attrs:{label:"LOGO"}},[o("el-upload",{staticClass:"avatar-uploader",attrs:{action:e.actionUrl,data:e.postData,"show-file-list":!1,"on-success":e.handleAvatarSuccess,"on-error":e.handleError,"before-upload":e.beforeAvatarUpload}},[e.imageUrl?o("img",{staticClass:"avatar",attrs:{src:e.imageUrl}}):o("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),o("el-form-item",{attrs:{label:"等级",prop:"orgLevel"}},[o("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.form.orgLevel,callback:function(t){e.$set(e.form,"orgLevel",t)},expression:"form.orgLevel"}},e._l(e.schoolLevelList,(function(e){return o("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),o("el-form-item",{attrs:{label:"类别",prop:"orgType"}},[o("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.form.orgType,callback:function(t){e.$set(e.form,"orgType",t)},expression:"form.orgType"}},e._l(e.schoolTypeList,(function(e){return o("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),o("el-form-item",{attrs:{label:"省/市/区/镇街"}},[o("select-address",{attrs:{address:e.selectedAdddress},on:{getAddress:e.getAddress}})],1),o("el-form-item",{attrs:{label:"详细地址",prop:"orgAddress"}},[o("el-input",{attrs:{placeholder:"请输入",type:"textarea",rows:4},model:{value:e.form.orgAddress,callback:function(t){e.$set(e.form,"orgAddress",t)},expression:"form.orgAddress"}})],1)],1),o("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[o("el-button",{on:{click:function(t){e.isVisible=!1}}},[e._v("取 消")]),o("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:e.submitting},on:{click:function(t){return e.submitClick()}}},[e._v("确 定")])],1)],1)],1)},r=[],l=o("5530"),i=(o("d3b7"),o("3ca3"),o("ddb0"),o("159b"),o("b0c0"),o("ad78")),n=o("349c"),s=o("3123"),u=(o("0eb1"),o("2209")),c={name:"EditDialog",components:{SelectAddress:function(){return Promise.resolve().then(o.bind(null,"0eb1"))}},mixins:[i["a"]],props:{entity:{type:Object}},data:function(){return{submitting:!1,form:{orgId:"",orgName:"",orgLogo:"",orgLevel:"",orgType:"",orgProvince:"",orgCity:"",orgArea:"",orgTown:"",orgAddress:"",orgLongitude:"",orgLatitude:""},postData:{token:"",key:""},domain:"",actionUrl:"",imageUrl:"",selectedAdddress:[],schoolLevelList:u["o"],schoolTypeList:u["p"],rules:{orgLevel:[{required:!0,message:"请选择！",trigger:"change"}],orgType:[{required:!0,message:"请选择！",trigger:"change"}],orgName:[{required:!0,message:"不能为空！",trigger:"blur"}]}}},computed:{isEdit:function(){return this.entity&&this.entity.orgId},dialogTitle:function(){return this.isVisible&&(this.getHttp(),this.getQiniuConfig()),this.isEdit?"编辑":"添加"}},watch:{entity:function(e){this.imageUrl="",this.selectedAdddress=[],e&&e.orgId?(this.form.orgId=e.orgId,this.form.orgName=e.orgName,this.form.orgLogo=e.orgLogo,this.form.orgLevel=e.orgLevel+"",this.form.orgType=e.orgType+"",this.form.orgProvince=e.orgProvince,this.form.orgCity=e.orgCity,this.form.orgArea=e.orgArea,this.form.orgTown=e.orgTown,this.form.orgAddress=e.orgAddress,this.form.orgLongitude=e.orgLongitude,this.form.orgLatitude=e.orgLatitude,this.imageUrl=e.orgLogo,this.selectedAdddress=[e.orgProvince,e.orgCity,e.orgArea,e.orgTown]):this.form={orgName:"",orgLogo:"",orgLevel:"",orgType:"",orgProvince:"",orgCity:"",orgArea:"",orgTown:"",orgAddress:"",orgLongitude:"",orgLatitude:""}}},methods:{getHttp:function(){var e=Object(s["a"])();this.actionUrl=e.url},getQiniuConfig:function(){var e=this;Object(s["b"])().then((function(t){t.success?(e.postData.token=t.tip,t.root.forEach((function(t){"qny_domain"===t.scvaCode&&(e.domain=t.scvaValue||"")}))):e.$notify({title:"提示",message:"上传参数失败！",type:"warning"})}))},submitClick:function(){var e=this;this.$refs.form.validate((function(t){if(t){var o=Object(l["a"])({},e.form);4===e.selectedAdddress.length&&(o.orgProvince=e.selectedAdddress[0],o.orgCity=e.selectedAdddress[1],o.orgArea=e.selectedAdddress[2],o.orgTown=e.selectedAdddress[3]),e.submitting=!0,(e.isEdit?Object(n["e"])(o):Object(n["d"])(o)).then((function(t){e.selectedAdddress=[],e.submitting=!1,t.success?(e.$refs["form"].resetFields(),e.$notify({title:"成功",message:"操作成功",type:"success"}),e.$emit("change"),e.isVisible=!1):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"})})).catch((function(t){e.selectedAdddress=[],e.submitting=!1,e.$notify.error({title:"失败",message:result.failReasonShow||"操作失败"})}))}}))},handleAvatarSuccess:function(e,t){this.imageUrl=this.domain+"/"+e.key,this.form.orgLogo=this.domain+"/"+e.key},handleError:function(e){this.LOG.info(["err --- ",e])},beforeAvatarUpload:function(e){this.postData.key=(new Date).getTime()+e.name;var t="image/jpeg"===e.type,o="image/png"===e.type,a=e.size/1024/1024<5;return t||o||this.$message.error("上传图片只能是 JPG/PNG 格式!"),a||this.$message.error("上传图片大小不能超过 5MB!"),(t||o)&&a},getAddress:function(e){this.selectedAdddress=e}}},d=c,g=(o("befc"),o("2877")),f=Object(g["a"])(d,a,r,!1,null,"5facf672",null);t["default"]=f.exports},"7fc6":function(e,t,o){"use strict";o.r(t);var a=function(){var e=this,t=e.$createElement,o=e._self._c||t;return o("div",{staticClass:"list-view-layout"},[o("json-filter",{attrs:{code:e.titleCode}},[o("el-form",{attrs:{inline:!0}},[o("el-form-item",{attrs:{label:"学校码"}},[o("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgCodeIsLike,callback:function(t){e.$set(e.param,"orgCodeIsLike",t)},expression:"param.orgCodeIsLike"}})],1),o("el-form-item",{attrs:{label:"学校名称"}},[o("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:e.param.orgNameIsLike,callback:function(t){e.$set(e.param,"orgNameIsLike",t)},expression:"param.orgNameIsLike"}})],1),o("el-form-item",[o("el-button",{on:{click:function(t){return e.doSearch()}}},[e._v("查询")]),o("permission-filter",{attrs:{code:"baseSchoolAdd",type:"primary"},nativeOn:{click:function(t){return e.doAdd()}}}),o("permission-filter",{attrs:{code:"baseSchoolImport",type:"primary"},nativeOn:{click:function(t){return e.doImport()}}}),o("permission-filter",{attrs:{code:"baseSchoolExport",type:"primary",loading:e.exportLoading,disabled:e.exportLoading},nativeOn:{click:function(t){return e.doExport()}}})],1)],1)],1),o("json-table",{ref:"myJsonTable",attrs:{"table-heads":e.tableHeads,loading:e.listLoading,"table-data":e.tableData,page:e.page},on:{"current-change":e.currentPageChange},scopedSlots:e._u([{key:"orgLogo",fn:function(e){return[o("el-image",{staticStyle:{width:"100px",height:"100px"},attrs:{src:e.rowData.scope.row.orgLogo,fit:"contain"}})]}},{key:"orgLevel",fn:function(t){return[o("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.orgLevel,e.schoolLevelList)))])]}},{key:"orgType",fn:function(t){return[o("span",[e._v(e._s(e._f("formatShowName")(t.rowData.scope.row.orgType,e.schoolTypeList)))])]}},{key:"operations",fn:function(t){return[o("permission-filter",{attrs:{code:"baseSchoolEdit",size:"mini",type:"warning"},nativeOn:{click:function(o){return e.doEdit(t.rowData.scope.row)}}}),o("permission-filter",{attrs:{code:"baseSchoolDelete",size:"mini",type:"danger"},nativeOn:{click:function(o){return e.doDelete(t.rowData.scope.row)}}})]}}])}),o("edit-dialog",{attrs:{entity:e.chooseEntity},on:{change:function(t){return e.handleChange()}},model:{value:e.dialogShow,callback:function(t){e.dialogShow=t},expression:"dialogShow"}}),o("import-dialog",{attrs:{title:"Excel文件导入",url:"/admin/organization/importExcel",formats:["xls","xlsx"]},on:{change:function(t){return e.doSearch()}},model:{value:e.importDialogShow,callback:function(t){e.importDialogShow=t},expression:"importDialogShow"}})],1)},r=[],l=o("5530"),i=(o("159b"),o("4303")),n=o("261a"),s=o("e101"),u=o("4080"),c=o("4f29"),d=o("2209"),g=o("349c"),f={components:{JsonFilter:i["a"],JsonTable:n["a"],PermissionFilter:s["a"],EditDialog:u["default"],ImportDialog:c["a"]},filters:{formatShowName:function(e,t){var o="";return t.forEach((function(t){e==t.value&&(o=t.label)})),o}},data:function(){return{titleCode:"baseSchool",tableHeads:[{prop:"orgCode",label:"学校码"},{prop:"orgName",label:"学校名称",overflowTooltip:"show"},{slot:!0,prop:"orgLogo",label:"LOGO",width:160},{slot:!0,prop:"orgLevel",label:"等级"},{slot:!0,prop:"orgType",label:"类别"},{prop:"orgProvince",label:"省"},{prop:"orgCity",label:"市"},{prop:"orgArea",label:"县区"},{prop:"orgTown",label:"城镇/街道"},{overflowTooltip:"show",prop:"orgAddress",label:"详细地址"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:160}],listLoading:!1,exportLoading:!1,importDialogShow:!1,tableData:[],schoolTypeList:d["p"],schoolLevelList:d["o"],param:{orgCodeIsLike:"",orgNameIsLike:""},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null}},computed:{},created:function(){this.getTableData()},methods:{getTableData:function(){var e=this;this.listLoading=!0,Object(g["c"])(Object(l["a"])(Object(l["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(t){e.listLoading=!1,t.success?(e.tableData=t.root,e.page.total=t.totalSize):(e.tableData=[],e.page.total=0,e.$notify({title:"提示",message:t.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(e){this.page.current=e,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},doDelete:function(e){var t=this;this.$confirm("确定删除？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",beforeClose:function(o,a,r){"confirm"===o?Object(g["a"])({orgId:e.orgId}).then((function(e){e.success?(t.$notify({title:"成功",message:"操作成功!",type:"success"}),t.getTableData()):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"}),r()})).catch((function(e){t.$notify.error({title:"失败",message:"请求发生错误， 请重试"}),r()})):r()}})},handleChange:function(){this.getTableData()},doAdd:function(){this.chooseEntity=null===this.chooseEntity?{}:null,this.dialogShow=!0},doEdit:function(e){this.chooseEntity=e,e.uniquelyIdentifies=(new Date).getTime(),this.dialogShow=!0},doExport:function(){var e=this;this.exportLoading||(this.exportLoading=!0,Object(g["b"])(Object(l["a"])({},this.param)).then((function(t){if(e.exportLoading=!1,t.success){var o=e.BASEFILEURL;window.open(o+t.tip)}else e.$notify({title:"提示",message:t.failReasonShow||"Excel 导出错误！",type:"warning"})})))},doImport:function(){this.importDialogShow=!0}}},p=f,m=o("2877"),b=Object(m["a"])(p,a,r,!1,null,"52ac8512",null);t["default"]=b.exports},befc:function(e,t,o){"use strict";o("e8b1")},e8b1:function(e,t,o){}}]);