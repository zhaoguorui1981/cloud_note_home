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