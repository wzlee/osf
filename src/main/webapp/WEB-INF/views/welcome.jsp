<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Welcome to OSF</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.fullPage.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/welcome.css">
	<style>
	.section1 { background: url("<%=request.getContextPath() %>/img/background.png");}
	.section4 { background: url("<%=request.getContextPath() %>/img/gallery/8.jpg") no-repeat;
				background-size: cover;}
	</style>
  	<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script src="<%=request.getContextPath() %>/js/jquery.fullPage.js"></script>
  	<script src="http://cdn.staticfile.org/jquery-easing/1.3/jquery.easing.min.js"></script>
  	<script src="<%=request.getContextPath() %>/js/jquery.row-grid.js"></script>
  	<script>
	$(function(){
		$('#welcome').fullpage({
			sectionsColor: ['#FFFFFF', '#FFFFFF', '#F7F8FA', '#FFFFFF'],
			'navigation': true,
			afterLoad: function(anchorLink, index){
				if(index == 2){
					$('.section2').find('p').fadeIn(2000);
				}
				if(index == 3){
					$('.section3').find('.features').delay(50).animate({
						bottom: '0'
					}, 1000, 'easeOutExpo');
				
				}
				if(index == 4){
					
				}

			}
		});
		
		var options = {minMargin: 5, maxMargin: 10, itemSelector: ".box", firstItemClass: "first-item"};
		$(".gallery").rowGrid(options);	
		
	});
	</script>
</head>
<body>
	<div class="nav">
		<div class="navbar">
			<div class="logo">
				<img src="<%=request.getContextPath() %>/img/logo.png" alt="" />
			</div>
			<button class="register">注册</button>
		</div>
	</div>
	<div id="welcome">

		<div class="section section1">
			<div class="container">
				<div class="row">
					<div class="span12">
						<div class="row">
							<div class="span6 overview">
								<p class="initials">OSF</p>
								<p class="en_initials">Open Share Freedom</p>
								<p class="zh_initials">开放 分享 自由</p>
							</div>
							<div class="span6">
								<div class="login">
									<div class="login_header">
										欢迎登录
									</div>
									<div class="field">
										<input type="text" placeholder="电子邮箱"/>
									</div>
									<div class="field">
										<input type="password" placeholder="密码"/>
									</div>
									<div class="field">
										<button class="login_btn">登录</button>
										<span class="forget_pwd"><a href="#">忘记密码?</a></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="pulldown_tip">
				<img src="/com.lvwang.osf/img/pulldown_tip.png" alt="向下滑动">
			</div>
		</div>
		<div class="section section2">
			<div class="container">
				<div class="row">
					<div class="span12 center">
						<div class="header">
							OSF是什么
						</div>	
					
					</div>
				</div>
				<div class="row">
					<div class="span12 center">
						<div class="content">
							<p>你可以用它打造一个SNS网站</p>
							<p>也可以从Tag出发构建一个垂直兴趣社区</p>
							<p>甚至是两者的混合，但绝不仅此</p>
						</div>
					</div>
				</div>
			</div>
			<div class="gallery">
				<div class="box first-item"><img src="<%=request.getContextPath() %>/img/gallery/1.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/2.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/4.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/5.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/7.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/8.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/9.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/10.jpg" alt="" /></div>
				<div class="box"><img src="<%=request.getContextPath() %>/img/gallery/6.jpg" alt="" /></div>
			</div>			
		</div>
		<div class="section section3">
			<div class="container">
				<div class="row">
					<div class="span12 center">
						<div class="header">
							在OSF 你可以
						</div>	
					
					</div>
				</div>
			</div>	
			<div class="features row1">
				<div class="feature">
					<div class="circle">
						<i class="blue big font icon"></i>
					</div>
					<div class="header">说说</div>
					<div class="desc">发送简短的消息<br/>分享你此时的状态</div>
				</div>
				<div class="feature">
					<div class="circle">
						<i class="pink big photo icon"></i>
					</div>
					<div class="header">相册</div>
					<div class="desc">上传图片<br/>记录美好时刻</div>
				</div>
				<div class="feature">
					<div class="circle">
						<i class="big write icon"></i>
					</div>
					<div class="header">日志</div>
					<div class="desc">记录生活点滴</div>
				</div>
			</div>	
			<div class="features row1">
				<div class="feature">
					<div class="circle">
						<i class="big orange tag icon"></i>
					</div>
					<div class="header">标签</div>
					<div class="desc">兴趣<br/>从标签出发</div>
				</div>
				<div class="feature">
					<div class="circle">
						<i class="big users icon"></i>
					</div>
					<div class="header">朋友</div>
					<div class="desc">关注喜欢的朋友<br/>不错过他的动态</div>
				</div>
				<div class="feature">
					<div class="circle">
						<i class="big red heart icon"></i>
					</div>
					<div class="header">喜欢</div>
					<div class="desc">相册、日志<br/>喜欢就收藏起来吧</div>
				</div>
			</div>
						
		</div>
		<!-- end section3 -->
		<div class="section section4">
			<div class="container">
				<div class="row">
					<div class="span12 center">
						<div class="header">
							探索, OSF
						</div>					
					</div>
				</div>			
				<div class="row">
					<div class="span6 offset3">
						<div class="row">
							<div class="login_btn span3">登录</div>
							<div class="register_btn span3">注册</div>						
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>