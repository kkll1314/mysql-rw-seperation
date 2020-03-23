package com.cb.separation.aop;

import com.cb.separation.dbutils.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author cb
 * @title: DataSourceAop
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 10:11
 */
@Aspect
@Component
public class DataSourceAop {
    @Pointcut("!@annotation(com.cb.separation.annotation.Master) " +
            "&& (execution(* com.cb.separation.service..*.select*(..)) " +
            "|| execution(* com.cb.separation.service..*.get*(..))" +
            "|| execution(* com.cb.separation.dao..*.select*(..)) "+
            "|| execution(* com.cb.separation.service..*.find*(..))" +
            "|| execution(* com.cb.separation.service..*.query*(..)))")
    public void slavePointcut() {

    }

    /*主库的切点,或者标注了Master注解或者方法名为insert、update等开头的方法，走主库*/
    @Pointcut("@annotation(com.cb.separation.annotation.Master) " +
            "|| execution(* com.cb.separation.service..*.insert*(..)) " +
            "|| execution(* com.cb.separation.service..*.add*(..)) " +
            "|| execution(* com.cb.separation.service..*.update*(..)) " +
            "|| execution(* com.cb.separation.service..*.edit*(..)) " +
            "|| execution(* com.cb.separation.service..*.delete*(..)) " +
            "|| execution(* com.cb.separation.service..*.remove*(..))")
    public void masterPointcut() {
    }

    @Before("slavePointcut()")
    public void slave() {
        DBContextHolder.slave();
    }
    @Before("masterPointcut()")
    public void master() {
        DBContextHolder.master();
    }
}
