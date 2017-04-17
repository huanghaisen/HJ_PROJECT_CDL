package com.framework.view;

import com.framework.base.domain.EntityImpl;

import java.util.ArrayList;
import java.util.List;

public class MenuViewApi extends EntityImpl implements Comparable<MenuViewApi>, java.io.Serializable {
	
	private Long id;//主键
	private String name;//菜单名
	private String action;//打开地址
	private Long level;//节点层级
	private Integer seq;//排序
	private Integer node;//是否节点(0表示分支,1表示节点)
	private Integer haschild;//是否有子节点(0表示无，1表示有)
	private List<MenuViewApi> children=new ArrayList<MenuViewApi>();//列表
	
	public MenuViewApi() {
		super();
	}
	
	public MenuViewApi(MenuViewApi model){
		this.id = model.getId();
		this.name = model.getName();
		this.action = model.getAction();
		this.level = model.getLevel();
		this.seq = model.getSeq();
		this.node = model.getNode();
		this.haschild = model.getHaschild();
	}
	
	
	public MenuViewApi(Long id, String name, String action, Long level,
			Integer seq, Integer node, Integer haschild, List<MenuViewApi> children) {
		super();
		this.id = id;
		this.name = name;
		this.action = action;
		this.level = level;
		this.seq = seq;
		this.node = node;
		this.haschild = haschild;
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
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public List<MenuViewApi> getChildren() {
		return children;
	}
	public void setChildren(List<MenuViewApi> children) {
		this.children = children;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	@Override
	public int compareTo(MenuViewApi o) {
		return this.getSeq().compareTo(o.getSeq());
	}
	
}
