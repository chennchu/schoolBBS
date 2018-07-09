<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择登陆类型</title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="<%=basePath%>assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/form-elements.css">
<link rel="stylesheet" href="<%=basePath%>assets/css/style.css">

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="<%=basePath%>assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<%=basePath%>assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=basePath%>assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=basePath%>assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=basePath%>assets/ico/apple-touch-icon-57-precomposed.png">
</head>
<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>在线教学论坛，欢迎您</strong>
						</h1>
						<div class="description">
							<p>
								请选择登陆类型
								<!-- This is a free responsive login form made with Bootstrap. 
	                            	Download it on <a href="#"><strong>AZMIND</strong></a>, customize and use it as you like! -->
							</p>
						</div>
					</div>
				</div>
				<!-- 登陆页面 -->
					<br><br>
					<a href="stuLogin.jsp" class="btn btn-primary">学生登陆</a>
					
					<a href="teaLogin.jsp" class="btn btn-success">老师登陆</a>
					<br><br>
					<p>没有账户? <a href="regchange.jsp" class ="btn btn-danger">点击注册</a></p>
				
			</div>
		</div>
	</div>


	<div class="copyrights">
		Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
	</div>


	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>
</html>