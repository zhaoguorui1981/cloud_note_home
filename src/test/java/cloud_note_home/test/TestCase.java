package cloud_note_home.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import cloud_note_home.init.BaseTest;
import cn.tedu.cloudnote.dao.EmpDAO;
import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.dao.NotebookDAO;
import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.Emp;
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
	@Test
	public void AssociationDAOtest(){
		NotebookDAO dao=ac.getBean("notebookDAO",NotebookDAO.class);
		Notebook book=dao.findById("e46239d6-4f54-426c-a448-f7a0d45f9425");
		System.out.println(book);
		System.out.println(book.getUser());
	}
	@Test
	public void AssociationDAOtest2(){
		NotebookDAO dao=ac.getBean("notebookDAO",NotebookDAO.class);
		List<Notebook>list=dao.findAllBook();
		for(Notebook book:list){
			System.out.println(book);
			System.out.println(book.getUser());
		}
		System.out.println(list.size());
	}
	@Test
	public void CollectionDAOtest1(){
		UserDAO dao=ac.getBean("UserDAO",UserDAO.class);
		User user=dao.findByUserId("ea09d9b1-ede7-4bd8-b43d-a546680df00b");
		for(Notebook book:user.getBooks()){
			System.out.println(book);
		}
	}
	@Test
	public void CollectionDAOtest2(){
		UserDAO dao=ac.getBean("UserDAO",UserDAO.class);
		List<User>list=dao.findAllUser();
		for(User u:list){
			String bookname="";
			for(Notebook b:u.getBooks()){
				bookname+=b.getCn_notebook_name();
				bookname+=",";
			}
			System.out.println("用户名:"+u.getCn_user_name()+"\t"+"笔记本数量:"+u.getBooks().size());
			if(bookname.length()>0){
				System.out.println("笔记本名称为:"+bookname.substring(0,bookname.length()-1));
			}else{
				System.out.println("无任何笔记本");
			}
		}
	}
	@Test
	public void EmpDAOtest(){
		EmpDAO dao=ac.getBean("empDAO",EmpDAO.class);
		Emp e=new Emp();
		e.setName("myh");
		dao.saveEmp(e);
		System.out.println(e.getNo());
	}
}

