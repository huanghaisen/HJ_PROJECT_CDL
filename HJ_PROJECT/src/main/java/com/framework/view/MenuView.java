package com.framework.view;

import com.framework.base.domain.EntityImpl;

import java.util.ArrayList;
import java.util.List;

public class MenuView extends EntityImpl implements Comparable<MenuView>, java.io.Serializable {
	
	private Long id;//主键
	private String name;//菜单名
	private String navtitle;//标题显示
	private Integer isopen;//打开模式(0是内部地址，1是外部地址)
	private String type;//菜单类型
	private String target;//打开标记()
	private String status;//打开状态
	private String action;//打开地址
	private String ico;//图标
	private String mclass;//扩展样式
	private Long code;//菜单编号
	private Long level;//节点层级
	private Integer seq;//排序
	private Integer node;//是否节点(0表示分支,1表示节点)
	private Integer haschild;//是否有子节点(0表示无，1表示有)
	private String sence;//场景
	private List<MenuView> children=new ArrayList<MenuView>();//列表
	
	public MenuView() {
		super();
	}
	
	public MenuView(MenuView model){
		this.id = model.getId();
		this.name = model.getName();
		this.navtitle=model.getNavtitle();
		this.isopen=model.getIsopen();
		this.type = model.getType();
		this.target = model.getTarget();
		this.status=model.getStatus();
		this.action = model.getAction();
		this.mclass=model.getMclass();
		this.code = model.getCode();
		this.ico = model.getIco();
		this.level = model.getLevel();
		this.seq = model.getSeq();
		this.node = model.getNode();
		this.haschild = model.getHaschild();
		this.sence=model.getSence();
	}
	
	public MenuView(Long id, String name,String navtitle,Integer isopen, String type, String target,String status,
			String action, String mclass,Long code,String ico,Long level, Integer seq, Integer node, Integer haschild,String sence,
			List<MenuView> children) {
		super();
		this.id = id;
		this.name = name;
		this.navtitle=navtitle;
		this.isopen=isopen;
		this.type = type;
		this.target = target;
		this.status=status;
		this.action = action;
		this.mclass=mclass;
		this.code = code;
		this.ico = ico;
		this.level = level;
		this.seq = seq;
		this.node = node;
		this.haschild = haschild;
		this.sence=sence;
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getIsopen() {
		return isopen;
	}

	public void setIsopen(Integer isopen) {
		this.isopen = isopen;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getAction() {
		return action;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMclass() {
		return mclass;
	}

	public void setMclass(String mclass) {
		this.mclass = mclass;
	}
	public String getIco() {
		return ico;
	}
	public void setIco(String ico) {
		this.ico = ico;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getNode() {
		return node;
	}
	public void setNode(Integer node) {
		this.node = node;
	}
	public Integer getHaschild() {
		return haschild;
	}

	public void setHaschild(Integer haschild) {
		this.haschild = haschild;
	}
	public List<MenuView> getChildren() {
		return children;
	}
	public void setChildren(List<MenuView> children) {
		this.children = children;
	}
	
	public String getNavtitle() {
		return navtitle;
	}

	public void setNavtitle(String navtitle) {
		this.navtitle = navtitle;
	}
	
	public String getSence() {
		return sence;
	}

	public void setSence(String sence) {
		this.sence = sence;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}
	
	@Override
	public int compareTo(MenuView o) {
		return this.getSeq().compareTo(o.getSeq());
	}
	
}
