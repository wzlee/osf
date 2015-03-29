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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	
	<script src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<script src="<%=request.getContextPath() %>/js/basic.js"></script>
	<script src="<%=request.getContextPath() %>/js/login.js"></script>
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