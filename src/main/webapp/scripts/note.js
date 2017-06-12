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
						$li.add("noteId",noteId);
						$('#note_ul').append($li);
				} 
			}
		},
		error:function(){alert("加载笔记列表失败")},
	})

}