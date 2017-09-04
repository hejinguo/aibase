package com.ai.base.service.data;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.base.mapper.core.CommonMapper;
import com.ai.base.model.data.Condition;
import com.ai.base.model.data.Field;
import com.ai.base.model.data.Grid;
import com.ai.base.tool.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author hejg
 *
 */
@Service
public class GridService {
	@Autowired
	private CommonMapper commonMapper;
	
	@Transactional(readOnly=true)
	public PageInfo<Map<String, Object>> getDataInfo(Grid grid,Map<String,String[]> paramMap,int pageNum,int pageSize){
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		int seq = 0;
		for (Field field : grid.getFields()) {
			if(!StringUtils.isEmpty(field.getCode())){
				sb.append((seq++ > 0 ? "," : "")+field.getCode());
			}
		}
		sb.append(" FROM ");
		sb.append(grid.getTable());
		sb.append(" WHERE 1 = 1 ");
		for (Condition condition : grid.getConditions()) {
			String conditionValue = paramMap.get(condition.getCode()) == null ? null : paramMap.get(condition.getCode())[0];
			if(!StringUtils.isEmpty(conditionValue)){
				if(!StringUtils.isEmpty(condition.getDataType()) 
						&& "STRING".equalsIgnoreCase(condition.getDataType())){
					if(condition.getWay() == 2){//模糊查询
						sb.append(" AND "+condition.getCode()+" ");
						sb.append(" LIKE '%"+conditionValue+"%'");
					}else{//精确查询
						sb.append(" AND "+condition.getCode()+" ");
						sb.append(" = '"+conditionValue+"'");
					}
				}else if(!StringUtils.isEmpty(condition.getDataType()) 
						&& "NUMBER".equalsIgnoreCase(condition.getDataType())){
					if(condition.getWay() == 2){//模糊查询
						sb.append(" AND TRIM(CHAR("+condition.getCode()+")) ");
						sb.append(" LIKE '%"+conditionValue+"%'");
					}else{//精确查询
						sb.append(" AND "+condition.getCode()+" ");
						sb.append(" = "+conditionValue);
					}
				}
			}
		}
		System.out.println("AIBASE_DATA_SQL:"+sb.toString());
		PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> rows = commonMapper.getResultBySql(sb.toString());
		return new PageInfo<Map<String, Object>>(rows,5);
	}
}
