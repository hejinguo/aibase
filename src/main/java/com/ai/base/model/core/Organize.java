package com.ai.base.model.core;

import java.io.Serializable;

import com.ai.base.item.core.OrganizeType;
import com.ai.base.item.core.YesOrNot;

public class Organize implements Serializable{
	private static final long serialVersionUID = 1L;
	private long orgId;
	private String orgName;// 机构名称
	private String orgAbbr;// 机构简写
	private OrganizeType orgType;// 机构类别
	private YesOrNot state;// 有效状态

	public long getOrgId() {
		return orgId;
	}

	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getOrgAbbr() {
		return orgAbbr;
	}

	public void setOrgAbbr(String orgAbbr) {
		this.orgAbbr = orgAbbr;
	}

	public int getOrgType() {
		return orgType.getValue();
	}
	
	public String getOrgTypeName() {
		return orgType.getName();
	}
	
	public void setOrgType(int orgType) {
		this.orgType = OrganizeType.get(orgType);
	}

	public void setOrgTypeEnum(OrganizeType orgType) {
		this.orgType = orgType;
	}

	public int getState() {
		return state.getValue();
	}
	
	public String getStateName() {
		return state.getName();
	}
	
	public void setState(int state) {
		this.state = YesOrNot.get(state);
	}

	public void setStateEnum(YesOrNot state) {
		this.state = state;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (orgId ^ (orgId >>> 32));
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
		Organize other = (Organize) obj;
		if (orgId != other.orgId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Organize [orgId=" + orgId + ", orgName=" + orgName + ", orgAbbr=" + orgAbbr + ", orgType=" + orgType
				+ ", state=" + state + "]";
	}
}
