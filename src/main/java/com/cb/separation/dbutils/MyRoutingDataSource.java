package com.cb.separation.dbutils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author cb
 * @title: MyRoutingDataSource
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 9:53
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
