package com.ai.base.model.core;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ai.base.item.core.RoleType;
import com.ai.base.item.core.YesOrNot;

/**
 * 系统用户信息BEAN
 *
 * @author hejg
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private long staffId;// 用户ID
    private String staffCode;// 登陆工号
    private String staffName;// 用户姓名
    private String phoneNo;// 手机号码
    private String photoAddr;// 用户头像信息
    private Organize manage;// 管理节点机构----ESOP管理节点主要用于分析系统
    private Organize region;// 归属节点机构------ESOP归属节点主要用于生成系统
    private String lastLoginMark;// 最近登陆验证码
    private String lastLoginToken;// 最近登陆TOKEN
    private Date lastLoginTime;// 最近登陆时间
    private YesOrNot state;// 有效状态
    private List<Role> roles;// 角色列表
    
    /**
     * 判断当前用户是否拥有某个角色
     * @param roleType
     * @return
     */
    public boolean validExistRole(RoleType roleType){
    	boolean flag = false;
    	for (Role role : roles) {
    		if(roleType.getValue() == role.getRoleType()){
    			flag = true;
    			break;
    		}
		}
    	return flag;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhotoAddr() {
        return photoAddr;
    }

    public void setPhotoAddr(String photoAddr) {
        this.photoAddr = photoAddr;
    }

	public Organize getManage() {
		return manage;
	}

	public void setManage(Organize manage) {
		this.manage = manage;
	}

	public Organize getRegion() {
		return region;
	}

	public void setRegion(Organize region) {
		this.region = region;
	}

	public String getLastLoginMark() {
        return lastLoginMark;
    }

    public void setLastLoginMark(String lastLoginMark) {
        this.lastLoginMark = lastLoginMark;
    }

    public String getLastLoginToken() {
        return lastLoginToken;
    }

    public void setLastLoginToken(String lastLoginToken) {
        this.lastLoginToken = lastLoginToken;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getState() {
        return state == null ? -1 : state.getValue();
    }

    public String getStateName() {
        return  state == null ? "未知" : state.getName();
    }

    public void setStateEnum(YesOrNot state) {
        this.state = state;
    }

    public void setState(int state) {
        this.state = YesOrNot.get(state);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (staffId ^ (staffId >>> 32));
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
		User other = (User) obj;
		if (staffId != other.staffId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [staffId=" + staffId + ", staffCode=" + staffCode + ", staffName=" + staffName + ", phoneNo="
				+ phoneNo + ", photoAddr=" + photoAddr + ", manage=" + manage + ", region=" + region
				+ ", lastLoginMark=" + lastLoginMark + ", lastLoginToken=" + lastLoginToken + ", lastLoginTime="
				+ lastLoginTime + ", state=" + state + ", roles=" + roles + "]";
	}
}
