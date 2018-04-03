<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>密码重置</title>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/font-awesome/css/font-awesome1.css"/>
<link type="text/css" rel="stylesheet" href="css/se7en-font.css"/>
<link type="text/css" rel="stylesheet" href="css/style1.css" />
<link type="text/css" rel="stylesheet" href="css/forget.css" />
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
</head>
<body class="login2">
	<div class="login-wrapper">
		<h3>密码重置</h3>
		<form id="reset_form" method="post">
			<div class="form-group">
				<input id="mobile" name="mobile" class="form-control" type="text" placeholder="请输入后台绑定的手机号码"/>
			</div>
			<div class="form-group message">
					<input id="yzm" name="yzm" class="form-control" type="text" placeholder="输入短信验证码"/>
					<span class="form-control" onclick="sendYzm(this);">获得验证码</span>
			</div>
			<div class="form-group">
				<input id="pwd" name="pwd" class="form-control" type="password" placeholder="请输入新的密码"/>
			</div>		
			<div class="form-group">
				<input id="confirm_pwd" name="confirm_pwd" class="form-control" type="password" placeholder="请再次输入新的密码"/>
			</div>	
			<input class="btn btn-lg btn-lv btn-block" type="submit" onclick="$('#reset_form').submit();" value="确定重置">
			<!-- <p class="t-r"><a href="http://www.yunnex.com/about.html">求助客服?</a></p> -->
		</form>
	</div>
<script type="text/javascript" src="javascripts/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="javascripts/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="javascripts/bootstrap.min.js"></script>
<script type="text/javascript" src="javascripts/raphael.min.js"></script>
<script type="text/javascript" src="javascripts/selectivizr-min.js"></script>
<script type="text/javascript" src="javascripts/jquery.mousewheel.js"></script>
<script type="text/javascript" src="javascripts/jquery.vmap.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.vmap.sampledata.js"></script>
<script type="text/javascript" src="javascripts/jquery.vmap.world.js"></script>
<script type="text/javascript" src="javascripts/jquery.bootstrap.wizard.js"></script>
<script type="text/javascript" src="javascripts/fullcalendar.min.js"></script>
<script type="text/javascript" src="javascripts/gcal.js"></script>
<script type="text/javascript" src="javascripts/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="javascripts/datatable-editable.js"></script>
<script type="text/javascript" src="javascripts/jquery.easy-pie-chart.js"></script>
<script type="text/javascript" src="javascripts/excanvas.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.isotope.min.js"></script>
<script type="text/javascript" src="javascripts/isotope_extras.js"></script>
<script type="text/javascript" src="javascripts/modernizr.custom.js"></script>
<script type="text/javascript" src="javascripts/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="javascripts/select2.js"></script>
<script type="text/javascript" src="javascripts/styleswitcher.js"></script>
<script type="text/javascript" src="javascripts/wysiwyg.js"></script>
<script type="text/javascript" src="javascripts/summernote.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.inputmask.min.js"></script>
<script type="text/javascript" src="javascripts/jquery.validate.js"></script>
<script type="text/javascript" src="javascripts/jquery.easyui.form.js"></script>
<script type="text/javascript" src="javascripts/bootstrap-fileupload.js"></script>
<script type="text/javascript" src="javascripts/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="javascripts/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="javascripts/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="javascripts/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="javascripts/typeahead.js"></script>
<script type="text/javascript" src="javascripts/daterange-picker.js"></script>
<script type="text/javascript" src="javascripts/date.js"></script>
<script type="text/javascript" src="javascripts/morris.min.js"></script>
<script type="text/javascript" src="javascripts/skycons.js"></script>
<script type="text/javascript" src="javascripts/fitvids.js"></script>
<script type="text/javascript" src="javascripts/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="javascripts/respond.js"></script>
<script type="text/javascript" src="js/plugins/layer/layer.min.js"></script>
<script type="text/javascript" src="js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<!-- Custom and plugin javascript -->
<script type="text/javascript" src="js/hplus.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>
<script type="text/javascript" src="javascripts/common.js"></script>
<script type="text/javascript" src="javascripts/alertDialog.js"></script>
<script type="text/javascript">
	var ctx = "${ctx}";
	var brand = "${param.brand}";
</script>
<script type="text/javascript" src="js/forget.js"></script>
</body>
</html>