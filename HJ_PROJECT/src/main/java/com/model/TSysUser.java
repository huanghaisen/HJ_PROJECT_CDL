package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import com.enums.UserType;
import com.framework.base.domain.EntityImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户表
 */
@Entity
@Table(name = "T_SYS_USER")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TSysUser extends EntityImpl implements java.io.Serializable {

	private Long id;
	private String loginName;
	private String nickName;
	private String password;
	private int sex;
	private String email;
	private String phone;
	private Date birthDay;
	private Date lastLoginTime;
	private int state;
	private Long createUser;
	private Date createTime;
	private Long tokenLimit;
	private String token;
	private String sence;
	private Long orgId;//默认为0.防空指针
	private Long manufId;//默认为0，为0表示不是厂商用户
	private int type;//用户类型 默认为0 0为营业厅用户，1为厂商用户  2为管理员
	@Transient
	private String userType;//用户类型说明
	
	private String orgName;//用户机构名称

	public TSysUser() {
	}

	public TSysUser(Long id) {
		this.id = id;
	}

	public TSysUser(Long id, String loginName, String nickName,
			String password, int sex, String email, String phone,
			Date birthDay, Date lastLoginTime, int state,
			Long createUser, Date createTime, Long tokenLimit,
			String token, String sence,Long orgId,Long manufId, int type) {
		this.id = id;
		this.loginName = loginName;
		this.nickName = nickName;
		this.password = password;
		this.sex = sex;
		this.email = email;
		this.phone = phone;
		this.birthDay = birthDay;
		this.lastLoginTime = lastLoginTime;
		this.state = state;
		this.createUser = createUser;
		this.createTime = createTime;
		this.tokenLimit = tokenLimit;
		this.token = token;
		this.sence = sence;
		this.orgId = orgId;
		this.manufId=manufId;
		this.type=type;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "LOGIN_NAME", length = 20)
	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "NICK_NAME", length = 20)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "PASSWORD", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "SEX", precision = 1, scale = 0)
	public int getSex() {
		return this.sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Column(name = "EMAIL", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "PHONE", length = 20)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DAY", length = 7)
	public Date getBirthDay() {
		return this.birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_LOGIN_TIME", length = 7)
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "STATE", precision = 22, scale = 0)
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "CREATE_USER", precision = 22, scale = 0)
	public Long getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "TOKEN_LIMIT", precision = 13, scale = 0)
	public Long getTokenLimit() {
		return this.tokenLimit;
	}

	public void setTokenLimit(Long tokenLimit) {
		this.tokenLimit = tokenLimit;
	}

	@Column(name = "TOKEN", length = 64)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "SENCE", length = 20)
	public String getSence() {
		return this.sence;
	}

	public void setSence(String sence) {
		this.sence = sence;
	}
	
	@Column(name = "ORG_ID",  precision = 15, scale = 0)
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "MANUF_ID",  precision = 15, scale = 0)
	public Long getManufId() {
		return this.manufId;
	}

	public void setManufId(Long manufId) {
		this.manufId = manufId;
	}
	@Column(name = "TYPE", precision = 22, scale = 0)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Transient
	public String getUserType() {
		return UserType.valueOf(this.getType()).toString();
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

	@Formula("(select c.ORG_NAME from T_SYS_ORG c where c.ID=ORG_ID)")
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "TSysUser [id=" + id + ", loginName=" + loginName
				+ ", nickName=" + nickName + ", password=" + password
				+ ", sex=" + sex + ", email=" + email + ", phone=" + phone
				+ ", birthDay=" + birthDay + ", lastLoginTime=" + lastLoginTime
				+ ", state=" + state + ", createUser=" + createUser
				+ ", createTime=" + createTime + ", tokenLimit=" + tokenLimit
				+ ", token=" + token + ", sence=" + sence + ", orgId=" + orgId
				+ ", manufId=" + manufId + ", type=" + type + "]";
	}
	
	
}
