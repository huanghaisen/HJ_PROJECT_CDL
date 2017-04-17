package com.model;

import javax.persistence.*;

/**
 * 评分表
 */
@Entity
@Table(name = "t_score", schema = "hj", catalog = "")
public class TScore {
    private long id;
    private Double begin;
    private Double end;
    private Integer firstAmount;

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
    @Column(name = "begin")
    public Double getBegin () {
        return begin;
    }

    public void setBegin (Double begin) {
        this.begin = begin;
    }

    @Basic
    @Column(name = "end")
    public Double getEnd () {
        return end;
    }

    public void setEnd (Double end) {
        this.end = end;
    }

    @Basic
    @Column(name = "first_amount")
    public Integer getFirstAmount () {
        return firstAmount;
    }

    public void setFirstAmount (Integer firstAmount) {
        this.firstAmount = firstAmount;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TScore tScore = (TScore) o;

        if (id != tScore.id) return false;
        if (begin != null ? ! begin.equals(tScore.begin) : tScore.begin != null) return false;
        if (end != null ? ! end.equals(tScore.end) : tScore.end != null) return false;
        if (firstAmount != null ? ! firstAmount.equals(tScore.firstAmount) : tScore.firstAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (begin != null ? begin.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (firstAmount != null ? firstAmount.hashCode() : 0);
        return result;
    }
}
