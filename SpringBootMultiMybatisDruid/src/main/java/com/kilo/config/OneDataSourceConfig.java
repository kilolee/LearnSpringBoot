package com.kilo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by kilo on 2018/5/1.
 */
@Configuration
@MapperScan(basePackages = "com.kilo.mapper.one",sqlSessionTemplateRef = "oneSqlSessionTemplate")
public class OneDataSourceConfig {

    /**
     * 创建一个SqlSessionFactory，
     * 将第一个数据源注入到其中
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Primary
    @Bean("oneSqlSessionFactory")
    public SqlSessionFactory oneSqlSessionFactory(@Qualifier("oneDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * 创建事务管理
     * 将数据源添加到事务中
     * @param dataSource
     * @return
     */
    @Primary
    @Bean("oneTransactionManager")
    public DataSourceTransactionManager oneTransactionManager(@Qualifier("oneDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 创建在 Mapper 中需要使用的SqlSessionTemplate
     * 将上面创建的SqlSessionFactory注入
     * @param sqlSessionFactory
     * @return
     */
    @Primary
    @Bean("oneSqlSessionTemplate")
    public SqlSessionTemplate oneSqlSessionTemplate(@Qualifier("oneSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
