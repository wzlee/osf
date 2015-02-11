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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/card.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/header.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/comment.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/form.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/message.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">

  	<style>
  	body {
    	font-family: "Microsoft YaHei","微软雅黑",tahoma,arial,"宋体";
  	}
	.post{
		line-height: 24px;
		margin-bottom: 30px;
	}
	.ui.avatar.image{
		margin-right: .25em;
		display: inline-block;
		width: 2.5em;
		height: 2.5em;
		border-radius: 500rem;		
	}
	#replybtn{
		margin-bottom: 20px;
	}

	.like{
		float: right;
	}
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
							<h1 class="ui dividing header">Title</h1>
						</div>
						<div class="post" id="post${post.id }">
							<p>
新华社北京12月30日电 中共中央总书记、国家主席、中央军委主席、中央全面深化改革领导小组组长习近平12月30日上午主持召开中央全面深化改革领导小组第八次会议并发表重要讲话。他强调，今年是全面深化改革的开局之年，改革形成了上下联动、主动作为、蹄疾步稳、狠抓落实的好局面，呈现出全面播种、次第开花的生动景象，在一些重要领域和关键环节取得重大进展和积极成效，有力促进了稳增长、调结构、惠民生、防风险等方面的工作。明年是全面深化改革的关键之年，气可鼓而不可泄，要巩固改革良好势头，再接再厉、趁热打铁、乘势而上，推动全面深化改革不断取得新成效。

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
								<div class="ui circular icon basic button like">
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
							
							  <!--  
						  	  <div class="comment" id="1" author="">
							    <a class="avatar">
							      <img src="<c:url value="/img/avatar.png"/>" alt="" />
							    </a>
							    <div class="content">
							      <a class="author">Matt</a>
							      <div class="metadata">
							        <span class="date">Today at 5:42PM</span>
							      </div>
							      <div class="text commentContent">
							        <p>How artistic!</p>
							      </div>
							      <div class="actions" >
							        <a class="reply" ref="1">Reply</a>
							      </div>
							    </div>
							  </div>	
							  -->
							  <jsp:include page="/comment/post/${post.id }"></jsp:include>				  	
						  </div>
						  <!-- comment list -->
						</div>
						<!-- end comment -->
					</div>
					<div class="span4">
			            <div class="ui card">
			              <div class="image">
			                <img src="<%=request.getContextPath() %>/img/avatar.png">
			              </div>
			              <div class="content">
			                <a class="header">Stevie Feliciano</a>
			                <div class="meta">
			                  <span class="date">Joined in 2014</span>
			                </div>
			                <div class="description">
			                  Stevie Feliciano is a library scientist living in New York City. She likes to spend her time reading, running, and writing.
			                </div>
			              </div>
			              <div class="extra content">
			                <a>
			                  <i class="user icon"></i>
			                  22 Friends
			                </a>
			                <div class="small ui inverted yellow button follow" following="10">
			                	Yellow
			                </div>
			              </div>		              
			            </div> 		<!-- end card -->				
					</div>
		</div>
	</div>
</body>
</html>