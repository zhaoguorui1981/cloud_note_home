package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloudnote.dao.NotebookDAO;
import cn.tedu.cloudnote.entity.Notebook;
import cn.tedu.cloudnote.util.LoginResult;
import cn.tedu.cloudnote.util.NoteUtil;
@Service("notebookService")
@Transactional
public class NotebookServiceImpl implements Serializable, NotebookService {
	@Resource(name="notebookDAO")
	private NotebookDAO dao;
	public LoginResult loadNotebook(String userId) {
		List<Notebook> list=dao.findNotebookById(userId);
		LoginResult lr=new LoginResult();
		lr.setStatus(0);
		lr.setMsg("笔记本载入成功");
		lr.setData(list);
		return lr;
	}
	public LoginResult addNotebook(String userId, String name) {
		Notebook book=new Notebook();
		book.setCn_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_name(name);
		book.setCn_user_id(userId);
		int index=dao.saveNotebook(book);
		LoginResult lr=new LoginResult();
		if(index>0){
			lr.setStatus(0);
			lr.setMsg("添加成功");
			lr.setData(book);
		}else{
			lr.setStatus(1);
			lr.setMsg("添加失败");
		}
		return lr;
	}
	public LoginResult renameNotebook(String bookId, String name) {
		Notebook book=new Notebook();
		book.setCn_notebook_id(bookId);
		book.setCn_notebook_name(name);
		int index=dao.renameByBookId(book);
		LoginResult lr=new LoginResult();
		if(index>0){
			lr.setStatus(0);
			lr.setMsg("名称修改成功");
		}else{
			lr.setStatus(1);
			lr.setMsg("名称修改失败");
		}
		return lr;
	}

}
