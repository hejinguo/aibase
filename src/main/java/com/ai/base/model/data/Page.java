package com.ai.base.model.data;

import java.io.Serializable;

/**
 * 查询分页
 * @author hejg
 *
 */
public class Page implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageSize;//每页展示记录条数
	private int[] pageList;//自定展示条数选择列表
	
	public Page(){
		pageSize = 15;
		pageList = new int[]{10,15,20,25,30};
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int[] getPageList() {
		return pageList;
	}

	public void setPageList(int[] pageList) {
		this.pageList = pageList;
	}
}
