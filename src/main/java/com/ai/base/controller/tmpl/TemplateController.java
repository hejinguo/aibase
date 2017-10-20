package com.ai.base.controller.tmpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.base.model.tmpl.Template;
import com.ai.base.tool.FTPTool;
import com.ai.base.tool.FileTool;
import com.ai.base.tool.PropertiesTool;
import com.ai.base.tool.vo.ResultObject;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/tmpl")
public class TemplateController {
	
	@RequestMapping("/getTemplateInfo")
	public Object getTemplateInfo(@RequestParam(required=true)String code,
			HttpServletRequest request){
		Template template = null;
	    try {
	    	String define = null;//No.1:FTP文件,No.2:APP文件,No.3：数据库
	    	PropertiesTool instance = PropertiesTool.getInstance();
	    	if("FTP".equalsIgnoreCase(instance.get("source.type"))){
	    		define = FTPTool.readFile("grid", code+".txt");
	    	}else if("APP".equalsIgnoreCase(instance.get("source.type"))){
	    		define = FileTool.readResourceFile("tmpl", code+".txt");
	    	}else if("DB".equalsIgnoreCase(instance.get("source.type"))){
	    		define = "";
	    	}
	    	
	    	System.out.println(define);
	    	
	    	ObjectMapper mapper = new ObjectMapper();
	    	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);//忽略不需要的多余字段
	    	mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);//tab空格换行等控制字符
	    	template = mapper.readValue(define, Template.class);
	    	/*if(!StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.setDbType(grid.getDataSource());
	    	}
	    	PageInfo<Map<String, Object>> data = gridService.getDataInfo(grid, request.getParameterMap() ,pageNum, pageSize);
	    	if(!StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.clearDbType();
	    	}*/
	    	Map<String,Object> resultMap = new HashMap<String,Object>();
	    	resultMap.put("template", template);
//	    	resultMap.put("data", data);
	    	return new ResultObject<Object>(true, resultMap);
		} catch (Exception e) {
			return new ResultObject<Object>(e);
		} finally{
			/*if(grid != null && !StringUtils.isEmpty(grid.getDataSource())){
	    		DbContextHolder.clearDbType();
	    	}*/
		}
	}
}
