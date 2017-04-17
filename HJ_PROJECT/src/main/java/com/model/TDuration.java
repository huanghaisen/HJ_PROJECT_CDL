package com.model;

import javax.persistence.*;

/**
 * 配送时长表
 */
@Entity
@Table(name = "t_duration", schema = "hj", catalog = "")
public class TDuration {
    private long id;
    private Double lDistance;
    private Double rDistance;
    private Integer useTime;

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
    @Column(name = "L_distance")
    public Double getlDistance () {
        return lDistance;
    }

    public void setlDistance (Double lDistance) {
        this.lDistance = lDistance;
    }

    @Basic
    @Column(name = "R_distance")
    public Double getrDistance () {
        return rDistance;
    }

    public void setrDistance (Double rDistance) {
        this.rDistance = rDistance;
    }

    @Basic
    @Column(name = "use_time")
    public Integer getUseTime () {
        return useTime;
    }

    public void setUseTime (Integer useTime) {
        this.useTime = useTime;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TDuration tDuration = (TDuration) o;

        if (id != tDuration.id) return false;
        if (lDistance != null ? ! lDistance.equals(tDuration.lDistance) : tDuration.lDistance != null) return false;
        if (rDistance != null ? ! rDistance.equals(tDuration.rDistance) : tDuration.rDistance != null) return false;
        if (useTime != null ? ! useTime.equals(tDuration.useTime) : tDuration.useTime != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (lDistance != null ? lDistance.hashCode() : 0);
        result = 31 * result + (rDistance != null ? rDistance.hashCode() : 0);
        result = 31 * result + (useTime != null ? useTime.hashCode() : 0);
        return result;
    }
}
