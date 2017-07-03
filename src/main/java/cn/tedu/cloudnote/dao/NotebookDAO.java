package cn.tedu.cloudnote.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Notebook;
@Repository("notebookDAO")
public interface NotebookDAO {
	public List<Notebook> findAllBook();
	public Notebook findById(String bookId);
	public List<Notebook> findNotebookById(String id);
	public List<Notebook> findNotebookByName(String name);
	public int saveNotebook(Notebook book);
	public int renameByBookId(Notebook book);
}
