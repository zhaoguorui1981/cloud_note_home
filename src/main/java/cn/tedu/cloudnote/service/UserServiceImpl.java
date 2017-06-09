package cn.tedu.cloudnote.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.LoginResult;
import cn.tedu.cloudnote.util.NoteException;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("UserService")
public class UserServiceImpl implements Serializable, UserService {
	@Resource(name="UserDAO")
	private UserDAO userdao;
	public LoginResult checkLogin(String account, String password) {
		User user=userdao.findUserByName(account);
		//定义返回结果变量
		LoginResult lr=new LoginResult();
		String md5_pwd;
		try {
			md5_pwd=NoteUtil.md5(password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NoteException("密码加密异常", e);
		}
		if(user==null){//返回值为空,用户名不存在
			lr.setStatus(1);
			lr.setMsg("用户名不存在");
		}else if(!md5_pwd.equals(user.getCn_user_password())){//密码不正确
			lr.setStatus(2);
			lr.setMsg("密码错误");
		}else{//登陆成功
			lr.setStatus(0);
			lr.setMsg("登陆成功");
			lr.setData(user);
		}
		return lr;
	}
	public LoginResult regist(String account, String password, String nick) {
		try{
			LoginResult lr=new LoginResult();
			
			if(userdao.findUserByName(account)!=null){//返回值不为空,用户名已经被占用
				lr.setStatus(1);
				lr.setMsg("用户名被占用");
				return lr;
			}
			User user=new User();
			user.setCn_user_name(account);
			user.setCn_user_nick(nick);
			user.setCn_user_password(NoteUtil.md5(password));
			user.setCn_user_id(NoteUtil.createId());
			userdao.save(user);
			lr.setStatus(0);
			lr.setMsg("注册成功");
			return lr;
		}catch(Exception e){
			e.printStackTrace();
			throw new NoteException("加密异常", e);
		}
	}
}
