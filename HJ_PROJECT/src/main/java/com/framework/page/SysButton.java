package com.framework.page;

public class SysButton {
    private String name;
    private String value;
    private String type;
    private String className;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "SysButtonData [name=" + name + ", value=" + value + ", type="
				+ type + ", className=" + className + "]";
	}


}
