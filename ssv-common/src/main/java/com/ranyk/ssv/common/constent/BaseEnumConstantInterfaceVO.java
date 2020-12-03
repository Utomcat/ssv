package com.ranyk.ssv.common.constent;

/**
 * ClassName:BaseEnumConstantInterfaceVO<br/>
 * Description:获取常量值的通用接口
 *
 * @author ranyi
 * @date 2020-11-10 10:36
 * Version: V1.0
 */
public interface BaseEnumConstantInterfaceVO<T> {

    /**
     * 获取指定的常量值
     * @return 返回给定类型的常量值
     */
    T getValue();

    /**
     * 设置指定的常量值
     *
     * @param value 需设置的值
     */
    void setValue(T value);


}
