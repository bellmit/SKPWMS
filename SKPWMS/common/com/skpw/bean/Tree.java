package com.skpw.bean;

/**
 * @author hjy
 * 树形结构属性
 */
public class Tree {
	private String id;// 树 id
	private String pId;// 父id
	private boolean open;// 是否可以打开
	private String name;// 节点名称
	private boolean ishide;// 是否隐藏 这些属性
	private boolean nocheck = true;// 表示节点具有正常的勾选功能

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public boolean isOpen() {

		return this.open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isIshide() {
		return ishide;
	}

	public void setIshide(boolean ishide) {
		this.ishide = ishide;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

}
