package com.skpw.common;

public interface Globals {

	/**
	 * 日志级别定义
	 */
	Short Log_Leavel_INFO = 1;
	Short Log_Leavel_WARRING = 2;
	Short Log_Leavel_ERROR = 3;
	/**
	 * 日志类型
	 */
	Short Log_Type_LOGIN = 1; // 登陆
	Short Log_Type_EXIT = 2; // 退出
	Short Log_Type_INSERT = 3; // 插入
	Short Log_Type_DEL = 4; // 删除
	Short Log_Type_UPDATE = 5; // 更新
	Short Log_Type_UPLOAD = 6; // 上传
	Short Log_Type_OTHER = 7; // 其他
}
