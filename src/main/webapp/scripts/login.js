	$(function(){//页面加载后调用
		//定义登录按钮单击事件
		$('#login').click(login);
		$('#regist_button').click(regist);
	})
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
							var userId=result.data.cn_user_id;
							addCookie("userId",userId,2);
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
	//定义注册函数
	function regist(){
		//获取各项请求参数
		var name=$('#regist_username').val();
		var nick=$('#nickname').val();
		var password=$('#regist_password').val();
		var f_password=$('#final_password').val();
		//进行请求参数检查
		var ok=true;
		$('#warning_1 span').empty();
		$('#warning_2 span').empty();
		$('#warning_3 span').empty();
		if(!name){
			var ok=false;
			$('#warning_1').show();
			$('#warning_1 span').html('用户名为空');
		}
		if(!password){
			var ok=false;
			$('#warning_2').show();
			$('#warning_2 span').html('密码为空');
		}else if(password.length<6){
			var ok=false;
			$('#warning_2').show();
			$('#warning_2 span').html('密码过短');
		}
		if(!f_password){
			var ok=false;
			$('#warning_3').show();
			$('#warning_3 span').html('确认密码为空');
		}else if(f_password!=password){
			var ok=false;
			$('#warning_3').show();
			$('#warning_3 span').html('密码输入不一致');
		}
		if(ok){
			$.ajax({
				url:base_path+"/user/regist.do",
				type:"post",
				data:{"name":name,"nick":nick,"password":password},
				dataType:"json",
				success:function(result){
						var status=result.status;
						switch(status){
							case 0: alert(result.msg);
									$('#back').click();
									break;
							case 1: $('#warning_1').show();
									$('#warning_1 span').html(result.msg);
									break;
						}
				},
				error:function(){
					alert(1);
				},
			});
		}
	}