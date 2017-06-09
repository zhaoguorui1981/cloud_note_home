package cn.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.UserService;
import cn.tedu.cloudnote.util.LoginResult;
@Controller
@RequestMapping("/user")
public class UserRegistController {
	@Resource(name="UserService")
	private UserService us;
	@RequestMapping("/regist.do")
	@ResponseBody
	public LoginResult execute(String name,String password,String nick){
		LoginResult lr=us.regist(name, password, nick);
		return lr;
	}
}
