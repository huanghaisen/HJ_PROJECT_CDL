package com.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 免运费
 */
@Entity
@Table(name = "t_freight_free", schema = "hj", catalog = "")
public class TFreightFree {
    private long id;
    private String name;
    private BigDecimal limitAmount;

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
    @Column(name = "name")
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "limit_amount")
    public BigDecimal getLimitAmount () {
        return limitAmount;
    }

    public void setLimitAmount (BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TFreightFree that = (TFreightFree) o;

        if (id != that.id) return false;
        if (name != null ? ! name.equals(that.name) : that.name != null) return false;
        if (limitAmount != null ? ! limitAmount.equals(that.limitAmount) : that.limitAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (limitAmount != null ? limitAmount.hashCode() : 0);
        return result;
    }
}
