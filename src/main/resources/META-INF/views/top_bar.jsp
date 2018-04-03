<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="yunnex.saofu.web.common.constants.SystemConstant" %>
<% 
	//访问资源文件的前缀
	String resourceServerUrlPrefix = SystemConstant.RESOURCE_SERVER_URL;
%>
<style type="text/css">
	.navbar .container-fluid.top-bar .nav li a {
		line-height: 35px;
	}
	
	.navbar .container-fluid.top-bar .nav .user img {
		width: 34px;
		height: 34px;
	}
</style>
<div class="container-fluid top-bar">
	<div class="pull-right">
		<ul class="nav navbar-nav pull-right">
			<li class="user">
				<a href="${ctx}/home">
					<c:choose>
						<c:when test="${sessionScope.shop.logoUrl == null || sessionScope.shop.logoUrl == ''}">
							<img src="images/avatar-female.jpg">
						</c:when>
						<c:when test="${fn:contains(fn:toLowerCase(sessionScope.shop.logoUrl), 'http://')}">
							<img src="${sessionScope.shop.logoUrl}">
						</c:when>
						<c:otherwise>
							<img src="<%=resourceServerUrlPrefix %>${sessionScope.shop.logoUrl}">
						</c:otherwise>
					</c:choose>
					${sessionScope.shop.name}
				</a>
            </li>
			<li class="dropdown user hidden-xs">
				<a href="${ctx}/logout"><i class="icon-signout"></i>退出</a>
			</li>
		</ul>
	</div>
	<a class="logo" href="http://www.saofu.cn/">${sys_web_name }</a>
</div>