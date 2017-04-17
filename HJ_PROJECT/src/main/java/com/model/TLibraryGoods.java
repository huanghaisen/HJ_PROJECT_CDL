package com.model;

import javax.persistence.*;

/**
 * 出库商品表
 */
@Entity
@Table(name = "t_library_goods", schema = "hj", catalog = "")
public class TLibraryGoods {
    private long id;
    private Long libraryId;
    private Long goodsId;
    private Integer number;

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
    @Column(name = "library_id")
    public Long getLibraryId () {
        return libraryId;
    }

    public void setLibraryId (Long libraryId) {
        this.libraryId = libraryId;
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
    @Column(name = "number")
    public Integer getNumber () {
        return number;
    }

    public void setNumber (Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLibraryGoods that = (TLibraryGoods) o;

        if (id != that.id) return false;
        if (libraryId != null ? ! libraryId.equals(that.libraryId) : that.libraryId != null) return false;
        if (goodsId != null ? ! goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (number != null ? ! number.equals(that.number) : that.number != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (libraryId != null ? libraryId.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
