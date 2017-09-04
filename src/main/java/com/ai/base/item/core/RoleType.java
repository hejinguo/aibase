package com.ai.base.item.core;

/**
 * 用户角色类型标识枚举
 * 
 * @author hejg
 *
 */
public enum RoleType {
	SYS_ADMIN(1, "管理者"), CUST_ADMIN(2, "客户经理主管"), CUST_MANAGER(3, "客户经理"), WORK_AUDIT(4, "挂牌行动审批者");

	private int value;
	private String name;

	private RoleType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static RoleType get(int value) {
		for (RoleType item : RoleType.values()) {
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
