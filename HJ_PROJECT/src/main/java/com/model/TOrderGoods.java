package com.model;

import javax.persistence.*;
/**
 * 订单商品表
 */
@Entity
@Table(name = "t_order_goods", schema = "hj", catalog = "")
public class TOrderGoods {
    private long id;
    private Long orderId;
    private Long goodsId;
    private Integer number;
    private Integer price;

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
    @Column(name = "order_id")
    public Long getOrderId () {
        return orderId;
    }

    public void setOrderId (Long orderId) {
        this.orderId = orderId;
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
    public Integer getPrice () {
        return price;
    }

    public void setPrice (Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOrderGoods that = (TOrderGoods) o;

        if (id != that.id) return false;
        if (orderId != null ? ! orderId.equals(that.orderId) : that.orderId != null) return false;
        if (goodsId != null ? ! goodsId.equals(that.goodsId) : that.goodsId != null) return false;
        if (number != null ? ! number.equals(that.number) : that.number != null) return false;
        if (price != null ? ! price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        result = 31 * result + (goodsId != null ? goodsId.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
