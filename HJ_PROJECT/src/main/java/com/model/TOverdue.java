package com.model;

import javax.persistence.*;

/**
 * 逾期未结算设置表
 */
@Entity
@Table(name = "t_overdue", schema = "hj", catalog = "")
public class TOverdue {
    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId () {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOverdue tOverdue = (TOverdue) o;

        if (id != tOverdue.id) return false;

        return true;
    }

    @Override
    public int hashCode () {
        return (int) (id ^ (id >>> 32));
    }
}
