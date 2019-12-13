$(
	function(){
		var registerUserUrl='/IAS/useradmin/userRegister';
		alert("欢迎来到IAS管理系统注册页面");
		getUser();
		function getUser(){
			//用FormData对象封装数据
			$('#register').click(function(){
				//获取数据，实例化实体类
				var user={};
				user.name=$('#user-name').val();
				user.account=$('#user-account').val();
				user.password=$('#user-password').val();
				user.remark=$('#user-remark').val();//获取value的值
				user.gender=$('#user-gender').val();//获取value的值
				var formData=new FormData();
				formData.append('userStr',JSON.stringify(user));
				//将数据送到后台
				$.ajax({
					url: registerUserUrl,//传送的目标地址
					type: 'POST',//传送方式
					data:formData,//上传formdata封装的数据
					//dataType:*,返回的数据类型 默认值为字符串
					contentType: false,//jQuery要不要去设置Content-Type请求头
					processData: false,//jQuery要不要去处理发送的数据
					cache: false, //是否缓存
					success:function(data){
						if(data){
							alert(data+"   注册成功!")
							window.location.href="http://localhost:8080/IAS/useradmin/userlogon";
						}
						else{
							alert(data+"   注册失败!")
						}
					}	
				});
				
			});
		}
	}	
)