<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang3.StringUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

