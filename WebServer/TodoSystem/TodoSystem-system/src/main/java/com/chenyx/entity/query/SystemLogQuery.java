package com.chenyx.entity.query;

import com.chenyx.entity.SystemLog;
import lombok.Data;

/**
 * <p>
 *  查询类
 * </p>
 *
 * @author 苏
 * @since 2021-03-30
*/
@Data
public class SystemLogQuery extends QueryParamOne<SystemLogQuery,SystemLog> {

    // likeParam
    // 请求者ip
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syloIpAddress")
    private String syloIpAddressIsLike;
    // 描述
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syloDescription")
    private String syloDescriptionIsLike;
    // 请求地址
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syloRequestUrl")
    private String syloRequestUrlIsLike;
    // 方法名称
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syloMethodName")
    private String syloMethodNameIsLike;

}
