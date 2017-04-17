package com.framework.util;

import java.util.List;

/**
 * @Description: 处理分页工具类
 * @author hhq
 * @date 2013-09-25 下午08:30:20
 * @version V1.0
 */
public class Page {

	public static final Long DEFAULT_PAGE_SIZE = 30l;

	private Long startIndex;
	private Long pageSize; // 每页记录数
	private List<?> list; // 每页属性值列表
	private Long totalSize; // 全部记录
	private Long currPage; // 当前页码
	private Long totalPage;// 全部页数
	private Long numPerPage;//记录手动分业数量

	public Page(Long currPage, Long totalSize, Long pageSize, Long numPerPage) {
		this.currPage = currPage;
		this.totalSize = totalSize;
		this.pageSize = pageSize;
		this.numPerPage = numPerPage;
		this.countStartIndex(currPage);
	}

	public Page(Long currPage, Long totalSize,Long numPerPage) {
		this.currPage = currPage;
		this.totalSize = totalSize;
		this.numPerPage = numPerPage;
		this.pageSize = DEFAULT_PAGE_SIZE;
		this.countStartIndex(currPage);
	}

	public void countStartIndex(Long currPage) {
		Double pageSize = new Double(this.pageSize);
		Double totalPage = Math.ceil(totalSize / pageSize);
		Long total_page = totalPage.longValue();
		this.totalPage = total_page;
		if (currPage <= 0) {
			currPage = 0l;
		}
		/*
		 * if(currPage>=total_page){ currPage = total_page; }
		 */
		Long page_num = currPage - 1;
		if (page_num < 0) {
			page_num = 0l;
		}
		this.startIndex = page_num * this.pageSize;
	}

	public void setTotalPage(Long totalPage) {
		this.totalPage = totalPage;
	}

	public Long getTotalPage() {
		return totalPage;
	}

	public Long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Long getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Long currPage) {
		this.currPage = currPage;
	}

	public Long getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(Long numPerPage) {
		this.numPerPage = numPerPage;
	}

}
