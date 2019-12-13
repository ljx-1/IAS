/**

 * 
 */
$(
	function(){
		var registerImage='/IAS/imageadmin/registerImage';
		getImageInitInfo();
		function getImageInitInfo(){
			$.getJSON('/IAS/useradmin/currentuser',function(data){
				alert("使用了getJSON函数");
				alert(data.currentUser.id);
				$('#user-id').val(data.currentUser.id);
			});
			//用FormData对象封装数据
			$('#submit').click(function(){
				//获取数据,转化成json数据
				var imageInfo={};
				imageInfo.userId=$('#user-id').val();
				imageInfo.category=$('#image-category').val();
				var image=$('#image')[0].files[0];
				var formData=new FormData();
				formData.append('image',image);
				formData.append('imageInfo',JSON.stringify(imageInfo));
				//将数据送到后台
				$.ajax({
					url: registerImage,//传送的目标地址
					type: 'POST',//传送方式
					data:formData,//上传formdata封装的数据
					contentType: false,//jQuery要不要去设置Content-Type请求头
					processData: false,//jQuery要不要去处理发送的数据
					cache: false, //是否缓存
					success: alert("成功")
				});
			});
		}
	}	
)