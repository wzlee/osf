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
  	#firstpage {
		background-image: url("<%=request.getContextPath() %>/img/background.png");
		background-repeat: no-repeat;
		min-height: 650px;
		width: 100%;
	}
  	/* .section { text-align: center; font: 50px "Microsoft Yahei"; color: #fff;}	
  	.section { text-align: center; font: 50px "Microsoft Yahei"; color: #fff;} */
	.section2 p { display: none;}
	.section3 p { position: relative; bottom: -120%;}
	.section4 p { display: none;}
	
	.section1 { background: url("<%=request.getContextPath() %>/img/background.png");}
	
	.overview{
		text-align: center;
		margin-top: -15px;
	}
	
	.overview .initials {
		font-family: Helvetica-Light;
		font-size: 100px;
		color: #FFFFFF;
		line-height: 120px;
		margin-bottom: 20px;
	}
	.overview .en_initials{
		font-family: Helvetica-Light;
		font-size: 40px;
		color: #FFFFFF;
		line-height: 48px;
		letter-spacing: 1px;
		margin-bottom: 10px;

	}
	.overview .zh_initials{
		font-family: STHeitiSC-Light;
		font-size: 36px;
		color: #FFFFFF;
		line-height: 37px;
		/* 开放 分享 自由: */	
		
	}
	
	.login{
		text-align: left;
		margin-left: 100px;
		margin-right: 60px;
	}
	.login .login_header{
		font-family: STHeitiSC-Light;
		font-size: 48px;
		color: #FFFFFF;
		line-height: 49px;
		margin-bottom: 20px;
	}
	.login input{
		border: none;
		border-radius: 2px;
		/* Rectangle 4: */
		background: #E7EBB2;
		height: 45px;
		width: 300px;
		margin-bottom: 10px;
		color:#9B9B9B;
		font-size: 20px;
		padding-left: 10px;
	}
	.login .login_btn{
		margin-top: 10px;
		border: none;
		border-radius: 2px;
		background: #F5A623;
		width: 150px;
  		height: 35px;	
  		font-size: 20px;
  		color:white;
	}
	.login .forget_pwd{
		float: right;
		margin-top: 20px;
		color:white;
		font-size: 15px;
	}
	.login .forget_pwd a{
		text-decoration: none;
		cursor: pointer;
		color: white;
	}
	.section1 .container {
		margin-top: -80px;
	}
	.pulldown_tip{
		text-align: center;
	}
	.pulldown_tip img{
		width:  40px;
		position: absolute;
		bottom: 20px;
	}
	.nav{
		width: 100%;
		position: fixed;
		z-index: 100;
	}
	
	.navbar {
		height: 60px;
		padding: 0;
		margin: 0;
		background: none;
	}
	.navbar .register{
		position: absolute;
		top: 10px;
		right: 30px;
		border: none;
		border-radius: 2px;
		background: #7ECBB1;
		width: 90px;
  		height: 40px;	
  		font-size: 20px;
  		color: white;
	}
  	</style>
  	<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script src="<%=request.getContextPath() %>/js/jquery.fullPage.js"></script>
  	<script src="http://cdn.staticfile.org/jquery-easing/1.3/jquery.easing.min.js"></script>
  	<script>
	$(function(){
		$('#welcome').fullpage({
			sectionsColor: ['#1bbc9b', '#4BBFC3', '#7BAABE', '#f90'],
			'navigation': true,
			afterLoad: function(anchorLink, index){
				if(index == 2){
					$('.section2').find('p').fadeIn(2000);
				}
				if(index == 3){
					$('.section3').find('p').delay(500).animate({
						bottom: '0'
					}, 1500, 'easeOutExpo');
				}
				if(index == 4){
					$('.section4').find('p').fadeIn(2000);
				}
			}
		});
	});
	</script>
</head>
<body>
	<div class="nav">
		<div class="navbar">
			<div class="logo"></div>
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
			<h3>第二屏</h3>
			<p>请查看右边的圆圈</p>
		</div>
		<div class="section section3">
			<h3>第三屏</h3>
			<p>圆圈还可以设置位置，颜色，加上 tip，点击可以控制</p>
		</div>
		<div class="section section4">
			<h3>第四屏</h3>
			<p>这是最后一屏</p>
		</div>		
	
	
	</div>

	<!-- <div class="container welcome">
		<div class="row">
			<div class="span4">

				<div class="ui circular segment">
				  <h2 class="ui header">
				    Buy Now
				    <div class="sub header">$10.99</div>
				  </h2>
				</div>
		        <p>The only dogma from this framework: <em>everything arbitrary is mutable</em>.</p>
		        <a class="ui large button">Integration Guide</a>


			</div>
			<div class="span4">
				<div class="ui circular segment">
				  <h2 class="ui header">
				    Buy Now
				    <div class="sub header">$10.99</div>
				  </h2>
				</div>
		        <p>The only dogma from this framework: <em>everything arbitrary is mutable</em>.</p>
		        <a class="ui large button">Integration Guide</a>
			</div>
			<div class="span4">
				<div class="ui circular segment">
				  <h2 class="ui header">
				    Buy Now
				    <div class="sub header">$10.99</div>
				  </h2>
				</div>
		        <p>The only dogma from this framework: <em>everything arbitrary is mutable</em>.</p>
		        <a class="ui large button">Integration Guide</a>
			</div>
		</div>
	</div> -->
</body>
</html>