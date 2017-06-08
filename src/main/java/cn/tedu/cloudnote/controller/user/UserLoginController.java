package cn.tedu.cloudnote.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.UserService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
@RequestMapping("/user")
public class UserLoginController {
	@Resource(name="UserService")
	private UserService userservice;
	@RequestMapping("/login.do")
	@ResponseBody
	public LoginResult execute(String acc,String pwd){
		LoginResult lr=userservice.checkLogin(acc, pwd);
		return lr;
	}
}
