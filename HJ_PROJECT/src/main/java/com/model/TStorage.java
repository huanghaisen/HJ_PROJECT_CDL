package com.model;

import javax.persistence.*;

/**
 * 入库表
 */
@Entity
@Table(name = "t_storage", schema = "hj", catalog = "")
public class TStorage {
    private long id;
    private String orderNo;
    private Long relationId;
    private Long userId;
    private Integer isInvoice;
    private Integer payType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_no")
    public String getOrderNo () {
        return orderNo;
    }

    public void setOrderNo (String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "relation_id")
    public Long getRelationId () {
        return relationId;
    }

    public void setRelationId (Long relationId) {
        this.relationId = relationId;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "is_invoice")
    public Integer getIsInvoice () {
        return isInvoice;
    }

    public void setIsInvoice (Integer isInvoice) {
        this.isInvoice = isInvoice;
    }

    @Basic
    @Column(name = "pay_type")
    public Integer getPayType () {
        return payType;
    }

    public void setPayType (Integer payType) {
        this.payType = payType;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TStorage tStorage = (TStorage) o;

        if (id != tStorage.id) return false;
        if (orderNo != null ? ! orderNo.equals(tStorage.orderNo) : tStorage.orderNo != null) return false;
        if (relationId != null ? ! relationId.equals(tStorage.relationId) : tStorage.relationId != null) return false;
        if (userId != null ? ! userId.equals(tStorage.userId) : tStorage.userId != null) return false;
        if (isInvoice != null ? ! isInvoice.equals(tStorage.isInvoice) : tStorage.isInvoice != null) return false;
        if (payType != null ? ! payType.equals(tStorage.payType) : tStorage.payType != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (relationId != null ? relationId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (isInvoice != null ? isInvoice.hashCode() : 0);
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        return result;
    }
}
