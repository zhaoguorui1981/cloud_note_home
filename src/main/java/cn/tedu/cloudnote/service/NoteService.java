package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface NoteService {
	public LoginResult loadNote(String notebookId);
}
