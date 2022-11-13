package com.chenyx.controller.admin;

import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.dto.SystemVersionDTO;
import com.chenyx.entity.query.SystemVersionQuery;
import com.chenyx.service.ISystemVersionService;
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
 * <p>
 * 接口类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
 */
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/systemVersion")
public class AdminSystemVersionController {
    @Resource
    private ISystemVersionService service;

    /**
     * 分页查询
     *
     * @return JsonResult
     */

    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute SystemVersionQuery query,
                                      HttpSession session) {
        JsonResult jsonResult = new JsonResult();

        return service.selectAll(query);
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return JsonResult
     */
    @MyLog(remark = "根据主键删除")
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                             @RequestParam(required = false) Long bindLoginOperatorId,
                             HttpSession session) {
        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改
     *
     * @param record 修改信息
     * @return JsonResult
     */
    @MyLog(remark = "根据主键修改")
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute SystemVersionDTO record,
                             HttpSession session) {
        return service.updateByKey(record);
    }

    /**
     * 插入
     *
     * @param record 插入信息
     * @return JsonResult
     */
    @MyLog(remark = "插入")
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute SystemVersionDTO record,
                          HttpSession session) {
        return service.add(record);
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
