package cloud_note_home.test;

import java.util.List;

import org.junit.Test;

import cloud_note_home.init.BaseTest;
import cn.tedu.cloudnote.dao.NotebookDAO;
import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.Notebook;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.service.UserService;

public class TestCase extends BaseTest{
	
	
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
	public void noteDAOtest(){
		NotebookDAO dao=ac.getBean("notebookDAO",NotebookDAO.class);
		List<Notebook>list=dao.findNotebookByName("demo");
		for(Notebook n:list){
			System.out.println(n);
		}
		
	}
}
