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
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

  <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  <script src="<%=request.getContextPath() %>/js/jquery.infinitescroll.js"></script>
  <script src="<%=request.getContextPath() %>/js/basic.js"></script>
  <script src="<%=request.getContextPath() %>/js/code.js"></script>
  <script src="<%=request.getContextPath() %>/js/like.js"></script>
  <script src="<%=request.getContextPath() %>/js/spost.js"></script>
</head>
<body>
  <%@ include file="topbar.jsp" %>
  <div class="container">
    <div class="row">  
          <div class="span8">  
	          	<div class="header_box">
	          		<div class="ui avatar image">
	                	<img src="img/avatar.png">
	                </div>                
					<div id="action_bar">
						<div class="ui labeled icon menu actions" >
						  <a class="item sport_link" href="#">
						    <i class="blue big font icon"></i>
						    发状态
						  </a>
						  <a class="item album_link" href="<c:url value="/album/upload"/>">
						    <i class="pink big photo icon"></i>
						    传图片
						  </a>
						  <a class="item post_link" href="<c:url value="/post/create"/>">
						    <i class="big write icon"></i>
						    写日志
						  </a>
						  <a class="item link">
						    <i class="green big linkify icon"></i>
						    链接
						  </a>							
						</div>

					  <div class="short_post">
					  	<textarea placeholder="说点什么..." id="spost_content"></textarea>
					  	<div class="bar">
					  		<div class="ui tiny blue button" id="spost_send">
					  			发表
					  		</div>
					  		<div class="ui tiny basic button" id="sport_cancel">
					  			取消
					  		</div>
					  	</div>
					  </div>			  
					</div>
	               </div>	
	               <!-- end header_box -->         
                  <div class="ui feed" id="feeds">
                   <div class="event empty">
                      <div class="label">
                        <img src="">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a href=""></a> 说
                          <div class="date">
                            刚刚
                          </div>
                        </div>
                        <div class="extra">
                        </div>
                        <div class="meta">							                     	
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i><span>0</span>
	                          </a>                           
	                          <a class="like">
	                          	<i class="heart icon" object_type="4" object_id=""></i><span>0</span>
	                          </a>                         
                          </div>

                        </div>                                               
                      </div>
                    </div> 
                    <!-- empty event , wait for full and show -->

                   <c:forEach items="${feeds }" var="feed"> 
                   	<c:if test="${feed.object_type == dic.object_type_shortpost }">
                   <div class="event">
                      <div class="label">
                        <img src="${feed.user_avatar }">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a href="<c:url value="/user/${feed.user_id }" />">${feed.user_name }</a> 说
                          <div class="date">
                            ${feed.ts }
                          </div>
                        </div>
                        <div class="extra">                         
                          ${feed.summary }
                        </div>
                        <div class="meta">							                     	
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i> ${feed.comment_count }
	                          </a>                           
	                          <a class="like">
	                          	<c:if test="${feed.is_like }">
	                          		<i class="red heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>
	                          	<c:if test="${!feed.is_like }">
	                          		<i class="heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>	                          	
	                          </a>                         
                          </div>

                        </div>                                               
                      </div>
                    </div>                    		
                   	</c:if>
                   	
                   <!-- new post -->
                    <c:if test="${feed.object_type == dic.object_type_post }">
                    <div class="event">
                      <div class="label">
                        <img src="${feed.user_avatar }">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a href="<c:url value="/user/${feed.user_id }" />">${feed.user_name }</a> 发表了日志
                          <div class="date">
                            ${feed.ts }
                          </div>
                        </div>
                        <div class="extra">
                        	<div class="postheader">
                           		<a href="<c:url value="/post/${feed.object_id }" />">${feed.title }</a>
                            </div>
                      		<img src="<c:url value="${feed.content }"/>" alt="" />
                      	</div>
                        <div class="extra">
                          
                          ${feed.summary }
                        </div>
                        <div class="meta">
                          <div class="tags">
                        	<i class="tag icon"></i>
                        	<c:forEach items="${feed.tags }" var="tag">
                        		<a href="<c:url value="/tag/${tag }"/>">${tag }</a>
                        	</c:forEach>
						  </div>							                     	
                          <div class="actions">
							  <a class="comment">
	                            <i class="comment outline icon"></i> ${feed.comment_count }
	                          </a>                           
	                          <a class="like">
	                          	<c:if test="${feed.is_like }">
	                          		<i class="red heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>
	                          	<c:if test="${!feed.is_like }">
	                          		<i class="heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>	                          	
	                          </a>                       
                          </div>

                        </div>                                               
                      </div>
                    </div>                    	
                    </c:if>
                    
                    <!-- new album -->
                    <c:if test="${feed.object_type == dic.object_type_album }">
                    <div class="event">
                      <div class="label">
                        <img src="${feed.user_avatar }">
                      </div>
                      <div class="content">
                        <div class="summary">
                          <a>${feed.user_name }</a> 上传了相册 <a href="<c:url value="/album/${feed.object_id }" /> "></a>
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
	                          	<c:if test="${feed.is_like }">
	                          		<i class="red heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>
	                          	<c:if test="${!feed.is_like }">
	                          		<i class="heart icon" author="${feed.user_id }" object_type=${feed.object_type } object_id=${feed.object_id }></i> 
	                          		<span>${feed.like_count }</span> 
	                          	</c:if>	                          	
	                          </a>                          
                          </div>
                        </div>
                      </div>
                    </div>                    
                    </c:if>
                    <!-- end album  -->    
                    
                    <c:if test="">
                    	
                    </c:if>
                                
                  </c:forEach>                                      
                  </div>  <!--end feed -->
                  <a id="next" href="<c:url value="/page/2" />"></a>
            </div>
          <div class="span4">
          	<div id="rightside">
          		<c:if test="${not empty sessionScope.user}">
	            <div class="ui card">
	              <div class="ui small centered circular  image">
	                <img src="<c:url value="${imgBaseUrl }/${sessionScope.user.user_avatar }"/> ">
	              </div>
	              <div class="content">
	                <a class="header centered" href="<c:url value="/user/${sessionScope.user.id}" />">
	                	${sessionScope.user.user_name }
	                </a>
	                <div class="meta centered">
	                  <span class="date">不想成为画家的黑客不是好摄影师</span>
	                </div>	                
					<div class="ui mini statistics">
					  <div class="statistic">
					    <div class="value">
					      22
					    </div>
					    <div class="label">粉丝
					    </div>
					  </div>
					  <div class="statistic">
					    <div class="value">
					      31
					    </div>
					    <div class="label">关注
					    </div>
					  </div>
					  <div class="statistic">
					    <div class="value">
					      22
					    </div>
					    <div class="label">状态
					    </div>
					  </div>
					</div>	<!-- end statistics --> 
					
					               
	              </div>
	            </div> 
	            </c:if>
				<div class="ui header">
				    热门用户
				</div>
				<div class="ui vertical menu">
				  <div class="item">
				   	  <a class="right floated compact">+关注</a>
				      <img class="ui avatar image" src="img/avatar.jpg">
				      <div class="content">
				     	<a class="header">两排杨树</a>
				      </div>
				  </div>
				  <div class="item">
				   	  <a class="right floated compact">+关注</a>
				      <img class="ui avatar image" src="img/avatar.jpg">
				      <div class="content">
				     	<a class="header">两排杨树</a>
				      </div>
				  </div>
				  <div class="item">
				   	  <a class="right floated compact">+关注</a>
				      <img class="ui avatar image" src="img/avatar.jpg">
				      <div class="content">
				     	<a class="header">两排杨树</a>
				      </div>
				  </div>
				  <div class="item">
				   	  <a class="right floated compact">+关注</a>
				      <img class="ui avatar image" src="img/avatar.jpg">
				      <div class="content">
				     	<a class="header">两排杨树</a>
				      </div>
				  </div>
				  <div class="item">
				   	  <a class="right floated compact">+关注</a>
				      <img class="ui avatar image" src="img/avatar.jpg">
				      <div class="content">
				     	<a class="header">两排杨树</a>
				      </div>
				  </div>
				</div>		
				<!-- end menu -->	
				
				<div class="ui header">
				    热门标签
				</div>
				<div class="ui vertical menu hottags">
				  <div class="tagitem" style="background: url(img/gallery/art.png)">
				  	<div class="mask"></div>
				  	<div class="tag">
				  		#艺术
				  	</div>
				  </div>
				  
				  <div class="tagitem" style="background: url(img/gallery/life.png)">
				  	<div class="mask"></div>
				  	<div class="tag">
				  		#生活
				  	</div>
				  </div>
				  <div class="tagitem" style="background: url(img/gallery/music.png); background-position: cover">
				  	<div class="mask"></div>
				  	<div class="tag">
				  		#音乐
				  	</div>
				  </div>
				  <div class="tagitem" style="background: url(img/gallery/sport.png)">
				  	<div class="mask"></div>
				  	<div class="tag">
				  		#运动
				  	</div>
				  </div>
				  <div class="tagitem">
				  </div>
				</div>					
										
            </div>           
          </div>
        </div>
      </div>


    </div>

  </div>
  <script type="text/javascript">
  $(document).ready(function(){
  	$('#feeds').infinitescroll({
  		loading: {
  		    finished: undefined,
  		    finishedMsg: "没有更多的了",
  		                img: null,
  		    msg: null,
  		    msgText: "正在加载...",
  		    selector: null,
  		    speed: 'fast',
  		    start: undefined
  		},
		navSelector  	: "#next:last",
		nextSelector 	: "a#next:last",
		itemSelector 	: ".event",
		dataType	 	: 'html',
		animate      : true, 
		extraScrollPx: 50
  	});
  })
  </script>
</body>
</html>