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
  <script src="<%=request.getContextPath() %>/js/semantic.js"></script>
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
                   <div class="event empty row">
                      <div class="label span2">
                        <img src="">
                      </div>
                      <div class="content span6">
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

                    <%@ include file="nextpage.jsp" %>                
                  </div>  <!--end feed -->
                  <a id="next" href="<c:url value="/page/2" />"></a>
            </div>
          <div class="span4">
          	<div id="rightside">
          		<c:if test="${not empty sessionScope.user}">
	            <div class="ui card">
	              <div class="ui small centered circular  image">
	                <img src="<c:url value="${img_base_url }${user.user_avatar }"/> ">
	              </div>
	              <div class="content">
	                <a class="header centered" href="<c:url value="/user/${user.id}" />">
	                	${user.user_name }
	                </a>
	                <div class="meta centered">
	                  <span class="date">不想成为画家的黑客不是好摄影师</span>
	                </div>	                
					<div class="ui mini statistics">
					  <div class="statistic">
					    <div class="value">
					      ${counter.follower }
					    </div>
					    <div class="label">粉丝
					    </div>
					  </div>
					  <div class="statistic">
					    <div class="value">
					      ${counter.following }
					    </div>
					    <div class="label">关注
					    </div>
					  </div>
					  <div class="statistic">
					    <div class="value">
					      ${counter.spost }
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
				<div class="ui vertical menu popusers">
				  <div class="item">
				  	<c:forEach items="${popusers }" var="popuser">
						<a href="#" class="popuser">
							<img class="ui inline image" src="${img_base_url }${popuser.user_avatar}">
						</a>
						<%-- <jsp:include page="/popup_usercard/${popuser.id }" flush="true"></jsp:include>	 --%>
					</c:forEach>
					<!-- end popup -->

					
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
  <script src="<%=request.getContextPath() %>/js/feed.js"></script>
</body>
</html>