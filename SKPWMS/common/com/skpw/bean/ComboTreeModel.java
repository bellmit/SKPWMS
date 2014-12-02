package com.skpw.bean;

/**
 * @author hjy easyui树形菜单 2014-10-27
 */
public class ComboTreeModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String id;// 节点编号

	private String text;// 节点名

	private String pid;// 父节点编号

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public ComboTreeModel(String id, String text, String pid) {
		super();
		this.id = id;
		this.text = text;
		this.pid = pid;
	}

	public ComboTreeModel() {
		super();
	}

	
}
