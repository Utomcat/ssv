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
@ApiModel(value = "系统角色部门类",description = "记录系统中角色和所属部门间的关系")
public class SysRoleDept extends BaseModel {

	@ApiModelProperty(name = "roleId",value = "角色ID",dataType = "java.lang.Long")
    private Long roleId;

	@ApiModelProperty(name = "deptId",value = "部门ID",dataType = "java.lang.Long")
    private Long deptId;

}