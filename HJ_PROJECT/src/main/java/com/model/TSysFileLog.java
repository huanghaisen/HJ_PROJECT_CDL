package com.model;

// Generated 2016-6-22 17:43:34 by Hibernate Tools 4.0.0

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * 系统文件表
 */
@Entity
@Table(name = "T_SYS_FILE_LOG")
public class TSysFileLog implements java.io.Serializable {

	private Long id;
	private Long fileTypeId;
	private String originalFileName;
	private String finalFileCode;
	private String fileName;
	private String directory;
	private String fileUrl;
	private String fileSuffix;
	private Long fileSize;
	private Boolean isDeleted;
	private Long modifier;
	private Date lastModifiedTime;
	
	private String fileTypeName;//文件类型名
	private String loginName;//获取用户名

	public TSysFileLog() {
	}

	public TSysFileLog(Long id) {
		this.id = id;
	}

	public TSysFileLog(Long id, Long fileTypeId, String originalFileName,
			String finalFileCode, String fileName,String directory,String fileUrl, String fileSuffix,
			Long fileSize, Boolean isDeleted, Long modifier,
			Date lastModifiedTime) {
		this.id = id;
		this.fileTypeId = fileTypeId;
		this.originalFileName = originalFileName;
		this.finalFileCode = finalFileCode;
		this.fileName = fileName;
		this.directory = directory;
		this.fileUrl = fileUrl;
		this.fileSuffix = fileSuffix;
		this.fileSize = fileSize;
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

	@Column(name = "FILE_TYPE_ID", precision = 15, scale = 0)
	public Long getFileTypeId() {
		return this.fileTypeId;
	}

	public void setFileTypeId(Long fileTypeId) {
		this.fileTypeId = fileTypeId;
	}

	@Column(name = "ORIGINAL_FILE_NAME", length = 100)
	public String getOriginalFileName() {
		return this.originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	@Column(name = "FINAL_FILE_CODE", length = 64)
	public String getFinalFileCode() {
		return this.finalFileCode;
	}

	public void setFinalFileCode(String finalFileCode) {
		this.finalFileCode = finalFileCode;
	}

	@Column(name = "FILE_NAME", length = 100)
	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	@Column(name = "DIRECTORY", length = 4000)
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	@Column(name = "FILE_URL", length = 4000)
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


	@Column(name = "FILE_SUFFIX", length = 10)
	public String getFileSuffix() {
		return this.fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	@Column(name = "FILE_SIZE", precision = 15, scale = 0)
	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
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
	
	@Formula("(select e.FILE_NAME from T_SYS_FILE_TYPE e where e.ID=FILE_TYPE_ID)")
	public String getFileTypeName() {
		return fileTypeName;
	}

	public void setFileTypeName(String fileTypeName) {
		this.fileTypeName = fileTypeName;
	}
	
	@Formula("(select c.LOGIN_NAME from T_SYS_USER c where c.ID=MODIFIER)")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

}
