<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
	<nav class="navbar navbar-default navbar-fixed-top">
	  <div class="nav-container container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <a class="navbar-brand" href="<c:url value="/"/>">Brand</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse">
	      <ul class="nav navbar-nav">
	        <li class="active"><a href="#">Link</a></li>
	        <li><a href="#">Link</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href='<c:url value="/account/register"></c:url>'>注册</a></li>
	        <li></li>
	        <li><a href='<c:url value="/account/login"></c:url>'>登录</a></li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
    