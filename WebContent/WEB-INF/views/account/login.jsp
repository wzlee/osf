<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">	
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
	<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/basic.js"></script>
	<script src="<%=request.getContextPath() %>/js/login.js"></script>
	<style>
	body {
	    font-family: "Microsoft YaHei","微软雅黑",tahoma,arial,"宋体";
	}	
	.loginarea{
		/*position: relative;*/
		padding-top: 30%;		
	}
	.forgetpwd{
		float: right;
		padding-top: 5px;
	}
	.social{

	}
	.social .button{
	
		margin-bottom: 10px;
	}

	.segment {
	  position: relative;
	  box-shadow: 0 0 0 1px rgba(39,41,43,.15),0 1px 2px 0 rgba(0,0,0,.05);
	  border-radius: .2857rem;
	  border: none;
	}	
	.ui.form input[type]{
		width: 98%;
	}	
	.ui.divider {
		width: 108.5%;
	}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span4 offset4">				
					<div class="row">
						<div class="loginarea">
							<div class="ui form">
							  <div class="field">
							    <label>邮箱<span id="emailTip"></span></label>
							    <input type="text" name="email" id="email">
							  </div>
							  <div class="field">
							    <label>密码<span id="paswordTip"></span></label>
							    <input type="password" name="password" id="password">
							  </div>
							  <div class="field">
							  	<div class="ui green button" id="loginbtn">登录</div>
							  </div>								
							</div>				
							<div class="ui section divider"></div>
							<div class="social">
								<div class="primary ui facebook button span4">
								  <i class="facebook icon"></i>
								  Facebook 登录
								</div>
								<div class="primary ui twitter button span4">
								  <i class="twitter icon"></i>
								  Twitter 登录
								</div>	
								<div class="ui orange basic button span4">
									邮箱注册
								</div>		
							</div>	

						</div>
					</div>					


			</div>
		</div>
	</div>
</body>
</html>