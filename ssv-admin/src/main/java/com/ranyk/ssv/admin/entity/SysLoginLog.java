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
@ApiModel(value = "系统登录日志类",description = "用于记录登录日志")
public class SysLoginLog extends BaseModel {

	@ApiModelProperty(name = "userName",value = "登录用户名",dataType = "java.lang.String")
    private String userName;

	@ApiModelProperty(name = "status",value = "登录状态",dataType = "java.lang.String")
    private String status;

	@ApiModelProperty(name = "ip",value = "IP地址",dataType = "java.lang.String")
    private String ip;

}