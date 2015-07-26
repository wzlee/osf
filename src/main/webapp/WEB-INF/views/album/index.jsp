<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="type" content="album">
	<meta name="id" content="${album.id }">
	<c:if test="${not empty sessionScope.user}">
		<meta name="isLogin" content="true"/>
	</c:if>
	<c:if test="${empty sessionScope.user}">
		<meta name="isLogin" content="false"/>
	</c:if>
	<title></title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/semantic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/basic.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/code.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/album.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/comment.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/follow.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/login.js"></script>

</head>
<body>
	<%@ include file="../topbar.jsp" %>
	<%@ include file="../login_modal.jsp" %>
	<div class="container">
		<div class="row">
			<div class="span8">
				<div id="imgcontainer">
					<img src="" alt="" id="mainimg" class="ui centered image"> 
				</div>
			</div>
			<div class="span4">
				<div id="rightside">
					<div class="album metas">
						<div class="meta author">
							<a href="<c:url value="/user/${u.id }" />"><img class="ui avatar image" src="<c:url value="${img_base_url}${u.user_avatar }"/>"></a>
							<span>${u.user_name }</span>
							<c:if test="${!empty sessionScope.user }">
								<c:if test="${sessionScope.user.id ne u.id }">
									<c:if test="${follow }">
										<span class="ui tiny basic button follow" following="${u.id }">已关注</span>
									</c:if>
									<c:if test="${!follow }">
										<span class="ui inverted tiny yellow button follow" following="${u.id }">+关注</span> 
									</c:if>									
								</c:if>						
							</c:if>	
							<c:if test="${empty sessionScope.user }">
								<span class="ui inverted tiny yellow button follow" following="${u.id }">+关注</span> 
							</c:if>				
						</div>
						<div class="ui tiny images meta" id="imgbox">
							<c:forEach items="${album.photos}" var="photo">
								<a href="#"><img src="<c:url value="${img_base_url}${photo.key }" />" alt="" id="preview_photo_${photo.id }"></a>
							</c:forEach>
						</div>
						<div class="meta tags">
							<c:forEach items="${album.album_tags }" var="tag">
                        		<a class="ui label" href="<c:url value="/tag/${tag }"/>">${tag }</a>
                        	</c:forEach>							
						</div>					
					</div>

				</div>
			</div>	
		</div>
	</div>
	<!--  -->
		
	<div class="album comments">
		<div class="container">
			<div class="row">
				<div class="span8">
					<div class="ui comments" id="comments">
		
					  <div id="replyarea">
							  <form class="ui reply form" id="replyform">
							    <div class="field">
							      <textarea id="replycontent"></textarea>
							    </div>
								<div class="ui tiny blue button" id="replybtn">
								  评论
								</div>							    
							  </form>								
					  </div>
					  
						
					  <jsp:include page="/comment/album/${album.id }"></jsp:include>				  	
					  
					  <!-- comment list -->
					</div>
					<!-- end comment -->
				</div>
			</div>
		</div>
	</div>	

</body>
</html>