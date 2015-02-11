<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/feed.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/card.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/list.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/label.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/comment.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/post.js"></script>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/follow.js"></script>  
  	<style>
	  .postheader{
	      font-weight: bold;
	      margin-bottom: 10px;
	  }
	  .header{
	  	padding-top: 0;
	  	margin-top: 0;
	  }
	  .postmeta{
	  	margin: 15px 0 0;
	  }
	  .postmeta  .avatar {
	  	float:left;
	  }
	  .postmeta .author {
	  	float:left;
	  	padding-top:7px;
	  	margin-left:7px;
	  }
	  .postmeta .tags {
	  	float: right;
	  } 	
	  #tags {
	  	padding-left: 20px;
	  }
	  .ui.images img {
	  	width:23.5%;
	  }
  	</style>  	
</head>
<body>
  <div class="container">
	<!--  <%@ include file="../topbar.jsp" %>-->
    <div class="row">  
          <div class="span8">     
          	<div class="ui feed">
          			<h4 class="ui header">我的日志 (<a href="#"> 全部 </a>)</h4>
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
          			
          			
          			<h4 class="ui header">我的相册 (<a href="#"> 全部 </a>)</h4>
          			<div class="ui divider"></div>
                    <div class="event">                    
                      <div class="content">
<div class="ui four cards">
  <div class="card">
    <a class="image">
      <img src="<c:url value="/img/avatar.jpg"/>">
    </a>
    <div class="extra">
      Rating:
      <div class="ui star rating" data-rating="4"></div>
    </div>
  </div>
  <div class="card">
    <a class="image">
      <img src="<c:url value="/img/avatar.jpg"/>">
    </a>
    <div class="extra">
      Rating:
      <div class="ui star rating" data-rating="2"></div>
    </div>
  </div>
  <div class="card">
    <a class="image">
      <img src="<c:url value="/img/avatar.jpg"/>">
    </a>
    <div class="extra">
      Rating:
      <div class="ui star rating" data-rating="3"></div>
    </div>
  </div>
  <div class="card">
    <a class="image">
      <img src="<c:url value="/img/avatar.jpg"/>">
    </a>
    <div class="extra">
      Rating:
      <div class="ui star rating" data-rating="4"></div>
    </div>
  </div>
</div>
                      </div>
                    </div>             			
          			
          			
          	</div>      
          </div>
          <div class="span4">
          	<div id="rightside">
	            <div class="ui card">
	              <div class="image">
	                <img src="<c:url value="/img/avatar.png"/>">
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
	              </div>
	            </div> 
	            
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