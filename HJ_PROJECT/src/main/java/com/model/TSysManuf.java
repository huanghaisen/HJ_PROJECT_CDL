package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 厂商表
 */
@Entity
@Table(name = "T_SYS_MANUF")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TSysManuf implements java.io.Serializable {

	private Long id;
	private String manufCode;
	private Long manufType;
	private String manufName;
	private String manufBriefName;
	private String contactWay1;
	private String contactWay2;
	private String manufAddress;
	private String manufAptitude;
	private Long creator;
	private Date createTime;
	private Boolean isDeleted;
	private Long modifier;
	private Long serialNumber;
	private Date lastModifiedTime;
	private Long manufLeader;
	
	private String manufTypeStr;//厂商类型名
	

	public TSysManuf() {
	}

	public TSysManuf(Long id, String manufCode, Long manufType,
			String manufName, Boolean isDeleted) {
		this.id = id;
		this.manufCode = manufCode;
		this.manufType = manufType;
		this.manufName = manufName;
		this.isDeleted = isDeleted;
	}

	public TSysManuf(Long id, String manufCode, Long manufType,
			String manufName, String manufBriefName, String contactWay1,
			String contactWay2, String manufAddress, Long creator,
			Date createTime, Boolean isDeleted, Long modifier,
			Long serialNumber, Date lastModifiedTime, String manufAptitude) {
		this.id = id;
		this.manufCode = manufCode;
		this.manufType = manufType;
		this.manufName = manufName;
		this.manufBriefName = manufBriefName;
		this.contactWay1 = contactWay1;
		this.contactWay2 = contactWay2;
		this.manufAddress = manufAddress;
		this.creator = creator;
		this.createTime = createTime;
		this.isDeleted = isDeleted;
		this.modifier = modifier;
		this.serialNumber = serialNumber;
		this.lastModifiedTime = lastModifiedTime;
		this.manufAptitude = manufAptitude;
	}
	
	public void copy(TSysManuf model) {
		this.manufCode=model.getManufCode();
		this.manufType = model.getManufType();
		this.manufName=model.getManufName();
		this.manufBriefName=model.getManufBriefName();
		this.contactWay1=model.getContactWay1();
		this.contactWay2=model.getContactWay2();
		this.manufAddress=model.getManufAddress();
		this.manufAptitude=model.getManufAptitude();
		this.modifier=model.getModifier();
		this.lastModifiedTime=model.getLastModifiedTime();
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

	@Column(name = "MANUF_CODE", nullable = false, length = 30)
	public String getManufCode() {
		return this.manufCode;
	}

	public void setManufCode(String manufCode) {
		this.manufCode = manufCode;
	}

	@Column(name = "MANUF_TYPE", nullable = false, precision = 22, scale = 0)
	public Long getManufType() {
		return this.manufType;
	}

	public void setManufType(Long manufType) {
		this.manufType = manufType;
	}

	@Column(name = "MANUF_NAME", nullable = false, length = 100)
	public String getManufName() {
		return this.manufName;
	}

	public void setManufName(String manufName) {
		this.manufName = manufName;
	}

	@Column(name = "MANUF_BRIEF_NAME", length = 100)
	public String getManufBriefName() {
		return this.manufBriefName;
	}

	public void setManufBriefName(String manufBriefName) {
		this.manufBriefName = manufBriefName;
	}

	@Column(name = "CONTACT_WAY1", length = 50)
	public String getContactWay1() {
		return this.contactWay1;
	}

	public void setContactWay1(String contactWay1) {
		this.contactWay1 = contactWay1;
	}

	@Column(name = "CONTACT_WAY2", length = 50)
	public String getContactWay2() {
		return this.contactWay2;
	}

	public void setContactWay2(String contactWay2) {
		this.contactWay2 = contactWay2;
	}

	@Column(name = "MANUF_ADDRESS", length = 300)
	public String getManufAddress() {
		return this.manufAddress;
	}

	public void setManufAddress(String manufAddress) {
		this.manufAddress = manufAddress;
	}

	@Column(name = "CREATOR", scale = 0)
	public Long getCreator() {
		return this.creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "IS_DELETED", nullable = false, precision = 1, scale = 0)
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Column(name = "MODIFIER", scale = 0)
	public Long getModifier() {
		return this.modifier;
	}

	public void setModifier(Long modifier) {
		this.modifier = modifier;
	}

	/*@Column(name = "SERIAL_NUMBER", precision = 22, scale = 0)
	public Long getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}*/

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_MODIFIED_TIME", length = 7)
	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Column(name = "MANUF_APTITUDE")
	public String getManufAptitude() {
		return this.manufAptitude;
	}

	public void setManufAptitude(String manufAptitude) {
		this.manufAptitude = manufAptitude;
	}

	@Formula("(select c.MANUF_TYPE_NAME from T_SYS_MANUF_TYPE c where c.ID=MANUF_TYPE)")
	@Generated(GenerationTime.ALWAYS)
	public String getManufTypeStr() {
		return manufTypeStr;
	}

	public void setManufTypeStr(String manufTypeStr) {
		this.manufTypeStr = manufTypeStr;
	}

	@Column(name = "MANUF_LEADER", scale = 0)
	public Long getManufLeader() {
		return manufLeader;
	}

	public void setManufLeader(Long manufLeader) {
		this.manufLeader = manufLeader;
	}
	
}
