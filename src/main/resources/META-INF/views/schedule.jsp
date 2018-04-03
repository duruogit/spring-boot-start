<%@page import="freemarker.template.utility.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" info="数据统计"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
* {
	padding: 0px;
	margin: 0px auto;
}

body {
	padding-top:50px;
}

td {
	padding:10px 5px;
}

select {
	background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CACACA;
    color: #333333;
    display: inline;
    height: 32px;
    padding: 5px 3px;
    width: 268px;
    cursor: pointer;
}

input {
	background: none repeat scroll 0 0 #FFFFFF;
    border: 1px solid #CACACA;
    color: #333333;
    display: inline;
    height: 24px;
    line-height: 18px;
    margin-left: 5px;
    padding: 3px;
    width: 260px;
}

a {
	color: #53B0F8;
}

a:hover {
	color: #FF0000;
}

a:active {
	star:expression(this.onFocus=this.blur());
}

a:focus {
	outline:0; 
}

</style>
<script type="text/javascript" src="javascripts/jquery-1.10.2.min.js"></script>
<script type="text/javascript">
	function startProcess(){
		$.post("${ctx}/updateTagXg/update", function(result){
			if(result.message){
				alert(result.message);
			}
		});
	}
</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td align="center">
					<a href="javascript:void(0);" onclick="startProcess()">为设备加上本地标签</a>
				</td>
			</tr>
			<tr>
				<td id="process_result" style="color: red;">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
