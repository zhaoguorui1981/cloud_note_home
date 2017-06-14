package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class LoadNoteController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/load.do")
	@ResponseBody
	public LoginResult execute(String noteId){
		LoginResult lr=noteService.loadNote(noteId);
		return lr;
	}
}
