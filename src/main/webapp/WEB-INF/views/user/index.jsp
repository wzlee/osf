<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/feed.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/card.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/list.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/label.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/statistic.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
    
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/code.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/comment.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/post.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/follow.js"></script>   	
</head>
<body>
  <%@ include file="../topbar.jsp" %>
  <div class="container">
    <div class="row">  
          <div class="span8">     
          		<div class="ui feed">
          			<h4 class="ui header">我的日志 (<a href="#"> 全部 </a>)</h4>
          			<c:forEach items="${posts }" var="post">     			
	          			<div class="ui divider"></div>
	                    <div class="event">                    
	                      <div class="content">
	                        <div class="text">
	                        	<div class="row">
	                        	<c:if test="${not empty post.post_cover}">
	                        		<div class="span6">
	                        	</c:if>
	                        	<c:if test="${empty post.post_cover}">
	                        		<div class="span8">
	                        	</c:if>
	                        		
	                        			<h3 class="ui header">
	                        				<a href="<%=request.getContextPath() %>/post/${post.id}">${post.post_title }</a>
	                        			</h3>
	                        			<div>
	                        				${post.post_excerpt }
	                        			</div>
	                        			<div class="postmeta">
	
	                        			</div>
	                        		</div>
	                        		<c:if test="${not empty post.post_cover}">
		                        		<div class="span2">
		                        			<img class="ui small image" src="${post.post_cover }" alt=""  />
		                        		</div>
	                        		</c:if>
	                        		
	                        	</div>
	                        </div>
	                        <div class="meta">
	                          <a class="like">
	                            <i class="like icon"></i> ${post.like_count } Likes
	                          </a>
	                          <a class="share">
	                            <i class="share alternate icon"></i> ${post.share_count } shares
	                          </a>   
	                          <a class="comment">
	                            <i class="comment outline icon"></i> ${post.comment_count } comments
	                          </a>                                                 
	                        </div>
	                        <div class="meta" id="tags">
	                        	<i class="tag icon"></i>
								<a href="#">#tag1
								</a>   
								<a href="#">#tag1
								</a>							                     	
	                        </div>
	                      </div>
	                    </div>     <!-- end event -->  
                    </c:forEach>
                              			
          			<div class="ui divider"></div>
                    <div class="event">                    
                      <div class="content">
                        <div class="text">
                        	<div class="row">
                        		<div class="span6">
                        			<h3 class="ui header">First header</h3>
                        			<div>
                        				Ours is a life of constant reruns. We're always circling back to where we'd we started, then starting all over again. Even if we don't run extra laps that day, we surely will come back for more of the same another day soon.
                        			</div>
                        			<div class="postmeta">

                        			</div>
                        		</div>
                        		<div class="span2">
                        			<img class="ui small image" src="<c:url value="/img/avatar.jpg"/>" alt=""  />
                        		</div>
                        	</div>
                        </div>
                        <div class="meta">
                          <a class="like">
                            <i class="like icon"></i> 5 Likes
                          </a>
                          <a class="share">
                            <i class="share alternate icon"></i> 10 shares
                          </a>   
                          <a class="comment">
                            <i class="comment outline icon"></i> 10 comments
                          </a>                                                 
                        </div>
                        <div class="meta" id="tags">
                        	<i class="tag icon"></i>
							<a href="#">#tag1
							</a>   
							<a href="#">#tag1
							</a>							                     	
                        </div>
                      </div>
                    </div>     <!-- end event -->       	
			
          			<div class="ui divider"></div>
                    <div class="event">                    
                      <div class="content">
                        <div class="text">
                        	<div class="row">
                        		<div class="span6">
                        			<h3 class="ui header">First header</h3>
                        			<div>
                        				Ours is a life of constant reruns. We're always circling back to where we'd we started, then starting all over again. Even if we don't run extra laps that day, we surely will come back for more of the same another day soon.
                        			</div>
                        			<div class="postmeta">

                        			</div>
                        		</div>
                        		<div class="span2">
                        			<img class="ui small image" src="<c:url value="/img/avatar.jpg"/>" alt=""  />
                        		</div>
                        	</div>
                        </div>
                        <div class="meta">
                          <a class="like">
                            <i class="like icon"></i> 5 Likes
                          </a>
                          <a class="share">
                            <i class="share alternate icon"></i> 10 shares
                          </a>   
                          <a class="comment">
                            <i class="comment outline icon"></i> 10 comments
                          </a>                                                 
                        </div>
                        <div class="meta" id="tags">
                        	<i class="tag icon"></i>
							<a href="#">#tag1
							</a>   
							<a href="#">#tag1
							</a>							                     	
                        </div>
                      </div>
                    </div>   <!-- end event -->
          		
          			<h4 class="ui header albumheader">我的相册 (<a href="#"> 全部 </a>)</h4>
          			<div class="ui divider"></div>
                    <div class="event">                    
                      <div class="content">
						<div class="ui four cards">
						  <c:forEach items="${albums }" var="album">
							  <div class="card">
							    <a class="image" href="<c:url value="/album/${album.id}/photos"/>">
							      <img src="<c:url value="${imgBaseUrl}${album.cover }"/>">
							    </a>
							    <div class="extra">
							      ${album.album_desc }
							    </div>
							  </div>						  						  
						  </c:forEach>
						</div>
                      </div>
                    </div>
          			
          	</div>     <!-- end feed --> 
          </div> <!-- end span8 -->
          
          <div class="span4">
          	<div id="rightside">
				<%@ include file="../usercard.jsp" %>
	            
	            <div class="ui card">
	            	<div class="content">
	            		<h4 class="ui header">Header</h4>
						<div class="ui list">
						  <div class="item">
							<a href="">Also quite a lovely city</a>
						  </div>
						  <div class="item">
							<a href="">Sometimes can be a lovely city</a>
						  </div>
						  <div class="item">
							<a href="">What a lovely city</a>
						  </div>
						  <div class="item">
							<a href="">Also quite a lovely city</a>
						  </div>						  						  						  

						</div>	            		
	            	</div>
	            </div>
            </div>           
          </div>
        </div>
      </div>
</body>
</html>