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
	private int btnNum;//分页按钮组的数量
	
	public Page(){
		this.pageSize = 15;
		this.pageList = new int[]{10,15,20,25,30};
		this.btnNum = 5;
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

	public int getBtnNum() {
		return btnNum;
	}

	public void setBtnNum(int btnNum) {
		this.btnNum = btnNum;
	}
}
