package com.framework.view;

import com.utils.PermissionUtils;
import org.apache.shiro.authz.Permission;

import java.io.Serializable;

/**
 * 权限使用模型
 * @author daniel
 *
 */
public class PermissionView implements Permission, Serializable{
	
	private String rname;//角色
	private String resources;//资源
	private String func;//动作
	
	
	
	public PermissionView() {
		super();
	}


	public PermissionView(String rname, String resources, String func) {
		super();
		this.rname = rname;
		this.resources = resources;
		this.func = func;
	}
	
	
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getResources() {
		resources=PermissionUtils.strToSence(resources);
		return resources;
	}
	public void setResources(String resources) {
		this.resources = resources;
	}
	public String getFunc() {
		return func;
	}
	public void setFunc(String func) {
		this.func = func;
	}
	
	
	public String getPermissionToString(){
		return this.getRname()+":"+this.getResources()+":"+this.getFunc();
	}


	@Override
	public boolean implies(Permission o) {
		PermissionView permission=(PermissionView) o;
		if (this.getRname().equals(permission.getRname())
				&& this.getResources().equals(permission.getResources())){
//			&& this.getFunc().equals(permission.getFunc())
			if ((permission.getFunc()!=null && !permission.getFunc().equals(""))) {
				if (this.getFunc().equals(permission.getFunc())) {
					return true;
				}else{
					return false;
				}
			}else{
				return true;
			}
		}else{
			return false;
		}
		
	}

}
