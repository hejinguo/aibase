package com.ai.base.item.core;

/**
 * 表示登陆系统方式的枚举
 * 
 * @author hejg
 *
 */
public enum LoginType {
	NOT(0, "NOT"),WEB(1, "WEB"), APP(2, "APP");

	private int value;
	private String name;

	private LoginType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static LoginType get(int value) {
		for (LoginType item : LoginType.values()) {
			if (item.getValue() == value) {
				return item;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
