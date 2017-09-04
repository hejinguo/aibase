package com.ai.base.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询定义
 * @author hejg
 *
 */
public class Grid implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;//查询代码-唯一标识
	private String name;//查询名称-显示标题
	private String table;//数据来源-表、视图或者查询结果集
	private String dataSource;//若数据源为ESOP默认库,可不用配置
	private String desc;//描述信息
	private List<Condition> conditions;//查询条件
	private List<Field> fields;//显示列字段
	private List<Button> buttons;//功能操作按钮
	private Page page;//是否分页,分页控件属性
	
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
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public List<Condition> getConditions() {
		return conditions;
	}
	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	public List<Button> getButtons() {
		return buttons;
	}
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	@Override
	public String toString() {
		return "Grid [code=" + code + ", name=" + name + ", table=" + table + ", dataSource=" + dataSource + ", desc="
				+ desc + ", conditions=" + conditions + ", fields=" + fields + ", buttons=" + buttons + "]";
	}
}
