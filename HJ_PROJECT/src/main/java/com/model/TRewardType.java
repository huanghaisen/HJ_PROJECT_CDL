package com.model;

import javax.persistence.*;

/**
 * 提成方式表
 */
@Entity
@Table(name = "t_reward_type", schema = "hj", catalog = "")
public class TRewardType {
    private long id;
    private String name;
    private Integer type;
    private Integer value;

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
    @Column(name = "value")
    public Integer getValue () {
        return value;
    }

    public void setValue (Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRewardType that = (TRewardType) o;

        if (id != that.id) return false;
        if (name != null ? ! name.equals(that.name) : that.name != null) return false;
        if (type != null ? ! type.equals(that.type) : that.type != null) return false;
        if (value != null ? ! value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
