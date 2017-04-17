package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 积分表
 */
@Entity
@Table(name = "t_integral", schema = "hj", catalog = "")
public class TIntegral {
    private long id;
    private String integralRule;
    private String content;
    private Timestamp createTime;

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
    @Column(name = "integral_rule")
    public String getIntegralRule () {
        return integralRule;
    }

    public void setIntegralRule (String integralRule) {
        this.integralRule = integralRule;
    }

    @Basic
    @Column(name = "content")
    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime () {
        return createTime;
    }

    public void setCreateTime (Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TIntegral tIntegral = (TIntegral) o;

        if (id != tIntegral.id) return false;
        if (integralRule != null ? ! integralRule.equals(tIntegral.integralRule) : tIntegral.integralRule != null)
            return false;
        if (content != null ? ! content.equals(tIntegral.content) : tIntegral.content != null) return false;
        if (createTime != null ? ! createTime.equals(tIntegral.createTime) : tIntegral.createTime != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (integralRule != null ? integralRule.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        return result;
    }
}
