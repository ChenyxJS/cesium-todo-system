package com.chenyx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.*;
import com.chenyx.entity.dto.UserInfoDTO;
import com.chenyx.entity.dto.UserRoleDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.enums.UserInfoStatusEnum;
import com.chenyx.entity.query.*;
import com.chenyx.mapper.UserInfoMapper;
import com.chenyx.service.*;
import com.chenyx.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 用户信息 服务实现类
 *
 * @author chenyx
 * @since 2021-07-29
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private IUserInfoService userInfoService;
    @Resource
    private IUserRoleService userRoleService;
    @Resource
    private IRoleService roleService;
    @Resource
    private ISystemConfigService systemConfigService;
    @Resource
    private IMiniProgramService miniProgramService;


    @Value("${myConfig.userDefaultHeadImg}")
    private String userDefaultHeadImg;

    /**
     * 分页查询用户信息
     *
     * @return JsonResult
     */
    @Override
    public JsonResult selectAll(UserInfoQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<UserInfo> page = query.buildPage(this);
        List<UserInfoDTO> records = EntityUtil.parentListToChildList(page.getRecords(), UserInfoDTO.class);
        if (ListUtil.isNotEmpty(records)) {
            jsonResult.buildTrueNew(page.getTotal(), records);
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除用户信息
     *
     * @param id 主键
     * @return JsonResult
     */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if (removeById(id)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改用户信息
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @Override
    public JsonResult updateByKey(UserInfoDTO record) {
        JsonResult jsonResult = new JsonResult();
        if (updateById(record)) {
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入用户信息
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @Override
    public JsonResult add(UserInfoDTO record) {
        JsonResult jsonResult = new JsonResult();
        initUser(record);
        // 验证账号重复性
        UserInfoQuery userInfoQuery = new UserInfoQuery();
        userInfoQuery.setParam(new UserInfoDTO());
        userInfoQuery.getParam().setUserAccount(record.getUserAccount());
        if (count(userInfoQuery.buildExample()) > 0) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "人员账号重复");
            return jsonResult;
        }
        if (save(record)) {
            jsonResult.buildTrueNew();
            jsonResult.setTip(record.getUserId().toString());
        }
        return jsonResult;
    }

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     * @param session  会话信息
     * @return JsonResult
     */
    @Override
    public JsonResult login(String account, String password, HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        // 根据账号信息获取员工信息
        UserInfoQuery userInfoQuery = new UserInfoQuery();
        userInfoQuery.setParam(new UserInfoDTO());
        userInfoQuery.getParam().setUserAccount(account);
        List<UserInfo> userInfos = list(userInfoQuery.buildExample());
        if (ListUtil.isEmpty(userInfos)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "账号不存在");
            return jsonResult;
        }
        if (userInfos.size() > 1) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "账号信息错误，请联系管理员");
            return jsonResult;
        }
        // 验证密码
        UserInfo userInfo = userInfos.get(0);
        if (!verifyPassword(password, userInfo)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "密码错误");
            return jsonResult;
        }

        // 登录
        jsonResult = loginUtil(session, userInfo, null, null);
        if (!jsonResult.isSuccess()) {
            return jsonResult;
        }
        jsonResult.buildTrueNew();
        return jsonResult;
    }


    /**
     * 验证密码
     *
     * @param password 输入的密码
     * @param userInfo 要验证的人员信息
     * @return 是否验证成功
     */
    private boolean verifyPassword(String password, UserInfo userInfo) {
        // 密码加密验证
        password = StringUtil.getSaltMd5(password, userInfo.getUserSalt());
        return StringUtil.isNotEmpty(userInfo.getUserPassword()) && userInfo.getUserPassword().equals(password);
    }

    /**
     * 登录信息保存（权限验证以及查询）
     *
     * @param session  会话信息
     * @param userInfo 登录人员信息
     * @return JsonResult
     */
    private JsonResult loginUtil(HttpSession session, UserInfo userInfo, Long lastOrgId, Long lastRoleId) {
        JsonResult jsonResult = new JsonResult();
        // 注销验证
        if (UserInfoStatusEnum.STATUS_cancellation.getCode().equals(userInfo.getUserStatus())) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "账号已被注销");
            return jsonResult;
        }
        userInfo.setUserPassword(null);
        userInfo.setUserSalt(null);
        // 查询人员角色信息
        UserRoleQuery userRoleQuery = new UserRoleQuery();
        userRoleQuery.setParam(new UserRoleDTO());
        userRoleQuery.setLimit(0);
        userRoleQuery.getParam().setUsroUserId(userInfo.getUserId());
        List<UserRole> userRoles = userRoleService.list(userRoleQuery.buildExample());

//        if (ListUtil.isEmpty(userRoles)) {
//            jsonResult.setRoot(null);
//            jsonResult.setObject(userInfo);
//            jsonResult.buildTrueNew();
//            return jsonResult;
//        }

        List<Long> roleIds = new ArrayList<>();
        List<Long> orgIds = new ArrayList<>();
        userRoles.forEach(v -> {
            roleIds.add(v.getUsroRoleId());
            orgIds.add(v.getUsroOrgId());
        });
        if (ListUtil.isEmpty(roleIds)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "账号无角色权限");
            return jsonResult;
        }
        RoleQuery roleQuery = new RoleQuery();
        roleQuery.setRoleIds(roleIds);
        roleQuery.setLimit(0);
        List<Role> roles = roleService.list(roleQuery.buildExample());
        if (ListUtil.isEmpty(roles)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "账号无角色权限");
            return jsonResult;
        }
        // 为每一个关联信息创建一个对应的角色
        List<Role> roleSession = new ArrayList<>();
        Map<Long, Role> roleMap = new HashMap<>();
        roles.stream().forEach(v -> roleMap.put(v.getRoleId(), v));
        userRoles.stream().forEach(v -> {
            Role role = JsonFormatUtil.stringToObject(JsonFormatUtil.objectToString(roleMap.get(v.getUsroRoleId())), Role.class);
            role.setOrgId(v.getUsroOrgId());
            roleSession.add(role);
        });
        // 将角色信息保存到session中
        SessionUtil.setLoginRole( roles.get(0),session);
        SessionUtil.setLoginRoles( roleSession,session);
        SessionUtil.setLoginInfoToSession(session, userInfo);
        settingLoginRole(session, roles.get(0).getRoleId());

        jsonResult.setRoot(null);
        jsonResult.setObject(userInfo);
        jsonResult.buildTrueNew();
        return jsonResult;
    }

    /**
     * 获取登录用户登录角色信息
     *
     * @param session 会话信息
     * @return JsonResult
     */
    @Override
    public JsonResult getLoginRole(HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.buildTrueNew(SessionUtil.getLoginRole(session));
        return jsonResult;
    }

    /**
     * 获取登录用户登录角色信息
     *
     * @param session 会话信息
     * @return JsonResult
     */
    @Override
    public JsonResult getLoginRoles(HttpSession session, Long orgId) {
        JsonResult jsonResult = new JsonResult();
        List<Role> roles = SessionUtil.getLoginRoles(session);
        if (ListUtil.isEmpty(roles)) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
            return jsonResult;
        }

        if (StringUtil.isEmpty(orgId)) {
            jsonResult.buildTrueNew(0, roles);
            return jsonResult;
        } else {
            List<Role> result = new ArrayList<>();
            roles.stream().forEach(v -> {
                if (orgId.equals(v.getOrgId())) {
                    result.add(v);
                }
            });
            jsonResult.buildTrueNew(0, result);
            return jsonResult;
        }
    }

    /**
     * 获取登录用户权限
     *
     * @param session 会话信息
     * @param roleId  角色id
     * @return JsonResult
     */
    @Override
    public JsonResult getLoginPer(HttpSession session, Long roleId) {
        JsonResult jsonResult = new JsonResult();
        List<Permission> permissions = sessionUtil.getLoginPer(session, roleId);
        if (ListUtil.isNotEmpty(permissions)) {
            jsonResult.buildTrueNew(0, permissions);
            return jsonResult;
        } else {
            Role role = SessionUtil.getLoginRole(session);
            if (role == null) {
                jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_LOGIN_INFO, "请先选择登录角色");
                return jsonResult;
            }
            // 根据角色获取权限
            jsonResult = roleService.getPermissionTreeByRoleId(roleId);
            if (!jsonResult.isSuccess()) {
                return jsonResult;
            }
        }
        jsonResult = sessionUtil.setLoginPer(jsonResult.getRoot(), session);
        if (!jsonResult.isSuccess()) {
            return jsonResult;
        }
        return jsonResult;
    }



    /**
     * @param session 会话信息
     * @param roleId  角色id
     * @return
     */
    @Override
    public JsonResult settingLoginRole(HttpSession session, Long roleId) {
        JsonResult jsonResult = new JsonResult();
        UserInfo userInfo = SessionUtil.getLoginUser(session);
        if (userInfo == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_NO_LOGIN);
            return jsonResult;
        }

        UserRoleQuery userRoleQuery = new UserRoleQuery();
        userRoleQuery.setParam(new UserRoleDTO());
        userRoleQuery.setLimit(0);
        userRoleQuery.getParam().setUsroRoleId(roleId);
        userRoleQuery.getParam().setUsroUserId(userInfo.getUserId());
        if (userRoleService.count(userRoleQuery.buildExample()) == 0) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_SETTING_ROLE_NO_ROLE_INFO);
            return jsonResult;
        }
        // 查询角色信息
        Role role = roleService.getById(roleId);
        if (role == null) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_SETTING_ROLE_NO_ROLE_INFO);
            return jsonResult;
        }
        SessionUtil.setLoginRole(role, session);
        // 查询该角色权限，并设置权限
        jsonResult = roleService.getPermissionTreeByRoleId(roleId);
        if (jsonResult.isSuccess()) {
            jsonResult = sessionUtil.setLoginPer(jsonResult.getRoot(), session);
            if (!jsonResult.isSuccess()) {
                return jsonResult;
            }
        }

        jsonResult.buildTrueNew(role);
        return jsonResult;
    }

    /**
     * @param userInfo
     */
    private void initUser(UserInfo userInfo) {

        userInfo.setUserSalt(StringUtil.getRandNumStr(4));
        //默认使用手机号作为密码
        if (StringUtil.isEmpty(userInfo.getUserPassword())) {
            userInfo.setUserPassword(StringUtil.getSaltMd5(userInfo.getUserPhone(), userInfo.getUserSalt()));
        } else {
            userInfo.setUserPassword(StringUtil.getSaltMd5(userInfo.getUserPassword(), userInfo.getUserSalt()));
        }
        if (StringUtil.isEmpty(userInfo.getUserAccount())) {
            userInfo.setUserAccount(userInfo.getUserPhone());
        }
    }

    /**
     * @param oldPassWord     旧密码
     * @param newPassWord     新密码
     * @param confirmPassWord 确认密码
     * @param empId           用户id
     * @param jsonResult      返回信息
     */
    @Override
    public void updatePassWordByPrimaryKeySelective(String oldPassWord, String newPassWord, String confirmPassWord, Long empId, JsonResult jsonResult) {
        // 检查新旧密码是否相同
        if (oldPassWord == null || "".equals(oldPassWord) || newPassWord == null || "".equals(newPassWord) || confirmPassWord == null || "".equals(confirmPassWord)) {
            jsonResult.setFailReason("请填写完整");
            return;
        }
        if (oldPassWord.equals(newPassWord)) {
            jsonResult.setFailReason("新密码不能与旧密码相同");
            return;
        }
        if (!newPassWord.equals(confirmPassWord)) {
            jsonResult.setFailReason("新密码的两次输入不相同");
            return;
        }
        //获取用户信息
        String salt = "";
        UserInfo userInfo = getById(empId);
        if (userInfo != null) {
            salt = userInfo.getUserSalt();
        } else {
            return;
        }

        // 检查旧密码是否正确
        // 检查旧密码是否正确
        if (StringUtil.isNotEmpty(userInfo.getUserPassword()) && userInfo.getUserPassword().equals(StringUtil.getSaltMd5(oldPassWord, salt))) {
            UserInfo userInfoForUpdate = new UserInfo();
            userInfoForUpdate.setUserId(empId);
            userInfoForUpdate.setUserPassword(StringUtil.getSaltMd5(newPassWord, salt));
            // 判断修改结果
            if (updateById(userInfoForUpdate)) {
                // 修改成功
                jsonResult.setSuccess(true);
                return;
            }
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "密码错误");
        }
    }




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
    @Override
    public JsonResult updateLoginUserInfo(Long userId, String userName, String phone, String msgCode, String headImg, Integer sex, String realName) {
        JsonResult jsonResult = new JsonResult();
        // 首先修改用户信息
        UserInfo user = new UserInfo();
        user.setUserId(userId);
        boolean userHaveUpdate = false;
        if (StringUtil.isNotEmpty(userName)) {
            user.setUserNickname(userName);
            userHaveUpdate = true;
        }
        if (StringUtil.isNotEmpty(realName)) {
            user.setUserRealName(realName);
            userHaveUpdate = true;
        }
        if (StringUtil.isNotEmpty(sex)) {
            user.setUserSex(sex);
            userHaveUpdate = true;
        }
        if (StringUtil.isNotEmpty(headImg)) {
            user.setUserHeadImg(headImg);
            userHaveUpdate = true;
        }
        if (StringUtil.isNotEmpty(phone)) {
            userHaveUpdate = true;


            // 验证手机号重复
            UserInfoQuery userQueryT = new UserInfoQuery();
            userQueryT.setParam(new UserInfoDTO());
            userQueryT.getParam().setUserPhone(phone);
            userQueryT.buildExample();
            userQueryT.getQuery().ne("user_id", user.getUserId());
            if (count(userQueryT.getQuery()) > 0) {
                jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_PARAMETER_ERROR, "修改失败，手机号已经在被其他用户使用");
                return jsonResult;
            }
            user.setUserPhone(phone);
        }

        if (userHaveUpdate) {
            if (!updateById(user)) {
                jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_UPDATE_ERROR, "修改失败，请重新尝试");
                return jsonResult;
            }
        }
        jsonResult.buildTrueNew();
        return jsonResult;
    }

    @Override
    public JsonResult register(String userName, String phone, Integer sex, String account, String password) {
        JsonResult jsonResult = new JsonResult();
        UserInfoDTO userInfo = new UserInfoDTO();
        userInfo.setUserNickname(userName);
        userInfo.setUserRealName(userName);
        userInfo.setUserSex(sex);
        userInfo.setUserPhone(phone);
        userInfo.setUserAccount(account);
        userInfo.setUserHeadImg("https://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoDFS53ziczCp40N5mOhJ1qzp3OpZH1Ocu8cxNwMWINpZgverJJ1wpZ6bYzl6OF8YECvPbg0gf0VoQ/132");
        userInfo.setUserCreateTime(new Date());
//        userInfo.setUserSalt("aaaa");
        userInfo.setUserPassword(password);
        JsonResult add = userInfoService.add(userInfo);
        Long id = Long.parseLong(add.getTip());
        UserRole userRole = new UserRole();
        userRole.setUsroUserId(id);
        userRole.setUsroRoleId(3L);
        if(userRoleService.save(userRole)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }


}
