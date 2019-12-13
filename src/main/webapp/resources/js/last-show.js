$(function() {
	var initUrl = '/IAS/imageadmin/listimage';
	var url2 = '/IAS/imageadmin/deleteImageById';
	alert(initUrl);
	var list="";
	getImageShow();
	function getImageShow() {
		// 从数据库获取数据
		$.getJSON(initUrl, function(data) {
			if (data.success) { // 验证数据是否正确
				var tempHtml = "";
				// 获取店铺类别名称
				data.imageList.map(function(item) {
					tempHtml += '<div class="' + item.id + '" style="float:left;margin:12px">' + '<img src="'
							+ item.addr
							+ '" style="height: 200px;width:200px">' + '<br>'
							+ '<button id="'+item.id+'">删除</button>'
							+ '</div>'
				});
				// 添加到页面
				$('#last-show').html(tempHtml);
				//请求删除数据库中的image
				$('#last-show').find('button').click(function(){
					imageId=$(this).attr('id');
	                $('.'+$(this).attr('id')).remove();
	                var formData=new FormData();
					formData.append('imageId',imageId);
					console.log(imageId);
					$.ajax({
						url: url2,//传送的目标地址
						type: 'GET',//传送方式
						data:{'imageId':imageId},//上传formdata封装的数据
						//dataType:*,返回的数据类型 默认值为字符串
						//contentType: false,//jQuery要不要去设置Content-Type请求头
						//processData: false,//jQuery要不要去处理发送的数据
						cache: false, //是否缓存
						success: function(){//成功回调
							alert("删除成功");
						},
						error:function(){
							
						}
					});
	            });
				$('#redisplay').click(function(){
					alert("segew新加载了");
					document.location.reload(true);
				});
			}
		});

	}
})