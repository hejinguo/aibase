package com.ai.base.controller.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.base.model.core.User;
import com.ai.base.tool.StringUtils;

/**
 * Created by hejg on 2017/5/10.
 */
@RestController
@RequestMapping("/base")
public class IntegrateController extends BaseController{
	
	/**
	 * 集成登陆
	 * @param token
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/inteLogin")
	public Object integrateLogin(@RequestParam(required=true)String token, String model, //@RequestParam(required=true)
			HttpServletRequest request, HttpServletResponse response) {
		User user = super._baseInteLogin(token, request, response);
		if(user != null){
			if(StringUtils.isEmpty(model)){
				return new ModelAndView("redirect:/pages/base/main.html");
			}else{
				return new ModelAndView("redirect:/pages/"+model+".html");
			}
		}else{
			return new ModelAndView("redirect:/pages/error/401.html");
		}
	}
    
	/**
	 * 集成退出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/inteLogout")
    public Object integrateLogout(HttpServletRequest request,HttpServletResponse response){
    	return super._baseInteLogout(request, response);
    }
}