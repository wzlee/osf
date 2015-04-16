<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>OSF</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/button.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/feed.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/card.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/image.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/divider.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/list.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/icon.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/page.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>
<body>
  <%@ include file="topbar.jsp" %>
  <div class="container">
    <div class="row">  
          <div class="span8">           
                  <div class="ui feed">
                   <c:forEach items="${feeds }" var="feed"> 
                   <!-- new post -->
                    <c:if test="${feed.object_type == 0 }">
                    <div class="event">
                      <div class="label">
                        <img src="img/avatar.png">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a>${feed.user_id }</a> 发表了日志
                          <div class="date">
                            ${feed.ts }
                          </div>
                        </div>
                        <div class="extra">
                          <div class="postheader">
                            <a href="<c:url value="/post/${feed.object_id }" />">${feed.title }</a>
                          </div>
                          ${feed.summary }
                        </div>
                        <div class="meta">
                          <div class="tags">
                        	<i class="tag icon"></i>
                        	<c:forEach items="${feed.tags }" var="tag">
                        		<a href="#">${tag }</a>
                        	</c:forEach>
						  </div>							                     	
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i> ${feed.comment_count }
	                          </a>                           
	                          <a class="like">
	                            <i class="like icon"></i> ${feed.like_count } 
	                          </a>                          
                          </div>

                        </div>                                               
                        <div class="ui divider"></div>
                      </div>
                    </div>                    	
                    </c:if>
                    
                    <!-- new album -->
                    <c:if test="${feed.object_type == 2 }">
                    <div class="event">
                      <div class="label">
                        <img src="img/avatar.png">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a>${feed.user_id }</a> added <a href="<c:url value="/album/${feed.object_id }" /> ">2 new illustrations</a>
	                      <div class="date">
	                        ${feed.ts }
	                      </div>                          
                        </div>
                        <div class="extra images">
                          <c:forTokens items="${feed.content }" delims=":" var="img">
                          	<a href="<c:url value="/album/${feed.object_id }/photos" />"><img alt="" src="${imgBaseUrl }${img }"></a>
                          </c:forTokens>
                        </div>
                        <div class="extra">${feed.summary }</div>
                        <div class="meta">
                          <c:if test="${not empty feed.tags }">	
	                          <div class="tags">
	                        	<i class="tag icon"></i>
	                        	<c:forEach items="${feed.tags }" var="tag">
	                        		<a href="#">${tag }</a>
	                        	</c:forEach>
							  </div> 
						  </c:if>                       
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i> ${feed.comment_count }
	                          </a>                           
	                          <a class="like">
	                            <i class="like icon"></i> ${feed.like_count } 
	                          </a>                          
                          </div>
                        </div>
                        <div class="ui divider"></div>
                      </div>
                    </div>                    
                    
                    
                    </c:if>
                                    
                  </c:forEach>
                    <div class="event">
                      <div class="label">
                        <img src="img/avatar.png">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a class="user">
                            Elliot Fu
                          </a> 关注了你
                          <div class="date">
                            1 Hour Ago
                          </div>
                        </div>
                        <div class="meta">
                          <a class="like">
                            <i class="like icon"></i> 4 
                          </a>  
                          <a class="comment">
                            <i class="comment outline icon"></i> 9
                          </a>                           
                        </div>
                        <div class="ui divider"></div>
                      </div>
                    </div>

                    <div class="event">
                      <div class="label">
                        <img src="img/avatar.png">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a>Joe Henderson</a> 发表了日志
                          <div class="date">
                            3 days ago
                          </div>
                        </div>
                        <div class="extra">
                          <div class="postheader">
                            <a href="#">你好广州</a>
                          </div>
                          新华社北京12月30日电 中共中央总书记、国家主席、中央军委主席、中央全面深化改革领导小组组长习近平12月30日上午主持召开中央全面深化改革领导小组第八次会议并发表重要讲话。他强调，今年是全面深化改革的开局之年，改革形成了上下联动、主动作为、蹄疾步稳、狠抓落实的好局面，呈现出全面播种、次第开花的生动景象
                        </div>
                        <div class="meta">
                          <div class="tags">
                        	<i class="tag icon"></i>
							<a href="#">摄影
							</a>   
							<a href="#">旅行
							</a>
						  </div>							                     	
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i> 9
	                          </a>                           
	                          <a class="like">
	                            <i class="like icon"></i> 5 
	                          </a>                          
                          </div>

                        </div>                                               
                        <div class="ui divider"></div>
                      </div>
                    </div>

                    <div class="event">
                      <div class="label">
                        <img src="img/avatar.png">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a>Helen Troy</a> added <a>2 new illustrations</a>
	                      <div class="date">
	                        4 days ago
	                      </div>                          
                        </div>
                        <div class="extra images">
                          <a><img src="img/avatar.jpg"></a>
                          <a><img src="img/avatar.jpg"></a>
                          <a><img src="img/avatar.jpg"></a>
                        </div>
                        <div class="extra">
                        	小组第八次会议并发表重要讲话。他强调，今年是全面深化改革的开局之年，改革形成了上下联动、主动作为、蹄疾步稳、狠抓落实的好局面，呈现出全面播种、次第开花的生动景象
                        </div>
                        <div class="meta">
                          <a class="like">
                            <i class="like icon"></i> 1 Like
                          </a>
                        </div>
                        <div class="ui divider"></div>
                      </div>
                    </div>

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
                        				<div class="avatar">
                        					<img class="ui avatar image" src="img/avatar.jpg">
                        				</div>
                        				<div class="author">
 									      <div class="date">
									        4 days ago
									      </div>
									      <div class="summary">
									        <a>Justen Kitsune</a>
									      </div>                       				
                        				</div>
										<div class="tags">

										</div>
                        			</div>
                        		</div>
                        		<div class="span2">
                        			<img class="ui small image" src="img/avatar.jpg" alt=""  />
                        		</div>
                        	</div>
                        </div>
                        <div class="meta">
                          <a class="like">
                            <i class="like icon"></i> 5 Likes
                          </a>
                        </div>
                      </div>
                    </div>                                        
                  </div>  <!--end feed -->
            </div>
          <div class="span4">
          	<div id="rightside">
	            <div class="ui card">
	              <div class="image">
	                <img src="img/avatar.jpg">
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


    </div>

  </div>
</body>
</html>