<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
				  <a class="item" href='<c:url value="/account/setting/info"/>' >
				    个人信息
				  </a>
				  <a class="item" href='<c:url value="/account/setting/avatar"/>'>
				    头像
				  </a>
				  <a class="active teal item" href='<c:url value="/account/setting/security"/>'>
				    账户安全
				  </a>
				</div>
			
			</div>
			<div class="span8">
				<div class="ui header">
					账户安全
				</div>
				<div class="ui divider">
					<div class="ui form" style="margin-top: 30px">
						<div class="inline field">
							<label for="">密码</label>
							<a href="#" class="ui tiny button">修改</a>
						</div>
					
					</div>
		
				</div>
				
			</div>
		</div>
	
	</div>
</body>
</html>