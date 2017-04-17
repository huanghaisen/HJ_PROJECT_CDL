package com.framework.view;


/**
 * 组件注册缓存
 * @author daniel
 *
 */

public class ComponentView implements java.io.Serializable {
	
	
	private String id;//组件编码
	private String model;//组件模型
	private String name;//组件名称
	
	public ComponentView() {
		super();
	}
	public ComponentView(String id, String model, String name) {
		super();
		this.id = id;
		this.model = model;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
