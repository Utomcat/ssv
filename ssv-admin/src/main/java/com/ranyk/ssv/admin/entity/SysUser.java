package com.ranyk.ssv.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SysUser
 * Description:
 *
 * @author ranyi
 * @date 2020-09-07 1:01
 * Version: V1.0
 */
@Data
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "系统用户类",description = "记录系统中各用户的信息")
public class SysUser extends BaseModel{

    @ApiModelProperty(name = "name",value = "用户姓名",dataType = "java.lang.String")
    private String name;

    @ApiModelProperty(name = "nickName",value = "用户昵称",dataType = "java.lang.String")
    private String nickName;

    @ApiModelProperty(name = "avatar",value = "头像",dataType = "java.lang.String")
    private String avatar;

    @ApiModelProperty(name = "password",value = "密码",dataType = "java.lang.String")
    private String password;

    @ApiModelProperty(name = "salt",value = "密码加密盐",dataType = "java.lang.String")
    private String salt;

    @ApiModelProperty(name = "email",value = "用户邮箱",dataType = "java.lang.String")
    private String email;

    @ApiModelProperty(name = "mobile",value = "手机号",dataType = "java.lang.String")
    private String mobile;

    @ApiModelProperty(name = "status",value = "状态",dataType = "java.lang.Byte",example = "0:禁用;1:正常;")
    private Byte status;

    @ApiModelProperty(name = "deptId",value = "机构ID",dataType = "java.lang.Long")
    private Long deptId;

    @ApiModelProperty(name = "delFlag",value = "删除标志",dataType = "java.lang.Byte",example = "-1:已删除;0:正常;")
    private Byte delFlag;

    @ApiModelProperty(name = "deptName",value = "非数据库字段:所属部门名称",dataType = "java.lang.String")
    private String deptName;

    @ApiModelProperty(name = "roleNames",value = "非数据库字段:用户拥有的角色名",dataType = "java.lang.String")
    private String roleNames;

    @ApiModelProperty(name = "userRoles",value = "用户拥有的所有角色的相关信息",dataType = "java.util.List")
    private List<SysUserRole> userRoles = new ArrayList<>();


}
