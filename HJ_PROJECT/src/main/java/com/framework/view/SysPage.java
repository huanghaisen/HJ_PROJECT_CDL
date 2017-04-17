package com.framework.view;

import java.io.Serializable;

public class SysPage implements Serializable{
	private int draw;//当前是第几页
	
	private int pageSize;//一页有几个
	
	private int totalCount;//总条目数  肯定要
	
	private int totalPageCount;//总页数
	
//	@SuppressWarnings("unused")//干嘛的？？？
//	private int startPos; // 开始位置，从0开
//	 
//	@SuppressWarnings("unused")
//	private boolean hasFirst;// 是否有首页
//	 
//	@SuppressWarnings("unused")
//	private boolean hasPre;// 是否有前一页
//	 
//
//	@SuppressWarnings("unused")
//	private boolean hasNext;// 是否有下一页
//	 
//	@SuppressWarnings("unused")
//	private boolean hasLast;// 是否有最后一页
	
	
	public SysPage(Integer totalCount,Integer pageSize){
		this.totalCount=totalCount;
		this.pageSize=pageSize;	
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
	
   
}
