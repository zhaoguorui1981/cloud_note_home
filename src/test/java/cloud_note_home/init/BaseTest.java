package cloud_note_home.init;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseTest {
	protected ApplicationContext ac;
	@Before
	public void init() throws SQLException{
		ac=new ClassPathXmlApplicationContext("conf/*.xml");
	}
}
