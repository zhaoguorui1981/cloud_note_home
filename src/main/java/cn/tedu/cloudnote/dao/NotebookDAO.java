package cn.tedu.cloudnote.dao;

import java.util.List;

import cn.tedu.cloudnote.entity.Notebook;

public interface NotebookDAO {
	public List<Notebook> findNotebookById(String id);
	public List<Notebook> findNotebookByName(String name);
}
