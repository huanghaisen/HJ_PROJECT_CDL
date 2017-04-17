package com.framework.view;

import com.model.TSysRole;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * User信息模型
 * @author daniel
 *
 */

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UserInfoView<E extends Object> implements Serializable {
	private Long uid;//用户表ID
	private int userFrom;//用户登录来源(1是PC,2是iphoneWeb,3是androidWeb,4是iphoneApp,5是androidApp,6是sso,9是访客)
	private String sence;//用户场景
	private String nickName;//昵称
	private int sex;//性别
	private String email;//邮箱
	private String phone;//手机号码
	private Date birthday;//生日
	private int userType;//用户类型(0为营业厅用户，1为厂商用户  2为管理员)
	private Date loginTime;//登录时间
//	private Long roleLevel;//用户角色级别(9为超级管理员,其他为一般管理员)
	private String lastLoginTime;//最后登录时间
	private Long orgId;//机构ID
	private Long manufId;//厂商ID(0表示不属于厂商,其他属于厂商)
	private String manufname;//厂商名称
	private Long manufLeader;//(0不是负责人,1是负责人)
	private TSysRole role;//用户角色
	private String token;//用户登录验证串
	private List<E> rList;//用户角色组
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public UserInfoView() {
		super();
	}

	public UserInfoView(Long uid, Date loginTime, List<E> rList,String sence) {
		super();
		this.uid = uid;
		this.loginTime = loginTime;
		this.rList = rList;
		this.sence=sence;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public List<E> getrList() {
		return rList;
	}

	public void setrList(List<E> rList) {
		this.rList = rList;
	}
	
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public String getSence() {
		return sence;
	}

	public void setSence(String sence) {
		this.sence = sence;
	}
	
	
	public TSysRole getRole() {
		return role;
	}

	public void setRole(TSysRole role) {
		this.role = role;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getManufId() {
		return manufId;
	}

	public void setManufId(Long manufId) {
		this.manufId = manufId;
	}

	public String getManufname() {
		return manufname;
	}

	public void setManufname(String manufname) {
		this.manufname = manufname;
	}

	public Long getManufLeader() {
		return manufLeader;
	}

	public void setManufLeader(Long manufLeader) {
		this.manufLeader = manufLeader;
	}

	public int getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(int userFrom) {
		this.userFrom = userFrom;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
