package cn.tedu.cloudnote.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.UserDAO;
import cn.tedu.cloudnote.entity.User;
import cn.tedu.cloudnote.util.LoginResult;
@Service("UserService")
public class UserServiceImpl implements Serializable, UserService {
	@Resource(name="UserDAO")
	private UserDAO userdao;
	public LoginResult checkLogin(String account, String password) {
		User user=userdao.findUserByName(account);
		//定义返回结果变量
		LoginResult lr=new LoginResult();
		if(user==null){//返回值为空,用户名不存在
			lr.setStatus(1);
			lr.setMsg("用户名不存在");
		}else if(!password.equals(user.getCn_user_password())){//密码不正确
			lr.setStatus(2);
			lr.setMsg("密码错误");
		}else{//登陆成功
			lr.setStatus(0);
			lr.setMsg("登陆成功");
			lr.setData(user);
		}
		return lr;
	}

}
