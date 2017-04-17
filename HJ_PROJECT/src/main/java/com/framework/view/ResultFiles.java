package com.framework.view;

public class ResultFiles {
	
	private Long size;//文件大小KB
	private String name;//文件名
	private String path;//全路径
	private String url;//虚拟路径
	private String suffix;//文件后缀
	private Long status;//上传状态
	private String msg;//上传信息
	
	public ResultFiles() {
		super();
	}
	public ResultFiles(Long size, String name, String path, String url, String suffix,
			Long status, String msg) {
		super();
		this.size = size;
		this.name = name;
		this.path = path;
		this.url = url;
		this.suffix = suffix;
		this.status = status;
		this.msg = msg;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
