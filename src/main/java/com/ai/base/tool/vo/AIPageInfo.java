package com.ai.base.tool.vo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 对查询分页结果进行包装<br/>
 * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
 * @author hejg
 * @param <T>
 */
public class AIPageInfo<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	// 当前页
	private int pageNum;
	// 每页的数量
	private int pageSize;
	// 当前页的数量
	private int size;
	//分页开始行
    private int startRow;
    //分页结束行
    private int endRow;
	// 总记录数
	private long total;
	// 总页数
	private int pages;
	// 结果集
	private List<T> list;
	// 前一页
	private int prePage;
	// 下一页
	private int nextPage;
	// 是否为第一页
	private boolean isFirstPage = false;
	// 是否为最后一页
	private boolean isLastPage = false;
	// 是否有前一页
	private boolean hasPreviousPage = false;
	// 是否有下一页
	private boolean hasNextPage = false;
	// 导航页码数
	private int navigatePages = 8;
	// 所有导航页号
	private int[] navigatepageNums;
	// 导航条上的第一页
	private int navigateFirstPage;
	// 导航条上的最后一页
	private int navigateLastPage;

	public AIPageInfo(int pageNum, int pageSize) {
		this.pageNum = pageNum == 0 ? 1 : pageNum;
		this.pageSize = pageSize;
		this.startRow = (this.pageNum-1)*this.pageSize+1;
		this.endRow = this.pageNum*this.pageSize;
	}
	
	public AIPageInfo(int pageNum, int pageSize,int navigatePages) {
		this(pageNum,pageSize);
		this.navigatePages = navigatePages;
	}
	
	public void initPage(long total, List<T> list) {
		this.list = list;
		this.size = list.size();
		init(total, this.pageNum, this.pageSize);
	}

	private void init(long total, int pageNum, int pageSize) {
		// 设置基本参数
		this.total = total;
		this.pageSize = pageSize;
		this.pages = (int) (this.total - 1) / this.pageSize + 1;
		// 根据输入可能错误的当前号码进行自动纠正
		if (pageNum < 1) {
			this.pageNum = 1;
		} else if (pageNum > this.pages) {
			this.pageNum = this.pages;
		} else {
			this.pageNum = pageNum;
		}
		// 基本参数设定之后进行导航页面的计算
		calcnavigatepageNums();
		// 计算页数据
		calcPage();
		// 以及页面边界的判定
		judgePageBoudary();
	}

	/**
	 * 计算导航页
	 */
	private void calcnavigatepageNums() {
		// 当总页数小于或等于导航页码数时
		if (pages <= navigatePages) {
			navigatepageNums = new int[pages];
			for (int i = 0; i < pages; i++) {
				navigatepageNums[i] = i + 1;
			}
		} else { // 当总页数大于导航页码数时
			navigatepageNums = new int[navigatePages];
			int startNum = pageNum - navigatePages / 2;
			int endNum = pageNum + navigatePages / 2;

			if (startNum < 1) {
				startNum = 1;
				// (最前navPageCount页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			} else if (endNum > pages) {
				endNum = pages;
				// 最后navPageCount页
				for (int i = navigatePages - 1; i >= 0; i--) {
					navigatepageNums[i] = endNum--;
				}
			} else {
				// 所有中间页
				for (int i = 0; i < navigatePages; i++) {
					navigatepageNums[i] = startNum++;
				}
			}
		}
	}

	/**
	 * 判定页面边界
	 */
	private void judgePageBoudary() {
		isFirstPage = pageNum == 1;
		isLastPage = pageNum == pages && pageNum != 1;
		hasPreviousPage = pageNum != 1;
		hasNextPage = pageNum != pages;
	}

	/**
	 * 计算页数据
	 */
	private void calcPage() {
		if (navigatepageNums != null && navigatepageNums.length > 0) {
			navigateFirstPage = navigatepageNums[0];
			navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
			if (pageNum > 1) {
				prePage = pageNum - 1;
			}
			if (pageNum < pages) {
				nextPage = pageNum + 1;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public long getTotal() {
		return total;
	}

	public List<T> getList() {
		return list;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public int getNavigateFirstPage() {
		return navigateFirstPage;
	}

	public int getNavigateLastPage() {
		return navigateLastPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPages() {
		return pages;
	}

	public int getNavigatePages() {
		return navigatePages;
	}

	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}

	public int getSize() {
		return size;
	}
	
	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	@Override
	public String toString() {
		return "AIPage [pageNum=" + pageNum + ", pageSize=" + pageSize + ", size=" + size + ", total=" + total
				+ ", pages=" + pages + ", list=" + list + ", prePage=" + prePage + ", nextPage=" + nextPage
				+ ", isFirstPage=" + isFirstPage + ", isLastPage=" + isLastPage + ", hasPreviousPage=" + hasPreviousPage
				+ ", hasNextPage=" + hasNextPage + ", navigatePages=" + navigatePages + ", navigatepageNums="
				+ Arrays.toString(navigatepageNums) + ", navigateFirstPage=" + navigateFirstPage + ", navigateLastPage="
				+ navigateLastPage + "]";
	}
}
