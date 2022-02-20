package io.candyboyou.common.framework.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * logback自定义格式转换器
 * 创建自定义格式转换符有两步。
 * 首先，必须继承ClassicConverter类。ClassicConverter对象负责从ILoggingEvent 提取信息，并产生一个字符串。
 * 例如，LoggerConverter，它是处理“% logger”转换符的转换器，
 * 它从ILoggingEvent提取logger 的名字并作为字符串返回。
 * 假设我们的自定义ClassicConverter的功能是按照ANSI终端惯例为记录事件的级别进行着色，
 * 下面是一种可能的实现：
 * 示例：样本转换器例子
 */
public class TintConverter extends ClassicConverter {

    private static final String END_COLOR = "\u001b[m";

    private static final String ERROR_COLOR = "\u001b[0;31m";
    private static final String WARN_COLOR = "\u001b[0;33m";

    @Override
    public String convert(ILoggingEvent event) {
        return getColor(event.getLevel()) +
                event.getLevel() +
                END_COLOR;
    }

    private String getColor(Level level) {
        return switch (level.toInt()) {
            case Level.ERROR_INT -> ERROR_COLOR;
            case Level.WARN_INT -> WARN_COLOR;
            default -> "";
        };
    }
}
