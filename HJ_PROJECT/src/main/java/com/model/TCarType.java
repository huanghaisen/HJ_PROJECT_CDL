package com.model;

import javax.persistence.*;

/**
 * 车型表
 */
@Entity
@Table(name = "t_car_type", schema = "hj", catalog = "")
public class TCarType {
    private long id;
    private String name;
    private Integer type;
    private Integer deep;
    private Long pid;
    private Integer isLeaf;
    private String code;

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
    @Column(name = "type")
    public Integer getType () {
        return type;
    }

    public void setType (Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "deep")
    public Integer getDeep () {
        return deep;
    }

    public void setDeep (Integer deep) {
        this.deep = deep;
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
    @Column(name = "is_leaf")
    public Integer getIsLeaf () {
        return isLeaf;
    }

    public void setIsLeaf (Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Basic
    @Column(name = "code")
    public String getCode () {
        return code;
    }

    public void setCode (String code) {
        this.code = code;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCarType tCarType = (TCarType) o;

        if (id != tCarType.id) return false;
        if (name != null ? ! name.equals(tCarType.name) : tCarType.name != null) return false;
        if (type != null ? ! type.equals(tCarType.type) : tCarType.type != null) return false;
        if (deep != null ? ! deep.equals(tCarType.deep) : tCarType.deep != null) return false;
        if (pid != null ? ! pid.equals(tCarType.pid) : tCarType.pid != null) return false;
        if (isLeaf != null ? ! isLeaf.equals(tCarType.isLeaf) : tCarType.isLeaf != null) return false;
        if (code != null ? ! code.equals(tCarType.code) : tCarType.code != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (deep != null ? deep.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (isLeaf != null ? isLeaf.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
