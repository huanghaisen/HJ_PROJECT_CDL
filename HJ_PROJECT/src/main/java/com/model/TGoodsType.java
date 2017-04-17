package com.model;

import javax.persistence.*;

/**
 * 商品类别表
 */
@Entity
@Table(name = "t_goods_type", schema = "hj", catalog = "")
public class TGoodsType {
    private long id;
    private String name;
    private Long pid;
    private Long bUnitId;
    private Long lUnitId;
    private String properties;
    private Integer isDeleted;

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
    @Column(name = "pid")
    public Long getPid () {
        return pid;
    }

    public void setPid (Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "b_unit_id")
    public Long getbUnitId () {
        return bUnitId;
    }

    public void setbUnitId (Long bUnitId) {
        this.bUnitId = bUnitId;
    }

    @Basic
    @Column(name = "l_unit_id")
    public Long getlUnitId () {
        return lUnitId;
    }

    public void setlUnitId (Long lUnitId) {
        this.lUnitId = lUnitId;
    }

    @Basic
    @Column(name = "properties")
    public String getProperties () {
        return properties;
    }

    public void setProperties (String properties) {
        this.properties = properties;
    }

    @Basic
    @Column(name = "is_deleted")
    public Integer getIsDeleted () {
        return isDeleted;
    }

    public void setIsDeleted (Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TGoodsType that = (TGoodsType) o;

        if (id != that.id) return false;
        if (name != null ? ! name.equals(that.name) : that.name != null) return false;
        if (pid != null ? ! pid.equals(that.pid) : that.pid != null) return false;
        if (bUnitId != null ? ! bUnitId.equals(that.bUnitId) : that.bUnitId != null) return false;
        if (lUnitId != null ? ! lUnitId.equals(that.lUnitId) : that.lUnitId != null) return false;
        if (properties != null ? ! properties.equals(that.properties) : that.properties != null) return false;
        if (isDeleted != null ? ! isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (bUnitId != null ? bUnitId.hashCode() : 0);
        result = 31 * result + (lUnitId != null ? lUnitId.hashCode() : 0);
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}
