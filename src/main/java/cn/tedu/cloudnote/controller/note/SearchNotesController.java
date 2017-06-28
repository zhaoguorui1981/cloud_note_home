package cn.tedu.cloudnote.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NoteService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class SearchNotesController {
	@Resource(name="noteService")
	private NoteService noteService;
	@RequestMapping("/note/searchnotes.do")
	@ResponseBody
	public LoginResult execute(String title,String statusid,String createTime,String modifyTime){
		LoginResult lr=noteService.searchNotes(title, statusid, createTime, modifyTime);
				return lr;
	}
}
