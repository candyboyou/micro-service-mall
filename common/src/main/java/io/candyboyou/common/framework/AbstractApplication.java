package io.candyboyou.common.framework;

import io.swagger.models.parameters.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelSpecification;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static springfox.documentation.builders.PathSelectors.regex;

public abstract class AbstractApplication {

    // 环境参数前缀
    private static final String ENV_EX = "--spring.profiles.active=";
    // 环境参数属性名
    public static final String ENV_PROPERTY_NAME ="user.env";

    @Value("${spring.application.name}")
    private String applicationName;

    public static void start(String[] args) {
        String env = EnvEnum.dev.name();

        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (null != arg && arg.startsWith("--spring.profiles.active=")) {
                    env = arg.substring("--spring.profiles.active=".length());
                }
            }
        }

        System.out.println(" *********************************");
        System.out.println("*** \t\t\t\t***");
        System.out.println("*** server is starting,evn is\t***");
        System.out.println("*** \t\t\t\t***");
        System.out.println("***\t\t" + env.toUpperCase() + " !\t\t***");
        System.out.println("*** \t\t\t\t***");
        System.out.println(" *********************************");
        System.setProperty("user.env", env.toLowerCase());
        System.setProperty("sun.net.client.defaultConnectTimeout", "2000");
        System.setProperty("sun.net.client.defaultReadTimeout", "1500");
        System.setProperty("user.timezone", "GMT+8");
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    /**
     * 目前我们的swagger页面并没有让我们输入token的文本框，因此我们需要改造
     */
    @Bean
    public Docket api() {
        List<RequestParameter> pars = new ArrayList<>();
        RequestParameterBuilder mockTokenBuilder = new RequestParameterBuilder();
        mockTokenBuilder.name("Authorization") // Updates the parameter name
                .description("用户token") // Updates the description of the parameter
                .query(p -> p.defaultValue("balaraking@gmail.com")
                        .model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
                .description("令牌")
                .in(ParameterType.HEADER).required(false).build();
        pars.add(mockTokenBuilder.build());

        RequestParameterBuilder authorizationTokenBuilder = new RequestParameterBuilder();
        // Authorization.name("Authorization").description("用户token").
        // modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        authorizationTokenBuilder.name("Authorization").description("用户token")
                        .query(p -> p.model(modelSpecificationBuilder -> modelSpecificationBuilder.scalarModel(ScalarType.STRING)))
                                .in(ParameterType.HEADER).required(false).build();
        pars.add(authorizationTokenBuilder.build());
        if(EnvEnum.prod.name().equals(System.getProperty(ENV_PROPERTY_NAME))){
            return new Docket(DocumentationType.OAS_30)//.ignoredParameterTypes(CurrentlyLoggedUser.class)
                    .groupName(applicationName).apiInfo(metadata()).select().paths(PathSelectors.none())
                    .apis(RequestHandlerSelectors.basePackage("io.candyboyou")).build();

        }else{
            return new Docket(DocumentationType.OAS_30)//.ignoredParameterTypes(CurrentlyLoggedUser.class)
                    .groupName(applicationName).apiInfo(metadata()).select().paths(regex("/*.*"))
                    .apis(RequestHandlerSelectors.basePackage("io.candyboyou")).build().globalRequestParameters(pars);
        }
    }

    public abstract ApiInfo metadata();

}
