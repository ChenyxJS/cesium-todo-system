(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-8c00c266"],{"0fad":function(t,e,i){"use strict";i.r(e);var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("el-dialog",{attrs:{title:t.dialogTitle,visible:t.isVisible,width:"770px",top:"3vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(e){t.isVisible=e}}},[i("el-form",{ref:"form",attrs:{model:t.form,rules:t.rules,"label-width":"100px"}},[i("el-form-item",{attrs:{label:"学校",prop:"articleOrgId"}},[i("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",filterable:""},on:{change:t.getSchoolName},model:{value:t.form.articleOrgId,callback:function(e){t.$set(t.form,"articleOrgId",e)},expression:"form.articleOrgId"}},t._l(t.schoolList,(function(t){return i("el-option",{key:t.orgId,attrs:{label:t.orgName,value:t.orgId}})})),1)],1),i("el-form-item",{attrs:{label:"标题",prop:"articleTitle"}},[i("el-input",{attrs:{placeholder:"请输入"},model:{value:t.form.articleTitle,callback:function(e){t.$set(t.form,"articleTitle",e)},expression:"form.articleTitle"}})],1),i("el-form-item",{attrs:{label:"排序",prop:"articleOrderNumber"}},[i("el-input-number",{attrs:{min:0},model:{value:t.form.articleOrderNumber,callback:function(e){t.$set(t.form,"articleOrderNumber",e)},expression:"form.articleOrderNumber"}})],1),i("el-form-item",{attrs:{label:"封面"}},[i("el-upload",{staticClass:"avatar-uploader",attrs:{action:t.actionUrl,data:t.postData,"show-file-list":!1,"on-success":t.handleAvatarSuccess,"on-error":t.handleError,"before-upload":t.beforeAvatarUpload}},[t.imageUrl?i("img",{staticClass:"avatar",attrs:{src:t.imageUrl}}):i("i",{staticClass:"el-icon-plus avatar-uploader-icon"})])],1),i("el-form-item",{attrs:{label:"内容",prop:"articleContent"}},[t.isVisible?i("tinymce",{ref:"editor",attrs:{height:400,"show-btn":!0,token:t.postData,domain:t.domain,"action-url":t.actionUrl},model:{value:t.form.articleContent,callback:function(e){t.$set(t.form,"articleContent",e)},expression:"form.articleContent"}}):t._e()],1)],1),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{on:{click:function(e){t.isVisible=!1}}},[t._v("取 消")]),i("el-button",{directives:[{name:"loading",rawName:"v-loading",value:t.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:t.submitting},on:{click:function(e){return t.submitClick()}}},[t._v("确 定")])],1)],1)],1)},r=[],o=i("5530"),n=(i("a9e3"),i("159b"),i("b0c0"),i("ad78")),l=i("23f3"),s=i("3123"),c=i("8256"),u={name:"EditDialog",mixins:[n["a"]],props:{entity:{type:Object},schoolList:{type:Array}},components:{Tinymce:c["a"]},data:function(){return{submitting:!1,form:{articleId:"",orgName:"",articleOrgId:"",articleTitle:"",articleContent:"",articleOrderNumber:"",articleCover:"",articleStatus:1,articleType:0},postData:{token:"",key:""},domain:"",actionUrl:"",imageUrl:"",rules:{articleOrgId:[{required:!0,message:"请选择！",trigger:"change"}]}}},watch:{entity:function(t){this.form.artiContent="",this.imageUrl="",t&&t.articleId?(this.form.articleId=t.articleId,this.form.orgName=t.orgName,this.form.articleOrgId=t.articleOrgId,this.form.articleTitle=t.articleTitle,this.form.articleContent=t.articleContent,this.form.articleOrderNumber=Number(t.articleOrderNumber),this.form.articleCover=t.articleCover,this.imageUrl=t.articleCover):this.form={articleId:"",orgName:"",articleOrgId:"",articleTitle:"",articleContent:"",articleOrderNumber:"",articleCover:"",articleStatus:1,articleType:0}}},computed:{isEdit:function(){return this.entity&&this.entity.articleId},dialogTitle:function(){return this.isVisible&&(this.getHttp(),this.getQiniuConfig()),this.isEdit?"编辑":"添加"}},methods:{getHttp:function(){var t=Object(s["a"])();this.actionUrl=t.url},getQiniuConfig:function(){var t=this;Object(s["b"])().then((function(e){e.success?(t.postData.token=e.tip,e.root.forEach((function(e){"qny_domain"===e.scvaCode&&(t.domain=e.scvaValue||"")}))):t.$notify({title:"提示",message:"上传参数失败！",type:"warning"})}))},getSchoolName:function(t){var e="";this.schoolList.forEach((function(i){i.orgId==t&&(e=i.orgName)})),this.form.artiOperName=e},submitClick:function(){var t=this;this.$refs.form.validate((function(e){if(e){var i=Object(o["a"])({},t.form);if(!i.articleCover)return void t.$message.warning("请上传封面！");t.submitting=!0,(t.isEdit?Object(l["d"])(i):Object(l["c"])(i)).then((function(e){t.submitting=!1,e.success?(t.$refs["form"].resetFields(),t.$notify({title:"成功",message:"操作成功",type:"success"}),t.$emit("change"),t.isVisible=!1):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"})}))}}))},handleAvatarSuccess:function(t,e){this.imageUrl=this.domain+"/"+t.key,this.form.articleCover=this.domain+"/"+t.key},handleError:function(t){this.LOG.info(["err --- ",t])},beforeAvatarUpload:function(t){this.postData.key=(new Date).getTime()+t.name;var e="image/jpeg"===t.type,i="image/png"===t.type,a=t.size/1024/1024<5;return e||i||this.$message.error("上传图片只能是 JPG/PNG 格式!"),a||this.$message.error("上传图片大小不能超过 5MB!"),(e||i)&&a}}},d=u,m=(i("ad39"),i("2877")),f=Object(m["a"])(d,a,r,!1,null,"874ef370",null);e["default"]=f.exports},"204c":function(t,e,i){},"23f3":function(t,e,i){"use strict";i.d(e,"a",(function(){return r})),i.d(e,"b",(function(){return o})),i.d(e,"c",(function(){return n})),i.d(e,"d",(function(){return l})),i.d(e,"e",(function(){return s}));var a=i("b775");function r(t){return Object(a["a"])({url:"/admin/article/delete",method:"post",data:t})}function o(t){return Object(a["a"])({url:"/admin/article/selectByArticleDTO",method:"post",data:t})}function n(t){return Object(a["a"])({url:"/admin/article/add",method:"post",data:t})}function l(t){return Object(a["a"])({url:"/admin/article/update",method:"post",data:t})}function s(t){return Object(a["a"])({url:"/admin/article/updateAuditByKey",method:"post",data:t})}},3123:function(t,e,i){"use strict";i.d(e,"b",(function(){return r})),i.d(e,"a",(function(){return o}));var a=i("b775");function r(){return Object(a["a"])({url:"/system/getQiNiuUploadToken",method:"post"})}function o(){var t=["http://up.qiniup.com","https://up.qiniup.com"],e={url:t[0]},i=document.location.protocol;return e.url="https:"===i?t[1]:t[0],e}},"349c":function(t,e,i){"use strict";i.d(e,"c",(function(){return r})),i.d(e,"b",(function(){return o})),i.d(e,"d",(function(){return n})),i.d(e,"e",(function(){return l})),i.d(e,"a",(function(){return s}));var a=i("b775");function r(t){return Object(a["a"])({url:"/admin/organization/selectByQuery",method:"post",data:t})}function o(t){return Object(a["a"])({url:"/admin/organization/exportExcel",method:"post",data:t})}function n(t){return Object(a["a"])({url:"/admin/organization/addOrgnization",method:"post",data:t})}function l(t){return Object(a["a"])({url:"/admin/organization/updateOrgnization",method:"post",data:t})}function s(t){return Object(a["a"])({url:"/admin/organization/deleteOrgnization",method:"post",data:t})}},7142:function(t,e,i){"use strict";i("204c")},ad39:function(t,e,i){"use strict";i("cb00")},cb00:function(t,e,i){},db5f:function(t,e,i){"use strict";i.r(e);var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",[i("el-dialog",{attrs:{title:t.dialogTitle,visible:t.isVisible,width:"50%",top:"4vh","close-on-click-modal":!1,"close-on-press-escape":!1},on:{"update:visible":function(e){t.isVisible=e}}},[null!==t.entity?i("el-form",{ref:"form",attrs:{model:t.form,"label-width":"150",rules:t.rules}},[i("el-form-item",{attrs:{label:"学校名称："}},[i("el-input",{attrs:{readonly:""},model:{value:t.form.orgName,callback:function(e){t.$set(t.form,"orgName",e)},expression:"form.orgName"}})],1),i("el-form-item",{attrs:{label:"文章标题："}},[i("el-input",{attrs:{type:"textarea",readonly:""},model:{value:t.form.articleTitle,callback:function(e){t.$set(t.form,"articleTitle",e)},expression:"form.articleTitle"}})],1),i("el-form-item",{attrs:{label:"文章内容："}},[t.isVisible?i("tinymce",{attrs:{height:400,readonly:""},model:{value:t.form.articleContent,callback:function(e){t.$set(t.form,"articleContent",e)},expression:"form.articleContent"}}):t._e()],1),i("el-form-item",{attrs:{label:"文章封面："}},[i("el-image",{attrs:{src:t.form.articleCover}})],1),i("el-form-item",{attrs:{label:"审核："}},[i("el-switch",{staticStyle:{"margin-right":"5px"},attrs:{"active-color":"#13ce66","active-value":1,"inactive-color":"#909399","inactive-value":2},model:{value:t.form.status,callback:function(e){t.$set(t.form,"status",e)},expression:"form.status"}}),1===t.form.status?i("el-tag",{attrs:{size:"small",type:"success"}},[t._v("通过")]):i("el-tag",{attrs:{size:"small",type:"danger"}},[t._v("不通过")])],1),2===t.form.status?i("el-form-item",{attrs:{label:"请输入原因：",prop:"articleAuditFailureReason"}},[i("el-input",{attrs:{type:"textarea",placeholder:"请输入原因："},model:{value:t.form.articleAuditFailureReason,callback:function(e){t.$set(t.form,"articleAuditFailureReason",e)},expression:"form.articleAuditFailureReason"}})],1):t._e()],1):t._e(),i("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{directives:[{name:"loading",rawName:"v-loading",value:t.submitting,expression:"submitting"}],attrs:{type:"primary",disabled:t.submitting},on:{click:function(e){return t.audit()}}},[t._v("确定")])],1)],1)],1)},r=[],o=i("5530"),n=i("ad78"),l=i("8256"),s=i("23f3"),c={name:"AuditDialog",mixins:[n["a"]],props:{entity:{type:Object}},components:{Tinymce:l["a"]},data:function(){return{submitting:!1,form:{id:"",status:"",articleAuditFailureReason:"",orgName:"",articleTitle:"",articleContent:"",articleCover:""},rules:{articleAuditFailureReason:[{required:!0,message:"请输入审核失败原因",trigger:"blur"}]}}},computed:{dialogTitle:function(){return"公益文章审核"}},watch:{entity:function(t){t&&t.articleId&&(this.form.id=t.articleId,this.form.orgName=t.orgName,this.form.articleTitle=t.articleTitle,this.form.articleContent=t.articleContent,this.form.articleCover=t.articleCover,this.form.status=1)}},methods:{audit:function(){var t=this;this.$refs.form.validate((function(e){if(e){var i=Object(o["a"])({},t.form);t.submitting=!0,Object(s["e"])(Object(o["a"])({},i)).then((function(e){t.submitting=!1,e.success?(t.$refs["form"].resetFields(),t.$notify({title:"成功",message:"操作成功",type:"success"}),t.$emit("change"),t.isVisible=!1):t.$notify.error({title:"失败",message:e.failReasonShow||"操作失败"})}))}}))}}},u=c,d=(i("7142"),i("2877")),m=Object(d["a"])(u,a,r,!1,null,"4b50c922",null);e["default"]=m.exports},f9c5:function(t,e,i){"use strict";i.r(e);var a=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"list-view-layout"},[i("json-filter",{attrs:{code:t.titleCode}},[i("el-form",{attrs:{inline:!0}},[i("el-form-item",{attrs:{label:"标题"}},[i("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:t.param.articleTitle,callback:function(e){t.$set(t.param,"articleTitle",e)},expression:"param.articleTitle"}})],1),i("el-form-item",{attrs:{label:"学校名称"}},[i("el-input",{attrs:{placeholder:"请输入",clearable:""},model:{value:t.param.orgName,callback:function(e){t.$set(t.param,"orgName",e)},expression:"param.orgName"}})],1),i("el-form-item",[i("el-button",{on:{click:function(e){return t.doSearch()}}},[t._v("查询")]),i("permission-filter",{attrs:{code:"contentArticleAdd",type:"primary"},nativeOn:{click:function(e){return t.doAdd()}}})],1)],1)],1),i("json-table",{ref:"myJsonTable",attrs:{"table-heads":t.tableHeads,loading:t.listLoading,"table-data":t.tableData,page:t.page},on:{"current-change":t.currentPageChange},scopedSlots:t._u([{key:"articleCreateTime",fn:function(e){return[i("span",[t._v(t._s(t._f("formatDate")(e.rowData.scope.row.articleCreateTime,"yyyy-MM-dd HH:mm:ss")))])]}},{key:"articleStatus",fn:function(e){return[0===e.rowData.scope.row.articleStatus?i("el-tag",{attrs:{type:"primary"}},[t._v("未审核")]):t._e(),1===e.rowData.scope.row.articleStatus?i("el-tag",{attrs:{type:"success"}},[t._v("通过")]):t._e(),2===e.rowData.scope.row.articleStatus?i("el-tag",{attrs:{type:"danger"}},[t._v("不通过")]):t._e()]}},{key:"operations",fn:function(e){return[i("permission-filter",{attrs:{code:"contentArticleAudit",size:"mini",type:"primary"},nativeOn:{click:function(i){return t.doAudit(e.rowData.scope.row)}}}),i("permission-filter",{attrs:{code:"contentArticleEdit",size:"mini",type:"warning"},nativeOn:{click:function(i){return t.doEdit(e.rowData.scope.row)}}}),i("permission-filter",{attrs:{code:"contentArticleDelete",size:"mini",type:"danger"},nativeOn:{click:function(i){return t.doDelete(e.rowData.scope.row)}}})]}}])}),i("edit-dialog",{attrs:{entity:t.chooseEntity,"school-list":t.schoolList},on:{change:function(e){return t.handleChange()}},model:{value:t.dialogShow,callback:function(e){t.dialogShow=e},expression:"dialogShow"}}),i("audit-dialog",{attrs:{entity:t.chooseAuditEntity,"school-list":t.schoolList},on:{change:function(e){return t.handleChange()}},model:{value:t.auditDialogShow,callback:function(e){t.auditDialogShow=e},expression:"auditDialogShow"}})],1)},r=[],o=i("5530"),n=i("4303"),l=i("261a"),s=i("e101"),c=i("0fad"),u=i("db5f"),d=i("23f3"),m=i("349c"),f={components:{JsonFilter:n["a"],JsonTable:l["a"],PermissionFilter:s["a"],EditDialog:c["default"],AuditDialog:u["default"]},data:function(){return{titleCode:"contentArticle",tableHeads:[{prop:"articleTitle",label:"标题"},{prop:"articleOrderNumber",label:"排序"},{slot:!0,prop:"articleStatus",label:"审核"},{slot:!0,prop:"articleCreateTime",label:"创建时间"},{prop:"orgName",label:"学校名称"},{slot:!0,fixed:"right",prop:"operations",label:"操作",width:300}],listLoading:!1,tableData:[],param:{articleTitle:"",orgName:"",articleType:"0"},page:{limit:15,current:1,total:15},dialogShow:!1,chooseEntity:null,schoolList:[],auditParam:{id:"",status:""},auditDialogShow:!1,chooseAuditEntity:null}},computed:{},created:function(){this.getTableData(),this.getAllSchool()},methods:{getAllSchool:function(){var t=this;Object(m["c"])({page:1,limit:0}).then((function(e){e.success&&(t.schoolList=e.root)}))},getTableData:function(){var t=this;this.listLoading=!0,Object(d["b"])(Object(o["a"])(Object(o["a"])({},this.param),{},{page:this.page.current,limit:this.page.limit})).then((function(e){t.listLoading=!1,e.success?(t.tableData=e.root,t.page.total=e.totalSize):(t.tableData=[],t.page.total=0,t.$notify({title:"提示",message:e.failReasonShow||"未获取到数据",type:"warning"}))}))},currentPageChange:function(t){this.page.current=t,this.getTableData()},doSearch:function(){this.page.current=1,this.getTableData()},doDelete:function(t){var e=this;this.$confirm("确定删除？","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning",beforeClose:function(i,a,r){"confirm"===i?Object(d["a"])({id:t.articleId}).then((function(t){t.success?(e.$notify({title:"成功",message:"操作成功!",type:"success"}),e.getTableData()):e.$notify.error({title:"失败",message:t.failReasonShow||"操作失败"}),r()})).catch((function(t){e.$notify.error({title:"失败",message:"请求发生错误， 请重试"}),r()})):r()}})},handleChange:function(){this.getTableData()},doAdd:function(){this.chooseEntity=null===this.chooseEntity?{}:null,this.dialogShow=!0},doEdit:function(t){this.chooseEntity=t,t.uniquelyIdentifies=(new Date).getTime(),this.dialogShow=!0},doAudit:function(t){this.chooseAuditEntity=t,t.uniquelyIdentifies=(new Date).getTime(),this.auditDialogShow=!0}}},p=f,g=i("2877"),h=Object(g["a"])(p,a,r,!1,null,"d933603e",null);e["default"]=h.exports}}]);