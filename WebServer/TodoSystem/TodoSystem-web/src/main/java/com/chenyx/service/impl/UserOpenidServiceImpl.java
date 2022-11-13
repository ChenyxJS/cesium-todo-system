package com.chenyx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenyx.entity.UserOpenid;
import com.chenyx.entity.dto.UserOpenidDTO;
import com.chenyx.entity.enums.RequestEnum;
import com.chenyx.entity.query.UserOpenidQuery;
import com.chenyx.mapper.ExtUserOpenidMapper;
import com.chenyx.service.IUserOpenidService;
import com.chenyx.util.EntityUtil;
import com.chenyx.util.JsonResult;
import com.chenyx.util.ListUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * user_openid_用户openid 服务实现类
 *
 * @author 苏友朋
 * @since 2021-07-30
 */
@Service
public class UserOpenidServiceImpl extends ServiceImpl<ExtUserOpenidMapper, UserOpenid> implements IUserOpenidService {


    /**
     * 分页查询user_openid_用户openid
     *
     * @return JsonResult
    */
    @Override
    public JsonResult selectAll(UserOpenidQuery query) {
        JsonResult jsonResult = new JsonResult();
        query.buildExample();
        // 创建page类并查询，本语句已经查询完毕
        Page<UserOpenid> page = query.buildPage(this);
        List<UserOpenidDTO> records = EntityUtil.parentListToChildList(page.getRecords(), UserOpenidDTO.class);
        if(ListUtil.isNotEmpty(records)){
            jsonResult.buildTrueNew(page.getTotal(),records);
        }else {
            jsonResult.buildFalseNew(RequestEnum.REQUEST_ERROR_DATABASE_QUERY_NO_DATA);
        }
        return jsonResult;
    }

    /**
     * 根据主键删除user_openid_用户openid
     *
     * @param id 主键
     * @return JsonResult
    */
    @Override
    public JsonResult deleteByKey(Long id) {
        JsonResult jsonResult = new JsonResult();
        if(removeById(id)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 根据主键修改user_openid_用户openid
     *
     * @param record 修改信息
     * @return JsonResult
    */
    @Override
    public JsonResult updateByKey(UserOpenidDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(updateById(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

    /**
     * 插入user_openid_用户openid
     *
     * @param record 插入信息
     * @return JsonResult
    */
    @Override
    public JsonResult add(UserOpenidDTO record) {
        JsonResult jsonResult = new JsonResult();
        if(save(record)){
            jsonResult.buildTrueNew();
        }
        return jsonResult;
    }

}
