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
					if(condition.isFuzzyWay()){//模糊查询
						sb.append(" AND "+condition.getCode()+" ");
						if(StringUtils.isEmpty(condition.getSymbol())){
							sb.append(" LIKE ");
						}else{
							sb.append(" "+condition.getSymbol()+" ");
						}
						sb.append(" '%"+conditionValue+"%'");
					}else{//精确查询
						sb.append(" AND "+condition.getCode()+" ");
						if(StringUtils.isEmpty(condition.getSymbol())){
							sb.append(" = ");
						}else{
							sb.append(" "+condition.getSymbol()+" ");
						}
						sb.append(" '"+conditionValue+"'");
					}
				}else if(!StringUtils.isEmpty(condition.getDataType()) 
						&& "NUMBER".equalsIgnoreCase(condition.getDataType())){
					if(condition.isFuzzyWay()){//模糊查询
						sb.append(" AND TRIM(CHAR("+condition.getCode()+")) ");
						if(StringUtils.isEmpty(condition.getSymbol())){
							sb.append(" LIKE ");
						}else{
							sb.append(" "+condition.getSymbol()+" ");
						}
						sb.append(" LIKE '%"+conditionValue+"%'");
					}else{//精确查询
						sb.append(" AND "+condition.getCode()+" ");
						if(StringUtils.isEmpty(condition.getSymbol())){
							sb.append(" = ");
						}else{
							sb.append(" "+condition.getSymbol()+" ");
						}
						sb.append(" "+conditionValue);
					}
				}
			}
		}
		
		System.out.println("AIBASE_DATA_SQL:"+sb.toString());
		
		int btnNum = 5;//分页按钮组的数量
		if(grid.getPage() != null){
			if(pageSize < 1){//默认提供不合法的pageSize时取配置中默认值
				pageSize = grid.getPage().getPageSize();
			}
			btnNum = grid.getPage().getBtnNum();
			PageHelper.startPage(pageNum, pageSize);
		}
		//PageHelper.orderBy("countryname desc");
		List<Map<String, Object>> rows = commonMapper.getResultBySql(sb.toString());
		return new PageInfo<Map<String, Object>>(rows,btnNum);
	}
}
