package cn.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class NotebookController {
	@Resource(name="notebookService")
	private NotebookService notebookService;
	@RequestMapping("/book/loadNotebooks.do")
	@ResponseBody
	public LoginResult execute(String userId){
		LoginResult lr=notebookService.loadNotebook(userId);
		return lr;
	}
}
