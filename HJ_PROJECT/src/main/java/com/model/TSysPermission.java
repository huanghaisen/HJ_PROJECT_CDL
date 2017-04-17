package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * 权限表
 */
@Entity
@Table(name = "T_SYS_PERMISSION")
public class TSysPermission implements java.io.Serializable {

	private Long id;
	private Long rmId;
	private Long funcId;

	public TSysPermission() {
	}

	public TSysPermission(Long id) {
		this.id = id;
	}
	
	public TSysPermission(Long rmId, Long funcId) {
		this.rmId = rmId;
		this.funcId = funcId;
	}

	public TSysPermission(Long id, Long rmId, Long funcId) {
		this.id = id;
		this.rmId = rmId;
		this.funcId = funcId;
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

	@Column(name = "RM_ID", precision = 22, scale = 0)
	public Long getRmId() {
		return this.rmId;
	}

	public void setRmId(Long rmId) {
		this.rmId = rmId;
	}

	@Column(name = "FUNC_ID", precision = 22, scale = 0)
	public Long getFuncId() {
		return this.funcId;
	}

	public void setFuncId(Long funcId) {
		this.funcId = funcId;
	}

}
