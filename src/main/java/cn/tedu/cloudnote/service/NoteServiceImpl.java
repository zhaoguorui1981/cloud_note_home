package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NoteDAO;
import cn.tedu.cloudnote.util.LoginResult;
@Service("noteService")
public class NoteServiceImpl implements Serializable, NoteService {
	@Resource(name="noteDAO")
	private NoteDAO dao;
	public LoginResult loadNote(String notebookId) {
		List<Map> list=dao.findNoteByNotebookId(notebookId);
		LoginResult lr=new LoginResult();
		lr.setStatus(0);
		lr.setMsg("笔记加载成功");
		lr.setData(list);
		return lr;
	}

}
