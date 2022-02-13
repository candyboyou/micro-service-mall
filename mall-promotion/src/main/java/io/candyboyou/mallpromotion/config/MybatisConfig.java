package io.candyboyou.mallpromotion.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan()
public class MybatisConfig {

    @Bean
    protected SqlSessionFactory getSqlSessionFactory(DataSource dataSource) {
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactory();
    }
}
