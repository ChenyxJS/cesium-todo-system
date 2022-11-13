package com.chenyx.controller.admin;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.UserInfo;
import com.chenyx.entity.dto.UserInfoDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.UserInfoQuery;
import com.chenyx.service.IUserInfoService;
import com.chenyx.util.JsonResult;
import com.chenyx.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户信息 接口类
 *
 * @author 苏
 * @since 2021-10-28
 */
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/userInfo")
public class AdminUserInfoController {
    @Resource
    private IUserInfoService service;

    /**
     * 分页查询用户信息
     *
     * @return JsonResult
     */
    @MyLog(remark = "分页查询用户信息")
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute UserInfoQuery query,
                                      HttpSession session) {
        return service.selectAll(query);
    }


    /**
     * 根据主键删除用户信息
     *
     * @param id 主键
     * @return JsonResult
     */
    @MyLog(remark = "根据主键删除用户信息")
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                             HttpSession session) {
        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改用户信息
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @MyLog(remark = "根据主键修改用户信息")
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute UserInfoDTO record,
                             HttpSession session) {
        record.setUserAccount(null);
        record.setUserPassword(null);
        record.setUserSalt(null);
        return service.updateByKey(record);
    }

    /**
     * 插入用户信息
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @MyLog(remark = "插入用户信息")
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute UserInfoDTO record,
                          HttpSession session) {
        return service.add(record);
    }

    /**
     * 修改登录用户密码
     *
     * @param oldPassWord     旧密码
     * @param newPassWord     新密码
     * @param confirmPassWord 确认密码
     * @return JsonResult
     */
    @PostMapping(value = "/updatePassWord")
    @MyLog(remark = "修改登录用户密码")
    public JsonResult updatePassWord(@RequestParam(required = false, defaultValue = "") String oldPassWord,
                                     @RequestParam(required = false, defaultValue = "") String newPassWord,
                                     @RequestParam(required = false, defaultValue = "") String confirmPassWord,
                                     HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        UserInfo userInfo = SessionUtil.getLoginUser(session);
        if (userInfo == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "未查询到人员信息");
            return jsonResult;
        }
        service.updatePassWordByPrimaryKeySelective(oldPassWord, newPassWord, confirmPassWord, userInfo.getUserId(), jsonResult);
        return jsonResult;
    }

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
