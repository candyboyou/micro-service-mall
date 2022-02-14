package io.candyboyou.mallpromotion.config;

import io.candyboyou.mallpromotion.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@Slf4j
@PropertySources({
        @PropertySource("classpath:props/${user.env}/durid.properties"),
        @PropertySource("classpath:props/${user.env}/jdbc.properties"),
        @PropertySource("classpath:props/${user.env}/nacos.properties"),
        @PropertySource("classpath:props/${user.env}/redis.properties")
})
public class AppConfig {

    private final String ENV = StringUtils.isEmpty(System.getProperty("user.env")) ? "dev" : System.getProperty("user.env");

//    @Bean
//    public static WebMvcConfigurer springMvcResolver(){
//        return new WebMvcConfigurer(){
//            @Override
//            public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//                argumentResolvers.add(new JsonBodyResolver());
//            }
//        };
//    }

}
