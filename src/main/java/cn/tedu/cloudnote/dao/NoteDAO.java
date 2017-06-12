package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository("noteDAO")
public interface NoteDAO {
	public List<Map> findNoteByNotebookId(String notebookId);
}
