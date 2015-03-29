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
	<script src="<%=request.getContextPath() %>/js/register.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span4 offset4">				
					<div class="row">
						<div class="registerArea">
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
							    <label>确认密码<span id="cfmPwdTip"></span></label>
							    <input type="password" name="cfmPwd" id="cfmPwd">
							  </div>

							  <div class="field">
							  	<div class="ui green button" id="registerBtn">注册</div>
							  </div>								
							</div>				
							<div class="ui section divider"></div>
							<div>
								<div class="ui orange basic button span4">
									已有账号登录
								</div>		
							</div>	

						</div>
					</div>					


			</div>
		</div>
	</div>
</body>
</html>