package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface NotebookService {
	public LoginResult loadNotebook(String userId);
	public LoginResult addNotebook(String userId,String name);
	public LoginResult renameNotebook(String bookId,String name);
}
