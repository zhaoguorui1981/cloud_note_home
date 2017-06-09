package cloud_note_home.init;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloudnote.dao.UserDAO;

public class BaseTest {
	protected ApplicationContext ac;
	@Before
	public void init() throws SQLException{
		ac=new ClassPathXmlApplicationContext("conf/*.xml");
	}
}
