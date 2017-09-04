package com.ai.base.model.data;

import java.io.Serializable;

/**
 * 查询条件
 * @author hejg
 *
 */
public class Condition implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;//字段代码
	private String name;//字段名称-显示标题
	private String dataType;//字段类型(字符:String,数字:Number,百分比:Percent)//button;{type:'button',target:[]}
	private String widgetType;//查询条件类型(下拉框:Select,文本框:Text,...)
	private String source;//查询条件数据源(Select[Redis:KEY,MTable:KEY,Script:SQL],Text[defaultValue])
	private int way;//查询方式(1:精确,2:模糊)
	private boolean fuzzyWay;//是否模糊匹配查询
	//‘添加’类按钮 buttons
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getWidgetType() {
		return widgetType;
	}
	public void setWidgetType(String widgetType) {
		this.widgetType = widgetType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getWay() {
		return way;
	}
	public void setWay(int way) {
		this.way = way;
	}
	@Override
	public String toString() {
		return "Condition [code=" + code + ", name=" + name + ", dataType=" + dataType + ", widgetType=" + widgetType
				+ ", source=" + source + ", way=" + way + "]";
	}
}
