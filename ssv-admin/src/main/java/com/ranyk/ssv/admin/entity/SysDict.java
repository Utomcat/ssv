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
@ApiModel(value = "系统字典类",description = "用于记录常用的一些数据值及其对应的数据码值")
public class SysDict extends BaseModel {

	@ApiModelProperty(name = "value",value = "数据值",dataType = "java.lang.String")
    private String value;

	@ApiModelProperty(name = "label",value = "标签名",dataType = "java.lang.String")
    private String label;

	@ApiModelProperty(name = "type",value = "数据值",dataType = "java.lang.String")
    private String type;

	@ApiModelProperty(name = "description",value = "描述",dataType = "java.lang.String")
    private String description;

	@ApiModelProperty(name = "sort",value = "排序",dataType = "java.lang.Long")
    private Long sort;

	@ApiModelProperty(name = "remarks",value = "备注信息",dataType = "java.lang.String")
    private String remarks;

	@ApiModelProperty(name = "delFlag",value = "删除标志",dataType = "java.lang.Byte")
    private Byte delFlag;

}