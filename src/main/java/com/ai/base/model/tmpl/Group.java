package com.ai.base.model.tmpl;

import java.io.Serializable;
import java.util.List;

/**
 * 模板分组
 * @author hejg
 *
 */
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	private String groupCode;//分组代码
	private String groupName;//分组名称;模板分组用途
	private int groupType;//分组类型;分组类型:1普通分组模板,2自定义增删模板
	private String tableSchema;//分组表模;数据存储分组的表模
	private String tableName;//分组表名;数据存储分组的表名
	private String groupDesc;//分组描述;描述分组详情
	
	private List<Item> templateItem;//分组条目

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}
	
	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	
	public String getTableSchema() {
		return tableSchema;
	}

	public void setTableSchema(String tableSchema) {
		this.tableSchema = tableSchema;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Item> getTemplateItem() {
		return templateItem;
	}

	public void setTemplateItem(List<Item> templateItem) {
		this.templateItem = templateItem;
	}
	
	@Override
	public String toString() {
		return "TemplateGroup [groupCode=" + groupCode + ", groupName=" + groupName
				+ ", groupType=" + groupType + ", groupDesc=" + groupDesc
				+ ", templateItem=" + templateItem + "]";
	}
}
