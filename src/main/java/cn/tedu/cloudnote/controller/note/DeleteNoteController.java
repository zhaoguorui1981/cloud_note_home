package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class DeleteNoteController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/delete.do")
	@ResponseBody
	public LoginResult execute(String noteId){
		LoginResult lr=noteService.deleteNote(noteId);
		return lr;
	}
}
