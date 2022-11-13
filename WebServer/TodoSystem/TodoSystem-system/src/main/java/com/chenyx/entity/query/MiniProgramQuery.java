package com.chenyx.entity.query;

import com.chenyx.entity.MiniProgram;
import lombok.Data;

/**
 * <p>
 * 小程序表 mini_program 查询类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
 */
@Data
public class MiniProgramQuery extends QueryParamOne<MiniProgramQuery, MiniProgram> {

    // likeParam
    // 小程序appid
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "miniAppid")
    private String miniAppidIsLike;
    // 小程序secret
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "miniSecret")
    private String miniSecretIsLike;
    // 小程序名称
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "miniAppName")
    private String miniAppNameIsLike;
    // 使用状态
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "miniStatus")
    private String miniStatusIsLike;

}
