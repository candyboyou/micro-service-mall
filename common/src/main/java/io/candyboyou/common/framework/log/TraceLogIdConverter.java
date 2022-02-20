package io.candyboyou.common.framework.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class TraceLogIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String v = (String)event.getMDCPropertyMap().get(TraceLogIdUtils.traceLogId);
        return v == null ? String.valueOf(Thread.currentThread().getId()) : v;
    }
}
