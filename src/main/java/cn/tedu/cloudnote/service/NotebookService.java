package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface NotebookService {
	public LoginResult loadNotebook(String userId);
}
