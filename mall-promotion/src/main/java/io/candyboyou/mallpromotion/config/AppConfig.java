package io.candyboyou.mallpromotion.config;

import io.candyboyou.mallpromotion.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@Slf4j
@PropertySources({
        @PropertySource("classpath:props/${user.env}/durid.properties"),
        @PropertySource("classpath:props/${user.env}/jdbc.properties"),
        @PropertySource("classpath:props/${user.env}/nacos.properties"),
        @PropertySource("classpath:props/${user.env}/redis.properties")
})
public class AppConfig {

    private final String ENV = StringUtils.isEmpty(System.getProperty("user.env")) ? "sit" : System.getProperty("user.env");


}
