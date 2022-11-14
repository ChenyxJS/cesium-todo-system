package com.chenyx.controller.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenyx.aop.annotation.MyLog;
import com.chenyx.entity.dto.TodoDTO;
import com.chenyx.entity.query.TodoQuery;
import com.chenyx.service.ITodoService;
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
 * @author chenyx
 * @since 2022-11-13
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/web/todo")
public class WebTodoController {
    @Resource
    private ITodoService service;

    /**
     * 分页查询
     *
     * @return JsonResult
    */
    @GetMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute TodoQuery query,
                                HttpSession session) {
        JsonResult jsonResult = new JsonResult();

        return service.selectAll(query);
    }

    /**
     * 查询所有
     *
     * @return JsonResult
     */
    @GetMapping(value = "/getAll")
    public JsonResult getAll(@ModelAttribute TodoQuery query) {
        query.setLimit(0);
        return service.selectAll(query);
    }

    /**
     * 根据主键删除
     *
     * @param id 主键
     * @return JsonResult
    */
    @MyLog(remark = "根据主键删除")
    @DeleteMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                                    @RequestParam(required = false) Long bindLoginOperatorId,
                                    HttpSession session) {
        JsonResult jsonResult = new JsonResult();

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
    public JsonResult update(@ModelAttribute TodoDTO record,
                                  HttpSession session) {
        JsonResult jsonResult = new JsonResult();

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
    public JsonResult add(@ModelAttribute TodoDTO record,
                          HttpSession session) {
        JsonResult jsonResult = new JsonResult();

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