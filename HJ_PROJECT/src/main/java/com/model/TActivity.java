package com.model;

import javax.persistence.*;

/**
 * 活动表
 */
@Entity
@Table(name = "t_activity", schema = "hj", catalog = "")
public class TActivity {
    private long id;
    private String name;
    private String content;

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
    @Column(name = "content")
    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TActivity tActivity = (TActivity) o;

        if (id != tActivity.id) return false;
        if (name != null ? ! name.equals(tActivity.name) : tActivity.name != null) return false;
        if (content != null ? ! content.equals(tActivity.content) : tActivity.content != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
