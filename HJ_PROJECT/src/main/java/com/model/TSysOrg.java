package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import com.enums.OrgType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;

/**
 * 区域表
 */
@Entity
@Table(name = "T_SYS_ORG")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TSysOrg implements java.io.Serializable {

	private Long id;
	private String orgCode;
	private String orgName;
	private String orgBriefName;
	private String orgFullName;
	private String address;
	private String telphone;
	private String contacter;
	private String email;
	private Integer depath;
	private Integer ident;
	private Integer endIdent;
	private Integer orderNo;
	private Integer orgType;
	private Long creator;
	private Date createTime;
	private Integer status;
	private Boolean isDeleted;
	private Long modifier;
	private Date lastModifiedTime;
	private Long parentOrgId;
	private String children;
	private Long leaderId;
	private Float mapY;
	private Float mapX;
	
	@Transient
	private String orgTypeStr;//机构类型
	private String leaderName;//负责人
	private String modifierNmae;//修改人
    
	
	public TSysOrg() {
	}

	public TSysOrg(Long id,String orgName){
		this.id=id;
		this.orgName=orgName;
	}
	
	public TSysOrg(Long id, String orgCode, String orgName,
			String orgFullName, Integer depath, Integer ident, Integer orderNo,
			Integer orgType, Integer status, Boolean isDeleted) {
		this.id = id;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgFullName = orgFullName;
		this.depath = depath;
		this.ident = ident;
		this.orderNo = orderNo;
		this.orgType = orgType;
		this.status = status;
		this.isDeleted = isDeleted;
		
	}

	public TSysOrg(Long id, String orgCode, String orgName,
			String orgBriefName, String orgFullName, String address,
			String telphone, String contacter, String email, Integer depath,
			Integer ident, Integer endIdent, Integer orderNo, Integer orgType,
			Long creator, Date createTime, Integer status,
			Boolean isDeleted, Long modifier, Date lastModifiedTime,
			Long parentOrgId, String children, Long leaderId,
			Float mapY, Float mapX) {
		this.id = id;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.orgBriefName = orgBriefName;
		this.orgFullName = orgFullName;
		this.address = address;
		this.telphone = telphone;
		this.contacter = contacter;
		this.email = email;
		this.depath = depath;
		this.ident = ident;
		this.endIdent = endIdent;
		this.orderNo = orderNo;
		this.orgType = orgType;
		this.creator = creator;
		this.createTime = createTime;
		this.status = status;
		this.isDeleted = isDeleted;
		this.modifier = modifier;
		this.lastModifiedTime = lastModifiedTime;
		this.parentOrgId = parentOrgId;
		this.children = children;
		this.leaderId = leaderId;
		this.mapY = mapY;
		this.mapX = mapX;
		
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

	@Column(name = "ORG_CODE", nullable = false, length = 20)
	public String getOrgCode() {
		return this.orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_NAME", nullable = false, length = 50)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_BRIEF_NAME")
	public String getOrgBriefName() {
		return this.orgBriefName;
	}

	public void setOrgBriefName(String orgBriefName) {
		this.orgBriefName = orgBriefName;
	}

	@Column(name = "ORG_FULL_NAME", nullable = false)
	public String getOrgFullName() {
		return this.orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "TELPHONE", length = 50)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "CONTACTER", length = 30)
	public String getContacter() {
		return this.contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "DEPATH", nullable = false, precision = 1, scale = 0)
	public Integer getDepath() {
		return this.depath;
	}

	public void setDepath(Integer depath) {
		this.depath = depath;
	}

	@Column(name = "IDENT", nullable = false, precision = 9, scale = 0)
	public Integer getIdent() {
		return this.ident;
	}

	public void setIdent(Integer ident) {
		this.ident = ident;
	}

	@Column(name = "END_IDENT", precision = 9, scale = 0)
	public Integer getEndIdent() {
		return this.endIdent;
	}

	public void setEndIdent(Integer endIdent) {
		this.endIdent = endIdent;
	}

	@Column(name = "ORDER_NO", nullable = false, precision = 9, scale = 0)
	public Integer getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "ORG_TYPE", nullable = false, precision = 1, scale = 0)
	public Integer getOrgType() {
		return this.orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	@Column(name = "CREATOR", scale = 0)
	public Long getCreator() {
		return this.creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "STATUS", nullable = false, precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "IS_DELETED", nullable = false, precision = 1, scale = 0)
	public Boolean isIsDeleted() {
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_TIME", length = 7)
	public Date getLastModifiedTime() {
		return this.lastModifiedTime;
	}

	public void setLastModifiedTime(Date lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	@Column(name = "PARENT_ORG_ID", precision = 22, scale = 0)
	public Long getParentOrgId() {
		return this.parentOrgId;
	}

	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	@Column(name = "CHILDREN")
	public String getChildren() {
		return this.children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	@Column(name = "LEADER_ID", scale = 0)
	public Long getLeaderId() {
		return this.leaderId;
	}

	public void setLeaderId(Long leaderId) {
		this.leaderId = leaderId;
	}

	@Column(name = "MAP_Y", precision = 11, scale = 6)
	public Float getMapY() {
		Float y = (this.mapY==null || "".equals(this.mapY)?0f:this.mapY);
		return y;
	}

	public void setMapY(Float mapY) {
		this.mapY = mapY;
	}

	@Column(name = "MAP_X", precision = 11, scale = 6)
	public Float getMapX() {
		Float x = (this.mapX==null || "".equals(this.mapX)?0f:this.mapX);
		return x;
	}

	public void setMapX(Float mapX) {
		this.mapX = mapX;
	}

	@Transient
	public String getOrgTypeStr() {
		orgTypeStr=OrgType.valueOf(this.getOrgType()).toString();
		return orgTypeStr;
	}
	
	@Formula("(select c.login_name from t_sys_user c where c.ID=LEADER_ID)")
	public String getLeaderName() {
		return leaderName;
	}

	@Formula("(select c.login_name from t_sys_user c where c.ID=MODIFIER)")
	public String getModifierNmae() {
		return modifierNmae;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public void setModifierNmae(String modifierNmae) {
		this.modifierNmae = modifierNmae;
	}

}
