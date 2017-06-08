package cloud_note_home.test;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.User;

public class TestCase {
	private UserDAO userdao;
	@Before
	public void init() throws SQLException{
		ApplicationContext ac=new ClassPathXmlApplicationContext("conf/*.xml");
		DataSource ds=ac.getBean("ds",DataSource.class);
		userdao=ac.getBean("UserDAO",UserDAO.class);
	}
	@Test
	//测试UserDAO
	public void test1(){
		User user=userdao.findUserByName("demo");
		System.out.println(user);
	}
}
