package com.ai.base.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询字段
 * @author hejg
 *
 */
public class Field implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;//字段代码[dataType为Sequence、Button时留空即可]
	private String name;//字段名称-显示标题
	private String title;//标记显示标题拓展信息
	private String width;//宽度(像素:50px,百分比:50%)
	private String dataType;//字段类型(序号:Sequence,字符:String,数字:Number,百分比:Percent,按钮:Button)
	private List<Button> buttons;//当dataType为button时启用改属性
	private boolean download;//是否下载
	private String orderby;//是否排序,ASC DESC 空串或null[不排序]
	private String desc;//描述信息
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
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public List<Button> getButtons() {
		return buttons;
	}
	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	public boolean isDownload() {
		return download;
	}
	public void setDownload(boolean download) {
		this.download = download;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
