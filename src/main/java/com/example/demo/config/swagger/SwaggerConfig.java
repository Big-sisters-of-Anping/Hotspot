package com.example.demo.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 类名称: SwaggerConfig
 * 类描述: swagger配置——支持在线接口调用
 *
 * @author: wqy
 * 创建时间: 2020/5/23 8:20 下午
 * Version 1.0
 */
@Configuration
public class SwaggerConfig {
    @Autowired
    private Environment env;

    @Bean
    public Docket createRestApi() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(env.getProperty("info.app.title"))
                .description(env.getProperty("info.app.description"))
                .termsOfServiceUrl(env.getProperty("info.app.termsOfServiceUrl"))
                .contact(env.getProperty("info.app.contact"))
                .version(env.getProperty("info.app.version"))
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(
                        env.getProperty("swagger.controller.package")))
                .paths(PathSelectors.any())
                .build();
    }
}
