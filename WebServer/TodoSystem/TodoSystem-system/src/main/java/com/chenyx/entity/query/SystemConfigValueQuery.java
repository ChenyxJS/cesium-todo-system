package com.chenyx.entity.query;

import com.chenyx.entity.SystemConfigValue;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 系统设置值表system_config_value 查询类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemConfigValueQuery extends QueryParamOne<SystemConfigValueQuery,SystemConfigValue> {

    // 设置id列表
    @JoinExample(queryType = QueryTypeEnum.IN, columnName = "scvaTypeId")
    private List<Long> configIds;

    // likeParam
    // value
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "scvaValue")
    private String scvaValueIsLike;
    // 编码
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "scvaCode")
    private String scvaCodeIsLike;
    // 状态
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "scvaStatus")
    private String scvaStatusIsLike;

}
