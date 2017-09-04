package com.ai.base.interceptor;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ai.base.tool.vo.ResultObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 非法字符过滤拦截器
 * @author hejg
 *
 */
public class CharacterFilterInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        boolean accessFlag = true;
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterValue = request.getParameter(parameterName);
            if (parameterValue.contains("'")) {//parameterValue.contains(">") ||
                accessFlag = false;
                break;
            }
        }
        if (!accessFlag) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            ObjectMapper mapper = new ObjectMapper();
            out.println(mapper.writeValueAsString(new ResultObject<String>("CHARACTER_WRONGFUL")));
            out.flush();
            out.close();
            return false;
        } else {
            return super.preHandle(request, response, handler);
        }
    }
}
