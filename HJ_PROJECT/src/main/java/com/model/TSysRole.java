package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;
import java.util.Date;

/**
 * 角色表
 */
@Entity
@Table(name = "T_SYS_ROLE")
public class TSysRole implements java.io.Serializable {

	private Long id;
	private String roleName;
	private String roleDesc;
	private Long createUser;
	private Date createTime;
	private String roleSence;
	private Long roleLevel;

	public TSysRole() {
	}

	public TSysRole(Long id) {
		this.id = id;
	}

	public TSysRole(Long id, String roleName, String roleDesc,
			Long createUser, Date createTime, String roleSence,
			Long roleLevel) {
		this.id = id;
		this.roleName = roleName;
		this.roleDesc = roleDesc;
		this.createUser = createUser;
		this.createTime = createTime;
		this.roleSence = roleSence;
		this.roleLevel = roleLevel;
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

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_DESC", length = 200)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	@Column(name = "ROLE_SENCE", length = 20)
	public String getRoleSence() {
		return this.roleSence;
	}

	public void setRoleSence(String roleSence) {
		this.roleSence = roleSence;
	}

	@Column(name = "ROLE_LEVEL", precision = 1, scale = 0)
	public Long getRoleLevel() {
		return this.roleLevel;
	}

	public void setRoleLevel(Long roleLevel) {
		this.roleLevel = roleLevel;
	}

}
