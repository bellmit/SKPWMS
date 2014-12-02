package com.skpw.service;

import java.util.ArrayList;
import java.util.List;

import com.skpw.bean.ComboTreeModel;

public class EasyuiTreeService {

	public static String getJsonData(List<ComboTreeModel> list) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("[");

		iterGet(list, "", buffer);

		buffer.append("]");

		// 将,\n]替换成\n]

		String tmp = buffer.toString();

		tmp = tmp.replaceAll(",\n]", "\n]");

		return tmp;

	}

	/**
	 * 
	 * 递归生成json格式的数据{id:1,text:'',children:[]}
	 * 
	 * @param args
	 */

	public static void iterGet(List<ComboTreeModel> list, String pid, StringBuffer buffer) {

		for (ComboTreeModel node : list) {

			// 查找所有父节点为pid的所有对象，然后拼接为json格式的数据

			if (node.getPid().equals(pid))

			{

				buffer.append("{\"id\":\"" + node.getId() + "\",\"text\":\""
						+ node.getText() + "\",\"children\":[");

				// 递归

				iterGet(list, node.getId(), buffer);

				buffer.append("]},\n");

			}

		}

	}
	
	
	public static void main(String[] args) {
		System.out.println("Hello");
		List<ComboTreeModel> list=new ArrayList<ComboTreeModel>();
		
		ComboTreeModel node1=new ComboTreeModel("1", "a", "0");
		ComboTreeModel node2=new ComboTreeModel("2", "b", "1");
		list.add(node1);
		list.add(node2);
		System.out.println(EasyuiTreeService.getJsonData(list));
	}
}
