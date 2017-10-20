package com.ai.base.model.tmpl;

import java.io.Serializable;
import java.util.List;

/**
 * 填报模板
 * @author hejg
 *
 */
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;
	private String templateCode;//模板代码
	private String templateName;//模板名称;描述模板用途
	private String templateDesc;//模板描述;描述模板详情
	
	private List<Group> templateGroup;//模板分组
	
	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
	}

	public List<Group> getTemplateGroup() {
		return templateGroup;
	}

	public void setTemplateGroup(List<Group> templateGroup) {
		this.templateGroup = templateGroup;
	}

	@Override
	public String toString() {
		return "TemplateDefine [templateCode=" + templateCode + ", templateName="
				+ templateName + ", templateDesc=" + templateDesc+ ", templateGroup=" + templateGroup + "]";
	}
}
