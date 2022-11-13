package com.chenyx.filter.permission;

import com.alibaba.fastjson.JSON;
import com.chenyx.entity.Organization;
import com.chenyx.entity.Role;
import com.chenyx.entity.UserInfo;
import com.chenyx.service.IPermissionService;
import com.chenyx.util.JsonResult;
import com.chenyx.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class MiniPermissionFilter extends OncePerRequestFilter {
//    @Resource
    private IPermissionService permissionService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        permissionService = WebApplicationContextUtils.getWebApplicationContext(httpServletRequest.getServletContext()).getBean(IPermissionService.class);
        String uri = httpServletRequest.getRequestURI();
        // 只拦截uri存在于权限中的
        JsonResult jsonResult = permissionService.verifyMiniPermissionHave(httpServletRequest.getSession(), uri);
        if (!jsonResult.isSuccess()) {
            try {
                httpServletResponse.setContentType("application/json");
                httpServletResponse.setCharacterEncoding("UTF-8");
                PrintWriter writer = httpServletResponse.getWriter();
                writer.write(JSON.toJSONString(jsonResult));
                return;
            } catch (IOException e) {
                // 重定向
                httpServletResponse.sendRedirect("/admin/error/permissionFilterErrorReturn");
            }
        } else {
            Map paramter = new HashMap(16);
            Map<String, Object> map = SessionUtil.getLoginInfoBySession(httpServletRequest.getSession());
            Role role = (Role) map.get(SessionUtil.LoginEnum.LOGIN_ROLE.getCode());
            Organization organization = (Organization) map.get(SessionUtil.LoginEnum.LOGIN_Organization.getCode());
            UserInfo userInfo = (UserInfo) map.get(SessionUtil.LoginEnum.LOGIN_EMP.getCode());
            if(role != null){
                paramter.put("loginRoleId", role.getRoleId());
                paramter.put("loginRoleName", role.getRoleName());
            }else {
                paramter.put("loginRoleId", 0L);
                paramter.put("loginRoleName", "");
            }
            if(organization != null){
                paramter.put("loginOrganizationId", organization.getOrgId());
                paramter.put("loginOrganizationName", organization.getOrgName());
            }else {
                paramter.put("loginOrganizationId", 0L);
                paramter.put("loginOrganizationName", "");
            }
            if(userInfo != null){
                paramter.put("loginUserId", userInfo.getUserId());
                paramter.put("loginUserName", userInfo.getUserRealName());
            }else {
                paramter.put("loginUserId", 0L);
                paramter.put("loginUserName", "");
            }
            ParameterRequestWrapper wrapper = new ParameterRequestWrapper(httpServletRequest, paramter);
            filterChain.doFilter(wrapper, httpServletResponse);
        }

//        PermissionQuery permissionQuery = new PermissionQuery();
//        permissionQuery.setParam(new PermissionDTO());
//        permissionQuery.setPermControlUrlIsLike(uri);
//        List<Permission> permissions = permissionService.list(permissionQuery.buildExample());
//        if (ListUtil.isNotEmpty(permissions) && permissions.size() == 1) {
//            // 权限存在，拦截
//            // 首先检查是否需要登录
//            if ("Y".equals(permissions.get(0).getPermLogin())) {
//                // 需登录
//                // TODO:检查用户是否已经登录并且拥有权限
//                log.debug("检查用户是否已经登录并且拥有权限");
//
//            } else {
//                // 权限不需登录，放行
//                filterChain.doFilter(httpServletRequest, httpServletResponse);
//            }
//        } else {
//            // 权限不存在，放行
//            filterChain.doFilter(httpServletRequest, httpServletResponse);
//        }
    }


}
