package com.model;

import javax.persistence.*;

/**
 * 短信平台表
 */
@Entity
@Table(name = "t_message", schema = "hj", catalog = "")
public class TMessage {
    private long id;
    private String name;
    private Integer state;

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
    @Column(name = "state")
    public Integer getState () {
        return state;
    }

    public void setState (Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMessage tMessage = (TMessage) o;

        if (id != tMessage.id) return false;
        if (name != null ? ! name.equals(tMessage.name) : tMessage.name != null) return false;
        if (state != null ? ! state.equals(tMessage.state) : tMessage.state != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}
