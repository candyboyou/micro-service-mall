package io.candyboyou.mallpromotion.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.candyboyou.mallpromotion.utils.PropUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@Slf4j
public class DataSourceConfig extends MybatisConfig {

    @Autowired
    private Environment env;

    @Bean(name = "druidDataSource", destroyMethod = "close")
    public DruidDataSource createDataSource() throws IOException
    {
        log.info("====== DataSource init start ! =======");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.configFromPropety(PropUtils.load(DataSourceConfig.class, "druid.properties"));
        log.info("====== DataSource init success ! =======");
        return dataSource;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
        return this.getSqlSessionFactory(dataSource);
    }

}
