package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * 厂商类型表
 */
@Entity
@Table(name = "T_SYS_MANUF_TYPE")
public class TSysManufType implements java.io.Serializable {

	private Long id;
	private String manufTypeName;
	private String manufTypeCode;
	private Boolean isDeleted;

	public TSysManufType() {
	}

	public TSysManufType(Long id) {
		this.id = id;
	}

	public TSysManufType(Long id, String manufTypeName,
			String manufTypeCode, Boolean isDeleted) {
		this.id = id;
		this.manufTypeName = manufTypeName;
		this.manufTypeCode = manufTypeCode;
		this.isDeleted = isDeleted;
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

	@Column(name = "MANUF_TYPE_NAME", length = 100)
	public String getManufTypeName() {
		return this.manufTypeName;
	}

	public void setManufTypeName(String manufTypeName) {
		this.manufTypeName = manufTypeName;
	}

	@Column(name = "MANUF_TYPE_CODE", length = 30)
	public String getManufTypeCode() {
		return this.manufTypeCode;
	}

	public void setManufTypeCode(String manufTypeCode) {
		this.manufTypeCode = manufTypeCode;
	}

	@Column(name = "IS_DELETED", precision = 1, scale = 0)
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
