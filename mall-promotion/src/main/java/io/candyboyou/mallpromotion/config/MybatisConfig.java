package io.candyboyou.mallpromotion.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Configuration
//@Slf4j
public class MybatisConfig {

    private static final Logger log = LoggerFactory.getLogger(MybatisConfig.class);

    @Value("${mybatis.mapper-locations:classpath:*Mapper.xml}")
    private String mapperLocation;

    @Value("${mybatis.configuration.mapUnderscoreToCamelCase:false}")
    private boolean mapUnderscoreToCamelCase;

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        return this.getSqlSessionFactory(dataSource, this.defaultMybatisConfiguration());
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration) throws Exception {
        return this.getSqlSessionFactory(dataSource, configuration, new ArrayList());
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource, org.apache.ibatis.session.Configuration configuration, List<Interceptor> interceptors) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = this.newSqlSessionFactoryBean(dataSource, interceptors);
        sessionFactoryBean.setConfiguration(configuration);
        return sessionFactoryBean.getObject();
    }

    private SqlSessionFactoryBean newSqlSessionFactoryBean(DataSource dataSource, List<Interceptor> interceptors) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = resourcePatternResolver.getResources(this.mapperLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (resources != null) {
            sqlSessionFactoryBean.setMapperLocations(resources);
        }

        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        sqlSessionFactoryBean.setDatabaseIdProvider(vendorDatabaseIdProvider);

        // mybatis的interceptor
//        MybatisSQLInterceptor mybatisSQLInterceptor = new MybatisSQLInterceptor();
//        mybatisSQLInterceptor.setTracer(this.defaultTracer);
//        mybatisSQLInterceptor.setReporter(this.defaultReporter);
//        mybatisSQLInterceptor.setMysqlReporter(this.mysqlReporter);
//        mybatisSQLInterceptor.setMonitor(this.defaultMonitor);
//        mybatisSQLInterceptor.setDependencyHelper(this.dependencyHelper);

        String url = "";

        try {
            Connection connection = dataSource.getConnection();

            Exception exception = null;

            try {
                url = connection.getMetaData().getURL();
            } catch (Exception e) {
                exception = e;
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        if (exception != null) {
                            exception.addSuppressed(e);
                        } else {
                            exception = e;
                        }
                    }
                }

                if (exception != null) {
                    throw exception;
                }
            }

        } catch (Exception e) {
            log.error("init failed ... ... create connection error", e);
        }

//        String defaultDbName = "";
//        if (url.contains("jdbc:mysql://")) {
//            String con = url.split("jdbc:mysql://")[1];
//            con = con.split("\\?")[0];
//            int index = con.indexOf("/");
//            if (index >= 0) {
//                defaultDbName = con.substring(con.indexOf("/") + 1);
//                con = con.substring(0, con.indexOf("/"));
//            }
//
//            url = "jdbc:mysql://" + con + "/";
//        }

        return sqlSessionFactoryBean;
    }

    private org.apache.ibatis.session.Configuration defaultMybatisConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(this.mapUnderscoreToCamelCase);
        return configuration;
    }
}
