package com.model;

import javax.persistence.*;

/**
 * 审核资料表
 */
@Entity
@Table(name = "t_check", schema = "hj", catalog = "")
public class TCheck {
    private long id;
    private String 商家Id;
    private String column3;
    private String column4;

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
    @Column(name = "商家id")
    public String get商家Id () {
        return 商家Id;
    }

    public void set商家Id (String 商家Id) {
        this.商家Id = 商家Id;
    }

    @Basic
    @Column(name = "Column_3")
    public String getColumn3 () {
        return column3;
    }

    public void setColumn3 (String column3) {
        this.column3 = column3;
    }

    @Basic
    @Column(name = "Column_4")
    public String getColumn4 () {
        return column4;
    }

    public void setColumn4 (String column4) {
        this.column4 = column4;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TCheck tCheck = (TCheck) o;

        if (id != tCheck.id) return false;
        if (商家Id != null ? ! 商家Id.equals(tCheck.商家Id) : tCheck.商家Id != null) return false;
        if (column3 != null ? ! column3.equals(tCheck.column3) : tCheck.column3 != null) return false;
        if (column4 != null ? ! column4.equals(tCheck.column4) : tCheck.column4 != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (商家Id != null ? 商家Id.hashCode() : 0);
        result = 31 * result + (column3 != null ? column3.hashCode() : 0);
        result = 31 * result + (column4 != null ? column4.hashCode() : 0);
        return result;
    }
}
