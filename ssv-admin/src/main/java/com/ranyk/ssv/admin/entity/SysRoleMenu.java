package com.ranyk.ssv.admin.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author ranyi
 */
@Data
@ToString
@Component
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "系统角色菜单类",description = "用于记录各角色拥有哪些菜单")
public class SysRoleMenu extends BaseModel {

	@ApiModelProperty(name = "roleId",value = "角色ID",dataType = "java.lang.Long")
    private Long roleId;

	@ApiModelProperty(name = "menuId",value = "菜单ID",dataType = "java.lang.Long")
    private Long menuId;


}