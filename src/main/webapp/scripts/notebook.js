function loadnotebooks(){
	//获取请求参数
	var userId=getCookie("userId");
	if(userId){
	//校验请求参数
	//发送ajax请求
	$.ajax({
		url:base_path+"/book/loadNotebooks.do",
		type:"post",
		data:{"userId":userId},
		dataType:"json",
		success:function(result){
			if(result.status==0){//返回成功
				var notebooks=result.data;//获得笔记本数据
				for (var i = 0; i < notebooks.length; i++) {//遍历笔记本
					var name=notebooks[i].cn_notebook_name;//获得笔记本名字
					var notebookId=notebooks[i].cn_notebook_id;
					var sli=""
						//插入笔记本列表
						sli+='<li class="online">';
						sli+='	<a>';
						sli+='		<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'; 
						sli+=name
						sli+='	</a>';
						sli+='</li>';
					var $li=$(sli);
						$li.data("notebookId",notebookId)
					$('#book_ul').append($li);
				}
			}
		},
		error:function(){alert("加载笔记本列表失败")},
	})
	}else{
		window.location.href="log_in.html";
	}
}