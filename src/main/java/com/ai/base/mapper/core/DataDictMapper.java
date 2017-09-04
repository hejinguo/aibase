package com.ai.base.mapper.core;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ai.base.model.core.DataDict;
import com.ai.base.tool.vo.SelectItem;

/**
 * 数据字典
 * 
 * @author hejg
 */
@Repository
public interface DataDictMapper {

	/**
	 * 通过字典类型编码获取数据字典选项控件
	 * 
	 * @param typeCode
	 * @return
	 */
	List<SelectItem<String, String>> getSelectItemByType(@Param("typeCode")String typeCode);
//	<T, V> List<SelectItem<T, V>> getSelectItemByType(String typeCode);

	/**
	 * 通过字典类型编码获取数据字典对象
	 * 
	 * @param typeCode
	 * @return
	 */
	List<DataDict> getDataDictByType(@Param("typeCode")String typeCode);
}
