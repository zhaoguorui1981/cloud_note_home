package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface NoteService {
	public LoginResult loadNotes(String notebookId);
	public LoginResult loadNote(String noteId);
	public LoginResult updateNote(String title,String noteId,String body);
}
