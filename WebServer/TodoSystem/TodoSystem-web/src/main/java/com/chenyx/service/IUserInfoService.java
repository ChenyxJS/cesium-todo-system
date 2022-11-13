package com.chenyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenyx.entity.UserInfo;
import com.chenyx.entity.dto.UserInfoDTO;
import com.chenyx.entity.query.UserInfoQuery;
import com.chenyx.util.JsonResult;

import javax.servlet.http.HttpSession;

/**
 * 用户信息 服务类
 *
 * @author 空城
 * @since 2021-07-29
 */
public interface IUserInfoService extends IService<UserInfo> {
    /**
     * 分页查询用户信息
     *
     * @return JsonResult
     */
    JsonResult selectAll(UserInfoQuery query);

    /**
     * 根据主键删除用户信息
     *
     * @param id 主键
     * @return JsonResult
     */
    JsonResult deleteByKey(Long id);

    /**
     * 根据主键修改用户信息
     *
     * @param record 修改信息
     * @return JsonResult
     */
    JsonResult updateByKey(UserInfoDTO record);

    /**
     * 插入用户信息
     *
     * @param record 插入信息
     * @return JsonResult
     */
    JsonResult add(UserInfoDTO record);

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     * @param session  会话信息
     * @return JsonResult
     */
    JsonResult login(String account, String password, HttpSession session);



    /**
     * 获取登录用户登录角色信息
     *
     * @param session 会话信息
     * @return JsonResult
     */
    JsonResult getLoginRole(HttpSession session);

    /**
     * 获取登录用户登录角色信息
     *
     * @param session 会话信息
     * @return JsonResult
     */
    JsonResult getLoginRoles(HttpSession session, Long orgId);

    /**
     * 获取登录用户权限
     *
     * @param session 会话信息
     * @param roleId  角色id
     * @return JsonResult
     */
    JsonResult getLoginPer(HttpSession session, Long roleId);


    /**
     * 选择登录用户角色
     *
     * @param session 会话信息
     * @param roleId  角色id
     * @return JsonResult
     */
    JsonResult settingLoginRole(HttpSession session, Long roleId);

    /**
     * 修改密码
     *
     * @param oldPassWord     旧密码
     * @param newPassWord     新密码
     * @param confirmPassWord 确认密码
     * @param empId           用户id
     * @param jsonResult      返回信息
     */
    void updatePassWordByPrimaryKeySelective(String oldPassWord, String newPassWord, String confirmPassWord, Long empId, JsonResult jsonResult);




    /**
     * 用户修改个人信息
     *
     * @param userId   要修改的用户id
     * @param userName 昵称
     * @param phone    手机号
     * @param msgCode
     * @param headImg  头像
     * @return JsonResult
     */
    JsonResult updateLoginUserInfo(Long userId, String userName, String phone, String msgCode, String headImg, Integer sex, String realName);

    JsonResult register(String userName, String phone, Integer sex, String account, String password);


//    JsonResult getUserListByClubId(Long clubId);
}
