<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/message.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/label.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/font-awesome.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/froala_editor.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/froala_style.min.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">


</head>
<body>
	<%@ include file="../topbar.jsp" %>
	<div class="container">
		<div class="row">
			<div class="span8">
				<div class="ui form">
				  <div class="field">
				    <label>标题</label>
				    <input type="text" id="title">
				  </div>
				  <div class="field">
				    <label>正文</label>
				    <textarea id="content"></textarea>
				    
				    <!--   <textarea id="content" class="post"></textarea> -->
				  </div>	

					<div class="field">
					  	<label>标签:</label>
					  	<div class="tags">
						    <div class="tagfield">   
						    </div>
					  		<input type="text" class="tag-input" id="tag-input">
					  	</div>
					</div>
				  			  
				  <div class="inline field">
				    <label>隐私:</label>
					<input type="radio" name="post_status" value="0" checked="checked"> 公开
					<input type="radio" name="post_status" value="1"> 仅自己可见
				  </div>
				  <div class="inline field">
				  	<label>评论:</label>
					<input type="radio" name="comment_status" value="0" checked="checked"> 允许评论
					<input type="radio" name="comment_status" value="1"> 不允许评论				  	
				  </div>
				  <div class="ui button green" id="send">发表</div>
				  <div class="ui button" id="cancel">取消</div>
				</div>				
				

			</div>
		</div>
	</div>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.11.1.min.js"></script> 
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/froala_editor.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/post.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tag.js"></script>
  	<script>
      $(function() {
          $('#content').editable({
        	  inlineMode: false,
        	  imageUploadURL: '<%=request.getContextPath() %>/album/upload/postphoto',
        	  imageUploadParam: "uploader_input"
          })
      });
    </script>	  	
</body>
</html>