package com.cb.separation.config;

import com.cb.separation.dbutils.MyRoutingDataSource;
import com.cb.separation.model.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cb
 * @title: DataSourceConfig
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 9:59
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DBTypeEnum.MASTER,masterDataSource);
        dataSourceMap.put(DBTypeEnum.SLAVE,slaveDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setTargetDataSources(dataSourceMap);
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        return myRoutingDataSource;
    }
}
