package com.model;

import javax.persistence.*;

/**
 * 出库表
 */
@Entity
@Table(name = "t_library", schema = "hj", catalog = "")
public class TLibrary {
    private long id;
    private String libraryNo;
    private Long orgId;
    private Long libraryType;
    private String goodsCarrier;
    private Long relarionId;

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
    @Column(name = "library_no")
    public String getLibraryNo () {
        return libraryNo;
    }

    public void setLibraryNo (String libraryNo) {
        this.libraryNo = libraryNo;
    }

    @Basic
    @Column(name = "org_id")
    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "library_type")
    public Long getLibraryType () {
        return libraryType;
    }

    public void setLibraryType (Long libraryType) {
        this.libraryType = libraryType;
    }

    @Basic
    @Column(name = "goods_carrier")
    public String getGoodsCarrier () {
        return goodsCarrier;
    }

    public void setGoodsCarrier (String goodsCarrier) {
        this.goodsCarrier = goodsCarrier;
    }

    @Basic
    @Column(name = "relarion_id")
    public Long getRelarionId () {
        return relarionId;
    }

    public void setRelarionId (Long relarionId) {
        this.relarionId = relarionId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TLibrary tLibrary = (TLibrary) o;

        if (id != tLibrary.id) return false;
        if (libraryNo != null ? ! libraryNo.equals(tLibrary.libraryNo) : tLibrary.libraryNo != null) return false;
        if (orgId != null ? ! orgId.equals(tLibrary.orgId) : tLibrary.orgId != null) return false;
        if (libraryType != null ? ! libraryType.equals(tLibrary.libraryType) : tLibrary.libraryType != null)
            return false;
        if (goodsCarrier != null ? ! goodsCarrier.equals(tLibrary.goodsCarrier) : tLibrary.goodsCarrier != null)
            return false;
        if (relarionId != null ? ! relarionId.equals(tLibrary.relarionId) : tLibrary.relarionId != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (libraryNo != null ? libraryNo.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (libraryType != null ? libraryType.hashCode() : 0);
        result = 31 * result + (goodsCarrier != null ? goodsCarrier.hashCode() : 0);
        result = 31 * result + (relarionId != null ? relarionId.hashCode() : 0);
        return result;
    }
}
