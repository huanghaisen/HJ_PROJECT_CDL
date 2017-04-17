package com.framework.base.domain;

public class EntityImpl implements Entity {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = -9134312608273914761L;

	/**
     * id:数据的主键
     */
	private Long id;

	
    /**
     * 数据的名称
     */
	//private String entityName;

	/*
	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
