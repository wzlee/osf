<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="type" content="post">
	<meta name="id" content="${post.id }">
	<title>post index</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/card.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/comment.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/message.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/statistic.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

  	<style>


  	</style>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/comment.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/post.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/follow.js"></script>
</head>
<body>
	<div class="container">
		<%@ include file="../topbar.jsp" %>
		<div class="row">
			<div class="span8">
						<div>
							<h1 class="ui dividing header">${post.post_title }</h1>
						</div>
						<div class="post" id="post${post.id }">
							<p>
								${post.post_content }
							</p>

						</div>
						<div>
							<div class="action">
								<div class="ui circular icon basic button">						
								  <i class="share alternate icon"></i>	
								</div>							
								<div class="ui circular orange icon button">
								  <i class="weibo icon" id="weiboshare"></i>
								</div>
								<div class="ui circular blue icon button">
								  <i class="qq icon" id="qqshare"></i>
								</div>
								<div class="ui circular green icon button">
								  <i class="wechat icon" id="wechatshare"></i>
								</div>	
								<div class="ui circular icon basic button post like">
								  <i class="empty red heart icon" id="like"></i>
								</div>																
							</div>
						</div>
						<div class="ui comments" id="comments">

						  <div id="replyarea">
								  <form class="ui reply form" id="replyform">
								    <div class="field">
								      <textarea id="replycontent"></textarea>
								    </div>
									<div class="ui primary button" id="replybtn">
									  评论
									</div>							    
								  </form>								
						  </div>
						  
						  <div id="commentList">							
							  <jsp:include page="/comment/post/${post.id }"></jsp:include>				  	
						  </div>
						  <!-- comment list -->
						</div>
						<!-- end comment -->
					</div>
					<div class="span4">
					  <div id="rightside">
			            <%@ include file="../usercard.jsp" %>  
			          </div>			
					</div>
		</div>
	</div>
</body>
</html>