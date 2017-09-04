package com.ai.base.interceptor;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.ai.base.model.core.User;
import com.ai.base.tool.ConstantField;

/**
 * Application Lifecycle Listener implementation class SessionAttributeListener
 */
//@WebListener
/**
 * Session属性变更监听器
 * @author hejg
 *
 */
public class SessionAttributeListener implements HttpSessionAttributeListener {

    /**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event) {
        if (ConstantField.USER_BEAN.equals(event.getName())) {
            UserManager.setUser((User) event.getValue());
        }
    }

    /**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (ConstantField.USER_BEAN.equals(event.getName())) {
            UserManager.setUser((User) event.getValue());
        }
    }

    /**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {
//    	if (ConstantField.USER_BEAN.equals(event.getName())) {
//    		UserManager.removeUser();
//    	}
    }
}
