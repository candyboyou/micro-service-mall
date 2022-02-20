package io.candyboyou.common.framework.mvc.interceptor;

import com.google.common.base.Charsets;
import io.candyboyou.common.framework.mvc.handler.GlobalRequestHandler;
import io.candyboyou.common.framework.mvc.handler.GlobalResponseHandler;
import io.candyboyou.common.sso.model.UserInfo;
import io.candyboyou.common.sso.UserInfoBag;
import io.candyboyou.common.utils.CollectionUtils;
import io.candyboyou.common.utils.JsonUtils;
import io.candyboyou.common.utils.TraceIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class AccessLogInterceptor implements HandlerInterceptor {

    private static final Logger accessLog = LoggerFactory.getLogger("io.candyboyou.access_log");

    private static final String REQUEST_ATTRIBUTE_START_TIME = "access_log_start_time";

    private static final Set<String> SENSITIVE_FIELDS = new HashSet<String>();

    public AccessLogInterceptor() {}

    public AccessLogInterceptor(List<String> sensitiveFields) {
        if (CollectionUtils.isNotEmpty(sensitiveFields)) {
            SENSITIVE_FIELDS.addAll(sensitiveFields);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            request.setAttribute(REQUEST_ATTRIBUTE_START_TIME, System.currentTimeMillis());
            HttpServletRequest httpServletRequest = getHttpServletRequest();
            // TODO http全链路
            TraceIdUtils.initTraceForHttp(httpServletRequest);
            return true;
        } catch (Exception ex) {
            accessLog.error("AccessLogInterceptor preHandle occur error", ex);
            return true;
        }
    }

    private static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            Object body = request.getAttribute(GlobalResponseHandler.HTTP_REQUEST_ATTRIBUTE_RESPONSE_BODY);
            accessLog(body, response.getStatus());

            Boolean hasException = (Boolean) getHttpServletRequest()
                    .getAttribute(GlobalResponseHandler.HTTP_REQUEST_ATTRIBUTE_HAS_EXCEPTION);

            TraceIdUtils.finishTraceForHttp(hasException != null && hasException);
        } catch (Exception e) {
            accessLog.error("AccessLogInterceptor postHandle occur error", ex);
        }
    }

    private static <T> void accessLog(Object returnValue, int httpStatus) {
        accessLog.info(buildAccessLogContent(returnValue, httpStatus));
    }

    private static String buildAccessLogContent(Object returnValue, int httpStatus) {
        HttpServletRequest request = getHttpServletRequest();

        StringBuilder logContentBuilder = new StringBuilder();
        try {
            // user ip
            logContentBuilder.append(getIpAddr(request)).append(" - - ");

            // request method
            String method = request.getMethod();
            logContentBuilder.append("\"").append(method).append(" ");

            // request url
            logContentBuilder.append("http://");
            logContentBuilder.append(request.getHeader("Host"));

            logContentBuilder.append(request.getRequestURI());

            // request params
            if ("GET".equals(method)) {
                logContentBuilder.append(request.getQueryString() == null ? "" : "?" + request.getQueryString()).append(" ");
            } else {
                boolean isRequestBodyJson = StringUtils.containsIgnoreCase(request.getContentType(), "application/json");
                if (isRequestBodyJson) {
                    Object requestBody = request.getAttribute(GlobalRequestHandler.HTTP_REQUEST_ATTRIBUTE_REQUEST_BODY);
                    logContentBuilder.append(" ")
                            .append(null != requestBody ? JsonUtils.toJson(requestBody, SENSITIVE_FIELDS) : "{}").append(" ");
                } else {
                    logContentBuilder.append("?");
                    Enumeration enumeration = request.getParameterNames();

                    while (enumeration.hasMoreElements()) {
                        String paramName = (String) enumeration.nextElement();
                        if (SENSITIVE_FIELDS.contains(paramName)) {
                            continue;
                        }
                        String value = request.getParameter(paramName);
                        try {
                            value = URLEncoder.encode(value, Charsets.UTF_8.toString());
                        } catch (UnsupportedEncodingException e) {
                            accessLog.error(e.getMessage(), e);
                        }
                        logContentBuilder.append(paramName).append("=").append(value).append("&");
                    }
                    logContentBuilder.deleteCharAt(logContentBuilder.length() - 1).append(" ");
                }

            }

            // protocol
            logContentBuilder.append(request.getProtocol()).append("\" ");

            // state code
            logContentBuilder.append(httpStatus).append(" - ");

            // referer
            String referer = request.getHeader("Referer");
            logContentBuilder.append("\"").append(StringUtils.isBlank(referer) ? "-" : referer).append("\" ");

            // user agent
            String userAgent = request.getHeader("User-Agent");
            logContentBuilder.append("\"").append(StringUtils.isBlank(userAgent) ? "-" : userAgent).append("\" ");

            if (returnValue != null) {
                logContentBuilder.append(formatReturnValue(returnValue)).append(" ");
            } else {
                logContentBuilder.append("returnValue:-").append(" ");
            }
            // 响应时间
            Long beginTime = (Long) request.getAttribute(REQUEST_ATTRIBUTE_START_TIME);
            if (beginTime != null) {
                logContentBuilder.append(String.valueOf(System.currentTimeMillis() - beginTime)).append(" ");
            } else {
                logContentBuilder.append("elapsed:-").append(" ");
            }

            UserInfo userInfo = UserInfoBag.get();
            if(userInfo != null) {
                logContentBuilder.append("loginUserId:").append(userInfo.getUserId()).append(" ")
                        .append("loginUserName:").append(userInfo.getName());
            } else {
                logContentBuilder.append("loginUser:-");
            }

        } catch (Exception e) {
            accessLog.error("get access log string Exception ", e);
        }
        return logContentBuilder.toString();
    }

    private static String formatReturnValue(Object returnValue) {
        if (0 == SENSITIVE_FIELDS.size()) {
            return JsonUtils.toJson(returnValue);
        } else {
            return JsonUtils.toJson(returnValue, SENSITIVE_FIELDS);
        }
    }

    private static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
