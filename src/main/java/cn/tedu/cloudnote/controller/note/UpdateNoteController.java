package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class UpdateNoteController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/update.do")
	@ResponseBody
	public LoginResult execute(String title,String noteId,String body){
		LoginResult lr=noteService.updateNote(title, noteId, body);
		return lr;
	}
}
