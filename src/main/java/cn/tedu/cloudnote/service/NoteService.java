package cn.tedu.cloudnote.service;

import cn.tedu.cloudnote.util.LoginResult;

public interface NoteService {
	public LoginResult searchNotes(String title,String statusid,String createTime,String modifyTime);
	public LoginResult loadNotes(String notebookId);
	public LoginResult loadNote(String noteId);
	public LoginResult updateNote(String title,String noteId,String body);
	public LoginResult addNote(String userId,String bookId,String title);
	public LoginResult deleteNote(String noteId);
	public LoginResult moveNote(String bookId,String noteId);
	public LoginResult shareNote(String noteId);
	public LoginResult searchNote(String keyword);
}
