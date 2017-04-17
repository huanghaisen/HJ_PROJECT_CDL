package com.model;

import javax.persistence.*;

/**
 * 配送奖励表
 */
@Entity
@Table(name = "t_distribution_reward", schema = "hj", catalog = "")
public class TDistributionReward {
    private long id;
    private Long distributionAmount;
    private Long rewardAmount;

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
    @Column(name = "distribution_amount")
    public Long getDistributionAmount () {
        return distributionAmount;
    }

    public void setDistributionAmount (Long distributionAmount) {
        this.distributionAmount = distributionAmount;
    }

    @Basic
    @Column(name = "reward_amount")
    public Long getRewardAmount () {
        return rewardAmount;
    }

    public void setRewardAmount (Long rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDistributionReward that = (TDistributionReward) o;

        if (id != that.id) return false;
        if (distributionAmount != null ? ! distributionAmount.equals(that.distributionAmount) : that.distributionAmount != null)
            return false;
        if (rewardAmount != null ? ! rewardAmount.equals(that.rewardAmount) : that.rewardAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (distributionAmount != null ? distributionAmount.hashCode() : 0);
        result = 31 * result + (rewardAmount != null ? rewardAmount.hashCode() : 0);
        return result;
    }
}
