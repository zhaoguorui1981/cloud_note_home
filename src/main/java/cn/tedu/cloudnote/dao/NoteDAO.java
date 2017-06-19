package cn.tedu.cloudnote.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.tedu.cloudnote.entity.Note;
@Repository("noteDAO")
public interface NoteDAO {
	public List<Map> findNotesByNotebookId(String notebookId);
	public Note findNoteByNoteId(String noteId);
	public int updateNote(Note note);
	public int saveNote(Note note);
	public int updateNoteStatusId(String noteId);
	public int updateBookId(Map map);
}
