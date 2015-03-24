<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  	<div class="row">
  		<a href='<c:url value="/account/login"></c:url>' class="ui button">登录</a>
  		<a href='<c:url value="/account/register"></c:url>' class="ui button">注册</a>
  		<a href='<c:url value="/account/logout"></c:url>' class="ui button">退出</a>
  		<a href='<c:url value="#"></c:url>' class="ui button">${sessionScope.user.user_email }</a>
  	</div>