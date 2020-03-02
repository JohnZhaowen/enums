package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/*
 * author: zhaowen.he
 * date: 2019/8/28
 * ticket:
 * description:
 */
@Configuration
@MapperScan(basePackages = "com.example.demo.mapper", sqlSessionFactoryRef  = "sqlSessionFactory")
public class DataSourceConfig {

        private final String sqlmap = "classpath*:mapper/*Mapper.xml";
        private final String mybatisConfig = "classpath:mapper/config.xml";

        @Bean(name = "dataSource")
        @ConfigurationProperties(prefix = "spring.datasource.biz")
        @Primary
        public DataSource dataSource() {
                return DataSourceBuilder.create().build();
        }

        @Bean(name = "sqlSessionFactory")
        @Primary
        public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(sqlmap));
                bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource(mybatisConfig));
                bean.getObject().getConfiguration().getTypeHandlerRegistry().register(Enum.class, BaseEnumHandler.class);
                bean.setDataSource(dataSource);
                //保证jar模式运行
                bean.setVfs(SpringBootVFS.class);
/*                SqlSession sqlSession = bean.getObject().openSession();
                ResultSet resultSet = sqlSession.getConnection().prepareStatement("select account_id as s from account order by account_id desc limit 1").executeQuery();
                resultSet.next();
                String  accoundId = resultSet.getString(1);*/
                return bean.getObject();
        }

        @Bean(name = "transactionManager")
        @Primary
        public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
                return new DataSourceTransactionManager(dataSource);
        }
}
