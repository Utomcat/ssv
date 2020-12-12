package com.ranyk.ssv.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:SwaggerConfig
 * Description:swagger3配置
 *
 * @author ranyi
 * @date 2020-09-07 0:24
 * Version: V1.0
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        // 添加请求参数，我们这里把token作为请求头部参数传入后端
        ParameterBuilder builder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();

        builder.name("Authorization")
                .required(false)
                .description("token 令牌")
                .parameterType("header")
                .modelRef(new ModelRef("string"))
                .build();

        parameters.add(builder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("Swagger2接口文档")
                .description("由ranyk进行开发")
                .contact(new Contact("ranyk", "http://www.ranyk.com", "1390851149@qq.com"))
                .version("V 1.0 版本")
                .build();
    }

}
