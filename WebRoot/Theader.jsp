<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap-responsive.min.css">
<script type="text/javascript"
	src="<%=basePath%>scripts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/js/docs.min.js"></script>
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
				<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Dropdown <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Action</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<!-- <form class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
			</form> -->
			<!-- 登录信息 -->
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${empty sessionScope.user }">
					<!-- 登陆框 -->
					<li><a href="change.jsp" data-target="#loginwindow">登录</a></li>
					<li><a data-toggle="modal" data-target="#registwindow">注册</a></li>
				</c:if>
				<c:if test="${!empty sessionScope.user }">
					<li><a>欢迎您！${sessionScope.user.username }</a></li>
					<li><a href="personalCen.jsp">个人中心</a></li>
					<li><a href="user/logout">退出</a></li>
				</c:if>
			</ul>
		</div>
	</div>
	</nav>

	<!-- 
	<div class="modal fade" id="loginwindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">用户登录</h4>
				</div>

				<div class="modal-body">
					<form action="user/login" method="post"
						class="form-horizontal" role="from" id="login">
						用户名
						<div class="form-group">
							<label for="name" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="name" name="name"
									placeholder="用户名">
							</div>
							
						</div>
						<div class="form-group">
							<label for="password" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-10">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="密码">
							</div>
						</div>
					</form>
				</div>

				<div class="modal-footer">
					<button type="submit" form="login" class="btn btn-primary">登录</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>


	注册框
	<div class="modal fade" id="registwindow" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="registFrom">用户注册</h4>
				</div>

				<div class="modal-body">
					用户注册表单
					<form action="user/regist" method="post"
						class="form-horizontal" role="from" id="regist">
						用户名
						<div class="form-group">
							<label for="regname" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="regname"
									name="regname" placeholder="请输入您的用户名">
							</div>
							<label for="regname" class="col-sm-2 control-label"
								id="regname-msg"></label>
						</div>

						密码
						<div class="form-group">
							<label for="password1" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password1"
									name="password1" placeholder="请输入密码">
							</div>
						</div>
						确认密码
						<div class="form-group">
							<label for="password2" class="col-sm-2 control-label">确认密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password2"
									name="password2" placeholder="请再次输入密码">
							</div>
						</div>
					</form>
				</div>

				<div class="modal-footer">
					<button type="submit" form="regist" class="btn btn-primary"
						id="regist-btn">注册</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div> -->
</body>
</html>