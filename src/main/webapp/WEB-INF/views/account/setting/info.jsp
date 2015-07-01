<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人信息</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">	
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
	<%@ include file="../../topbar.jsp" %>
	<div class="container">
		<div class="row">
			<div class="span4">
				<div class="ui vertical menu">
				  <a class="active teal item" href='<c:url value="/account/setting/info"/>' >
				    个人信息
				  </a>
				  <a class="item" href='<c:url value="/account/setting/avatar"/>'>
				    头像
				  </a>
				  <a class="item" href='<c:url value="/account/setting/security"/>'>
				    账户安全
				  </a>
				</div>
			
			</div>
			<div class="span8">
				<div class="ui header">
					个人信息
				</div>
				<div class="ui divider">
				
					<div class="ui form setting info">
					    <div class="inline field">
					      <label>用户名</label>
					      <input type="text" placeholder="两排杨树">
					    </div>
					    <div class="inline field">
					      <label>签名</label>
					      <textarea plcaeholder=""></textarea>
					    </div>
					    <div class="inline field">
					   		<label for=""></label>
					    	<div class="ui tiny blue button">保存</div>
					    </div>

					</div>
					<!-- end form -->			
				</div>
				
			</div>
		</div>
	
	</div>
</body>
</html>