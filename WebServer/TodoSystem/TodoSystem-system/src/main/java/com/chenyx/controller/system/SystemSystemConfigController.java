package com.chenyx.controller.system;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.dto.SystemConfigDTO;
import com.chenyx.entity.query.SystemConfigQuery;
import com.chenyx.service.ISystemConfigService;
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
 * 系统设置
 *
 * @author 空城
 * @since 2021-06-25
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/system/systemConfig")
public class SystemSystemConfigController {
    @Resource
    private ISystemConfigService service;

    /**
     * 分页查询系统设置表system_config
     *
     * @return JsonResult
    */
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute SystemConfigQuery query,
                                HttpSession session) {
        return service.selectAll(query);
    }

    /**
     * 根据主键删除系统设置表system_config
     *
     * @param id 主键
     * @return JsonResult
    */
    @MyLog(remark = "根据主键删除系统设置表system_config")
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                                    @RequestParam(required = false) Long bindLoginOperatorId,
                                    HttpSession session) {
        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改系统设置表system_config
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @MyLog(remark = "根据主键修改系统设置表system_config")
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute SystemConfigDTO record,
                                  HttpSession session) {
        return service.updateByKey(record);
    }

    /**
     * 插入系统设置表system_config
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @MyLog(remark = "插入系统设置表system_config")
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute SystemConfigDTO record,
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
