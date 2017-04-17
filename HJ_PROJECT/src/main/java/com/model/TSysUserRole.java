package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * 用户角色表
 */
@Entity
@Table(name = "T_SYS_USER_ROLE")
public class TSysUserRole implements java.io.Serializable {

	private Long id;
	private Long userId;
	private Long roleId;

	public TSysUserRole() {
	}

	public TSysUserRole(Long id) {
		this.id = id;
	}
	
	public TSysUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public TSysUserRole(Long id, Long userId, Long roleId) {
		this.id = id;
		this.userId = userId;
		this.roleId = roleId;
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

	@Column(name = "USER_ID", precision = 22, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ROLE_ID", precision = 22, scale = 0)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
