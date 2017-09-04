package com.ai.base.model.core;

import java.io.Serializable;

import com.ai.base.item.core.YesOrNot;

/**
 * 数据字典
 * @author hejg
 *
 */
public class DataDict implements Serializable {
	private static final long serialVersionUID = 1L;
	private String typeCode;// 数据字典类型编码
	private String typeName;// 数据字典类型名称
	private String dictCode;// 数据字典条目取值
	private String dictName;// 数据字典条目名称
	private YesOrNot dictState;// 数据字典启用状态
	private int showOrder;// 数据字典条目顺序

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getDictState() {
		return dictState.getValue();
	}
	
	public String getDictStateName() {
		return dictState.getName();
	}
	
	public void setDictState(int dictState) {
		this.dictState = YesOrNot.get(dictState);
	}

	public void setDictStateEnum(YesOrNot dictState) {
		this.dictState = dictState;
	}

	public int getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(int showOrder) {
		this.showOrder = showOrder;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dictCode == null) ? 0 : dictCode.hashCode());
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataDict other = (DataDict) obj;
		if (dictCode == null) {
			if (other.dictCode != null)
				return false;
		} else if (!dictCode.equals(other.dictCode))
			return false;
		if (typeCode == null) {
			if (other.typeCode != null)
				return false;
		} else if (!typeCode.equals(other.typeCode))
			return false;
		return true;
	}
}
