package ${package.Controller};

import ${cfg.parentBKG}.aop.annotation.MyLog;
import ${package.Entity}.dto.${entity}DTO;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.query.${entity}Query;
import ${cfg.parentBKG}.util.JsonResult;
import ${cfg.parentBKG}.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ${table.comment!} 接口类
 *
 * @author ${author}
 * @since ${date}
*/
@Slf4j
@RestController
@SuppressWarnings("SpringJavaAutowiringInspection")
@RequestMapping(value = "/admin/${table.entityPath}")
public class ${table.controllerName} {
    @Resource
    private ${table.serviceName} service;

    /**
     * 分页查询${table.comment!}
     *
     * @return JsonResult
    */
    @PostMapping(value = "/selectByExample")
    public JsonResult selectByExample(@ModelAttribute ${entity}Query query,
                                HttpSession session) {
        JsonResult jsonResult = new JsonResult();

        return service.selectAll(query);
    }

    /**
     * 根据主键删除${table.comment!}
     *
     * @param id 主键
     * @return JsonResult
    */
    @MyLog(remark = ${'"'}根据主键删除${table.comment!}${'"'})
    @PostMapping(value = "/delete")
    public JsonResult delete(@RequestParam Long id,
                                    @RequestParam(required = false) Long bindLoginOperatorId,
                                    HttpSession session) {
        JsonResult jsonResult = new JsonResult();

        return service.deleteByKey(id);
    }

    /**
     * 根据主键修改${table.comment!}
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @MyLog(remark = ${'"'}根据主键修改${table.comment!}${'"'})
    @PostMapping(value = "/update")
    public JsonResult update(@ModelAttribute ${entity}DTO record,
                                  HttpSession session) {
        JsonResult jsonResult = new JsonResult();

        return service.updateByKey(record);
    }

    /**
     * 插入${table.comment!}
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @MyLog(remark = ${'"'}插入${table.comment!}${'"'})
    @PostMapping(value = "/add")
    public JsonResult add(@ModelAttribute ${entity}DTO record,
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