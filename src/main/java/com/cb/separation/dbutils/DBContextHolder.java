package com.cb.separation.dbutils;

import com.cb.separation.model.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * @author cb
 * @title: DBContextHolder
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 9:44
 */
@Slf4j
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();


    public static void set(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    public static DBTypeEnum get(){
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        log.info("切换到主库");
    }
    public static void slave() {
        set(DBTypeEnum.SLAVE);
        log.info("切换到从库");
    }

}
