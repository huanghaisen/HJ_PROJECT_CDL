package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * 角色菜单表
 */
@Entity
@Table(name = "T_SYS_ROLE_MENU")
public class TSysRoleMenu implements java.io.Serializable {

	private Long id;
	private Long roleId;
	private Long menuId;

	public TSysRoleMenu() {
	}

	public TSysRoleMenu(Long id) {
		this.id = id;
	}

	public TSysRoleMenu( Long roleId, Long menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
	}
	public TSysRoleMenu(Long id, Long roleId, Long menuId) {
		this.id = id;
		this.roleId = roleId;
		this.menuId = menuId;
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

	@Column(name = "ROLE_ID", precision = 22, scale = 0)
	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "MENU_ID", precision = 22, scale = 0)
	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
