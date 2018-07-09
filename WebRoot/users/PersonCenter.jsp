<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<title>学生个人中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript">addEventListener("load", function() {
		setTimeout(hideURLbar, 0);
	}, false);
	function hideURLbar() {
		window.scrollTo(0, 1);
	}
</script>
<!-- Bootstrap Core CSS -->
<link href="<%=basePath%>css/bootstrap.css" rel='stylesheet'
	type='text/css' />
<!-- Custom CSS -->
<link href="<%=basePath%>css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js-->
<script src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>js/modernizr.custom.js"></script>
<!--webfonts-->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
<!--animate-->
<link href="<%=basePath%>css/animate.css" rel="stylesheet"
	type="text/css" media="all">
<script src="<%=basePath%>js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!-- chart -->
<script src="<%=basePath%>js/Chart.js"></script>
<!-- //chart -->
<!--Calender-->
<link rel="stylesheet" href="<%=basePath%>css/clndr.css" type="text/css" />
<script src="<%=basePath%>js/underscore-min.js" type="text/javascript"></script>
<script src="<%=basePath%>js/moment-2.2.1.js" type="text/javascript"></script>
<script src="<%=basePath%>js/clndr.js" type="text/javascript"></script>
<script src="<%=basePath%>js/site.js" type="text/javascript"></script>
<!--End Calender-->
<!-- Metis Menu -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<script src="<%=basePath%>js/custom.js"></script>
<link href="<%=basePath%>css/custom.css" rel="stylesheet">
<!--//Metis Menu -->
</head>
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class=" sidebar" role="navigation">
			<div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left"
					id="cbp-spmenu-s1">
					<ul class="nav" id="side-menu">
						<li><a href="PersonCenter.jsp" class="active"><i
								class="fa fa-home nav_icon"></i>个人中心</a></li>



						<li><a href="stuInfo.jsp"><i class="fa fa-table nav_icon"></i>个人用户信息
						</a></li>
						<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i>基本操作<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li><a href="UpdateStuInfo.jsp">修改个人信息 </a></li>
								<li><a href="updateStuPwd.jsp">修改密码</a></li>
								<li><a href="updateAvatar.jsp">修改头像</a></li>
							</ul> <!-- //nav-second-level --></li>
						<li><a href="#"><i class="fa fa-envelope nav_icon"></i>师生互动<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li><a href="stuuploadHomework.jsp">上传作业</a></li>
								<li><a href="studownloadHomework.jsp">下载作业</a></li>
								<li><a href="stuPost.jsp">发表帖子</a></li>
							</ul> <!-- //nav-second-level --></li>

					</ul>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush">
					<i class="fa fa-bars"></i>
				</button>
				<!--toggle button end-->
				<!--logo -->
				<div class="logo">
					<a href="PersonCenter.jsp">
						<h1>个人中心</h1> <span>UsersPanel</span>
					</a>
				</div>
				<!--//logo-->
				<!--search-box-->

				<div class="clearfix"></div>
			</div>
			<div class="header-right">
				<div class="profile_details_left">
					<!--notifications of menu start -->

					<div class="clearfix"></div>
				</div>
				<!--notification menu end -->
				<div class="profile_details">
					<ul>
						<li class="dropdown profile_details_drop"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							aria-expanded="false">
								<div class="profile_img">
									<%-- <span class="prfil-img">
									
									<img
										src="<%=basePath %>${sessionScope.user.avatar }"  alt="#">
									
									</span> --%>
									<div class="user-name">
										<p>${sessionScope.user.username }</p>
										<span>个人中心，欢迎您！</span>
									</div>

									<div class="clearfix"></div>
								</div>
						</a></li>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
		<!-- //header-ends -->
		<!-- main content start-->
		<div id="page-wrapper">
			<div class="main-page"></div>
			<div class="row calender widget-shadow">
				<h4 class="title">Calender</h4>
				<div class="cal1"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<!--footer-->
	<div class="footer">
		<p>
			Copyright &copy; 2017.Company name All rights reserved.<a
				target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a>
		</p>
	</div>
	<!--//footer-->
	</div>
	<!-- Classie -->
	<script src="js/classie.js"></script>
	<script>
		var menuLeft = document.getElementById('cbp-spmenu-s1'),
			showLeftPush = document.getElementById('showLeftPush'),
			body = document.body;
	
		showLeftPush.onclick = function() {
			classie.toggle(this, 'active');
			classie.toggle(body, 'cbp-spmenu-push-toright');
			classie.toggle(menuLeft, 'cbp-spmenu-open');
			disableOther('showLeftPush');
		};
	
	
		function disableOther(button) {
			if (button !== 'showLeftPush') {
				classie.toggle(showLeftPush, 'disabled');
			}
		}
	</script>
	<!--scrolling js-->
	<script src="<%=basePath%>js/jquery.nicescroll.js"></script>
	<script src="<%=basePath%>js/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
	<script src="<%=basePath%>js/bootstrap.js"> </script>
</body>
</html>