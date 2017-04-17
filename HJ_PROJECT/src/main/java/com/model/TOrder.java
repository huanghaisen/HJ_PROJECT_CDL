package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 订单表
 */
@Entity
@Table(name = "t_order", schema = "hj", catalog = "")
public class TOrder {
    private long id;
    private String orderNo;
    private String shoppingUnit;
    private String shoppers;
    private Timestamp orderTime;
    private Timestamp shipTime;
    private Long shipId;
    private String reveiver;
    private String address;
    private String phone;
    private Integer status;
    private Long orgId;
    private Long pid;

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
    @Column(name = "shopping_unit")
    public String getShoppingUnit () {
        return shoppingUnit;
    }

    public void setShoppingUnit (String shoppingUnit) {
        this.shoppingUnit = shoppingUnit;
    }

    @Basic
    @Column(name = "shoppers")
    public String getShoppers () {
        return shoppers;
    }

    public void setShoppers (String shoppers) {
        this.shoppers = shoppers;
    }

    @Basic
    @Column(name = "order_time")
    public Timestamp getOrderTime () {
        return orderTime;
    }

    public void setOrderTime (Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    @Basic
    @Column(name = "ship_time")
    public Timestamp getShipTime () {
        return shipTime;
    }

    public void setShipTime (Timestamp shipTime) {
        this.shipTime = shipTime;
    }

    @Basic
    @Column(name = "ship_id")
    public Long getShipId () {
        return shipId;
    }

    public void setShipId (Long shipId) {
        this.shipId = shipId;
    }

    @Basic
    @Column(name = "reveiver")
    public String getReveiver () {
        return reveiver;
    }

    public void setReveiver (String reveiver) {
        this.reveiver = reveiver;
    }

    @Basic
    @Column(name = "address")
    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone () {
        return phone;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus () {
        return status;
    }

    public void setStatus (Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "org_id")
    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "pid")
    public Long getPid () {
        return pid;
    }

    public void setPid (Long pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOrder tOrder = (TOrder) o;

        if (id != tOrder.id) return false;
        if (orderNo != null ? ! orderNo.equals(tOrder.orderNo) : tOrder.orderNo != null) return false;
        if (shoppingUnit != null ? ! shoppingUnit.equals(tOrder.shoppingUnit) : tOrder.shoppingUnit != null)
            return false;
        if (shoppers != null ? ! shoppers.equals(tOrder.shoppers) : tOrder.shoppers != null) return false;
        if (orderTime != null ? ! orderTime.equals(tOrder.orderTime) : tOrder.orderTime != null) return false;
        if (shipTime != null ? ! shipTime.equals(tOrder.shipTime) : tOrder.shipTime != null) return false;
        if (shipId != null ? ! shipId.equals(tOrder.shipId) : tOrder.shipId != null) return false;
        if (reveiver != null ? ! reveiver.equals(tOrder.reveiver) : tOrder.reveiver != null) return false;
        if (address != null ? ! address.equals(tOrder.address) : tOrder.address != null) return false;
        if (phone != null ? ! phone.equals(tOrder.phone) : tOrder.phone != null) return false;
        if (status != null ? ! status.equals(tOrder.status) : tOrder.status != null) return false;
        if (orgId != null ? ! orgId.equals(tOrder.orgId) : tOrder.orgId != null) return false;
        if (pid != null ? ! pid.equals(tOrder.pid) : tOrder.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderNo != null ? orderNo.hashCode() : 0);
        result = 31 * result + (shoppingUnit != null ? shoppingUnit.hashCode() : 0);
        result = 31 * result + (shoppers != null ? shoppers.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (shipTime != null ? shipTime.hashCode() : 0);
        result = 31 * result + (shipId != null ? shipId.hashCode() : 0);
        result = 31 * result + (reveiver != null ? reveiver.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }
}
