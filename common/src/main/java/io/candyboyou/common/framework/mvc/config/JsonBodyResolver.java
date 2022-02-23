package io.candyboyou.common.framework.mvc.config;

import com.google.gson.JsonObject;
import io.candyboyou.common.utils.IOUtils;
import io.candyboyou.common.utils.JsonUtils;
import io.candyboyou.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonBodyResolver implements HandlerMethodArgumentResolver {

    private static final Logger log = LoggerFactory.getLogger(JsonBodyResolver.class);

    public JsonBodyResolver() {}

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(JsonParam.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // NativeWebRequest抽象了各种request，调用这个方法获得实际真正的request
        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest(HttpServletRequest.class);
        assert request != null;
        ServletInputStream in = null;
        try {
            in = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject obj = null;
        assert in != null;
        if (!in.isFinished()) {
            String strObj = null;
            try {
                strObj = new String(IOUtils.toByteArray(new BufferedInputStream(in)), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (StringUtils.isBlank(strObj)) {
                return null;
            }

            obj = JsonUtils.parseJson(strObj, JsonObject.class);
            webRequest.setAttribute("JSON_BODY_OBJ", obj, 1);
        } else {
            obj = (JsonObject) webRequest.getAttribute("JSON_BODY_OBJ", 1);
        }

        if (obj == null) {
            return null;
        } else {
            JsonParam annotation = parameter.getParameterAnnotation(JsonParam.class);
            assert annotation != null;
            String paramName = StringUtils.isEmpty(annotation.value()) ? parameter.getParameterName() : annotation.value();
            // 将指定成员变量转为 JsonObject
            JsonObject target = obj.getAsJsonObject(paramName);
            WebDataBinder binder = null;
            try {
                binder = binderFactory.createBinder(webRequest, (Object) null, paramName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            assert binder != null;
            binder.convertIfNecessary(target, parameter.getParameterType());
            return target;
        }
    }
}
