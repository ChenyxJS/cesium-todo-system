package com.chenyx.entity.query;

import com.chenyx.entity.SystemConfig;
import lombok.Data;

/**
 * <p>
 * 系统设置表system_config 查询类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemConfigQuery extends QueryParamOne<SystemConfigQuery,SystemConfig> {

    // 是否携带设置值信息
    private String isHaveValue;

    // likeParam
    // 设置名
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoName")
    private String sycoNameIsLike;
    // 设置编码
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoCode")
    private String sycoCodeIsLike;
    // 类型
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoType")
    private String sycoTypeIsLike;
    // 对应几个值：one：一个   more：多个
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoValueCount")
    private String sycoValueCountIsLike;
    // Y/N(该类型是否可编辑)
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoTypeCanEd")
    private String sycoTypeCanEdIsLike;
    // Y/N(该类型对应的值是否可编辑)
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoValueCanEd")
    private String sycoValueCanEdIsLike;
    // 状态
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "sycoStatus")
    private String sycoStatusIsLike;

}
