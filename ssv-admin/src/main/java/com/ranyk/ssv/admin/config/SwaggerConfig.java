package com.ranyk.ssv.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

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
        /*ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        parameterBuilder.name("token").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);*/

        /*List<RequestParameter> parameters = new ArrayList<>();
        RequestParameter parameter = new RequestParameter(
                "token",
                ParameterType.HEADER,
                "令牌",
                false,
                false,
                false,
                null,
                null,
                null,
                0,
                null,
                0);
        parameters.add(parameter);*/

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
                //.globalRequestParameters(new ArrayList<RequestParameter>().add(new RequestParameter("token", ParameterType.HEADER,"令牌",false,false,false,null,null,null,0,null,0)));

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().
                title("Swagger3接口文档")
                .description("由ranyk进行开发")
                .contact(new Contact("ranyk","http://www.ranyk.com","ranyk@foxmain.com"))
                .version("1.0")
                .build();
    }

}
