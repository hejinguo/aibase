package com.ai.base.tool.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
//		System.out.println("DbContextHolder.getDbType()="+DbContextHolder.getDbType());
		return DbContextHolder.getDbType();
	}
}
