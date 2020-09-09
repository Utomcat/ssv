package com.ranyk.ssv.admin.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 基础模型
 * @author ranyk
 * @date: 2019年12月16日 上午12:41:27
 * @version 1.0
 */
@Data
@ToString
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "基本类",description = "所有实体类的公共字段的类")
public class BaseModel {

    @ApiModelProperty(name = "id",value = "公共字段主键ID",dataType = "java.lang.Long")
	private Long id;

    @ApiModelProperty(name = "createBy",value = "公共字段创建人员ID",dataType ="java.lang.String")
    private String createBy;

    @ApiModelProperty(name = "createTime",value = "公共字段创建时间",dataType ="java.util.Date")
    private Date createTime;

    @ApiModelProperty(name = "lastUpdateBy",value = "公共字段最后一次修改人员ID",dataType = "java.lang.String")
    private String lastUpdateBy;

    @ApiModelProperty(name = "lastUpdateTime",value = "公共字段最后一次修改时间",dataType = "java.util.Date")
    private Date lastUpdateTime;


    
}
