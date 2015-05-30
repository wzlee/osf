<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
	            <div class="ui card">
	              <div class="ui small centered circular  image">
	                <img src="<c:url value="${imgBaseUrl }/${u.user_avatar }"/> ">
	              </div>
	              <div class="content">
	                <a class="header centered" href="<c:url value="/user/${u.id}" />">
	                	${u.user_name }
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
	              <div class="extra content">
	              	<c:if test="${follow }">
	              		<div class="mini ui basic button follow" following="${u.id}">已关注</div>
	              	</c:if>
	              	<c:if test="${!follow }">
						<div class="mini ui yellow button follow" following="${u.id}">+关注</div>
					</c:if>	              
	              </div>
	            </div> 