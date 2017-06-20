package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.dao.ShareDAO;
import cn.tedu.cloudnote.entity.Note;
import cn.tedu.cloudnote.entity.Share;
import cn.tedu.cloudnote.util.LoginResult;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("noteService")
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource(name="noteDAO")
	private NoteDAO dao;
	@Resource(name="shareDAO")
	private ShareDAO sdao;
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
	/* (non-Javadoc)
	 * @see cn.tedu.cloudnote.service.NoteService#addNote(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LoginResult addNote(String userId, String bookId, String title) {
		Note note=new Note();
		Long time=System.currentTimeMillis();
		note.setCn_note_create_time(time);
		note.setCn_note_id(NoteUtil.createId());
		note.setCn_note_last_modify_time(time);
		note.setCn_note_title(title);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		int index=dao.saveNote(note);
		LoginResult lr=new LoginResult();
		if(index>0){
			lr.setStatus(0);
			lr.setMsg("笔记添加成功");
			lr.setData(note);
		}else{
			lr.setStatus(1);
			lr.setMsg("笔记添加失败");
		}
		return lr;
	}
	public LoginResult deleteNote(String noteId) {
		int rows=dao.updateNoteStatusId(noteId);
		LoginResult lr=new LoginResult();
		if(rows>0){
			lr.setStatus(0);
			lr.setMsg("笔记删除成功");
		}else{
			lr.setStatus(1);
			lr.setMsg("笔记删除失败");
		}
		return lr;
	}
	public LoginResult moveNote(String bookId, String noteId) {
		Map map=new HashMap();
		map.put("bookId",bookId);
		map.put("noteId", noteId);
		int rows=dao.updateBookId(map);
		LoginResult lr=new LoginResult();
		if(rows>0){
			lr.setStatus(0);
			lr.setMsg("笔记移动成功");
		}else{
			lr.setStatus(1);
			lr.setMsg("笔记移动失败");
		}
		return lr;
	}
	public LoginResult shareNote(String noteId) {
		Note note=dao.findNoteByNoteId(noteId);
		LoginResult lr=new LoginResult();
		if(note==null){
			lr.setStatus(1);
			lr.setMsg("找不到该笔记");
			return lr;
		}
		if("2".equals(note.getCn_note_type_id())){
			lr.setStatus(2);
			lr.setMsg("该笔记已经被分享");
			return lr;
		}
		Share share=new Share();
		share.setCn_note_id(note.getCn_note_id());
		share.setCn_share_body(note.getCn_note_body());
		share.setCn_share_id(NoteUtil.createId());
		share.setCn_share_title(note.getCn_note_title());
		int rows=sdao.saveShareNote(share);
		
		if(rows>0){
			dao.updateNoteTypeId(noteId);
			lr.setStatus(0);
			lr.setMsg("分享笔记成功");
			lr.setData(share);
		}else{
			lr.setStatus(1);
			lr.setMsg("分享笔记失败");
		}
		return lr;
	}
	public LoginResult searchNote(String keyword) {
		String title="%";
		if(keyword != null && !"".equals(keyword)){
			title="%"+keyword+"%";
		}
		List<Share>list=sdao.findLikeKeyword(title);
		LoginResult lr=new LoginResult();
		lr.setStatus(0);
		lr.setMsg("搜索成功");
		lr.setData(list);
		return lr;
	}

}
