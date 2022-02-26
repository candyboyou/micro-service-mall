package io.candyboyou.mallcommodity;

import io.candyboyou.common.framework.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;

import java.util.Objects;

@SpringBootApplication
@EnableOpenApi
public class MallCommodityApplication extends AbstractApplication {

    public static void main(String[] args) {
        start(args);
        refreshLocalEnv();
        SpringApplication.run(MallCommodityApplication.class, args);
    }

    private static void refreshLocalEnv() {
        String property = System.getProperty("user.env");
        if (Objects.equals(property, "local")) {
            System.setProperty("user.env", "props/dev");
        }
    }

    @Override
    public ApiInfo metadata() {
        return null;
    }
}
