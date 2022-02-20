package io.candyboyou.common.utils;

import org.slf4j.MDC;

import javax.servlet.http.HttpServletRequest;

public class TraceIdUtils {

    private static final String TRACE_ID_KEY = "traceId";

    private static final String SPAN_ID_KEY = "spanId";

    public static String getTraceId() {
        return MDC.get(TRACE_ID_KEY);
    }

    public static void initTraceForHttp(HttpServletRequest httpServletRequest) {
//        SpanContext currentSpanContext = tracer.extract(httpServletRequest);
//
//        if (currentSpanContext == null)
//        {
//            currentSpanContext = startSpanForHttp(httpServletRequest);
//        }
//
//        if (currentSpanContext == null)
//        {
//            log.warn("http全链路监控打标未成功开始");
//            return;
//        }
//
//        MDC.put(TRACE_ID_KEY, currentSpanContext.getTraceId());
//        MDC.put(SPAN_ID_KEY, currentSpanContext.getSpanId());
//        MDC.put(CAT_MESSAGE_ID_KEY,Cat.getCurrentMessageId());
    }

    public static void finishTraceForHttp(boolean b) {

    }
}
