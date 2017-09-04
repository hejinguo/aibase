package com.ai.base.tool.vo;

import java.util.Date;

/**
 * 数据统一反馈封装BEAN
 * @author hejg
 *
 * @param <T>
 */
public class ResultObject<T> {
	private boolean state;// 当state为true:表示本次处理过程成功,当state为fasle:表示本次处理过程失败
	private String code;// 当state返回false:code为补充的错误编码,当state返回true:code无强制要求可自定用途
	private T info;// 用于前后台交互的核心数据
	private Exception ex;// 失败时的错误信息

	/**
	 * 推荐反馈失败ERROR处理结果使用
	 * 
	 * @param code
	 */
	public ResultObject(String code) {
		super();
		this.state = false;
		this.code = code;
		this.ex = null;
		this.info = null;
	}

	/**
	 * 推荐反馈失败ERROR处理结果使用
	 * 
	 * @param code
	 * @param ex
	 */
	public ResultObject(String code, Exception ex) {
		super();
		this.state = false;
		this.code = code;
		this.ex = ex;
		this.info = null;
	}
	
	/**
	 * 推荐反馈失败JAVA_EXCEPTION结果使用
	 * @param ex
	 */
	public ResultObject(Exception ex) {
		super();
		this.state = false;
		this.code = "JAVA_EXCEPTION";
		this.ex = ex;
		this.info = null;
	}

	/**
	 * 任何反馈场景均可适用(失败时无法反馈错误详情信息和编码)
	 * 
	 * @param state
	 * @param info
	 */
	public ResultObject(boolean state, T info) {
		super();
		this.state = state;
		this.info = info;
		this.code = null;
		this.ex = null;
	}

	/**
	 * 任何反馈场景均可适用(失败时无法反馈错误详情信息)
	 * 
	 * @param state
	 * @param code
	 * @param info
	 */
	public ResultObject(boolean state, String code, T info) {
		super();
		this.state = state;
		this.code = code;
		this.info = info;
		this.ex = null;
	}

	/**
	 * 任何反馈场景均可适用
	 * 
	 * @param state
	 * @param code
	 * @param info
	 * @param ex
	 */
	public ResultObject(boolean state, String code, T info, Exception ex) {
		super();
		this.state = state;
		this.code = code;
		this.info = info;
		this.ex = ex;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public String getEx() {
		if(ex != null){
			long time = new Date().getTime();
			System.out.println("server happen exception:"+time);
			System.out.println("server exception message:"+ex.getMessage());
			ex.printStackTrace();
			return "server happen exception:"+time;
		}else{
			return "";
		}
//		return ex == null ? "" : ex.toString();
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}
}
