//移动note至其他笔记本
function moveNote(){
	//获取请求参数	
	var $li=$('#note_ul a.checked').parent();
	var noteId=$li.data('noteId');
	var bookId=$('#moveSelect').val();
	//校验请求参数
	var ok=true;
	if(!noteId){
		alert("请选择笔记");
		ok=false;
	}
	if(!bookId || bookId=='none'){
		$('#movenote_msg').html('请选择笔记本').css({"color":"red"})
		ok=false;
	}
	if(ok){
		$.ajax({
			url:base_path+"/note/move.do",
			type:"post",
			data:{"noteId":noteId,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeWindow();
					$li.remove();
					alert(result.msg);
				}
			},
			error:function(){alert("移动笔记异常")}
		})
	}
}		
//删除当前note
function deleteNote(){
	//获取请求参数
	var $li=$('#note_ul a.checked').parent();
	var noteId=$li.data('noteId');
	//校验请求参数
	var ok=true;
	if(!noteId){
		alert("请选择笔记");
		ok=false;
	}
	//发送请求参数
	if(ok){
		$.ajax({
			url:base_path+"/note/delete.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeWindow();
					$li.remove();
					alert(result.msg);
				}
			},
			error:function(){alert("删除笔记异常")}
		})
	}
}
//添加笔记标题
function addNote(){
		//获取请求参数
		var noteTitle=$('#input_note').val().trim();
		var userId=getCookie("userId");
		var notebookId=$('#book_ul a.checked').parent().data("notebookId");
		var ok=true;
		//校验请求参数
		if(!noteTitle){//笔记标题不能为空
			ok=false;
			$('#createnote_msg').html("笔记标题为空").css({"color":"red"});			
		}
		if(!notebookId){//笔记本ID不能为空
			ok=false;
			alert("请选择笔记本");
		}
		if(!userId){//用户ID不能为空
			ok=false;
			alert("系统错误,请重新登录");
			window.location.href="log_in.html";
		}
		//校验重名笔记标题
		var lis=$('#note_ul').children('li');
		for (var i = 0; i < lis.length; i++) {
			var existTitle=$(lis[i]).text().trim();
			if(noteTitle==existTitle){
				ok=false;
				$('#createnote_msg').html("笔记名称已存在").css({"color":"red"});
				break;
			}
		}
		//校验结束,发送ajax请求
		if(ok){
			$.ajax({
				url:base_path+"/note/add.do",
				type:"post",
				data:{"title":noteTitle,"userId":userId,"bookId":notebookId},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						closeWindow()
						var noteId=result.data.cn_note_id;
						var title=result.data.cn_note_title;
						createNoteLi(title,noteId);
					}
				},
				error:function(){alert("笔记添加失败")},
			})
		}
	}
			

//根据笔记本ID查找笔记列表
function loadNotes(){
	//去除样式
	$("#book_ul").find('a').removeClass();
	//高亮单击笔记本
	$(this).find('a').addClass("checked");
	//获取请求参数notebookId
	var notebookId=$(this).data("notebookId");
	//发送ajax请求
	$.ajax({
		url:base_path+"/note/loadNotes.do",
		type:"post",
		data:{"notebookId":notebookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes=result.data;//获取返回笔记集合
				$('#note_ul').empty();//清空笔记列表
				for (var i = 0; i < notes.length; i++) {
					var notetitle=notes[i].cn_note_title;//获取笔记名称
					var noteId=notes[i].cn_note_id;//获取笔记ID
					//插入笔记
					createNoteLi(notetitle,noteId);
				} 
			}
		},
		error:function(){alert("加载笔记列表失败")},
	})

}
//根据笔记ID加载笔记内容和标题
function loadNote(){
	//高亮选中项
	$('#note_ul').find('a').removeClass();
	$(this).find('a').addClass('checked');
	//获取请求参数
	var noteId=$(this).data("noteId");
	//校验参数
	if(noteId){
		$.ajax({
			url:base_path+"/note/load.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var title=result.data.cn_note_title;
					var body=result.data.cn_note_body;
					$('#input_note_title').val(title);
					if(body){
						um.setContent(body);
					}else{
						um.setContent("");
					}
				}
			},
			error:function(){alert("加载笔记失败")}
		})
	}else{
		alert("系统繁忙,请重新登录")
	}
}

//笔记修改更新
function updateNote(){//更新笔记
	//获取请求参数
	var title=$('#input_note_title').val();
	var body=um.getContent();
	var noteId=$('#note_ul a.checked').parent().data("noteId");
	//校验请求参数
	if(!noteId){//需要选择笔记
		alert("请选择笔记");
	}else if(!title){//标题不能为空
		$('#updatenote_msg').html("标题为空").css({"color":"red","font-size":'10px'})
	}else{
		//发送ajax请求
		$.ajax({
			url:base_path+"/note/update.do",
			type:"post",
			data:{"title":title,"body":body,"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					console.log(result);
					alert(result.msg);
					var sli='';
					sli+='<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
					sli+= title;
					sli+='<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					sli+='<i class="fa fa-chevron-down"></i>';
					sli+='</button>';
					$('#note_ul a.checked').html(sli)
				}else{
					alert(result.msg)
				}
			},
			error:function(){alert("笔记保存失败")}
		})
	}
}


//封装创建笔记li方法
function createNoteLi(notetitle,noteId){
	var sli=""
		sli+='<li class="online">';
		sli+='		<a>';
		sli+='			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>';
		sli+=           notetitle;
		sli+='			<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
		sli+='				<i class="fa fa-chevron-down"></i>';
		sli+='			</button>';
		sli+='		</a>';
		sli+='		<div class="note_menu" tabindex="-1">';
	    sli+='			<dl>';
		sli+='				<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
		sli+='				<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
		sli+='				<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
		sli+='			</dl>';
		sli+='	    </div>';
		sli+='</li>';
	var $li=$(sli);
	//为笔记绑定ID
		$li.data("noteId",noteId);
		$('#note_ul').append($li);
}