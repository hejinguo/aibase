package com.ai.base.model.data;

import java.io.Serializable;

/**
 * 查询字段
 * @author hejg
 *
 */
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;//字段代码
	private String name;//字段名称-显示标题
	private String title;//标记显示标题拓展信息
	private String dataType;//字段类型(字符:String,数字:Number,百分比:Percent)//button;{type:'button',target:[]}
	private String width;//宽度(像素:50px,百分比:50%)
	private String desc;//描述信息
	private boolean download;//是否下载
	private String orderby;//是否排序,ASC DESC null
	//PageHelper.orderBy http://blog.163.com/_kid/blog/static/30405476201601510263976/
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Override
	public String toString() {
		return "Field [code=" + code + ", name=" + name + ", title=" + title + ", dataType=" + dataType + ", width="
				+ width + ", desc=" + desc + "]";
	}
}
