package com.chenyx.utils;

import com.chenyx.util.ListUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author gyy
 * @date 2021/8/13
 **/
public class GyyListUtil {
    /**
     * String 列表 转 Integer 列表
     */
    public static List<Integer> parseToIntegerList(String[] dataList) {
        return parseToIntegerList(Arrays.asList(dataList));
    }

    public static List<Integer> parseToIntegerList(List<String> dataList) {
        List<Integer> list = new ArrayList<>();
        dataList.forEach(data -> {
            list.add(Integer.parseInt(data));
        });
        return list;
    }

    /**
     * String 列表 转 Long 列表
     */
    public static List<Long> parseToLongList(String[] dataList) {
        return parseToLongList(Arrays.asList(dataList));
    }

    public static List<Long> parseToLongList(List<String> dataList) {
        List<Long> list = new ArrayList<>();
        dataList.forEach(data -> {
            list.add(Long.parseLong(data));
        });
        return list;
    }

    /**
     * 判断选项选择的答案是否相同
     * 例如 A,B,C 和 A,C,B 是相同的
     */
    public static boolean isEqualItemAnswer(String str1, String str2) {
        List<String> str1List = Arrays.asList(str1.split(","));
        List<String> str2List = Arrays.asList(str2.split(","));

        //没有解析数据
        if (ListUtil.isEmpty(str1List) || ListUtil.isEmpty(str2List)) {
            return false;
        }

        //利用set降低对比时间复杂度
        HashSet<String> str1Set = new HashSet<>(str1List);
        HashSet<String> str2Set = new HashSet<>(str2List);

        //判断
        return str1Set.equals(str2Set);
    }
}

//public class ListUtil {
//    /**
//     * 判断字符串是否是null或空字符串：是，返回true
//     *
//     * @param str 要判断的字符串
//     * @return boolean  是，返回true    否，返回false
//     */
//    public static boolean isEmpty(List list) {
//        return !isNotEmpty(list);
//    }
//
//    /**
//     * 判断字符串是否是null或空字符串：否，返回true
//     *
//     * @param str 要判断的字符串
//     * @return boolean  否，返回true    是，返回false
//     */
//    public static boolean isNotEmpty(List list) {
//        return list != null && list.size() > 0;
//    }
//}
