$(
	function(){
		var logonUserUrl='/IAS/useradmin/userLogon';
		var registerUserUrl='/IAS/useradmin/userregister'
		alert("欢迎来到登录页面");
		getUser();
		function getUser(){
			//用FormData对象封装数据
			$('#logon').click(function(){
				//获取数据，实例化实体类
				var user={};
				user.account=$('#user-account').val();
				user.password=$('#user-password').val();
				var formData=new FormData();
				formData.append('userStr',JSON.stringify(user));
				//将数据送到后台
				$.ajax({
					url: logonUserUrl,//传送的目标地址
					type: 'POST',//传送方式
					data:formData,//上传formdata封装的数据
					//dataType:*,返回的数据类型 默认值为字符串
					contentType: false,//jQuery要不要去设置Content-Type请求头
					processData: false,//jQuery要不要去处理发送的数据
					cache: false, //是否缓存
					success: function(data){//成功回调
						
						//window.location.href = "index.html";
						if(data==true){
							alert("欢迎来到图片管理系");
							window.location.href="http://localhost:8080/IAS/useradmin/index";
						}
						else{
							alert("你输入的账号或密码有误，请重新输入！");
						}
							
						//在一个新的页面中打开
						//var newWin = window.open('', '_blank');
						//newWin.document.write(data);
					}
				});
			});
			$('#register').click(function(){
				alert("正在进入注册页面");
				$.ajax({
					url: registerUserUrl,//传送的目标地址
					type: 'GET',//传送方式
					contentType: false,//jQuery要不要去设置Content-Type请求头
					processData: false,//jQuery要不要去处理发送的数据
					cache: false, //是否缓存
					success: function(){//成功回调
						    window.location.href="http://localhost:8080/IAS/useradmin/userregister";
						//document.write(data);
					}
				});
			});
		}
	}	
)