package com.model;

import javax.persistence.*;

/**
 * 货架表
 */
@Entity
@Table(name = "t_shelves", schema = "hj", catalog = "")
public class TShelves {
    private long id;
    private Long 机构Id;

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
    @Column(name = "机构id")
    public Long get机构Id () {
        return 机构Id;
    }

    public void set机构Id (Long 机构Id) {
        this.机构Id = 机构Id;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TShelves tShelves = (TShelves) o;

        if (id != tShelves.id) return false;
        if (机构Id != null ? ! 机构Id.equals(tShelves.机构Id) : tShelves.机构Id != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (机构Id != null ? 机构Id.hashCode() : 0);
        return result;
    }
}
