package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * 菜单按钮表
 */
@Entity
@Table(name = "T_SYS_FUNC")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class TSysFunc implements java.io.Serializable {

	private Long id;
	private String funcName;
	private String funcSign;
	private String funcCode;
	private String funcIcon;
	private String funcBg;

	public TSysFunc() {
	}

	public TSysFunc(Long id) {
		this.id = id;
	}

	public TSysFunc(Long id, String funcName, String funcSign,
			String funcCode, String funcIcon,String funcBg) {
		this.id = id;
		this.funcName = funcName;
		this.funcSign = funcSign;
		this.funcCode = funcCode;
		this.funcIcon = funcIcon;
		this.funcBg   = funcBg;
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

	@Column(name = "FUNC_NAME", length = 20)
	public String getFuncName() {
		return this.funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	@Column(name = "FUNC_SIGN", length = 20)
	public String getFuncSign() {
		return this.funcSign;
	}

	public void setFuncSign(String funcSign) {
		this.funcSign = funcSign;
	}

	@Column(name = "FUNC_CODE", length = 20)
	public String getFuncCode() {
		return this.funcCode;
	}

	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}

	@Column(name = "FUNC_ICON", length = 200)
	public String getFuncIcon() {
		return this.funcIcon;
	}

	public void setFuncIcon(String funcIcon) {
		this.funcIcon = funcIcon;
	}
	
	@Column(name = "FUNC_BG", length = 50)
	public String getFuncBg() {
		return funcBg;
	}

	public void setFuncBg(String funcBg) {
		this.funcBg = funcBg;
	}
	
	

}
