package com.chenyx.controller.system;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.dto.SystemConfigValueDTO;
import com.chenyx.entity.query.SystemConfigValueQuery;
import com.chenyx.service.ISystemConfigValueService;
import com.chenyx.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 系统设置值
 *
 * @author 空城
 * @since 2021-06-25
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/system/systemConfigValue")
public class SystemSystemConfigValueController {
    @Resource
    private ISystemConfigValueService service;

    /**
     * 分页查询系统设置值表system_config_value
     *
     * @return JsonResult
    */
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute SystemConfigValueQuery query,
                                HttpSession session) {
        return service.selectAll(query);
    }

    /**
     * 根据主键删除系统设置值表system_config_value
     *
     * @param id 主键
     * @return JsonResult
    */
    @MyLog(remark = "根据主键删除系统设置值表system_config_value")
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                                    @RequestParam(required = false) Long bindLoginOperatorId,
                                    HttpSession session) {
        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改系统设置值表system_config_value
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @MyLog(remark = "根据主键修改系统设置值表system_config_value")
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute SystemConfigValueDTO record,
                                  HttpSession session) {
        return service.updateByKey(record);
    }

    /**
     * 插入系统设置值表system_config_value
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @MyLog(remark = "插入系统设置值表system_config_value")
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute SystemConfigValueDTO record,
                          HttpSession session) {
        return service.add(record);
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
