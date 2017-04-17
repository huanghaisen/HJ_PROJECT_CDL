package com.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 商家表
 */
@Entity
@Table(name = "t_merchant", schema = "hj", catalog = "")
public class TMerchant {
    private long id;
    private String num;
    private String name;
    private String username;
    private String pwd;
    private String contact;
    private String phoneNumber;
    private Long orgId;
    private Integer checkState;
    private Timestamp applyTime;
    private Timestamp checkTime;
    private Integer level;
    private Integer score;
    private Integer useState;
    private String site;
    private String opinion;

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
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "username")
    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd () {
        return pwd;
    }

    public void setPwd (String pwd) {
        this.pwd = pwd;
    }

    @Basic
    @Column(name = "contact")
    public String getContact () {
        return contact;
    }

    public void setContact (String contact) {
        this.contact = contact;
    }

    @Basic
    @Column(name = "phone_number")
    public String getPhoneNumber () {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    @Column(name = "check_state")
    public Integer getCheckState () {
        return checkState;
    }

    public void setCheckState (Integer checkState) {
        this.checkState = checkState;
    }

    @Basic
    @Column(name = "apply_time")
    public Timestamp getApplyTime () {
        return applyTime;
    }

    public void setApplyTime (Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    @Basic
    @Column(name = "check_time")
    public Timestamp getCheckTime () {
        return checkTime;
    }

    public void setCheckTime (Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel () {
        return level;
    }

    public void setLevel (Integer level) {
        this.level = level;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore () {
        return score;
    }

    public void setScore (Integer score) {
        this.score = score;
    }

    @Basic
    @Column(name = "use_state")
    public Integer getUseState () {
        return useState;
    }

    public void setUseState (Integer useState) {
        this.useState = useState;
    }

    @Basic
    @Column(name = "site")
    public String getSite () {
        return site;
    }

    public void setSite (String site) {
        this.site = site;
    }

    @Basic
    @Column(name = "opinion")
    public String getOpinion () {
        return opinion;
    }

    public void setOpinion (String opinion) {
        this.opinion = opinion;
    }

    @Override
    public boolean equals (Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TMerchant tMerchant = (TMerchant) o;

        if (id != tMerchant.id) return false;
        if (num != null ? ! num.equals(tMerchant.num) : tMerchant.num != null) return false;
        if (name != null ? ! name.equals(tMerchant.name) : tMerchant.name != null) return false;
        if (username != null ? ! username.equals(tMerchant.username) : tMerchant.username != null) return false;
        if (pwd != null ? ! pwd.equals(tMerchant.pwd) : tMerchant.pwd != null) return false;
        if (contact != null ? ! contact.equals(tMerchant.contact) : tMerchant.contact != null) return false;
        if (phoneNumber != null ? ! phoneNumber.equals(tMerchant.phoneNumber) : tMerchant.phoneNumber != null)
            return false;
        if (orgId != null ? ! orgId.equals(tMerchant.orgId) : tMerchant.orgId != null) return false;
        if (checkState != null ? ! checkState.equals(tMerchant.checkState) : tMerchant.checkState != null) return false;
        if (applyTime != null ? ! applyTime.equals(tMerchant.applyTime) : tMerchant.applyTime != null) return false;
        if (checkTime != null ? ! checkTime.equals(tMerchant.checkTime) : tMerchant.checkTime != null) return false;
        if (level != null ? ! level.equals(tMerchant.level) : tMerchant.level != null) return false;
        if (score != null ? ! score.equals(tMerchant.score) : tMerchant.score != null) return false;
        if (useState != null ? ! useState.equals(tMerchant.useState) : tMerchant.useState != null) return false;
        if (site != null ? ! site.equals(tMerchant.site) : tMerchant.site != null) return false;
        if (opinion != null ? ! opinion.equals(tMerchant.opinion) : tMerchant.opinion != null) return false;

        return true;
    }

    @Override
    public int hashCode () {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (contact != null ? contact.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (orgId != null ? orgId.hashCode() : 0);
        result = 31 * result + (checkState != null ? checkState.hashCode() : 0);
        result = 31 * result + (applyTime != null ? applyTime.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        result = 31 * result + (useState != null ? useState.hashCode() : 0);
        result = 31 * result + (site != null ? site.hashCode() : 0);
        result = 31 * result + (opinion != null ? opinion.hashCode() : 0);
        return result;
    }
}
