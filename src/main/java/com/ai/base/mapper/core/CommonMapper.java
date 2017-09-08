package com.ai.base.mapper.core;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ai.base.tool.vo.SelectItem;

/**
 * 
 * @author hejg
 */
@Repository
public interface CommonMapper {
	
	/**
	 * 通用查询类型方法
	 * @param sql
	 * @return
	 */
    List<Map<String,Object>> getResultBySql(@Param("sql")String sql);
    
    /**
     * 通用查询下拉选项方法
     * @param sql
     * @return
     */
    List<SelectItem<String, String>> getSelectBySql(@Param("sql")String sql);
    
    /**
     * 字典查询下拉选项方法
     * @param key
     * @return
     */
    List<SelectItem<String, String>> getSelectByDict(@Param("key")String key);
}
