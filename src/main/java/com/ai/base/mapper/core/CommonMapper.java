package com.ai.base.mapper.core;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author hejg
 */
@Repository
public interface CommonMapper {

	/**
	 * 
	 * @param sql
	 * @return
	 */
    List<Map<String,Object>> getResultBySql(@Param("sql")String sql);
    
}
