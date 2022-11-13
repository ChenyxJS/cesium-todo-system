//package com.jtexplorer.util;
//
//import com.jtexplorer.entity.Organization;
//import com.jtexplorer.entity.Permission;
//import com.jtexplorer.entity.Role;
//import com.jtexplorer.entity.UserInfo;
//import com.jtexplorer.entity.enums.RequestEnum;
//import com.jtexplorer.redis.RedisCache;
//import org.springframework.beans.factory.annotation.Value;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * session 登录用户工具类
// *
// * @author 苏友朋
// * @date 2016/7/10
// */
//public class MPSessionUtil {
//    @Resource
//    private RedisCache redisCache;
//    @Value("${myConfig.redis.timeout}")
//    private long timeout;
//    /**
//     * 将登录信息存储到session中
//     *
//     * @param session 会话
//     * @param userInfo 登录人员信息
//     * @param organization 登录运营商信息
//     * @param role 登录角色信息
//     */
//    public static void setLoginInfoToSession(HttpSession session, UserInfo userInfo, Organization organization, Role role) {
//        setLoginUser(userInfo,session);
//        setLoginOrganization(organization,session);
//        setLoginRole(role,session);
//        List<Role> roles = new ArrayList<>();
//        roles.add(role);
//        setLoginRoles(roles,session);
//    }
//
//    /**
//     * 将登录信息存储到session中
//     *
//     * @param session 会话
//     * @param userInfo 登录人员信息
//     * @param organization 登录运营商信息
//     * @param role 登录人员全部角色信息
//     */
//    public static void setLoginInfoToSession(HttpSession session, UserInfo userInfo, Organization organization, List<Role> role) {
//        setLoginUser(userInfo,session);
//        setLoginOrganization(organization,session);
//        setLoginRoles(role,session);
//    }
//
//    /**
//     * 将登录信息存储到session中
//     *
//     * @param session 会话
//     * @param userInfo 登录人员信息
//     * @param organization 登录运营商信息
//     */
//    public static void setLoginInfoToSession(HttpSession session, UserInfo userInfo, Organization organization) {
//        setLoginUser(userInfo,session);
//        setLoginOrganization(organization,session);
//    }
//
//    /**
//     * 将登录信息取出
//     *
//     * @param session 会话
//     */
//    public static Map<String,Object> getLoginInfoBySession(HttpSession session) {
//        Map<String,Object> map = new HashMap<>();
//        map.put(SessionUtil.LoginEnum.LOGIN_EMP.getCode(),getLoginUser(session));
//        map.put(SessionUtil.LoginEnum.LOGIN_Organization.getCode(),getLoginOrganization(session));
//        map.put(SessionUtil.LoginEnum.LOGIN_ROLE.getCode(),getLoginRole(session));
//        map.put(SessionUtil.LoginEnum.LOGIN_ROLES.getCode(),getLoginRoles(session));
//        return map;
//    }
//
//
//    /**
//     * 获取登录用户信息
//     *
//     * @return JsonResult
//     */
//    public static UserInfo getLoginUser(HttpSession session) {
//        return (UserInfo) session.getAttribute(SessionUtil.LoginEnum.LOGIN_EMP.getCode());
//    }
//
//    /**
//     * 获取登录用户绑定组织信息
//     *
//     * @return JsonResult
//     */
//    public static Organization getLoginOrganization(HttpSession session) {
//        return (Organization) session.getAttribute(SessionUtil.LoginEnum.LOGIN_Organization.getCode());
//    }
//
//    /**
//     * 获取登录用户登录角色信息
//     *
//     * @return JsonResult
//     */
//    public static Role getLoginRole(HttpSession session) {
//        return (Role) session.getAttribute(SessionUtil.LoginEnum.LOGIN_ROLE.getCode());
//    }
//
//    /**
//     * 获取登录用户全部角色信息
//     *
//     * @return JsonResult
//     */
//    public static List<Role> getLoginRoles(HttpSession session) {
//        return (List<Role>) session.getAttribute(SessionUtil.LoginEnum.LOGIN_ROLES.getCode());
//    }
//
//    /**
//     * 获取登录用户权限
//     *
//     * @return JsonResult
//     */
//    public List<Permission> getLoginPer(HttpSession session) {
//        List<Permission> permissions = redisCache.getCacheObject(SessionUtil.LoginEnum.LOGIN_PERMISSION.getCode()+":"+session.getId(), ArrayList.class);
//        return permissions;
//    }
//
//
//    /**
//     * 写入登录用户信息
//     *
//     *
//     */
//    public static void setLoginUser(UserInfo userInfo,HttpSession session) {
//        session.setAttribute(SessionUtil.LoginEnum.LOGIN_EMP.getCode(), userInfo);
//    }
//
//    /**
//     * 写入登录用户绑定组织信息
//     *
//     *
//     */
//    public static void setLoginOrganization(Organization organization,HttpSession session) {
//        session.setAttribute(SessionUtil.LoginEnum.LOGIN_Organization.getCode(), organization);
//    }
//
//    /**
//     * 写入登录用户登录角色信息
//     *
//     *
//     */
//    public static void setLoginRole(Role role,HttpSession session) {
//        session.setAttribute(SessionUtil.LoginEnum.LOGIN_ROLE.getCode(), role);
//    }
//
//    /**
//     * 写入登录用户全部角色信息
//     *
//     *
//     */
//    public static void setLoginRoles(List<Role> roles,HttpSession session) {
//        session.setAttribute(SessionUtil.LoginEnum.LOGIN_ROLES.getCode(), roles);
//    }
//
//    /**
//     * 写入登录用户权限
//     *
//     *
//     */
//    public void setLoginPer(List<Permission> permissions,HttpSession session) {
//        redisCache.setCacheObject(SessionUtil.LoginEnum.LOGIN_PERMISSION.getCode()+":"+session.getId(),permissions);
//        redisCache.expire(SessionUtil.LoginEnum.LOGIN_PERMISSION.getCode()+":"+session.getId(),timeout);
//    }
//
//
//
//
//    /**
//     * 清除登录信息
//     *
//     *
//     */
//    public static void removeLoginInfo(HttpSession session) {
//        removeLoginUser(session);
//        removeLoginOrganization(session);
//        removeLoginRole(session);
//        removeLoginRoles(session);
//    }
//
//    /**
//     * 清除登录用户信息
//     *
//     *
//     */
//    public static void removeLoginUser(HttpSession session) {
//        session.removeAttribute(SessionUtil.LoginEnum.LOGIN_EMP.getCode());
//    }
//
//    /**
//     * 清除登录用户绑定组织信息
//     *
//     *
//     */
//    public static void removeLoginOrganization(HttpSession session) {
//        session.removeAttribute(SessionUtil.LoginEnum.LOGIN_Organization.getCode());
//    }
//
//    /**
//     * 清除登录用户登录角色信息
//     *
//     *
//     */
//    public static void removeLoginRole(HttpSession session) {
//        session.removeAttribute(SessionUtil.LoginEnum.LOGIN_ROLE.getCode());
//    }
//
//    /**
//     * 清除登录用户全部角色信息
//     *
//     *
//     */
//    public static void removeLoginRoles(HttpSession session) {
//        session.removeAttribute(SessionUtil.LoginEnum.LOGIN_ROLES.getCode());
//    }
//
//    /**
//     * 清除登录用户权限
//     *
//     *
//     */
//    public void removeLoginPer(HttpSession session) {
//        redisCache.deleteObject(SessionUtil.LoginEnum.LOGIN_PERMISSION.getCode()+":"+session.getId());
//    }
//
//    public static boolean verifyLoginUser(HttpSession session,JsonResult jsonResult){
//        UserInfo userInfo = getLoginUser(session);
//        if(userInfo == null){
//            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_NO_LOGIN);
//            return false;
//        }
//        if(StringUtil.isEmpty(userInfo.getUserId())){
//            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_NO_LOGIN);
//            return false;
//        }
//        jsonResult.buildTrueNew();
//        return true;
//    }
//
//    public static boolean verifyLoginOrganization(HttpSession session,JsonResult jsonResult){
//        Organization organization = getLoginOrganization(session);
//        if(organization == null){
//            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_NO_LOGIN,"无登录运营商");
//            return false;
//        }
//        if(StringUtil.isEmpty(organization.getOrgId())){
//            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_NO_LOGIN,"无登录运营商");
//            return false;
//        }
//        jsonResult.buildTrueNew();
//        return true;
//    }
//
//    public static boolean verifyLogin(HttpSession session,JsonResult jsonResult){
//        if(!verifyLoginUser(session,jsonResult)){
//            return false;
//        }
//        if(!verifyLoginOrganization(session,jsonResult)){
//            return false;
//        }
//        return true;
//    }
//
//    public enum MPLoginEnum {
//        /**
//         * 用于分辨
//         */
//        LOGIN_EMP("登录人信息", "MPUserInfo"),
//        LOGIN_ROLE("登录人选择角色信息", "role"),
//        LOGIN_ROLES("登录人全部角色信息", "roles"),
//        LOGIN_Organization("登录人登录机构信息 ", "organization"),
//        LOGIN_PERMISSION("登录人选择角色绑定的权限信息 ", "permission"),
//        ;
//
//        /**
//         * 说明
//         */
//        private String meaning;
//
//        /**
//         * 代码
//         */
//        private String code;
//
//        public String getMeaning() {
//            return meaning;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        MPLoginEnum(String meaning, String code) {
//            this.meaning = meaning;
//            this.code = code;
//        }
//    }
//}
