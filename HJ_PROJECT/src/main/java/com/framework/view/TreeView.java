package com.framework.view;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用树结构对象
 * @author daniel
 *
 */
public class TreeView {
	private Long id;
	private Long pId;
	private String name;
	private Integer depth;
	private Integer ident;
	private Integer endIdent;
	private Integer orderNo;
	private Integer status;
	private List<TreeView> children=new ArrayList<TreeView>();
	
	
	public TreeView() {
		super();
	}

	public TreeView(Long id, Long pId, String name, Integer depth,
			Integer ident, Integer endIdent, Integer orderNo,Integer status) {
		super();
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.depth = depth;
		this.ident = ident;
		this.endIdent = endIdent;
		this.orderNo = orderNo;
		this.status=status;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TreeView> getChildren() {
		return children;
	}

	public void setChildren(List<TreeView> children) {
		this.children = children;
	}

	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public Integer getIdent() {
		return ident;
	}
	public void setIdent(Integer ident) {
		this.ident = ident;
	}
	public Integer getEndIdent() {
		return endIdent;
	}
	public void setEndIdent(Integer endIdent) {
		this.endIdent = endIdent;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
