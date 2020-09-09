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
@ApiModel(value = "系统部门类",description = "记录当前的各部门信息")
public class SysDept extends BaseModel {

	@ApiModelProperty(name = "name",value = "部门的名称",dataType = "java.lang.String")
    private String name;

	@ApiModelProperty(name = "parentId",value = "当前部门的父级Id",dataType = "java.lang.Long")
    private Long parentId;

	@ApiModelProperty(name = "orderNum",value = "排序号",dataType = "java.lang.Integer")
    private Integer orderNum;

	@ApiModelProperty(name = "delFlag",value = "删除标志",dataType = "java.lang.Byte",example = "-1:已删除;0:正常")
    private Byte delFlag;

	@ApiModelProperty(name = "children",value = "非数据库字段:当前部门对象拥有的子级部门对象",dataType = "java.util.List")
    private List<SysDept> children;

	@ApiModelProperty(name = "parentName",value = "非数据库字段:当前部门对象所属的父级部门名称")
    private String parentName;

	@ApiModelProperty(name = "level",value = "当前部门所属等级",dataType = "java.lang.Integer")
    private Integer level;

    
}