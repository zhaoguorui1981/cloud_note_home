package cn.tedu.cloudnote.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloudnote.dao.NotebookDAO;
import cn.tedu.cloudnote.entity.Notebook;
import cn.tedu.cloudnote.util.LoginResult;
@Service("notebookService")
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

}
