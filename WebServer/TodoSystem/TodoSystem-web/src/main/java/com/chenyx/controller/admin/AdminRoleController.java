package com.chenyx.controller.admin;

import com.chenyx.entity.dto.RoleDTO;
import com.chenyx.entity.query.RoleQuery;
import com.chenyx.service.IRoleService;
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
 *  接口类
 *
 * @author 苏
 * @since 2021-10-28
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/role")
public class AdminRoleController {
    @Resource
    private IRoleService service;

    /**
     * 分页查询
     *
     * @return JsonResult
    */
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute RoleQuery query,
                                HttpSession session) {
        return service.selectAll(query);
    }

    /**
     * 根据主键删除
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
     * 根据主键修改
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute RoleDTO record,
                                  HttpSession session) {
        return service.updateByKey(record);
    }

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute RoleDTO record,
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
