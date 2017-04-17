package com.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户操作表
 */
@Entity
@Table(name = "T_SYS_USER_ACTION_LOG")
public class TSysUserActionLog implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private String username;
	private Integer type;
	private String action;
	private String ip;
	private Date ctime;

	// Constructors

	/** default constructor */
	public TSysUserActionLog() {
	}

	/** full constructor */
	public TSysUserActionLog(Long userid, String username,
			Integer type, String action, String ip, Date ctime) {
		this.userid = userid;
		this.username = username;
		this.type = type;
		this.action = action;
		this.ip = ip;
		this.ctime = ctime;
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

	@Column(name = "USERID", precision = 15)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "USERNAME", length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "TYPE", precision = 8)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "ACTION", length = 50)
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "IP", length = 200)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "CTIME", length = 7)
	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}