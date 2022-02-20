package io.candyboyou.common.framework.mvc.handler;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GlobalResponseConverterConfiguration {

    @Bean
    public HttpMessageConverters gsonHttpMessageConverters()
    {
        //需要定义一个convert转换消息的对象;
        GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        //处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastMediaTypes.add(MediaType.TEXT_HTML);
        //在convert中添加配置信息.
        gsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);

        // 在这里配置gson序列化的一些特性
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        gsonHttpMessageConverter.setGson(gson);
        return new HttpMessageConverters(gsonHttpMessageConverter);
    }
}
