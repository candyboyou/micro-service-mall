package pers.candyboyou.commodity.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.candyboyou.common.framework.mvc.config.MybatisConfig;
import io.candyboyou.common.utils.PropUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "pers.candyboyou.commodity.business.mapper",
            sqlSessionFactoryRef = "sqlSessionFactory"
)
@Slf4j
public class DataSourceConfig extends MybatisConfig {

    @Autowired
    private Environment env;

    @Value("props/druid.properties")
    private String druidFileName;

    @Bean(name = "druidDataSource", destroyMethod = "close")
    public DruidDataSource createDataSource() {
        log.info("====== DataSource init start ! =======");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver-class-name"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.configFromPropety(PropUtils.load(DataSourceConfig.class, druidFileName));
        log.info("====== DataSource init success ! =======");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception {
        return getSqlSessionFactory(dataSource);
    }

}
