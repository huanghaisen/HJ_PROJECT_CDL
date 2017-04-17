package com.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户登录日记表
 */
@Entity
@Table(name = "T_SYS_USER_LOG")
public class TSysUserLog implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userId;
	private Integer userfrom;
	private String ip;
	private Integer type;
	private Date ctime;

	// Constructors

	/** default constructor */
	public TSysUserLog() {
	}

	/** full constructor */
	public TSysUserLog(Long userId, Integer userfrom, String ip,
			Integer type, Date ctime) {
		this.userId = userId;
		this.userfrom = userfrom;
		this.ip = ip;
		this.type = type;
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

	@Column(name = "USERID", precision = 15, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USERFROM", precision = 8)
	public Integer getUserfrom() {
		return this.userfrom;
	}

	public void setUserfrom(Integer userfrom) {
		this.userfrom = userfrom;
	}

	@Column(name = "IP", length = 200)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "TYPE", precision = 8)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CTIME", length = 7)
	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}