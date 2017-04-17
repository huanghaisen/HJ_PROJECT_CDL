package com.model;

import javax.persistence.*;

/**
 * 供应商
 */
@Entity
@Table(name = "t_supplier", schema = "hj", catalog = "")
public class TSupplier {
    private long id;
    private String num;
    private Long name;
    private Long orgId;

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
    @Column(name = "num")
    public String getNum () {
        return num;
    }

    public void setNum (String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "name")
    public Long getName () {
        return name;
    }

    public void setName (Long name) {
        this.name = name;
    }

    @Basic
    @Column(name = "org_id")
    public Long getOrgId () {
        return orgId;
    }

    public void setOrgId (Long orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TSupplier tSupplier = (TSupplier) o;

        if (id != tSupplier.id) return false;
        if (num != null ? ! num.equals(tSupplier.num) : tSupplier.num != null) return false;
        if (name != null ? ! name.equals(tSupplier.name) : tSupplier.name != null) return false;
        if (orgId != null ? ! orgId.equals(tSupplier.orgId) : tSupplier.orgId != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        return result;
    }
}
