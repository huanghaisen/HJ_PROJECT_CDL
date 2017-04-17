package com.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 入库商品表
 */
@Entity
@Table(name = "t_stroage_goods", schema = "hj", catalog = "")
public class TStroageGoods {
    private long id;
    private Long stroageId;
    private Long goodsId;
    private Integer number;
    private BigDecimal price;

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
    @Column(name = "stroage_id")
    public Long getStroageId () {
        return stroageId;
    }

    public void setStroageId (Long stroageId) {
        this.stroageId = stroageId;
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

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice () {
        return price;
    }

    public void setPrice (BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TStroageGoods that = (TStroageGoods) o;

        if (id != that.id) return false;
        if (stroageId != null ? ! stroageId.equals(that.stroageId) : that.stroageId != null) return false;
        if (goodsId != null ? ! goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (number != null ? ! number.equals(that.number) : that.number != null) return false;
        if (price != null ? ! price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (stroageId != null ? stroageId.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
