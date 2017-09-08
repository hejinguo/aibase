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
	private String dataType;//字段类型(字符:String,数字:Number)目前只提供2个
	private String widgetType;//查询条件类型(下拉框:Select,文本框:Text,按钮:Button,下载:Download(Button可实现自定下载))
	private String source;//查询条件数据源(Select[Redis:KEY,MTable:KEY,Script:SQL],Text[defaultValue])
	private boolean fuzzyWay;//是否模糊匹配查询
	private String symbol;//条件运算符(模糊查询默认为like,精确查询默认为=,可自定义为>、<、!=等情况)
	private Button button;//widgetType为Button的其他拓展按钮(如添加按钮)
//	private List<SelectItem<String,String>> sitems;//widgetType为Select的列表结果
	
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
	public boolean isFuzzyWay() {
		return fuzzyWay;
	}
	public void setFuzzyWay(boolean fuzzyWay) {
		this.fuzzyWay = fuzzyWay;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Button getButton() {
		return button;
	}
	public void setButton(Button button) {
		this.button = button;
	}
}
