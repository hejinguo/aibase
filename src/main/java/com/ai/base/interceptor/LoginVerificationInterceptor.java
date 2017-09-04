package com.ai.base.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ai.base.model.core.User;
import com.ai.base.service.core.UserService;
import com.ai.base.tool.ConstantField;
import com.ai.base.tool.CookieTool;
import com.ai.base.tool.vo.ResultObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 登陆验证拦截器
 * @author hejg
 *
 */
public class LoginVerificationInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//最后执行，通常用于释放资源，处理异常。我们可以根据ex是否为空，来进行相关的异常处理。因为我们在平时处理异常时，都是从底层向上抛出异常，最后到了spring框架从而到了这个方法中。
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		//该方法在action执行后，生成视图前执行。在这里，我们有机会修改视图层数据。
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//该方法在action执行前执行，可以实现对数据的预处理，比如：编码、安全控制等。
		boolean logined = false;//默认为未登录,悲观模式
		boolean ajaxRequest = false;//默认为非Ajax请求
		String requestType  = request.getHeader("X-Requested-With");//XMLHttpRequest
		if(requestType == null){//WEB直接访问地址(非Ajax的请求)
			ajaxRequest = false;
			Object userBean = request.getSession().getAttribute(ConstantField.USER_BEAN);
			if (userBean != null) {
//				System.out.println("WEB直接访问 SESSION验证通过。");
				logined = true;
				UserManager.setUser((User)userBean);
			}else{
				Cookie cookie = CookieTool.getCookieByName(request, ConstantField.SESSION_ID);
				if(cookie != null){
					User user = userService.getUserByToken(cookie.getValue());
					if(user != null) {
//						System.out.println("WEB直接访问 COOKIE验证通过。");
						logined = true;
						UserManager.setUser(user);
					}
				}
			}
		}else{//Ajax访问(Ajax才甄别请求是APP或WEB的)
			ajaxRequest = true;
			String requestWay = request.getHeader(ConstantField.REQUEST_WAY);
			if("APP".equals(requestWay)){
				User user = userService.getUserByToken(request.getHeader(ConstantField.LOGIN_TOKEN));
				if(user != null) {
//					System.out.println("APP的AJAX访问 COOKIE验证通过。");
					logined = true;
					UserManager.setUser(user);
				}
			}else{
				Object userBean = request.getSession().getAttribute(ConstantField.USER_BEAN);
				if (userBean != null) {
//					System.out.println("PC的AJAX访问 SESSION验证通过。");
					logined = true;
					UserManager.setUser((User)userBean);
				}else{
					Cookie cookie = CookieTool.getCookieByName(request, ConstantField.SESSION_ID);
					if(cookie != null){
						User user = userService.getUserByToken(cookie.getValue());
						if(user != null) {
//							System.out.println("PC的AJAX访问 COOKIE验证通过。");
							logined = true;
							UserManager.setUser(user);
						}
					}
				}
			}
		}
		//判断如果是Ajax请求便返回JOSN,否则便跳转到未登陆认证描述页面
		if(!logined){
			if(ajaxRequest){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				ObjectMapper mapper = new ObjectMapper();
				out.println(mapper.writeValueAsString(new ResultObject<String>("NOT_LOGINED")));
				out.flush();
				out.close();
			}else{
				response.sendRedirect(request.getContextPath()+"/pages/base/login.html");
//				response.sendRedirect("http://10.109.209.100:9081/uac");
			}
			return false;
		}else{
			return super.preHandle(request, response, handler);
		}
	}
}