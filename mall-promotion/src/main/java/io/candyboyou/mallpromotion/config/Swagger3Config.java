package io.candyboyou.mallpromotion.config;

import io.swagger.annotations.ApiOperation;
import io.swagger.models.parameters.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Swagger3Config {
    private static final String ENV_EX = "--spring.profiles.active=";
    public static final String ENV_PROPERTY_NAME = "user.env";

    // 这个地方有问题
    @Value("${spring.application.name}")
    private String applicationName;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

//    @Bean
//    public Docket api() {
//        List<Parameter> pars = new ArrayList();
//        RequestParameterBuilder tokenPar = new RequestParameterBuilder();
//        tokenPar.name("Authorization").description("mork").name("X-Mork-Id").defaultValue("1233456").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(tokenPar.build());
//        ParameterBuilder Authorization = new ParameterBuilder();
//        Authorization.name("Authorization").description("用户token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
//        pars.add(Authorization.build());
//        return Env.prod.name().equals(System.getProperty("user.env")) ? (new Docket(DocumentationType.SWAGGER_2)).groupName(this.applicationName).apiInfo(this.metadata()).select().paths(PathSelectors.none()).apis(RequestHandlerSelectors.basePackage("io.candyboyou")).build() : (new Docket(DocumentationType.SWAGGER_2)).groupName(this.applicationName).apiInfo(this.metadata()).select().paths(PathSelectors.regex("/*.*")).apis(RequestHandlerSelectors.basePackage("io.candyboyou")).build().globalOperationParameters(pars);
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("更多请咨询服务开发者Ray。")
                .contact(new Contact("Ray。", "http://www.ruiyeclub.cn", "ruiyeclub@foxmail.com"))
                .version("1.0")
                .build();
    }

}
