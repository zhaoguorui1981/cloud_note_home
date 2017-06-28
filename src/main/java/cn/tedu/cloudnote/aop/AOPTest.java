package cn.tedu.cloudnote.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPTest {
	@Before("within(cn.tedu.cloudnote.controller..*)")
	public void test(){
		System.out.println("controller组件已启用");
	}
}
