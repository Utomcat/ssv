package com.ranyk.ssv.admin.constent;

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
public enum SysConstants {

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
     * get方法
     *
     * @return 返回对应的值
     */
    public String getName() {
        return name;
    }

    /**
     * set方法
     *
     * @param name 参数值
     */
    public void setName(String name) {
        this.name = name;
    }
}
