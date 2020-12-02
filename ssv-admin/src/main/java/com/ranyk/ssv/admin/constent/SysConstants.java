package com.ranyk.ssv.admin.constent;

import com.ranyk.ssv.common.constent.BaseEnumConstantInterfaceVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * ClassName:SysConstants
 * Description:系统常量类
 *
 * @author ranyi
 * @date 2020-09-07 21:50
 * Version: V1.0
 */
@ApiModel(value = "系统定义的常量类")
public enum SysConstants implements BaseEnumConstantInterfaceVO<String> {

    /**
     * 管理员名称
     */
    @ApiModelProperty(name = "ADMIN", value = "管理员名称字段", dataType = "java.lang.String", example = "ADMIN/admin")
    ADMIN("admin");

    /**
     * 构造方法
     *
     * @param name 传入参数值
     */
    SysConstants(String name) {
        this.name = name;
    }

    /**
     * 字段值
     */
    private String name;

    /**
     * 获取指定的常量值
     *
     * @return 返回给定类型的常量值
     */
    @Override
    public String getValue() {
        return this.name;
    }

    /**
     * 设置指定的常量值
     *
     * @param value 需设置的值
     */
    @Override
    public void setValue(String value) {
        this.name = value;
    }
}
