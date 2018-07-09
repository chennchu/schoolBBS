<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员后台管理</title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet"
	href="<%=basePath%>assets3/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=basePath%>assets3/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=basePath%>assets3/css/form-elements.css">
<link rel="stylesheet" href="<%=basePath%>assets3/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="<%=basePath%>assets3/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="<%=basePath%>assets3/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="<%=basePath%>assets3/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="<%=basePath%>assets3/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="<%=basePath%>assets3/ico/apple-touch-icon-57-precomposed.png">

</head>
<body>
	<!-- Top menu -->
	<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#top-navbar-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html">在线教学论坛系统 后台管理系统</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->

	</div>
	</nav>

	<!-- Top content -->
	<div class="top-content">
		<div class="copyrights">
			Collect from <a href="http://www.cssmoban.com/" title="网站模板">网站模板</a>
		</div>
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-12 text">
						<h1>
							<strong>在线教学论坛系统</strong> 
						</h1>
							<h1>
							<strong>Online Teaching Forum System</strong>
							</h1>
						<div class="description">
							<p>在线教学论坛系统管理员后台管理中心</p>
						</div>
						<div class="top-big-link">
							<a class="btn btn-link-1" href="admReg.jsp">注册</a> <a
								class="btn btn-link-2" href="AdmLogin.jsp">登陆</a>
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="<%=basePath%>assets3/js/jquery-1.11.1.min.js"></script>
	<script src="<%=basePath%>assets3/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>assets3/js/jquery.backstretch.min.js"></script>
	<script src="<%=basePath%>assets3/js/retina-1.1.0.min.js"></script>
	<script src="<%=basePath%>assets3/js/scripts.js"></script>


</body>
</html>