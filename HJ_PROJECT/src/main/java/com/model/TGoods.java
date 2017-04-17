package com.model;

import javax.persistence.*;

/**
 * 商品表
 */
@Entity
@Table(name = "t_goods", schema = "hj", catalog = "")
public class TGoods {
    private long id;
    private String name;
    private String num;
    private String code;
    private String type;
    private Long brandId;
    private Long goodsTypeId;
    private Long outPrice;
    private Double cess;
    private String title;
    private String introduction;
    private String picture;

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
    @Column(name = "num")
    public String getNum () {
        return num;
    }

    public void setNum (String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "code")
    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "type")
    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "brand_id")
    public Long getBrandId () {
        return brandId;
    }

    public void setBrandId (Long brandId) {
        this.brandId = brandId;
    }

    @Basic
    @Column(name = "goods_type_id")
    public Long getGoodsTypeId () {
        return goodsTypeId;
    }

    public void setGoodsTypeId (Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    @Basic
    @Column(name = "out_price")
    public Long getOutPrice () {
        return outPrice;
    }

    public void setOutPrice (Long outPrice) {
        this.outPrice = outPrice;
    }

    @Basic
    @Column(name = "cess")
    public Double getCess () {
        return cess;
    }

    public void setCess (Double cess) {
        this.cess = cess;
    }

    @Basic
    @Column(name = "title")
    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "introduction")
    public String getIntroduction () {
        return introduction;
    }

    public void setIntroduction (String introduction) {
        this.introduction = introduction;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture () {
        return picture;
    }

    public void setPicture (String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TGoods tGoods = (TGoods) o;

        if (id != tGoods.id) return false;
        if (name != null ? ! name.equals(tGoods.name) : tGoods.name != null) return false;
        if (num != null ? ! num.equals(tGoods.num) : tGoods.num != null) return false;
        if (code != null ? ! code.equals(tGoods.code) : tGoods.code != null) return false;
        if (type != null ? ! type.equals(tGoods.type) : tGoods.type != null) return false;
        if (brandId != null ? ! brandId.equals(tGoods.brandId) : tGoods.brandId != null) return false;
        if (goodsTypeId != null ? ! goodsTypeId.equals(tGoods.goodsTypeId) : tGoods.goodsTypeId != null) return false;
        if (outPrice != null ? ! outPrice.equals(tGoods.outPrice) : tGoods.outPrice != null) return false;
        if (cess != null ? ! cess.equals(tGoods.cess) : tGoods.cess != null) return false;
        if (title != null ? ! title.equals(tGoods.title) : tGoods.title != null) return false;
        if (introduction != null ? ! introduction.equals(tGoods.introduction) : tGoods.introduction != null)
            return false;
        if (picture != null ? ! picture.equals(tGoods.picture) : tGoods.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brandId != null ? brandId.hashCode() : 0);
        result = 31 * result + (goodsTypeId != null ? goodsTypeId.hashCode() : 0);
        result = 31 * result + (outPrice != null ? outPrice.hashCode() : 0);
        result = 31 * result + (cess != null ? cess.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (introduction != null ? introduction.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
}
