<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/menu.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/dropdown.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/label.css">
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="nav-container container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <a class="navbar-brand" href="<c:url value="/"/>">OSF</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="<c:url value="/explore" />">探索</a></li>
	        <li><a href="#">Link</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	      	<c:if test="${not empty sessionScope.user}">
	      		<li>
	      			<%-- <a href='<c:url value="/user/${sessionScope.user.id }"></c:url>'>${sessionScope.user.user_name }</a> --%>
	      			<div class="ui simple dropdown item">
				      ${sessionScope.user.user_name }
				      <i class="dropdown icon"></i>
					  <div class="ui vertical menu">
						  <a class="active teal item">
						    InBox
						    <div class="ui teal label">1</div>
						  </a>
						  <a class="item">
						    Spam
						    <div class="ui label">51</div>
						  </a>
						  <a class="item">
						    Updates
						    <div class="ui label">1</div>
						  </a>
						  <a href='<c:url value="/account/logout" />' class="item">退出</a>
					  </div>
				    </div>
	      		</li> 

   				 
	      	</c:if>
	      	<c:if test="${empty sessionScope.user}">
		        <li><a href='<c:url value="/account/register"></c:url>'>注册</a></li>
		        <li></li>
		        <li><a href='<c:url value="/account/login"></c:url>'>登录</a></li>
	        </c:if>
	      </ul>
	            
	      
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
    