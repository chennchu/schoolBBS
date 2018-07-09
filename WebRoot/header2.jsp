<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>header</title>


</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">在线教学论坛</a>
		</div>

		<!-- 导航栏 -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
        <li class="active"><a href="#">浏览帖子</a></li>
        <li><a href="<%=basePath %>admin/listAllFileG3">下载公共资源</a></li>
        <li><a href="<%=basePath %>admins/upLoadFilelist2.jsp">上传公共资源</a></li>
       
			</ul>

			<!-- 登录信息 -->
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.teacher }">
					<!-- 登陆框 -->
					<li><a href="<%=basePath %>change.jsp" data-target="#loginwindow">登录</a></li>
					<li><a href="<%=basePath %>regchange.jsp" data-target="#registwindow">注册</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.teacher }">
					<li><a>欢迎您！${sessionScope.teacher.teachername }</a></li>
					<li><a href="<%=basePath%>teachers/teacherPersonCen.jsp">个人中心</a></li>
					<li><a href="<%=basePath%>teacher/logout">退出</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>


</body>
</html>