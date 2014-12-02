package com.skpw.common;

public class Page {

	private int currentPage = 1;// 当前页

	private int pageSize = 10;// 每页显示记录数

	private long totalSize;// 总记录数

	private long totalPage;// 总页数

	public Page() {

	}

	public Page(int currentPage, int pageSize, long totalSize) {

		this.currentPage = currentPage;
		this.pageSize = pageSize;

		this.totalSize = totalSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}

	public long getTotalPage() {

		if (0 == pageSize) {
			return 0;
		}
		long result = totalSize % pageSize;
		if (result == 0) {
			return totalSize / pageSize;
		}
		return totalSize / pageSize + 1;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

}
