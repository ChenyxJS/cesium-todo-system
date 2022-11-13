package com.chenyx.utils;

import java.math.BigDecimal;

/**
 * 成绩计算工具
 * 当前工具类是分数计算公式
 * @author xuwang
 */
public class ScoreCalculateUtil {


    /**
     * 评分累计计算公式
     * @param baseScore 基础评分
     * @param time 当前触发次数
     * @param unScoreTime 不计分触发次数
     * @param changeRate 重复触发的分数增减率
     * @return
     */
    public static BigDecimal cumulativeScore(BigDecimal baseScore,Integer time,Integer unScoreTime,BigDecimal changeRate){

        // 触发次数小于不计分触发次数则不计分
        if(time <= unScoreTime){
            return BigDecimal.ZERO;
        }

        // 分数增减率 = 分数增减率的 （ 现在触发的次数 - 不计分次数 -1）次方
        changeRate = changeRate.pow(time - unScoreTime -1);

        // 本次分数 = 基础分数*分数增减率
        return baseScore.multiply(changeRate);
    }





}
