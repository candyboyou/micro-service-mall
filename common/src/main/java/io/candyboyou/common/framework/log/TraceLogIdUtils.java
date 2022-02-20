package io.candyboyou.common.framework.log;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

public class TraceLogIdUtils {
    public static String traceLogId = "TRACE_LOG_ID";

    public TraceLogIdUtils() {
    }

    public static void setTraceLogId(String traceLogId) {
        if (StringUtils.isNotEmpty(traceLogId)) {
            MDC.put(TraceLogIdUtils.traceLogId, traceLogId);
        } else {
            MDC.put(TraceLogIdUtils.traceLogId, UUID.randomUUID().toString());
        }

    }

    public static String getTraceLogId() {
        return MDC.get(traceLogId);
    }
}
