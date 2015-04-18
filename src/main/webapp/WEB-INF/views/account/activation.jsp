<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">	
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
<%@ include file="../topbar.jsp" %>
<div class="container">
	<div class="row">
		<div class="span8 offset3">
			激活链接已发送到您的邮箱<a href="#">${email }</a>, 请激活 
			<a href="#" class="ui primary button">重新发送</a> 
		</div>
	</div>
</div>
</body>
</html>