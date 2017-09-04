package com.ai.base.tool;

/**
 * 常量定义对象
 * @author hejg
 */
public class ConstantField {
    public static final String USER_BEAN = "SESSION_USER_BEAN";

    public static final String LOCK_CODE = "SESSION_LOCK_CODE";// 用户锁屏代码

    public static final String SESSION_ID = "AI_ESOP_SESSION_ID";// PC版本登陆后存于浏览器Cookie:TOKEN
    
    public static final String LOGIN_TOKEN = "AI-Login-Token";// APP版本登陆后存于本地Storage:TOKEN

    public static final String REQUEST_WAY = "AI-Requested-Way";// 请求方式 WEB,APP
    
    public static final String SHOW_ALERT_MSG = "SHOW_MSG";
}
