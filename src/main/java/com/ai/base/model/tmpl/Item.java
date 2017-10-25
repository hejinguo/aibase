package com.ai.base.model.tmpl;

import java.io.Serializable;
import java.util.List;

import com.ai.base.tool.vo.SelectItem;

/**
 * 模板输入项
 * @author hejg
 *
 */
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	private String itemCode;//输入项代码;用作后台与页面数据传输的NAME(KEY)，或者在纵(宽)表模式中表的列名称(FIELD)
	private String itemName;//输入项名称;用于页面展示的名称
	private int itemType;//输入项类型;0隐藏文本框、1普通文本框、2日期选择框、3时间选择框、4日期时间选择框、5下拉框、6文本域、7编辑器、8单选框、9多选框、10文件选择、11拓展按钮[X]
	private String itemSource;//输入项来源;数据列表来源:下拉框、单选框、多选框类型时数据项目配置到数据字典中,这里只存放对应的TYPE_CODE即可，
	private String itemValue;//输入项默认值;作为默认展示的取值
	private int itemSpan;//输入项占位;输入项在页面上展示的宽度分块(默认基于bootstrap的1-12分块)
	private String itemRegex;//输入项验证规则;验证填写项目信息有效性的正则表达式
	private String itemRegexTitle;//输入项验证说明;当验证输入项规则不合法时的提醒消息
	private int itemRequire;//是否要必填标记;在页面上以红色*标记的必填项目提示
	private String itemDesc;//输入项描述;输入项描述信息
	/**
	 * 不参与持久化的字段(下拉框、单选框、多选框的扩展选项)
	 */
	private List<SelectItem<String, String>> selectList;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
	
	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	public int getItemSpan() {
		return itemSpan;
	}

	public void setItemSpan(int itemSpan) {
		this.itemSpan = itemSpan;
	}

	public String getItemSource() {
		return itemSource;
	}

	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}

	public String getItemRegex() {
		return itemRegex;
	}

	public void setItemRegex(String itemRegex) {
		this.itemRegex = itemRegex;
	}

	public String getItemRegexTitle() {
		return itemRegexTitle;
	}

	public void setItemRegexTitle(String itemRegexTitle) {
		this.itemRegexTitle = itemRegexTitle;
	}
	
	public int getItemRequire() {
		return itemRequire;
	}

	public void setItemRequire(int itemRequire) {
		this.itemRequire = itemRequire;
	}

	public List<SelectItem<String, String>> getSelectList() {
		return selectList;
	}

	public void setSelectList(List<SelectItem<String, String>> selectList) {
		this.selectList = selectList;
	}

	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", itemName=" + itemName + ", itemType=" + itemType + ", itemSource="
				+ itemSource + ", itemValue=" + itemValue + ", itemSpan=" + itemSpan + ", itemRegex=" + itemRegex
				+ ", itemRegexTitle=" + itemRegexTitle + ", itemRequire=" + itemRequire + ", itemDesc=" + itemDesc
				+ ", selectList=" + selectList + "]";
	}
}
