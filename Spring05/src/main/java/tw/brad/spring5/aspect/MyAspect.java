package tw.brad.spring5.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
	
	@Pointcut("execution(* tw.brad.spring05.controller..*(..))")
	public void doControllerMethod() {
		
	}
	
	public void doBefore() {}
	
}
