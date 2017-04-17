package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 订单操作表
 */
@Entity
@Table(name = "t_order_action", schema = "hj", catalog = "")
public class TOrderAction {
    private long id;
    private Long userId;
    private Timestamp actionTime;
    private Integer status;
    private Integer payStatus;
    private String remark;

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
    @Column(name = "user_id")
    public Long getUserId () {
        return userId;
    }

    public void setUserId (Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "action_time")
    public Timestamp getActionTime () {
        return actionTime;
    }

    public void setActionTime (Timestamp actionTime) {
        this.actionTime = actionTime;
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
    @Column(name = "pay_status")
    public Integer getPayStatus () {
        return payStatus;
    }

    public void setPayStatus (Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Basic
    @Column(name = "remark")
    public String getRemark () {
        return remark;
    }

    public void setRemark (String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOrderAction that = (TOrderAction) o;

        if (id != that.id) return false;
        if (userId != null ? ! userId.equals(that.userId) : that.userId != null) return false;
        if (actionTime != null ? ! actionTime.equals(that.actionTime) : that.actionTime != null) return false;
        if (status != null ? ! status.equals(that.status) : that.status != null) return false;
        if (payStatus != null ? ! payStatus.equals(that.payStatus) : that.payStatus != null) return false;
        if (remark != null ? ! remark.equals(that.remark) : that.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (actionTime != null ? actionTime.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (payStatus != null ? payStatus.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
