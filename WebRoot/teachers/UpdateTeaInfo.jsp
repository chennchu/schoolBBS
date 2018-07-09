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
<title>修改教师个人信息</title>
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
<link href="<%=basePath%>css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="<%=basePath%>css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="<%=basePath%>css/font-awesome.css" rel="stylesheet">
<!-- //font-awesome icons -->
<!-- js-->
<script src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script src="<%=basePath%>js/modernizr.custom.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/moment-with-locales.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>scripts/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=basePath%>scripts/js/docs.min.js"></script>
<!--webfonts-->
<link
	href='https://fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
<!--animate-->
<link href="<%=basePath%>css/animate.css" rel="stylesheet" type="text/css"
	media="all">
<script src="<%=basePath%>js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!--//end-animate-->
<!-- Metis Menu -->
<script src="<%=basePath%>js/metisMenu.min.js"></script>
<script src="<%=basePath%>js/custom.js"></script>
<link href="<%=basePath%>css/custom.css" rel="stylesheet">
<script type="text/javascript">
	$(function() {
		//设置日期时间控件
		$('#datetimepicker1').datetimepicker({
			language : 'zh-CN', //显示中文
			format : 'yyyy-mm-dd', //显示格式
			minView : "month", //设置只显示到月份
			initialDate : new Date(),
			autoclose : true, //选中自动关闭
			todayBtn : true, //显示今日按钮
			locale : moment.locale('zh-cn')
		});
		//默认获取当前日期
		var today = new Date();
		var nowdate = (today.getFullYear()) + "-" + (today.getMonth() + 1) + "-" + today.getDate();
		//对日期格式进行处理
		var date = new Date(nowdate);
		var mon = date.getMonth() + 1;
		var day = date.getDate();
		var mydate = date.getFullYear() + "-" + (mon < 10 ? "0" + mon : mon) + "-" + (day < 10 ? "0" + day : day);
		document.getElementById("nowdate").value = mydate;



	})
</script>
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
					<li><a href="teacherPersonCen.jsp"><i
							class="fa fa-home nav_icon"></i>个人中心</a></li>
							<li>
							<a href="#"><i class="fa fa-envelope nav_icon"></i>师生互动<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="uploadHomework.jsp">上传作业</a>
								</li>
								<li>
									<a href="downloadHomework.jsp">下载作业</a>
								</li>
								<li>
									<a href="teaPost.jsp">发表帖子</a>
								</li>
							</ul>
							<!-- //nav-second-level -->
						</li>
					<li><a href="teaInfo.jsp"><i class="fa fa-table nav_icon"></i>个人用户信息
							</a></li>
					<li><a href="#"><i class="fa fa-check-square-o nav_icon"></i>基本操作<span
							class="fa arrow"></span></a>
						<ul class="nav nav-second-level collapse">
							<li><a href="UpdateTeaInfo.jsp">修改个人信息</a></li>
							<li><a href="updateTeaPwd.jsp">修改密码</a></li>
							<li><a href="updateTeaAvatar.jsp">修改头像</a></li>
						</ul> <!-- //nav-second-level --></li>
					
				
				</ul>
				<div class="clearfix"></div>
				<!-- //sidebar-collapse --> </nav>
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
						<h1>个人中心</h1> <span>TeacherPanel</span>
					</a>
				</div>
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
										<p>${sessionScope.teacher.teachername }</p>
										<span>个人中心，欢迎您！</span>
									</div>

									
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
			<div class="main-page">
				<div class="row">
					<!-- 修改个人信息 -->
					<h3 class="title1">修改个人信息 :</h3>
					<div class="form-three widget-shadow">
						<form class="form-horizontal" role="form"
							action="<%=basePath %>teacher/updateUserInfo">
							<!-- 修改用户名 -->
							<div class="form-group">
								<label for="username" class="col-sm-2 control-label">用户名</label>
								<div class="col-sm-8">
									<input type="text" class="form-control1" name="username"
										readonly="true" id="username"
										placeholder="${sessionScope.teacher.teachername }">
								</div>
								<div class="col-sm-2">
									<p class="help-block">用户名不可修改！</p>
								</div>
							</div>
							<!-- 修改邮箱 -->
							<div class="form-group">
								<label for="email" class="col-sm-2 control-label">Email</label>
								<div class="col-sm-8">
									<input type="text" class="form-control1" id="email"
										name="email" placeholder="${sessionScope.teacher.email }">
								</div>
							</div>
							<!-- 修改电话号码 -->
							<div class="form-group">
								<label for="phoneNum" class="col-sm-2 control-label">电话号码</label>
								<div class="col-sm-8">
									<input type="text" class="form-control1" id="phoneNum"
										name="phoneNum" placeholder="${sessionScope.teacher.phoneNum }">
								</div>
							</div>
							<!-- 复选框 -->
							<!-- <div class="form-group">
									<label for="checkbox" class="col-sm-2 control-label">电话号码</label>
									<div class="col-sm-8">
										<div class="checkbox-inline1"><label><input type="checkbox"> Unchecked</label></div>
										<div class="checkbox-inline1"><label><input type="checkbox" checked=""> Checked</label></div>
										<div class="checkbox-inline1"><label><input type="checkbox" disabled=""> Disabled Unchecked</label></div>
										<div class="checkbox-inline1"><label><input type="checkbox" disabled="" checked=""> Disabled Checked</label></div>
									</div>
								</div> -->
							<!-- 修改性别 -->
							<div class="form-group">
								<label for="radio" class="col-sm-2 control-label">性别</label>
								<div class="col-sm-8">
									<label class="radio-inline class"> <input type="radio"
										name="optionsRadios" id="optionsRadios1" value="男" checked>
										男
									</label> <label class="radio-inline class"> <input type="radio"
										name="optionsRadios" id="optionsRadios2" value="女"> 女
									</label>
								</div>
							</div>
							<!-- 修改生日 -->
							<div class="form-group">
								<label for="nowdate" class="col-sm-2 control-label">请选择您的生日</label>
								<div class="col-sm-8">
									<a class="input-group date" id="datetimepicker1"
										style="float: left; left: 320px;"><input type="text"
										class="form-control" id="nowdate" name="nowdate"
										style="width: 150px; height: 30px;" align="left" /> <span
										class="input-group-addon"
										style="float: left; width: 50px; height: 30px;"> <span
											class="glyphicon glyphicon-calendar"></span>
									</span> </a>
								</div>
							</div>
							<div class="form-group" align="center">
								<button type="submit" class="btn">确认修改</button>
							</div>
						</form>
					</div>
				</div>

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
	<script src="<%=basePath%>js/classie.js"></script>
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