package io.candyboyou.common.framework.mvc.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解按生命周期来划分可分为3类：
 * <li>1.RetentionPolicy.SOURCE：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；</li>
 * <li>2.RetentionPolicy.CLASS：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期；</li>
 * <li>3.RetentionPolicy.RUNTIME：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在；</li>
 */
@Target(ElementType.PARAMETER) // 说明作用位置是方法参数
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonParam {
    String value() default "";
}
