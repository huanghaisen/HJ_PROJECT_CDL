package com.framework.view;
/**
 * 返回类容包装器
 * @author daniel
 *
 */
public class ResultDatas {
	
	
	private long start;//第一条数据的起始位置，比如0代表第一条数据
	private long length;//返回每页的条目数
	private long recordsTotal;//记录总数
	private long recordsFiltered;//过滤后的记录总数
	private Object data;//返回对象数据
	private int success;//是否成功(0成功,1失败,9没有权限)
	private String msg;//失败原因
	
	public long getStart() {
		return start;
	}
	public void setStart(long start) {
		this.start = start;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	

	
	public ResultDatas() {
		super();
	}
	public ResultDatas(int success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	public int getSuccess() {
		return success;
	}
	public void setSuccess(int success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	
	
}
