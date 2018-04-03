<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String m = request.getParameter("m");
	String p = "";
	String c = "";
	if(StringUtils.isNotBlank(m)) {
	    if(m.indexOf("/") == -1) {
	        p = m;
	    } else {
	        String[] a = m.split("/");
		    p = a[0];
		    c = a[1];
	    }
	} else {
	    p = "home";
	}
%>
<div class="container-fluid main-nav clearfix">
	<div class="nav-collapse">
		<ul class="nav">
			<li>
				<a class="<%if("home".equals(p)) out.print("current"); %>" href="${ctx}/home?m=home">
					<span class="icon-home"></span>首页
				</a>
			</li>
			<li class="dropdown">
				<a class="<%if("shop".equals(p)) out.print("current"); %>" data-toggle="dropdown" href="#">
					<span class="icon-user"></span>商户管理<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a class="<%if("base".equals(c)) out.print("current"); %>" href="${ctx}/shop/base?m=shop/base">基本信息</a></li>
					<li><a class="<%if("config".equals(c)) out.print("current"); %>" href="${ctx}/shop/config?m=shop/config">功能配置</a></li>
					<li><a class="<%if("device".equals(c)) out.print("current"); %>" href="${ctx}/shop/device?m=shop/device">设备管理</a></li>
					<li><a class="<%if("branch".equals(c)) out.print("current"); %>" href="${ctx}/shop/branch?m=shop/branch">分店</a></li>
					<li><a class="<%if("wenxin".equals(c)) out.print("current"); %>" href="${ctx}/shop/wx_config?m=shop/wx_config">微信公众账号配置</a></li>
					<!-- <li><a class="<%if("account".equals(c)) out.print("current"); %>" href="${ctx}/shop/account?m=shop/account">账号及权限配置</a></li> -->
				</ul>
			</li>
			<li class="dropdown">
				<a class="<%if("trade".equals(p)) out.print("current"); %>" data-toggle="dropdown" href="#">
					<span class="icon-shopping-cart"></span>交易信息<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a class="<%if("order".equals(c)) out.print("current"); %>" href="${ctx}/trade/order?m=trade/order">订单记录</a></li>
					<%-- <li><a class="<%if("draw".equals(c)) out.print("current"); %>" href="${ctx}/trade/draw?m=trade/draw">提现记录</a></li> --%>
					<li><a class="<%if("cpain".equals(c)) out.print("current"); %>" href="${ctx}/trade/complain?m=trade/cpain">维权投诉申请</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="<%if("kq".equals(p)) out.print("current"); %>" data-toggle="dropdown" href="#">
					<span class="se7en-pages"></span>营销中心<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a class="<%if("card".equals(c)) out.print("current"); %>" href="${ctx}/kq/card?m=kq/card">会员卡配置</a></li>
					<li><a class="<%if("carddynamic".equals(c)) out.print("current"); %>" href="${ctx}/kq/carddynamic?m=kq/carddynamic">会员卡交易</a></li>
					<li><a class="<%if("coupon".equals(c)) out.print("current"); %>" href="${ctx}/kq/coupon?m=kq/coupon">优惠券配置</a></li>
					<li><a class="<%if("coupondynamic".equals(c)) out.print("current"); %>" href="${ctx}/kq/coupondynamic?m=kq/coupondynamic">优惠券使用</a></li>
					<li><a class="<%if("customer".equals(c)) out.print("current"); %>" href="${ctx}/kq/customer?m=kq/customer">会员信息</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="<%if("dining".equals(p)) out.print("current"); %>" data-toggle="dropdown" href="#">
					<span class="icon-food"></span>微餐饮<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a class="<%if("dconfig".equals(c)) out.print("current"); %>" href="${ctx}/dining/dconfig?m=dining/dconfig">微餐饮配置</a></li>
					<li><a class="<%if("food".equals(c)) out.print("current"); %>" href="${ctx}/dining/food?m=dining/food">菜品管理</a></li>
					<li><a class="<%if("desk".equals(c)) out.print("current"); %>" href="${ctx}/dining/desk?m=dining/desk">桌号/排队配置</a></li>
				</ul>
			</li>
			<li class="dropdown">
				<a class="<%if("mall".equals(p)) out.print("current"); %>" data-toggle="dropdown" href="#">
					<span class="icon-maxcdn"></span>商城<b class="caret"></b>
				</a>
				<ul class="dropdown-menu">
					<li><a class="<%if("goods".equals(c)) out.print("current"); %>" href="${ctx}/mall/goods?m=mall/goods">商品管理</a></li>
					<li><a class="<%if("groups".equals(c)) out.print("current"); %>" href="${ctx}/mall/groups?m=mall/groups">团购管理</a></li>
					<li><a class="<%if("morder".equals(c)) out.print("current"); %>" href="${ctx}/mall/morder?m=mall/morder">商城订单</a></li>
				</ul>
			</li>
			
		</ul>
	</div>
</div>