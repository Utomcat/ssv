package com.ranyk.ssv.common.utils;

/**
 * ClassName:StringUtils
 * Description:字符串工具类
 *
 * @author ranyi
 * @date 2019-12-19 22:35
 * Version: V1.0
 */
public class StringUtils {

    /**
     * 判空操作
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {
        return value == null || "".equals(value) || "null".equals(value) || "undefined".equals(value);
    }

}
