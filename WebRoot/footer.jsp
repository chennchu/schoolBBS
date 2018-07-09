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

</body>
<footer class="footer ">
<div class="container">
	<div class="row footer-top">
	<!-- 	
		<div class="col-md-12  col-lg-12 col-lg-offset-1">
			<div class="row about">
				<div class="col-sm-3">
					<h4>关于我们</h4>
					
				</div>
				<div class="col-sm-3">
					<h4>联系方式</h4>
					<ul class="list-unstyled">
						<li><a href="https://weibo.com/xagydx" title="西安工业大学校园微博"
							target="_blank">新浪微博</a></li>
						
					</ul>
				</div>
				<div class="col-sm-4">
					<h4>参考网站</h4>
					<ul class="list-unstyled">
						<li><a href="http://www.bootcss.com/" target="_blank">bootstrap</a></li>
					</ul>
				</div>
				<div class="col-sm-2">
					<h4>鸣谢</h4>
					<ul class="list-unstyled">
						<li><a href="#" target="_blank">galaxyLemon</a></li>
					</ul>
				</div>
			</div>

		</div> -->
	</div>
	<hr />
	<div class="row footer-bottom">
		<ul class="list-inline text-center">
			<li><a href="#" target="_blank">@2018 在线教学论坛系统</a></li>
			
		</ul>
	</div>
</div>
</footer>

</html>