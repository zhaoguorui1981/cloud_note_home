package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface UserService {
	public LoginResult checkLogin(String account,String password);
	public LoginResult regist(String account,String password,String nick);
}
