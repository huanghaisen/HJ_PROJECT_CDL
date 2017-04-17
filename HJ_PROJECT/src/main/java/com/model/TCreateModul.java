package com.model;

import javax.persistence.*;

/**
 * 商品分类与车型建模
 */
@Entity
@Table(name = "t_create_modul", schema = "hj", catalog = "")
public class TCreateModul {
    private long id;
    private String name;
    private String codes;
    private Long goodsId;

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
    @Column(name = "codes")
    public String getCodes () {
        return codes;
    }

    public void setCodes (String codes) {
        this.codes = codes;
    }

    @Basic
    @Column(name = "goods_id")
    public Long getGoodsId () {
        return goodsId;
    }

    public void setGoodsId (Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCreateModul that = (TCreateModul) o;

        if (id != that.id) return false;
        if (name != null ? ! name.equals(that.name) : that.name != null) return false;
        if (codes != null ? ! codes.equals(that.codes) : that.codes != null) return false;
        if (goodsId != null ? ! goodsId.equals(that.goodsId) : that.goodsId != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (codes != null ? codes.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        return result;
    }
}
