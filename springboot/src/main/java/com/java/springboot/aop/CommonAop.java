package com.java.springboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
/**
 * 切面
 * @author tzh
 *
 */
@Component//将CommonAop放入spring ioc容器中，交由spring管理
@Aspect//定义一个切面
public class CommonAop {
	
	private static final String STARTS=new String(",切点信息:");
	/**
	 * 注解@Before说明在进入切点之前执行，value表明此切面扫描包的范围
	 * 后面@annotation是的自己定义的注解生效
	 * @param joinPoint 切点
	 * @param loggerManage 日志注解对象
	 */
	@Before(value = "within(com.java.springboot..*) && @annotation(loggerManage)")
	public void beforeLog(ProceedingJoinPoint  joinPoint,LoggerManage loggerManage){
		String start=new String("开始执行方法前:");
		
		System.out.println(start+loggerManage.descrption()+STARTS+joinPoint.getSignature().toLongString());
	}
	
	@AfterReturning(value="within(com.java.springboot..*) && @annotation(loggerManage)")
	public void afterLog(ProceedingJoinPoint  joinPoint,LoggerManage loggerManage){
		String start=new String("执行方法后:");
		System.out.println(start+loggerManage.descrption()+STARTS+joinPoint.getSignature().toLongString());
	}
	
	@AfterThrowing(value="within(com.java.springboot..*) && @annotation(loggerManage)")
	public void afterException(JoinPoint joinPoint,LoggerManage loggerManage){
		String start=new String("执行方法发生异常:");
		System.out.println(start+loggerManage.descrption()+STARTS+joinPoint.getSignature().toLongString());
	}
	
	@Around(value="within(com.java.springboot..*) && @annotation(aroundManage)")
	public Object doAroundService(ProceedingJoinPoint joinPoint,AroundManage aroundMange) {
		System.out.println("环绕通知的目标方法名为 ： "+joinPoint.getSignature().getName());
        try {
            Object object=joinPoint.proceed();
            return object;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return  null;
    }


}
