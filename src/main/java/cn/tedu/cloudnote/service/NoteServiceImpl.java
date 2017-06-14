package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.util.LoginResult;
@Service("noteService")
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource(name="noteDAO")
	private NoteDAO dao;
	public LoginResult loadNotes(String notebookId) {
		List<Map> list=dao.findNotesByNotebookId(notebookId);
		LoginResult lr=new LoginResult();
		lr.setStatus(0);
		lr.setMsg("笔记加载成功");
		lr.setData(list);
		return lr;
	}
	public LoginResult loadNote(String noteId) {
		Note note=dao.findNoteByNoteId(noteId);
		LoginResult lr=new LoginResult();
		lr.setStatus(0);
		lr.setMsg("载入成功");
		lr.setData(note);
		return lr;
	}
	public LoginResult updateNote(String title, String noteId, String body) {
		Note note=new Note();
		note.setCn_note_body(body);
		note.setCn_note_id(noteId);
		note.setCn_note_title(title);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int index=dao.updateNote(note);
		LoginResult lr=new LoginResult();
		if(index>0){
			lr.setStatus(0);
			lr.setMsg("保存成功");
		}else{
			lr.setStatus(1);
			lr.setMsg("保存失败");
		}
		return lr;
	}

}
