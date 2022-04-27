package pers.candyboyou.commodity;

import io.candyboyou.common.framework.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;

import java.util.Objects;

@SpringBootApplication
@EnableOpenApi
public class CommodityApplication extends AbstractApplication  {

    public static void main(String[] args) {
        start(args);
        refreshLocalEnv();
        SpringApplication.run(CommodityApplication.class, args);
    }

    private static void refreshLocalEnv() {
        String property = System.getProperty("user.env");
        if (Objects.equals(property, "local")) {
            System.setProperty("user.env", "dev");
        }
    }

    @Override
    public ApiInfo metadata() {
        return null;
    }
}
