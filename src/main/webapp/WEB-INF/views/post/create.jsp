<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
  	<style>
  	body {
    	font-family: "Microsoft YaHei","微软雅黑",tahoma,arial,"宋体";
  	}
	.post{
		line-height: 24px;
	}
	.tags{
	        font-family: Lato,'Helvetica Neue',Arial,Helvetica,sans-serif;
	        margin: 0;
	        outline: 0;
	        -webkit-appearance: none;
	        -webkit-tap-highlight-color: rgba(255,255,255,0);
	        line-height: 1.2142em;
	        padding: 0;
	        font-size: 1em;
	        background: #fff;
	        height: 37px;
	        border: 1px solid rgba(39,41,43,.15);
	        color: rgba(0,0,0,.8);
	        border-radius: .2857rem;
	        box-shadow: 0 0 0 0 transparent inset;
	        -webkit-transition: background-color .2s ease,color .2s ease,box-shadow .2s ease,border-color .2s ease;
	        transition: background-color .2s ease,color .2s ease,box-shadow .2s ease,border-color .2s ease;
	
	  }
	  .ui.form input[type="text"].tag-input{
	        border: 0;
	        padding: 0;
	        margin: 0;
	        outline: 0;
	        position: relative;
	        height: 30px;
	        padding-top: 3px;
	        width: auto;
			vertical-align: baseline;
	  }
	  .tagfield {
	    display: inline-block;
	    padding-left:4px;
	  }	
  	</style>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/post.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/tag.js"></script>
</head>
<body>
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
				    <textarea id="content" class="post"></textarea>
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
</body>
</html>