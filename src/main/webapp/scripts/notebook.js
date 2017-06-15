//为笔记本改名
function renameNotebook(){
	//获取请求参数
	var bookName=$('#input_notebook_rename').val().trim();
	var bookId=$('#book_ul a.checked').parent().data("notebookId");
	//校验请求参数
	var ok=true;
	if(!bookName){//笔记本名称不能为空
		ok=false;
		$('#renamenotebook_msg').html('笔记本名称不能为空').css({"color":"red"});
	}
	if(!bookId){//笔记ID不能为空
		ok=false;
		alert("请选择笔记本");
	}
	//不能与原笔记本名称相同
	var lis=$('#book_ul').children('li');
	for (var i = 0; i < lis.length; i++) {
		var existName=$(lis[i]).text().trim();
		if(bookName==existName){
			ok=false;
			$('#renamenotebook_msg').html('该名已被使用').css({"color":"red"});
			break;
		}
	}
	//校验成功,发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/book/rename.do",
			type:"post",
			data:{"name":bookName,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					 closeWindow();
					 var si='';
					 si+='<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'; 
					 si+=bookName
					 $('#book_ul a.checked').html(si);
					 alert(result.msg)
				}
				
			},
			error:function(){alert("笔记本更名异常")}
		})
	}
}
			
//加载笔记本列表
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
					createBookLi(name,notebookId)
				}
			}
		},
		error:function(){alert("加载笔记本列表失败")},
	})
	}else{
		window.location.href="log_in.html";
	}
}

//创建笔记本
function addNotebook(){
	//获取请求参数
	var userId=getCookie("userId");
	var bookName=$('#input_notebook').val().trim();
	var ok=true;
	//校验请求参数
	$('#createnotebook_msg').html('');
	if(!bookName){//名称不能空
		ok=false;
		$('#createnotebook_msg').html('笔记本名称不能为空').css({'color':'red'});
	}
	if(!userId){//账号ID不能空
		ok=false;
		alert("获取账号失败,请重新登录");
		window.location.href='log_in.html';
	}
	//校验是否有重名笔记本
	var lis=$('#book_ul').children('li')
	for (var i = 0; i < lis.length; i++) {
		var exsitName=$(lis[i]).text().trim();
		if(bookName==exsitName){
			ok=false;
			$('#createnotebook_msg').html('有重名笔记本').css({'color':'red'});
			break;
		}
	}
	//校验成功,发送ajax请求
	if(ok){
		$.ajax({
			url:base_path+"/book/add.do",
			type:"post",
			data:{"userId":userId,"bookName":bookName},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeWindow();
					var bookName=result.data.cn_notebook_name;
					var bookId=result.data.cn_notebook_id;
					createBookLi(bookName,bookId)
					alert(result.msg)
				}
			},
			error:function(){alert("添加笔记本失败")}
		})
	}
	
}
//封装创建笔记本li方法
function createBookLi(name,notebookId){
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