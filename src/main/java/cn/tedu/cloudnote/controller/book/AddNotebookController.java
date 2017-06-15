package cn.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class AddNotebookController {
	@Resource(name="notebookService")
	private NotebookService notebookService;
	@RequestMapping("/book/add.do")
	@ResponseBody
	public LoginResult execute(String userId,String bookName){
		LoginResult lr=notebookService.addNotebook(userId, bookName);
		return lr;
		
	}
}
