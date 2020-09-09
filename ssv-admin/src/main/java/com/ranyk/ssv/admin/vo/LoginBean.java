package com.ranyk.ssv.admin.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * ClassName:LoginBean
 * Description:登录接口封装对象
 *
 * @author ranyi
 * @date 2019-12-27 23:04
 * Version: V1.0
 */
@Data
@ToString
@Component
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "登录对象类",description = "对登录的对象进行封装")
public class LoginBean {

    @ApiModelProperty(name = "account",value = "登录用户名",dataType = "java.lang.String")
    private String account;

    @ApiModelProperty(name = "password",value = "登录密码",dataType = "java.lang.String")
    private String password;

    @ApiModelProperty(name = "captcha",value = "登录验证码",dataType = "java.lang.String")
    private String captcha;
}
