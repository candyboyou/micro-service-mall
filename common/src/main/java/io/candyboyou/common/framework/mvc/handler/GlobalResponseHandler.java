package io.candyboyou.common.framework.mvc.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>一般传统的做法是在Controller层方法直接接收ApiRequest参数和直接返回ApiResult的实例：</p>
 * <P>在参数中传入ApiRequest对象，然后手动获取业务参数data进行处理。
 * 每个接口手动生成ApiResult对象并返回。</p>
 * <p>这一部分工作其实是重复也无太多意义的，那么有没有一种方法可以自动做到：</p>
 * <p>我们只关注 ApiRequest.data和 ApiResult.data，让程序自动将参数传入到 业务参数data中和 控制层方法只返回 data，程序自动封装成ApiResult并返回呢？</p>
 * <p>RequestBodyAdvice的作用就是这个</p>
 * <p>RequestBodyAdvice是SpringMVC4.2提供的一个接口，它允许请求体被读取并转换为对象，并将处理结果对象作为@RequestBody参数或者 @HttpEntity方法参数。由此可见，它的作用范围为：</p>
 * <p>使用@RequestBody进行标记的参数;参数为HttpEntity</p>
 * <p></p>
 * <p>{@code @Order}：注解{@code @Order}或者接口{@code @Ordered}的作用是定义Spring IOC容器中Bean的执行顺序的优先级</p>
 * <P>{@code @ControllerAdvice}：{@code @ControllerAdvice}注解是Spring3.2中新增的注解，学名是Controller增强器，作用是给Controller控制器添加统一的操作或处理。</P>
 */
@Order
@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class GlobalResponseHandler<T> implements ResponseBodyAdvice<Object> {

    public static final String HTTP_REQUEST_ATTRIBUTE_HAS_EXCEPTION = "response_has_exception";

    public static final String HTTP_REQUEST_ATTRIBUTE_RESPONSE_BODY = "response_body";

    public static final String HTTP_REQUEST_ATTRIBUTE_EXT_MAP = "ext_map";

    private static final Logger log = LoggerFactory.getLogger(GlobalResponseHandler.class);

    private static final Map<String, String> ignoreTypes = new HashMap<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !ignoreTypes.containsKey(returnType.getParameterType().getName());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        String returnTypeName = returnType.getParameterType().getName();
        GlobalResponse<Object> globalResponse = "void".equals(returnTypeName) ?
                GlobalResponse.success(null) :
                GlobalResponse.success(body);

        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        httpServletRequest.setAttribute(HTTP_REQUEST_ATTRIBUTE_RESPONSE_BODY, globalResponse);

        Map<String, String> extMap = (Map) httpServletRequest.getAttribute(HTTP_REQUEST_ATTRIBUTE_EXT_MAP);
        globalResponse.setExtMap(extMap);
        return globalResponse;
    }

    static {
        ignoreTypes.put(GlobalResponse.class.getName(), "1");
        ignoreTypes.put(ResponseEntity.class.getName(), "1");
    }
}
