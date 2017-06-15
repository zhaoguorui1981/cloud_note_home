package cn.tedu.cloudnote.controller.book;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloudnote.service.NotebookService;
import cn.tedu.cloudnote.util.LoginResult;

@Controller
public class RenameNotebookController {
	@Resource(name="notebookService")
	private NotebookService notebookService;
	@RequestMapping("/book/rename.do")
	@ResponseBody
	public LoginResult execute(String bookId,String name){
		LoginResult lr=notebookService.renameNotebook(bookId, name);
		return lr;
	}
}
