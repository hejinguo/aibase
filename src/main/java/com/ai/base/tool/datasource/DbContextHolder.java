package com.ai.base.tool.datasource;

public class DbContextHolder {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}
	
	public static void clearDbType() {
		contextHolder.remove();
	}

	public static String getDbType() {
		return contextHolder.get();
	}
}
