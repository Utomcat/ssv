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
@ApiModel(value = "系统日志类",description = "记录系统的相关操作信息")
public class SysLog extends BaseModel {

	@ApiModelProperty(name = "userName",value = "用户名",dataType = "java.lang.String")
    private String userName;

	@ApiModelProperty(name = "operation",value = "用户操作",dataType = "java.lang.String")
    private String operation;

	@ApiModelProperty(name = "method",value = "请求方法",dataType = "java.lang.String")
    private String method;

	@ApiModelProperty(name = "params",value = "请求参数",dataType = "java.lang.String")
    private String params;

	@ApiModelProperty(name = "time",value = "执行时长(毫秒)",dataType = "java.lang.Long")
    private Long time;

	@ApiModelProperty(name = "ip",value = "IP地址",dataType = "java.lang.String")
    private String ip;

}