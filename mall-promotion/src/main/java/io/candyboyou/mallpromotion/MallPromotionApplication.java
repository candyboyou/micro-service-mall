package io.candyboyou.mallpromotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.Objects;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MallPromotionApplication{

    public static void main(String[] args) {
        SpringApplication.run(MallPromotionApplication.class, args);
    }

    /**
     * 刷新本地环境变量
     */
    private static void refreshLocalEnv() {
        String property = System.getProperty("user.env");
        if (Objects.equals(property, "local")) {
            System.setProperty("user.env", "dev");
        }
    }

}