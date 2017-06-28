package cloud_note_home.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import cloud_note_home.init.BaseTest;
import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.dao.NotebookDAO;
import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.Notebook;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.service.UserService;

public class TestCase extends BaseTest{
	
	@Test
	public void test() throws SQLException{
		DataSource ds=ac.getBean("ds",DataSource.class);
		Connection con=ds.getConnection();
		System.out.println(con);
		con.close();
		SqlSessionFactory factory=ac.getBean("ssfb",SqlSessionFactory.class);
		System.out.println(factory.openSession());
	}
	@Test
	//测试UserDAO
	public void test1(){
		UserDAO userdao=ac.getBean("UserDAO",UserDAO.class);
		User user=userdao.findUserByName("demo");
		System.out.println(user);
	}
	@Test
	public void savatest(){
		UserDAO userdao=ac.getBean("UserDAO",UserDAO.class);
		User user=new User();
		user.setCn_user_id("123");
		user.setCn_user_name("zhaoguorui");
		user.setCn_user_nick("zgr");
		user.setCn_user_password("123456");
		userdao.save(user);
	}
	@Test
	public void registtest(){
		UserService us=ac.getBean("UserService",UserService.class);
		System.out.println(us.regist("liuxiaojing", "123456", "lxf"));
	}
	@Test
	public void notebookDAOtest(){
		NotebookDAO dao=ac.getBean("notebookDAO",NotebookDAO.class);
		List<Notebook>list=dao.findNotebookByName("demo");
		for(Notebook n:list){
			System.out.println(n);
		}
		
	}
	@Test
	public void noteDAOtest(){
		NoteDAO dao=ac.getBean("noteDAO",NoteDAO.class);
		List<Map>list=dao.findNotesByNotebookId("0037215c-09fe-4eaa-aeb5-25a340c6b39b");
		System.out.println(list);
		}
		
	}

