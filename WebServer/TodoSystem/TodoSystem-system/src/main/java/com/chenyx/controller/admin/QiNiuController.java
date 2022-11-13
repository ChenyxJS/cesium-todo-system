package com.chenyx.controller.admin;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.SystemConfigValue;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.service.ISystemConfigService;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import com.chenyx.util.StringUtil;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * QiNiuController class
 *
 * @author 苏友朋
 * @date 2019/03/15 9:25
 */
@Slf4j
@RestController
@RequestMapping("/system")
@SuppressWarnings("SpringJavaAutowiringInspection")
public class QiNiuController {
    @Resource
    private ISystemConfigService systemConfigService;

    /**
     * 获取七牛云token
     *
     * @return JsonResult
     */
    @MyLog(remark = "获取七牛云token")
    @PostMapping(value = "/getQiNiuUploadToken")
    public JsonResult getUploadToken(HttpSession session) {
        JsonResult jsonResult = new JsonResult();
        //获取发布服务器配置文件
        String accessKey = "";
        String secretKey = "";
        String bucket = "";
        List<SystemConfigValue> systemConfigValues = systemConfigService.getSystemConfigValueByConfigNumber("QiNiu-config");
        if (ListUtil.isEmpty(systemConfigValues)) {
            jsonResult.setFailReason("七牛云配置不全");
            jsonResult.setSuccess(false);
            return jsonResult;
        }

        if (systemConfigValues.size() < 3) {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_SYSTEM_CONFIG_ERROR, "七牛云配置不全");
            return jsonResult;
        }
        for (SystemConfigValue systemConfigValue : systemConfigValues) {
            if ("Y".equals(systemConfigValue.getScvaStatus())) {
                switch (systemConfigValue.getScvaCode()) {
                    case "AccessKey":
                        accessKey = systemConfigValue.getScvaValue();
                        break;
                    case "SecretKey":
                        secretKey = systemConfigValue.getScvaValue();
                        break;
                    case "bucket":
                        bucket = systemConfigValue.getScvaValue();
                        break;
                    default:
                        break;
                }
            }

        }
        if (StringUtil.isNotEmpty(accessKey) && StringUtil.isNotEmpty(secretKey) && StringUtil.isNotEmpty(bucket)) {
            Auth auth = Auth.create(accessKey, secretKey);
            jsonResult.buildTrueNew();
            jsonResult.setTip(auth.uploadToken(bucket));
            jsonResult.setRoot(systemConfigService.getSystemConfigValueByConfigNumber("qny_domain"));
            return jsonResult;
        } else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_SYSTEM_CONFIG_ERROR, "七牛云配置不全");
            return jsonResult;
        }

    }

    /**
     * 获取七牛云token
     *
     * @return JsonResult
     */
    @MyLog(remark = "获取七牛云token")
    @GetMapping(value = "/qiNiuUploadToken")
    public Map qiNiuUploadToken(HttpSession session) {
        Map<String, String> jsonResult = new HashMap<>();
        //获取发布服务器配置文件
        String accessKey = "";
        String secretKey = "";
        String bucket = "";
        //获取发布服务器配置文件
        List<SystemConfigValue> systemConfigValues = systemConfigService.getSystemConfigValueByConfigNumber("QiNiu-config");
        if (ListUtil.isEmpty(systemConfigValues)) {
            return jsonResult;
        }

        if (systemConfigValues.size() < 3) {
            return jsonResult;
        }
        for (SystemConfigValue systemConfigValue : systemConfigValues) {
            if ("Y".equals(systemConfigValue.getScvaStatus())) {
                switch (systemConfigValue.getScvaCode()) {
                    case "AccessKey":
                        accessKey = systemConfigValue.getScvaValue();
                        break;
                    case "SecretKey":
                        secretKey = systemConfigValue.getScvaValue();
                        break;
                    case "bucket":
                        bucket = systemConfigValue.getScvaValue();
                        break;
                    default:
                        break;
                }
            }

        }
        if (StringUtil.isNotEmpty(accessKey) && StringUtil.isNotEmpty(secretKey) && StringUtil.isNotEmpty(bucket)) {
            Auth auth = Auth.create(accessKey, secretKey);
            jsonResult.put("uptoken", auth.uploadToken(bucket));
            return jsonResult;
        } else {
            return jsonResult;
        }
    }
}
