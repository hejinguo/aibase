package com.ai.base.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * 操作按钮
 * @author hejg
 *
 */
public class Button implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classs;//类样式,暂时不提供行内样式
	private String icon;//按钮图标
	private String text;//按钮内容
	private String type;//Dialog,Window,Redirect,Ajax,Function
	private boolean confirm;//是否先提醒
	private String url;//地址
	private List<String> params;//传递参数
	
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isConfirm() {
		return confirm;
	}
	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getParams() {
		return params;
	}
	public void setParams(List<String> params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "Button [classs=" + classs + ", icon=" + icon + ", text=" + text + ", type="
				+ type + ", url=" + url + ", params=" + params + "]";
	}
}
