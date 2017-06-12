package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Notebook;
@Repository("notebookDAO")
public interface NotebookDAO {
	public List<Notebook> findNotebookById(String id);
	public List<Notebook> findNotebookByName(String name);
}
