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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.Jcrop.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.Jcrop.min.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/code.js"></script>
	
	
	<style>
	/* Apply these styles only when #preview-pane has
	   been placed within the Jcrop widget */
	.jcrop-holder #preview-pane {
	  display: block;
	  position: absolute;
	  z-index: 2000;
	  top: 0px;
	  right: -200px;
	  padding: 6px;
	  background-color: white;
	}
	
	/* The Javascript code will set the aspect ratio of the crop
	   area based on the size of the thumbnail preview,
	   specified here */
	#preview-pane .preview-container {
	  width: 150px;
	  height: 150px;
	  overflow: hidden;
	}
	
	.change_avatar{
		position: relative;
	}
	.change_avatar .button{
		position: relative;
		width: 90px;
		height: 30px;

	}
	#avatar_file{
		position: absolute;
		top:0;
		left: 0;
		width: 90px;
		height: 30px;
		opacity: 0;
		text-align: center;
	}
	</style>
	

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
				  <a class="active teal item" href='<c:url value="/account/setting/avatar"/>'>
				    头像
				  </a>
				  <a class="item" href='<c:url value="/account/setting/security"/>'>
				    账户安全
				  </a>
				</div>
			
			</div>
			<div class="span8">
				<div class="ui header">
					头像
				</div>
				<div class="ui divider">
				
					<div class="avatar" style="margin-top: 30px">
						<img class="ui small circular image" src='<c:url value="/img/avatar.jpg"></c:url>'>
					</div>
					
					<div class="change_avatar">
						<div class="ui tiny button">更改头像</div>
						<input type="file" id="avatar_file" name="avatar_file" class="ui tiny button" />
					</div>
					
					
					  <div id="target_img_cnt">	
					  	<img src="<c:url value="/img/avatar.jpg"></c:url>" id="target" alt="[Jcrop Example]" />
					  </div>
					  
					  <div id="preview-pane">
					    <div class="preview-container">
					      <img src="<c:url value="/img/avatar.jpg"></c:url>" class="jcrop-preview" alt="Preview" />
					    </div>
					  </div>					
					  
					  <div class="ui tiny blue button" id="avatar_save">
					  	保存
					  </div>
						
				</div>
				
			</div>
		</div>
	
	</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/js/ajaxfileupload.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath() %>/js/avatar.js"></script>
</body>
</html>