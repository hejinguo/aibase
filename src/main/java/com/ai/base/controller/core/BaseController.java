package com.ai.base.controller.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.base.model.core.User;
import com.ai.base.service.core.UserService;
import com.ai.base.tool.ConstantField;
import com.ai.base.tool.CookieTool;
import com.ai.base.tool.vo.ResultObject;

public class BaseController {
	@Autowired
	private UserService userService;

	/**
	 * <b>集成登陆</b><br/>
	 * <i>若token合法将返回不为null的User对象,若token不合法将返回null值</i>
	 * 
	 * @return
	 */
	protected User _baseInteLogin(String token, HttpServletRequest request, HttpServletResponse response) {
		User user = userService.getUserByToken(token);
		if (user != null) {
			request.getSession().invalidate();
			request.getSession().setAttribute(ConstantField.USER_BEAN, user);
			CookieTool.addCookie(response, ConstantField.SESSION_ID, user.getLastLoginToken());
		}
		return user;
	}

	/**
	 * <b>集成退出</b><br/>
	 * <i>这里的集成退出系统，是指任何一个子系统调用此方法都将清理Token导致全部兄弟系统下线</i>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	protected ResultObject<Object> _baseInteLogout(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getSession().invalidate();
			CookieTool.removeCookie(response, ConstantField.SESSION_ID);
			return new ResultObject<Object>(true, "HTTP_CODE_200", null);
		} catch (Exception e) {
			return new ResultObject<Object>(e);
		}
	}
}
