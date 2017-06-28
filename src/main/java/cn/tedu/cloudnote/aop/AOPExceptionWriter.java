package cn.tedu.cloudnote.aop;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPExceptionWriter {
	@AfterThrowing(throwing="e",pointcut="within(cn.tedu.cloudnote..*)")
	public void ExceptionWriter(Exception e){
		try {
			FileWriter fw=new FileWriter("F:\\a.txt",true);
			PrintWriter pw=new PrintWriter(fw);
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			String time=sdf.format(new Date());
			pw.println("*************************************");
			pw.println("**"+e);
			pw.println("**"+time);
			pw.println("******************异常详情*************");
			e.printStackTrace(pw);
			pw.close();
			fw.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
