package com.chenyx.controller.admin;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.dto.PermissionDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.PermissionQuery;
import com.chenyx.service.IPermissionService;
import com.chenyx.util.JsonResult;
import com.chenyx.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 权限表 接口类
 *
 * @author 苏
 * @since 2021-10-28
 */
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/permission")
public class AdminPermissionController {
    @Resource
    private IPermissionService service;

    /**
     * 分页查询权限表
     *
     * @return JsonResult
     */
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute PermissionQuery query,
                                      HttpSession session) {
        return service.selectAll(query);
    }

    /**
     * 根据主键删除权限表
     *
     * @param id 主键
     * @return JsonResult
     */
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                             HttpSession session) {
        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改权限表
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute PermissionDTO record,
                             HttpSession session) {
        return service.updateByKey(record);
    }

    /**
     * 插入权限表
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute PermissionDTO record,
                          HttpSession session) {
        return service.add(record);
    }

    /**
     * 给角色配置权限
     *
     * @param ids    权限id列表（逗号隔开）
     * @param roleId 角色id
     * @return JsonResult
     */
    @PostMapping(value = "/settingPermissionToRole")
    @Transactional
    @MyLog(remark = "给角色配置权限")
    public JsonResult settingPermissionToRole(@RequestParam(required = false) String ids,
                                              @RequestParam Long roleId,
                                              HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtil.isEmpty(roleId)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "请选择角色信息");
            return jsonResult;
        }
        return service.settingPermissionToRole(ids, roleId);
    }

//    /**
//     * 给用户配置权限
//     *
//     * @param ids    权限id列表（逗号隔开权限，-隔开权限id和是否拥有）
//     * @param userId 用户id
//     * @return JsonResult
//     */
//    @PostMapping(value = "/settingPermissionToUser")
//    @Transactional
//    @MyLog(remark = "给用户配置权限")
//    public JsonResult settingPermissionToUser(@RequestParam(required = false) String ids,
//                                              @RequestParam Long userId,
//                                              HttpSession session) {
//        JsonResult jsonResult = new JsonResult();
//        if (StringUtil.isEmpty(userId)) {
//            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "请选择用户信息");
//            return jsonResult;
//        }
//        return service.settingPermissionToUser(ids, userId);
//    }

    /**
     * 表单提交 Date类型数据绑定
     *
     * @param binder 网页数据绑定
     * @see [类、类#方法、类#成员]
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
