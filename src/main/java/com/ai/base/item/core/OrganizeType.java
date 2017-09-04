package com.ai.base.item.core;

/**
 * 组织机构类型枚举
 * 
 * @author hejg
 *
 */
public enum OrganizeType {
	OTHER(0, "其他机构层级"), 
	PROVINCE(1, "省"), 
	CITY(2, "地市"), 
	COUNTY(3, "区县"), 
	AREA(4, "片区"), 
	@Deprecated
	SERVICE(5, "客户经理"),//政企营销已经使用了5作为客户经理
	GRID(6, "网格");

	private int value;
	private String name;

	private OrganizeType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public static OrganizeType get(int value) {
		for (OrganizeType item : OrganizeType.values()) {
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
