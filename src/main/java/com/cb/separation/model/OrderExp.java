package com.cb.separation.model;

import lombok.Data;

import java.util.Date;

/**
 * @author cb
 * @title: OrderExp
 * @projectName separation
 * @description: TODO
 * @date 2020/3/23 9:42
 */
@Data
public class OrderExp {

    public OrderExp(){
        super();
    }

    public OrderExp(Long id, String orderNo, String orderNote, Date insertTime,
                    Long expireDuration, Date expireTime, Short orderStatus) {
        this.id = id;
        this.orderNo = orderNo;
        this.orderNote = orderNote;
        this.insertTime = insertTime;
        this.expireDuration = expireDuration;
        this.expireTime = expireTime;
        this.orderStatus = orderStatus;
    }
    private Long id;

    private String orderNo;

    private String orderNote;

    private Date insertTime;

    private Long expireDuration;

    private Date expireTime;

    /*0：未支付；1：已支付；-1：已过期，关闭*/
    private Short orderStatus;
}
