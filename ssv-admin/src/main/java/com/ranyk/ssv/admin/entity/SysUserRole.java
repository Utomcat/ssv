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
@ApiModel(value = "系统用户角色类",description = "记录一个用户拥有那些角色的信息")
public class SysUserRole extends BaseModel {

	@ApiModelProperty(name = "userId",value = "用户ID",dataType = "java.lang.Long")
    private Long userId;

	@ApiModelProperty(name = "roleId",value = "角色ID",dataType = "java.lang.Long")
    private Long roleId;

}