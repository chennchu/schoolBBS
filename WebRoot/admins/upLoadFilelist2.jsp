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
<title>公共资源上传区</title>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>scripts/css/bootstrap-responsive.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>kkpager-master/css/kkpager_orange.css">
<script type="text/javascript"
	src="<%=basePath%>scripts/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>kkpager-master/js/kkpager.min.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/js/docs.min.js">
</script>

</head>
<body>
	<%-- <jsp:include page="../header.jsp"></jsp:include> --%>
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
        <li><a href="#">浏览帖子</a></li>
        <li><a href="<%=basePath %>admin/listAllFileG3">下载公共资源</a></li>
        <li class="active"><a href="<%=basePath %>admins/upLoadFilelist2.jsp">上传公共资源</a></li>
       
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
	<div class="container">
		<div class="row clearfix" align="center">
			<div class="col-md-12 column">
				<div class="carousel slide" id="carousel-938497">
					<ol class="carousel-indicators">
						<li class="active" data-slide-to="0"
							data-target="#carousel-938497"></li>
						<li data-slide-to="1" data-target="#carousel-938497"></li>
						<li data-slide-to="2" data-target="#carousel-938497"></li>
					</ol>
					<div class="carousel-inner" align="center">
						<div class="item active">
							<img alt="" src="<%=basePath%>tupian/1.jpg" />
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img alt="" src="<%=basePath%>tupian/2.jpg" />
							<div class="carousel-caption"></div>
						</div>
						<div class="item">
							<img alt="" src="<%=basePath%>tupian/3.jpg" />
							<div class="carousel-caption"></div>
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-938497"
						data-slide="prev"><span
						class="glyphicon glyphicon-chevron-left"></span></a> <a
						class="right carousel-control" href="#carousel-938497"
						data-slide="next"><span
						class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
			</div>
		</div>
		<br>
		<ul class="breadcrumb">
			<li>在线教学论坛</li>
			<li class="active">公共资源上传区</li>
		</ul>
		<form class="form-inline" method="post" role="form"
							action="<%=basePath %>admin/uploadFile2" enctype="multipart/form-data">
							<!-- 上传资源 -->
							<div class="form-group">
								<label for="file" class="col-sm-2 control-label">上传资源</label>
								<div class="col-sm-8">
									<input type="file" class="form-control1" id="file" name="file">
								</div>
							</div>


							<div class="form-group" align="center">
								<button type="submit" class="btn">上传</button>
							</div>
						</form>
		
	</div>
	

</body>
</html>