package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * 菜单名称表
 */
@Entity
@Table(name = "T_SYS_MENU")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TSysMenu implements java.io.Serializable {

	private Long id;
	private String menuName;
	private Long menuCode;
	private Integer menuIsopen;
	private String menuType;
	private String menuTarget;
	private String menuUrl;
	private String menuIcon;
	private Integer menuOrder;
	private Long parentId;
	private Integer isLeaf;
	private Integer isVisible;
	private String status;
	private String mclass;
	private String menuSence;
	private String menuPermission;
	private Integer depath;

	public TSysMenu() {
	}
	

	public TSysMenu(Long id) {
		this.id = id;
	}

	public TSysMenu(Long id, String menuName, Long menuCode,
			String menuType, String menuTarget, String menuUrl,
			String menuIcon, Integer menuOrder, Long parentId,
			Integer isLeaf, Integer isVisible, String status, String mclass,
			Integer menuIsopen, String menuSence, String menuPermission) {
		this.id = id;
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.menuType = menuType;
		this.menuTarget = menuTarget;
		this.menuUrl = menuUrl;
		this.menuIcon = menuIcon;
		this.menuOrder = menuOrder;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		this.isVisible = isVisible;
		this.status = status;
		this.mclass = mclass;
		this.menuIsopen = menuIsopen;
		this.menuSence = menuSence;
		this.menuPermission = menuPermission;
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

	@Column(name = "MENU_NAME", length = 50)
	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Column(name = "MENU_CODE", precision = 20, scale = 0)
	public Long getMenuCode() {
		return this.menuCode;
	}

	public void setMenuCode(Long menuCode) {
		this.menuCode = menuCode;
	}

	@Column(name = "MENU_TYPE", length = 200)
	public String getMenuType() {
		return this.menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	@Column(name = "MENU_TARGET", length = 20)
	public String getMenuTarget() {
		return this.menuTarget;
	}

	public void setMenuTarget(String menuTarget) {
		this.menuTarget = menuTarget;
	}

	@Column(name = "MENU_URL", length = 200)
	public String getMenuUrl() {
		return this.menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	@Column(name = "MENU_ICON", length = 200)
	public String getMenuIcon() {
		return this.menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	@Column(name = "MENU_ORDER", precision = 5, scale = 0)
	public Integer getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Column(name = "PARENT_ID", precision = 15, scale = 0)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "IS_LEAF", precision = 1, scale = 0)
	public Integer getIsLeaf() {
		return this.isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	@Column(name = "IS_VISIBLE", precision = 1, scale = 0)
	public Integer getIsVisible() {
		return this.isVisible;
	}

	public void setIsVisible(Integer isVisible) {
		this.isVisible = isVisible;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MCLASS", length = 20)
	public String getMclass() {
		return this.mclass;
	}

	public void setMclass(String mclass) {
		this.mclass = mclass;
	}

	@Column(name = "MENU_ISOPEN", precision = 1, scale = 0)
	public Integer getMenuIsopen() {
		return this.menuIsopen;
	}

	public void setMenuIsopen(Integer menuIsopen) {
		this.menuIsopen = menuIsopen;
	}

	@Column(name = "MENU_SENCE", length = 20)
	public String getMenuSence() {
		return this.menuSence;
	}

	public void setMenuSence(String menuSence) {
		this.menuSence = menuSence;
	}

	@Column(name = "MENU_PERMISSION", length = 20)
	public String getMenuPermission() {
		return this.menuPermission;
	}

	public void setMenuPermission(String menuPermission) {
		this.menuPermission = menuPermission;
	}

	@Column(name = "DEPATH", precision = 8, scale = 0)
	public Integer getDepath() {
		return this.depath;
	}

	public void setDepath(Integer depath) {
		this.depath = depath;
	}

}
