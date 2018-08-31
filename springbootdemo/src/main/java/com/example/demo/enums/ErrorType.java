package com.example.demo.enums;

import java.util.HashMap;
import java.util.Map;



/**
 * 
 * 错误码定义
 *
 */
public enum ErrorType{
	
	SUCCESS(200, "操作成功"),
	
	//账号相关30开头
	UN_LOGIN(300,"用户未登录"),
	LOGIN_FAIL(301, "登录失败"),
	NOT_ACCOUNT(302,"用户名或密码错误"),
	DISABLED(303,"该账号已被禁用"),
	REAPET_LOGIN(304,"重复登录"),
	
	//没有找到
	NOT_FOUND(404, "未找到"),
	
	//服务器相关异常
	UNKNOWN_ERROR(500, "未知错误"),
	
	//权限相关60开头
	UNAUTHORIZED(600,"权限不足")
	;	
	
	private String name;
	private int index;

	private ErrorType(int index, String name) {
		this.index = index;
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}

	
	private static Map<Integer, ErrorType> indexMap;
    
	static {
	        indexMap = new HashMap<>();
	        ErrorType[] ptArr = ErrorType.values();
	        for (ErrorType pt : ptArr) {
	            indexMap.put(pt.getIndex(), pt);
	        }
	}
	 public static ErrorType getByIndex(int index) {
	        return indexMap.get(index);
	 }
}
