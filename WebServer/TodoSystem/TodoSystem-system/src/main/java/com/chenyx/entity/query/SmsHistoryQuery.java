package com.chenyx.entity.query;

import com.chenyx.entity.SmsHistory;
import lombok.Data;

/**
 * <p>
 * 短信验证记录表 sms_history 查询类
 * </p>
 *
 * @author 苏
 * @since 2021-03-31
*/
@Data
public class SmsHistoryQuery extends QueryParamOne<SmsHistoryQuery,SmsHistory> {

    // likeParam
    // 随机编码
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "randomCode")
    private String randomCodeIsLike;
    // 手机号
    @JoinExample(queryType = QueryTypeEnum.LIKE, columnName = "phone")
    private String phoneIsLike;

}
