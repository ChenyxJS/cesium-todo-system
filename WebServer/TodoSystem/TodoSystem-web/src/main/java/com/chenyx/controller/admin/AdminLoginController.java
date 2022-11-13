package com.chenyx.controller.admin;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.UserInfo;
import com.chenyx.entity.dto.UserInfoDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.service.IUserInfoService;
import com.chenyx.util.JsonResult;
import com.chenyx.util.SessionUtil;
import com.chenyx.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 登录相关
 *
 * @author chenyx
 * @since 2021-03-25
 */
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin")
public class AdminLoginController {
    @Resource
    private IUserInfoService service;
    @Resource
    private SessionUtil sessionUtil;

    /**
     * 登录
     *
     * @return JsonResult
     */
    @PostMapping(value = "/login")
    @MyLog(remark = "登录")
    public JsonResult login(@RequestParam(required = false) String account,
                            @RequestParam(required = false) String password,
                            HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        if (StringUtil.isEmpty(account)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_ACCOUNT_EMPTY, "账号不可为空");
            return jsonResult;
        }
        if (StringUtil.isEmpty(password)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_PASSWORD_EMPTY, "密码不可为空");
            return jsonResult;
        }
        return service.login(account, password, session);
    }

    /**
     * 获取登录人全部角色信息
     *
     * @return JsonResult
     */
    @PostMapping(value = "/getLoginRoleList")
    @MyLog(remark = "获取登录人全部角色信息")
    public JsonResult getLoginRoleList(HttpSession session, @RequestParam(required = false) Long loginOrganizationId) {
        return service.getLoginRoles(session,null);
    }


    /**
     * 获取登录用户信息
     *
     * @return JsonResult
     */
    @PostMapping(value = "/getLoginEmp")
    @MyLog(remark = "获取登录用户信息")
    public JsonResult getLoginEmp(HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.buildTrueNew(SessionUtil.getLoginUser(session));
        return jsonResult;
    }

    /**
     * 获取登录用户登录角色信息
     *
     * @return JsonResult
     */
    @PostMapping(value = "/getLoginRole")
    @MyLog(remark = "获取登录用户登录角色信息")
    public JsonResult getLoginRole(HttpSession session) {
        return service.getLoginRole(session);
    }

    /**
     * 获取登录用户权限
     *
     * @param loginRoleId 角色id
     * @return JsonResult
     */
    @PostMapping(value = "/getLoginPer")
    @MyLog(remark = "获取登录用户权限")
    public JsonResult getLoginPer(HttpSession session, @RequestParam(required = false) Long loginRoleId) {
        return service.getLoginPer(session, loginRoleId);
    }

    /**
     * 选择登录用户角色
     *
     * @param roleId 角色id
     * @return JsonResult
     */
    @PostMapping(value = "/settingLoginRole")
    @MyLog(remark = "选择登录用户角色")
    public JsonResult settingLoginRole(HttpSession session, @RequestParam(required = false) Long roleId) {
        return service.settingLoginRole(session, roleId);
    }


    /**
     * 退出登录
     *
     * @return JsonResult
     */
    @PostMapping(value = "/logOut")
    @MyLog(remark = "退出登录")
    public JsonResult logOut(HttpSession session) {
        SessionUtil.removeLoginInfo(session);
        // 删除权限信息
        sessionUtil.removeLoginPer(session);
        JsonResult jsonResult = new JsonResult();
        jsonResult.buildTrueNew();
        return jsonResult;
    }

    /**
     * 修改登录人员信息
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @PostMapping(value = "/updateLoginInfo")
    @MyLog(remark = "修改登录运营商工作人员信息")
    public JsonResult updateLoginInfo(@ModelAttribute UserInfoDTO record,
                                      HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        UserInfo userInfo = SessionUtil.getLoginUser(session);
        if (userInfo == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "未查询到人员信息");
            return jsonResult;
        }
        record.setUserId(userInfo.getUserId());
        record.setUserAccount(null);
        record.setUserPassword(null);
        record.setUserSalt(null);
        record.setUserStatus(null);

        return service.updateByKey(record);
    }

    /**
     * 表单提交 Date类型数据绑定
     * <功能详细描述>
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
