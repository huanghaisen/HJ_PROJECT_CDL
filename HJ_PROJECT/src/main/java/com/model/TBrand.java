package com.model;

import javax.persistence.*;

/**
 * 品牌表
 */
@Entity
@Table(name = "t_brand", schema = "hj", catalog = "")
public class TBrand {
    private long id;
    private String name;

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

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TBrand tBrand = (TBrand) o;

        if (id != tBrand.id) return false;
        if (name != null ? ! name.equals(tBrand.name) : tBrand.name != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
