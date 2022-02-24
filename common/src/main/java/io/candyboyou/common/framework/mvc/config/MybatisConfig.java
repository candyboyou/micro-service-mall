package io.candyboyou.common.framework.mvc.config;

import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class MybatisConfig {

    Logger log =  LoggerFactory.getLogger(MybatisConfig.class);

    // 冒号后面的值，*Mapper.xml为前面的值为空时的默认值
    @Value("${mybatis.mapper-locations:classpath:*Mapper.xml}")
    private String mapperLocation;


    // @Value的值有两类：
    // ① ${ property : default_value }
    // ② #{ obj.property? :default_value }
    // 第一个注入的是外部配置文件对应的property，第二个则是SpEL表达式对应的内容。 那个
    // default_value，就是前面的值为空时的默认值。注意二者的不同，#{}里面那个obj代表对象。
    @Bean
    // 每次注入或者通过上下文获取的时候，都会创建一个新的bean实例
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    // 通过@ConfigurationProperties注解则会将application.yml配置的值映射到类中
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration defaultMybatisConfiguration() {
        return new org.apache.ibatis.session.Configuration();
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        return getSqlSessionFactory(dataSource, defaultMybatisConfiguration());
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
        return getSqlSessionFactory(dataSource, defaultMybatisConfiguration(), new ArrayList<>());
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, List<Interceptor> interceptors) throws Exception {
        return getSqlSessionFactory(dataSource, defaultMybatisConfiguration(), interceptors);
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration, List<Interceptor> interceptors) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = newSqlSessionFactoryBean(dataSource, interceptors);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

    private SqlSessionFactoryBean newSqlSessionFactoryBean(DataSource dataSource, List<Interceptor> interceptors) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resourceResolver.getResources(mapperLocation);
        } catch (FileNotFoundException e) {
            log.info("no mapper file found in mapperLocation:{}", mapperLocation);
        }
        if (resources != null) {
            sessionFactoryBean.setMapperLocations(resources);
        }
        VendorDatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        sessionFactoryBean.setDatabaseIdProvider(databaseIdProvider);
//        这里添加interceptor
//        MybatisSQLInterceptor mybatisSQLInterceptor = new MybatisSQLInterceptor();
//        mybatisSQLInterceptor.setTracer(defaultTracer);
//        mybatisSQLInterceptor.setReporter(defaultReporter);
//        mybatisSQLInterceptor.setMonitor(defaultMonitor);
//        interceptors.add(mybatisSQLInterceptor);
//        sessionFactoryBean.setPlugins(interceptors.toArray(new Interceptor[interceptors.size()]));
        return sessionFactoryBean;
    }
}
