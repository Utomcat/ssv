package com.ranyk.ssv.backup.constent;

import com.ranyk.ssv.common.constent.BaseEnumConstantInterfaceVO;

/**
 * ClassName:BackUpConstant<br/>
 * Description:数据备份环境常量值
 *
 * @author ranyi
 * @date 2020-12-14 14:58
 * Version: V1.0
 */
public enum BackUpConstant implements BaseEnumConstantInterfaceVO<String> {

    /**
     * WINDOWS 操作系统下路径分割符
     */
    WINDOWS_PATH_SEPARATOR("\\"),
    /**
     * UNIX 操作系统下路径分割符
     */
    UNIX_PATH_SEPARATOR("/"),
    /**
     * 数据备份时 win 操作系统
     */
    BACK_UP_WIN("win");


    /**
     * 字段值
     */
    private String name;

    BackUpConstant(String name) {
        this.name = name;
    }

    /**
     * 获取常量值
     * @return 返回对应的常量值
     */
    @Override
    public String getValue() {
        return this.name;
    }

    /**
     * 设置常量值
     * @param value 需要设置的值
     */
    @Override
    public void setValue(String value) {
        this.name = value;
    }
}
