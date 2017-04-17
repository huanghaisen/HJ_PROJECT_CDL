package com.model;

import javax.persistence.*;

/**
 * 区域商品表
 */
@Entity
@Table(name = "t_goods_area", schema = "hj", catalog = "")
public class TGoodsArea {
    private long id;
    private Long goodsId;
    private Long orgId;
    private Integer isOn;
    private Integer isHot;
    private Long activityId;
    private Integer activityState;

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
    @Column(name = "goods_id")
    public Long getGoodsId () {
        return goodsId;
    }

    public void setGoodsId (Long goodsId) {
        this.goodsId = goodsId;
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
    @Column(name = "is_on")
    public Integer getIsOn () {
        return isOn;
    }

    public void setIsOn (Integer isOn) {
        this.isOn = isOn;
    }

    @Basic
    @Column(name = "is_hot")
    public Integer getIsHot () {
        return isHot;
    }

    public void setIsHot (Integer isHot) {
        this.isHot = isHot;
    }

    @Basic
    @Column(name = "activity_id")
    public Long getActivityId () {
        return activityId;
    }

    public void setActivityId (Long activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "activity_state")
    public Integer getActivityState () {
        return activityState;
    }

    public void setActivityState (Integer activityState) {
        this.activityState = activityState;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TGoodsArea that = (TGoodsArea) o;

        if (id != that.id) return false;
        if (goodsId != null ? ! goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (orgId != null ? ! orgId.equals(that.orgId) : that.orgId != null) return false;
        if (isOn != null ? ! isOn.equals(that.isOn) : that.isOn != null) return false;
        if (isHot != null ? ! isHot.equals(that.isHot) : that.isHot != null) return false;
        if (activityId != null ? ! activityId.equals(that.activityId) : that.activityId != null) return false;
        if (activityState != null ? ! activityState.equals(that.activityState) : that.activityState != null)
            return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (isOn != null ? isOn.hashCode() : 0);
        result = 31 * result + (isHot != null ? isHot.hashCode() : 0);
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (activityState != null ? activityState.hashCode() : 0);
        return result;
    }
}
