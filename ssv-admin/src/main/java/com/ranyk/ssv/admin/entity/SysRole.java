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
@ApiModel(value = "系统角色类",description = "用于存放当前系统拥有哪些角色")
public class SysRole extends BaseModel {

	@ApiModelProperty(name = "name",value = "角色名称",dataType = "java.lang.String")
    private String name;

	@ApiModelProperty(name = "remark",value = "备注/角色的中文解释",dataType = "java.lang.String")
    private String remark;

	@ApiModelProperty(name = "delFlag",value = "删除标志",dataType = "java.lang.Byte",example = "-1:已删除;0:正常;")
    private Byte delFlag;

}