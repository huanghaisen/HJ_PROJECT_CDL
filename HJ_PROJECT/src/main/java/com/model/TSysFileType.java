package com.model;

// Generated 2016-6-22 17:43:34 by Hibernate Tools 4.0.0

import javax.persistence.*;
import java.util.Date;

/**
 * 系统文件类型表
 */
@Entity
@Table(name = "T_SYS_FILE_TYPE")
public class TSysFileType implements java.io.Serializable {

	private Long id;
	private String fileName;
	private String fileRequire;
	private Boolean isDeleted;
	private Long modifier;
	private Date lastModifiedTime;

	public TSysFileType() {
	}

	public TSysFileType(Long id) {
		this.id = id;
	}

	public TSysFileType(Long id, String fileName, String fileRequire,
			Boolean isDeleted, Long modifier, Date lastModifiedTime) {
		this.id = id;
		this.fileName = fileName;
		this.fileRequire = fileRequire;
		this.isDeleted = isDeleted;
		this.modifier = modifier;
		this.lastModifiedTime = lastModifiedTime;
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

	@Column(name = "FILE_NAME", length = 100)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_REQUIRE", length = 100)
	public String getFileRequire() {
		return this.fileRequire;
	}

	public void setFileRequire(String fileRequire) {
		this.fileRequire = fileRequire;
	}

	@Column(name = "IS_DELETED", precision = 1, scale = 0)
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "MODIFIER", precision = 15, scale = 0)
	public Long getModifier() {
		return this.modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_MODIFIED_TIME", length = 7)
	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

}
