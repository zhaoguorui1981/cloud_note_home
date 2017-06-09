//定义登录函数
	function login(){
		//获取请求参数
		var acc=$('#count').val().trim();
		var pwd=$('#password').val().trim();
		$('#count_span').empty();
		$('#password_span').empty();
		//检测参数格式
		var eswitch=true; //设置检测开关
		if(!acc){
			$('#count_span').html("用户名为空").css({"color":"red",
														"font-size":"5px"});
			eswitch=false;
		}
		if(!pwd){
			$('#password_span').html("密码为空").css({"color":"red",
												"font-size":"5px"});
			eswitch=false;
		}
		if(eswitch){
			$.ajax({
				url:base_path+"/user/login.do",
				type:"post",
				data:{"acc":acc,"pwd":pwd},
				dataType:"json",
				success:function(result){
					var status=result.status
					switch(status){
						case 0:
							window.location.href="edit.html";
							break;
						case 1:
							$('#count_span').html(result.msg).css({"color":"red",
								"font-size":"5px"});
							break;
						case 2:
							$('#password_span').html(result.msg).css({"color":"red",
								"font-size":"5px"});
							break;
					}
				},
				error:function(){
					alert("登录异常");
				},
			});
		}
		
	}
	