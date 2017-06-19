//弹出移动note对话框,加载select列表
function alertMoveNoteWindow(){
	$('#can').load("alert/alert_move.html",function(){
		var lis=$('#book_ul li');
		for (var i = 0; i < lis.length; i++) {
			var name=$(lis[i]).text().trim();
			var bookId=$(lis[i]).data('notebookId');
			var opt='';
			opt+='<option value='+bookId+'>'+name+'</option>';
			$('#moveSelect').append($(opt));
		}
	})
	$('.opacity_bg').show();
}
//弹出删除note对话框
function alertDeleteNoteWindow(){
	$('#can').load("alert/alert_delete_note.html")
	$('.opacity_bg').show();
}
//弹出note编辑按钮
function showMenuBar(){
	$('#note_ul div').hide();
	$(this).parent().next().slideDown(500);
	$('#note_ul').find('a').removeClass();
	$(this).parent().addClass('checked');
	return false;
}
//关闭note编辑按钮
function closeMenuBar(){
	$('#note_ul div').hide();
}
//调用增加笔记对话框
function addNoteWindow(){
	var lis=$('#book_ul a.checked')
	if(lis.length==0){
		alert("请选择笔记本");
	}else{
	$('#can').load("alert/alert_note.html")
	$('.opacity_bg').show();
	}
}
//调用增加笔记本对话框
function addNotebookWindow(){
	$('#can').load("alert/alert_notebook.html")
	$('.opacity_bg').show();
}
//关闭对话框
function closeWindow(){
	$('#can').empty();
	$('.opacity_bg').hide();
}
//调用笔记本改名对话框
function renameNotebookWindow(){
	$('#can').load("alert/alert_rename.html")
	$('.opacity_bg').show();
}