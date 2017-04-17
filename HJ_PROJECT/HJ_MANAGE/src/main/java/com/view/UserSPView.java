package com.view;

import java.io.Serializable;

public class UserSPView implements Serializable {
	private String code;
	private String sid;
	private String timestamp;
	private String serviceid;
	private Integer rsp;
	private String mainacctid;
	private String appacctid;
	
	public UserSPView() {
		super();
	}

	public UserSPView(String code, String sid, String timestamp,
			String serviceid, Integer rsp, String mainacctid, String appacctid) {
		super();
		this.code = code;
		this.sid = sid;
		this.timestamp = timestamp;
		this.serviceid = serviceid;
		this.rsp = rsp;
		this.mainacctid = mainacctid;
		this.appacctid = appacctid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getServiceid() {
		return serviceid;
	}

	public void setServiceid(String serviceid) {
		this.serviceid = serviceid;
	}

	public Integer getRsp() {
		return rsp;
	}

	public void setRsp(Integer rsp) {
		this.rsp = rsp;
	}

	public String getMainacctid() {
		return mainacctid;
	}

	public void setMainacctid(String mainacctid) {
		this.mainacctid = mainacctid;
	}

	public String getAppacctid() {
		return appacctid;
	}

	public void setAppacctid(String appacctid) {
		this.appacctid = appacctid;
	}

}
