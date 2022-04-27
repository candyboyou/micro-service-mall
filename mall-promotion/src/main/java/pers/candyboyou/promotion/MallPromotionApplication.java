package pers.candyboyou.mallpromotion;

import io.candyboyou.common.framework.AbstractApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;

import java.util.Objects;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableOpenApi
public class MallPromotionApplication extends AbstractApplication {

    public static void main(String[] args) {
        start(args);
        refreshLocalEnv();
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

    public static void doPost() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 11_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E217 MicroMessenger/6.8.0(0x16080000) NetType/WIFI Language/en Branch/Br_trunk MiniProgramEnv/Mac");
        map.add("Referer", "https://servicewechat.com/wx17ea87763491620f/1427/page-frame.html");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( "https://app.m.mi.com/v2/cate/index", request , String.class );
    }

    @Override
    public ApiInfo metadata() {
        return null;
    }
}