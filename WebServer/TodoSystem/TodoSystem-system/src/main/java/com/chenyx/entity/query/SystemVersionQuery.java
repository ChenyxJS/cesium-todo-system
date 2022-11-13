package com.chenyx.entity.query;

import com.chenyx.entity.SystemVersion;
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
public class SystemVersionQuery extends QueryParamOne<SystemVersionQuery,SystemVersion> {

    // likeParam
    // 版本号变更操作IP地址
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveOperationIp")
    private String syveOperationIpIsLike;
    // 版本号
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveNumber")
    private String syveNumberIsLike;
    // 版本号类型：固定为：小程序：mini      后台管理：admin      移动app：flutter     收银端：cashier
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveType")
    private String syveTypeIsLike;
    // 状态：使用中：useing        历史记录：history      还未使用的：future
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveStatus")
    private String syveStatusIsLike;
    // 版本简介（简单介绍）
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveLog")
    private String syveLogIsLike;
    // 更新包地址（用于收银端和移动端）
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "syveUpdatePackUrl")
    private String syveUpdatePackUrlIsLike;

}
