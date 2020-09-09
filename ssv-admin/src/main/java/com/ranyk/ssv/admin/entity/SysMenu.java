package com.ranyk.ssv.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author ranyi
 */
@Data
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统菜单类",description = "存放系统中的所有菜单名称及对应路径")
public class SysMenu extends BaseModel {

	@ApiModelProperty(name = "parentId",value = "当前菜单的父级ID",dataType = "java.lang.Long")
    private Long parentId;

	@ApiModelProperty(name = "name",value = "菜单名称",dataType = "java.lang.String")
    private String name;

	@ApiModelProperty(name = "url",value = "菜单的请求路径",dataType = "java.lang.String")
    private String url;

	@ApiModelProperty(name = "perms",value = "授权(多个逗号分隔)",dataType = "java.lang.String",example = "sys:user:add,sys:user:edit")
    private String perms;

	@ApiModelProperty(name = "type",value = "类型",dataType = "java.lang.Integer",example = "0：目录；1：菜单；2：按钮")
    private Integer type;

	@ApiModelProperty(name = "icon",value = "菜单图标",dataType = "java.lang.String")
    private String icon;

	@ApiModelProperty(name = "orderNum",value = "排序",dataType = "java.lang.Integer")
    private Integer orderNum;

	@ApiModelProperty(name = "delFlag",value = "删除标志",dataType = "java.lang.Byte",example = "-1:已删除;0:正常;")
    private Byte delFlag;

	@ApiModelProperty(name = "parentName",value = "非数据库字段,父级菜单名称",dataType = "java.lang.String")
    private String parentName;

	@ApiModelProperty(name = "level",value = "菜单等级",dataType = "java.lang.Integer")
    private Integer level;

	@ApiModelProperty(name = "children",value = "当前菜单拥有的子级菜单列表",dataType = "java.util.List")
    private List<SysMenu> children;

}