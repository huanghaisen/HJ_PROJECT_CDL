package com.model;

// Generated 2016-6-14 10:25:08 by Hibernate Tools 4.0.0

import javax.persistence.*;

/**
 * 组件表
 */
@Entity
@Table(name = "T_SYS_COMPONENT")
public class TSysComponent implements java.io.Serializable {

	private Long id;
	private String code;
	private String model;
	private String name;
	private String dm;

	public TSysComponent() {
	}

	public TSysComponent(Long id) {
		this.id = id;
	}

	public TSysComponent(Long id, String code, String model, String name,
			String dm) {
		this.id = id;
		this.code = code;
		this.model = model;
		this.name = name;
		this.dm = dm;
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

	@Column(name = "CODE", length = 64)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "MODEL", length = 20)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DM", length = 64)
	public String getDm() {
		return this.dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

}
