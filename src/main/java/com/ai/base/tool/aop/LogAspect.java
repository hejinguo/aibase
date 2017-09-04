package com.ai.base.tool.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.ai.base.interceptor.UserManager;
import com.ai.base.model.core.User;

@Component
@Aspect
public class LogAspect {
//	@Autowired
//	private LogService logService;

	/**
	 * 记录方法访问日志
	 */
//	@Pointcut("execution(* com.ai.emop.controller.base.BaseController.*(..))")
	@Pointcut("execution(* com.ai.emop.controller..*.*(..))")
	public void methodExecution() {}

	
	@After("methodExecution()")
	public void logMethodExecutionRecord(JoinPoint joinPoint) {
		User user = UserManager.getUser();
		if(user != null){
//			joinPoint.getArgs();
			String content = joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName();
			System.out.println("LogAspect " + content);
//			OptLog log = new OptLog();
//			log.setStaffId(user.getStaffId());
//			log.setLogType(content.endsWith("BaseController.login") ? "登陆" : "WEB访问");
//			log.setContent(content);
//			log.setOpTime(new Date());
//			logService.recordOptLog(log);
		}
//		Object[] args = joinPoint.getArgs();
//		for (Object object : args) {
//			System.out.println(object);
//		}
//		System.out.println("log Ending method: "  + joinPoint.getTarget().getClass().getName() + "."  + joinPoint.getSignature().getName());  
	}
}
