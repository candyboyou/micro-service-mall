package io.candyboyou.mallpromotion;

import io.candyboyou.mallpromotion.config.Env;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public abstract class AbstractApplication {
    private static final String ENV_EX = "--spring.profiles.active=";
    public static final String ENV_PROPERTY_NAME = "user.env";
    @Value("${spring.application.name}")
    private String applicationName;

    public AbstractApplication() {
    }

    public static void start(String[] args) {
        String env = Env.Env.local.name();
        if (args != null && args.length > 0) {
            String[] var2 = args;
            int var3 = args.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String arg = var2[var4];
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

    @Bean
    public Docket api() {
        List<Parameter> pars = new ArrayList();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization").description("mork").name("X-Mork-Id").defaultValue("1233456").description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        ParameterBuilder Authorization = new ParameterBuilder();
        Authorization.name("Authorization").description("用户token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(Authorization.build());
        return Env.prod.name().equals(System.getProperty("user.env")) ? (new Docket(DocumentationType.SWAGGER_2)).groupName(this.applicationName).apiInfo(this.metadata()).select().paths(PathSelectors.none()).apis(RequestHandlerSelectors.basePackage("com.xhs")).build() : (new Docket(DocumentationType.SWAGGER_2)).groupName(this.applicationName).apiInfo(this.metadata()).select().paths(PathSelectors.regex("/*.*")).apis(RequestHandlerSelectors.basePackage("com.xhs")).build().globalOperationParameters(pars);
    }

    public abstract ApiInfo metadata();
}
