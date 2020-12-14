package com.ranyk.ssv.common.utils;

import java.util.Collection;
import java.util.Map;

/**
 * ClassName:ObjectUtils<br/>
 * Description:对象工具类
 *
 * @author ranyi
 * @date 2020-12-14 14:29
 * Version: V1.0
 */
public class ObjectUtils {


    /**
     * 判断对象是否为空
     *
     * @param obj 需要进行判断的对象参数
     * @return 当对象为空时返回 true;反之返回 false;
     */
    public static Boolean objectIsEmpty(Object obj) {
        // 判断对象是否为 null
        if (null == obj) {
            return true;
        }
        // 当对象是字符串时,判断是否为空
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
        // 当对象是 Collection 集合时,判断是否为空
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        // 当对象是 Map 集合时,判断是否为空
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        // 当对象是数组时,判断是否为空
        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (Object o : object) {
                if (!objectIsEmpty(o)) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

}
