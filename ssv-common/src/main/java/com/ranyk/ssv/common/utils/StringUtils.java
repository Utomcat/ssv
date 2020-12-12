package com.ranyk.ssv.common.utils;

/**
 * ClassName:StringUtils<BR/>
 * Description:字符串工具类
 *
 * @author ranyi
 * @date 2019-12-19 22:35
 * Version: V1.0
 */
public class StringUtils {

    /**
     * 字符串控制判断操作
     *
     * @param value 需要进行判断的字符串
     * @return 返回判断结果: 是空字符串 true;反之 false;
     */
    public static boolean isBlank(String value) {

        boolean isEmpty = org.springframework.util.StringUtils.isEmpty(value);
        boolean hasText = org.springframework.util.StringUtils.hasText(value);
        boolean hasLength = org.springframework.util.StringUtils.hasLength(value);

        return isEmpty || !hasText || !hasLength;
    }

}
