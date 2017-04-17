package com.model;

import javax.persistence.*;

/**
 * 业务员提成表
 */
@Entity
@Table(name = "t_reward", schema = "hj", catalog = "")
public class TReward {
    private long id;
    private Long userId;
    private Long merchantId;
    private Long rewardTypeId;

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
    @Column(name = "merchant_id")
    public Long getMerchantId () {
        return merchantId;
    }

    public void setMerchantId (Long merchantId) {
        this.merchantId = merchantId;
    }

    @Basic
    @Column(name = "reward_type_id")
    public Long getRewardTypeId () {
        return rewardTypeId;
    }

    public void setRewardTypeId (Long rewardTypeId) {
        this.rewardTypeId = rewardTypeId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TReward tReward = (TReward) o;

        if (id != tReward.id) return false;
        if (userId != null ? ! userId.equals(tReward.userId) : tReward.userId != null) return false;
        if (merchantId != null ? ! merchantId.equals(tReward.merchantId) : tReward.merchantId != null) return false;
        if (rewardTypeId != null ? ! rewardTypeId.equals(tReward.rewardTypeId) : tReward.rewardTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (merchantId != null ? merchantId.hashCode() : 0);
        result = 31 * result + (rewardTypeId != null ? rewardTypeId.hashCode() : 0);
        return result;
    }
}
