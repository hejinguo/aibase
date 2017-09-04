package com.ai.base.controller.data;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.base.model.data.Grid;
import com.ai.base.service.data.GridService;
import com.ai.base.tool.FTPTool;
import com.ai.base.tool.StringUtils;
import com.ai.base.tool.datasource.DbContextHolder;
import com.ai.base.tool.vo.ResultObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/grid")
public class GridController {
	@Autowired
	private GridService gridService;
	
	@RequestMapping("/getDataInfo")
	public Object getDataInfo(@RequestParam(required=true)String code,
			@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="10")int pageSize,
			HttpServletRequest request){
		Grid grid = null;
	    try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略不需要的多余字段
	    	String define = FTPTool.readFile("grid", code+".txt");//1:文件，2：数据库
	    	grid = mapper.readValue(define, Grid.class);
	    	if(!StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.setDbType(grid.getDataSource());
	    	}
	    	PageInfo<Map<String, Object>> data = gridService.getDataInfo(grid, request.getParameterMap() ,pageNum, pageSize);
	    	if(!StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.clearDbType();
	    	}
	    	Map<String,Object> resultMap = new HashMap<String,Object>();
	    	resultMap.put("define", grid);
	    	resultMap.put("data", data);
	    	return new ResultObject<Object>(true, resultMap);
		} catch (Exception e) {
			return new ResultObject<Object>(e);
		} finally{
			if(grid != null && !StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.clearDbType();
	    	}
		}
	}
}
