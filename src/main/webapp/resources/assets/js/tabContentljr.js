
	//找到选像卡列表的父容器
	var container = document.getElementsByClassName('container_box');
	//获取到下面的所有要点击切换的选项卡
	var btnslist = container[0].getElementsByTagName('li');
	//alert(btnslist.length) 4个
	//获取内容显示区域的父容器
	var countent_box = document.getElementsByClassName("countent_box");
	//在获取到下面的每一个要显示内容的盒子
	var aLi1 = countent_box[0].getElementsByClassName("mian1");

	//循环所有的选项卡
	for(var i = 0; i < btnslist.length; i++) {
		//给每一个要点击的按钮标记一个序号
		btnslist[i].index = i;
		//添加点击事件
		btnslist[i].onclick = function() {
			//初始化每个li 的默认样式包括css里面写的
			for(var i = 0; i < btnslist.length; i++) {
				//清除每个选项卡的样式class 因为第一个我们开始添加类
				btnslist[i].className = '';
				//为当前点击的选项卡添加样式 首先准备一个class样式  例如active 这里例子只是添加下边框
				this.className = 'active';
				//上面记录了每个选项卡的标记 下面要显示内容的盒子和选项卡是一样多的
				//此时 我们只需要将所有的mian1隐藏起来
				aLi1[i].style.display = 'none';
			}
			//再将当前点击选项卡对应的内容区域显示出来
			aLi1[this.index].style.display = "block"   // this当前作用的对象
		}
	}